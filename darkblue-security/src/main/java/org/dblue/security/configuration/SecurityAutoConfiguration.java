/*
 * Copyright (c) 2023-2024. the original authors and DBLUE.ORG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dblue.security.configuration;

import org.dblue.security.authorization.DatabaseAuthorizationManager;
import org.dblue.security.authorization.NoopDatabaseAuthorizationManager;
import org.dblue.security.converter.AccessTokenAuthenticationConverter;
import org.dblue.security.converter.AccessTokenFinder;
import org.dblue.security.converter.CompositeAccessTokenFinder;
import org.dblue.security.filter.AccessTokenTtlRefreshFilter;
import org.dblue.security.filter.PreAccessTokenProcessingFilter;
import org.dblue.security.handler.*;
import org.dblue.security.properties.SecurityProperties;
import org.dblue.security.token.TokenManager;
import org.dblue.security.user.UserStoreService;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 登录即权限控制配置信息
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@EnableConfigurationProperties({
        SecurityProperties.class
})
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
@ComponentScan(basePackages = {
        "org.dblue.security.token",
        "org.dblue.security.user"
})
public class SecurityAutoConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        DatabaseAuthorizationManager authorizationManager = applicationContext.getBean(DatabaseAuthorizationManager.class);
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/favicon.ico").permitAll()
                        .requestMatchers("/doc.html").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/v2/**").permitAll()
                        .requestMatchers("/v3/**").permitAll()
                        .anyRequest().access(authorizationManager)
                )
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(accessTokenTtlRefreshFilter(), HeaderWriterFilter.class)
                .addFilterBefore(preAccessTokenProcessingFilter(), AbstractPreAuthenticatedProcessingFilter.class)
                .formLogin(formLoginConfigurer -> formLoginConfigurer
                        .successHandler(authenticationSuccessHandler())
                        .failureHandler(authenticationFailureHandler())
                        .loginProcessingUrl("/api/login")
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutUrl("/api/logout")
                        .addLogoutHandler(logoutHandler())
                        .logoutSuccessHandler(new DefaultLogoutSuccessHandler())
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsCustomizer -> corsCustomizer.configurationSource(corsConfigurationSource()))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new CustomerAuthenticationEntryPoint())
                        .accessDeniedHandler(new DefaultAccessDeniedHandler())
                )
        ;
        return http.build();
    }

    @ConditionalOnMissingBean(DatabaseAuthorizationManager.class)
    @Bean
    public NoopDatabaseAuthorizationManager databaseAuthorizationManager() {
        return new NoopDatabaseAuthorizationManager();
    }

    public AccessTokenTtlRefreshFilter accessTokenTtlRefreshFilter() {
        TokenManager tokenManager = this.applicationContext.getBean(TokenManager.class);
        AccessTokenFinder accessTokenFinder = this.applicationContext.getBean(AccessTokenFinder.class);
        UserStoreService userStoreService = this.applicationContext.getBean(UserStoreService.class);

        AccessTokenTtlRefreshFilter accessTokenTtlRefreshFilter = new AccessTokenTtlRefreshFilter();
        accessTokenTtlRefreshFilter.setTokenManager(tokenManager);
        accessTokenTtlRefreshFilter.setAccessTokenFinder(accessTokenFinder);
        accessTokenTtlRefreshFilter.setUserStoreService(userStoreService);
        return accessTokenTtlRefreshFilter;
    }

    public PreAccessTokenProcessingFilter preAccessTokenProcessingFilter() {
        TokenManager tokenManager = this.applicationContext.getBean(TokenManager.class);
        AccessTokenAuthenticationConverter authenticationConverter = this.applicationContext.getBean(AccessTokenAuthenticationConverter.class);
        UserStoreService userStoreService = this.applicationContext.getBean(UserStoreService.class);

        PreAccessTokenProcessingFilter preAccessTokenProcessingFilter = new PreAccessTokenProcessingFilter();
        preAccessTokenProcessingFilter.setTokenManager(tokenManager);
        preAccessTokenProcessingFilter.setAccessTokenAuthenticationConverter(authenticationConverter);
        preAccessTokenProcessingFilter.setUserStoreService(userStoreService);
        return preAccessTokenProcessingFilter;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        TokenManager tokenManager = this.applicationContext.getBean(TokenManager.class);
        UserStoreService userStoreService = this.applicationContext.getBean(UserStoreService.class);
        return new DefaultAuthenticationSuccessHandler(tokenManager, userStoreService);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new DefaultAuthenticationFailureHandler();
    }

    @Bean
    public LogoutHandler logoutHandler() {
        TokenManager tokenManager = this.applicationContext.getBean(TokenManager.class);
        return new DefaultLogoutHandler(tokenManager);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(false);
        configurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return configurationSource;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        Collection<AuthenticationProvider> authenticationProviderList = this.applicationContext.getBeansOfType(AuthenticationProvider.class).values();
        ProviderManager authenticationManager = new ProviderManager(new ArrayList<>(authenticationProviderList));
        authenticationManager.setEraseCredentialsAfterAuthentication(false);
        return authenticationManager;
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }

    @Bean
    public DaoAuthenticationProvider usernamePasswordAuthenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(AccessTokenAuthenticationConverter.class)
    public AccessTokenAuthenticationConverter accessTokenAuthenticationConverter(AccessTokenFinder accessTokenFinder) {
        return new AccessTokenAuthenticationConverter(accessTokenFinder);
    }

    @Bean
    @ConditionalOnMissingBean(AccessTokenFinder.class)
    public AccessTokenFinder accessTokenFinder() {
        return CompositeAccessTokenFinder.DEFAULT_INSTANCE;
    }
}
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
package org.dblue.application.security.user;

import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.permission.infrastructure.entiry.Permission;
import org.dblue.application.module.permission.infrastructure.repository.PermissionRepository;
import org.dblue.application.module.role.infrastructure.entiry.Role;
import org.dblue.application.module.role.infrastructure.entiry.RolePermission;
import org.dblue.application.module.role.infrastructure.repository.RoleRepository;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.dblue.application.module.user.infrastructure.entity.UserRole;
import org.dblue.application.module.user.infrastructure.repository.UserRepository;
import org.dblue.security.user.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        SecurityUser securityUser = new SecurityUser();
        BeanUtils.copyProperties(user, securityUser);

        if (CollectionUtils.isEmpty(user.getRoles())) {
            securityUser.setAuthorities(Collections.emptyList());
            return securityUser;
        }

        Set<String> roleIdSet = user.getRoles().stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        List<Role> roleList = this.roleRepository.findAllById(roleIdSet);

        Set<String> permissionIdList = roleList.stream()
                .filter(o -> CollectionUtils.isNotEmpty(o.getPermissions()))
                .flatMap(o -> o.getPermissions().stream())
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toSet());
        List<Permission> permissionList = this.permissionRepository.findAllById(permissionIdList);

        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (Permission permission : permissionList) {
            authorities.add(new SimpleGrantedAuthority(permission.getPermissionCode()));
        }
        securityUser.setAuthorities(authorities);

        return securityUser;
    }
}
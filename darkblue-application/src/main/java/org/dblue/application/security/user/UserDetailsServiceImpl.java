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

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.dblue.application.module.permission.domain.service.PermissionDomainQueryService;
import org.dblue.application.module.permission.infrastructure.entiry.Permission;
import org.dblue.application.module.role.domain.service.RoleDomainQueryService;
import org.dblue.application.module.role.infrastructure.entiry.Role;
import org.dblue.application.module.role.infrastructure.entiry.RolePermission;
import org.dblue.application.module.user.infrastructure.entity.User;
import org.dblue.application.module.user.infrastructure.entity.UserRole;
import org.dblue.application.module.user.infrastructure.repository.UserRepository;
import org.dblue.application.module.usergroup.domain.service.UserGroupDomainQueryService;
import org.dblue.application.module.usergroup.infrastructure.entity.UserGroupRole;
import org.dblue.security.user.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserGroupDomainQueryService userGroupDomainQueryService;
    private final RoleDomainQueryService roleDomainQueryService;
    private final PermissionDomainQueryService permissionDomainQueryService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        SecurityUser securityUser = new SecurityUser();
        BeanUtils.copyProperties(user, securityUser);
        securityUser.setAdmin(Boolean.TRUE.equals(user.getIsAdmin()));

        if (CollectionUtils.isEmpty(user.getRoles())) {
            securityUser.setAuthorities(Collections.emptyList());
            return securityUser;
        }
        Collection<GrantedAuthority> authorities = new HashSet<>();
        List<Permission> permissionList;
        if (Boolean.TRUE.equals(user.getIsAdmin())) {
            permissionList = permissionDomainQueryService.getAll();
        } else {
            Set<String> roleIdSet = user.getRoles().stream().map(UserRole::getRoleId).collect(Collectors.toSet());
            List<UserGroupRole> groupRoles = userGroupDomainQueryService.getUserGroupRoleByUserId(securityUser.getUserId());
            if (CollectionUtils.isNotEmpty(groupRoles)) {
                roleIdSet.addAll(groupRoles.stream().map(UserGroupRole::getRoleId).collect(Collectors.toSet()));
            }
            List<Role> roleList = this.roleDomainQueryService.getMoreByIds(roleIdSet);
            for (Role role : roleList) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleCode()));
            }

            Set<String> permissionIdSet = roleList.stream()
                    .filter(o -> CollectionUtils.isNotEmpty(o.getPermissions()))
                    .flatMap(o -> o.getPermissions().stream())
                    .map(RolePermission::getPermissionId)
                    .collect(Collectors.toSet());
            permissionList = this.permissionDomainQueryService.getPermissionByPermissionIds(permissionIdSet);
        }


        for (Permission permission : permissionList) {
            authorities.add(new SimpleGrantedAuthority(permission.getPermissionCode()));
        }
        securityUser.setAuthorities(authorities);

        return securityUser;
    }
}
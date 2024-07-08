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
package org.dblue.security.event;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.dblue.security.enums.LoginPlatform;
import org.dblue.security.enums.LoginType;
import org.dblue.security.user.SecurityUser;
import org.springframework.context.ApplicationEvent;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Getter
public class LoginSuccessEvent extends ApplicationEvent {

    private final LoginPlatform loginPlatform;
    private final LoginType loginType;
    private final SecurityUser user;
    private final transient HttpServletRequest request;

    public LoginSuccessEvent(Object source, SecurityUser user, HttpServletRequest request) {
        this(source, LoginPlatform.PC, LoginType.PASSWORD, user, request);
    }

    public LoginSuccessEvent(Object source, LoginPlatform loginPlatform, LoginType loginType, SecurityUser user, HttpServletRequest request) {
        super(source);
        this.user = user;
        this.loginPlatform = loginPlatform;
        this.loginType = loginType;
        this.request = request;
    }
}
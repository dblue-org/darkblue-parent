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
package org.dblue.application.module.logs.application.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dblue.application.module.logs.infrastructure.entity.LoginLog;
import org.dblue.security.enums.LoginPlatform;
import org.dblue.security.enums.LoginType;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * 登录日志
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Data
@Schema(description = "登录日志")
public class LoginLogPageListVo {

    /**
     * 登录日志ID
     */
    @Schema(description = "登录日志ID")
    private String loginLogId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String userId;

    /**
     * 用户姓名
     */
    @Schema(description = "用户姓名")
    private String name;

    /**
     * 登录平台（1-PC;2-Android;3-IOS;4-小程序）
     */
    @Schema(description = "登录平台（1-PC;2-Android;3-IOS;4-小程序）")
    private Integer loginPlatform;

    /**
     * 登录平台名称
     */
    @Schema(description = "登录平台名称")
    private String loginPlatformName;

    /**
     * 登录类型（1-密码登录;2-微信登录;3-支付宝登录;9-其他）
     */
    @Schema(description = "登录类型（1-密码登录;2-微信登录;3-支付宝登录;9-其他）")
    private Integer loginType;

    /**
     * 登录类型名称
     */
    @Schema(description = "登录类型名称")
    private String loginTypeName;

    /**
     * 登录时间
     */
    @Schema(description = "登录时间")
    private LocalDateTime loginTime;

    /**
     * 登录IP
     */
    @Schema(description = "登录IP")
    private String loginIp;

    /**
     * request 中的user-agent
     */
    @Schema(description = "request 中的user-agent")
    private String userAgent;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    public static LoginLogPageListVo of(LoginLog loginLog) {
        LoginLogPageListVo vo = new LoginLogPageListVo();
        BeanUtils.copyProperties(loginLog, vo);
        vo.setLoginPlatformName(LoginPlatform.getName(loginLog.getLoginPlatform()));
        vo.setLoginTypeName(LoginType.getName(loginLog.getLoginType()));
        return vo;
    }

}
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

package org.dblue.application.module.logs.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_sys_login_log")
@TableName("tb_sys_login_log")
public class LoginLog {

    /**
     * 登录日志ID
     */
    @Id
    @Size(max = 64)
    @Column(name = "login_log_id", nullable = false, length = 64)
    @TableId(value = "login_log_id", type = IdType.INPUT)
    private String loginLogId;

    /**
     * 登录用户ID
     */
    @Size(max = 64)
    @Column(name = "user_id", length = 64)
    @TableField("user_id")
    private String userId;

    /**
     * 登录平台（1-PC;2-Android;3-IOS;4-小程序）
     */
    @Column(name = "login_platform")
    @TableField("login_platform")
    private Integer loginPlatform;

    /**
     * 登录类型（1-密码登录;2-微信登录;3-支付宝登录;9-其他）
     */
    @Column(name = "login_type")
    @TableField("login_type")
    private Integer loginType;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    @TableField("login_time")
    private LocalDateTime loginTime;

    /**
     * 登录IP
     */
    @Size(max = 128)
    @Column(name = "login_ip", length = 128)
    @TableField("login_ip")
    private String loginIp;

    /**
     * request 中的user-agent
     */
    @Size(max = 512)
    @Column(name = "user_agent", length = 512)
    @TableField("user_agent")
    private String userAgent;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Size(max = 64)
    @Column(name = "create_user", length = 64)
    @TableField("create_user")
    private String createUser;

}
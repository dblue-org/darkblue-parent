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

INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level,
                                  sort_num, menu_icon, remark, is_enable, create_time, create_user, update_time,
                                  update_user, is_visible, is_production_visible)
VALUES ('5299007534248493213', null, 1, 1, '消息管理', null, null, 1, 4, 'iconify#uiw:message', null, 1,
        '2024-09-03 22:51:02', '000001', '2024-09-03 22:51:02', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level,
                                  sort_num, menu_icon, remark, is_enable, create_time, create_user, update_time,
                                  update_user, is_visible, is_production_visible)
VALUES ('5299009238561980573', '5299007534248493213', 1, 2, '消息模板', null, '/message/template', 2, 1,
        'iconify#fluent:calendar-template-32-regular', null, 1, '2024-09-03 22:52:43', '000001', '2024-09-03 22:56:35',
        '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level,
                                  sort_num, menu_icon, remark, is_enable, create_time, create_user, update_time,
                                  update_user, is_visible, is_production_visible)
VALUES ('5299011700266107037', '5299007534248493213', 1, 2, '通知管理', null, '/message/notification', 2, 2,
        'iconify#streamline:chat-bubble-oval-notification-solid', null, 1, '2024-09-03 22:55:10', '000001',
        '2024-09-03 22:55:10', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level,
                                  sort_num, menu_icon, remark, is_enable, create_time, create_user, update_time,
                                  update_user, is_visible, is_production_visible)
VALUES ('5299012924767994013', '5299007534248493213', 1, 2, '待办管理', null, '/message/todo', 2, 3,
        'iconify#lucide:list-todo', null, 1, '2024-09-03 22:56:23', '000001', '2024-09-03 22:56:23', '000001', 1, 1);


create table tb_sys_message_template_group
(
    message_template_group_id   varchar(32) primary key not null comment '消息模板组ID',
    message_template_group_name varchar(256)            not null comment '消息模板组名称',
    create_time                 datetime                null comment '创建时间',
    create_user                 varchar(32)             null comment '创建人',
    update_time                 datetime                null comment '更新时间',
    update_user                 varchar(32)             null comment '更新人'
) comment '消息模板组';

-- 消息模板组名称不能重复
alter table tb_sys_message_template_group
    add unique index udx_message_template_group_name (message_template_group_name);

create table tb_sys_message_template
(
    message_template_id       varchar(32) primary key not null comment '消息模板ID',
    message_template_group_id varchar(32)             not null comment '消息模板组ID',
    message_template_code     varchar(64)             not null comment '消息模板编码',
    message_template_name     varchar(256)            not null comment '消息模板名称',
    message_template_type     int                     not null comment '消息模板类型（1-通知；2-待办）',
    service_code_tpl          varchar(256)            null comment '业务编码配置',
    message_title_tpl         varchar(256)            not null comment '消息标题配置',
    message_content_tpl       text                    not null comment '消息内容配置',
    create_time               datetime                null comment '创建时间',
    create_user               varchar(32)             null comment '创建人',
    update_time               datetime                null comment '更新时间',
    update_user               varchar(32)             null comment '更新人'
) comment '消息模板';

-- message_template_code 不能重复
alter table tb_sys_message_template
    add unique index udx_message_template_code (message_template_code);

create table tb_sys_message_template_direct_route
(
    message_template_direct_route_id varchar(32)  not null comment '路由ID'
        primary key,
    message_template_id              varchar(32)  not null comment '模板ID',
    router_type                      int          not null comment '路由类型(1-PC;2-Android;3-IOS;4-小程序;5-H5)',
    router_link                      varchar(512) not null comment '路由地址',
    create_time                      datetime     null comment '创建时间',
    create_user                      varchar(32)  null comment '创建人'
) comment '模板直接路由';

-- 路由配置不能重复
alter table tb_sys_message_template_direct_route
    add unique index udx_router_type (message_template_id, router_type);

create table tb_sys_message_template_tag
(
    message_template_tag_id varchar(32)  not null comment '标签ID'
        primary key,
    message_template_id     varchar(32)  not null comment '模板ID',
    tag_name                varchar(64)  not null comment '标签名称',
    show_conditional        varchar(512) not null comment '显示条件',
    create_user             varchar(32)  null comment '创建人',
    create_time             datetime     null comment '创建时间'
) comment '模板标签配置';

-- 标签在数据库中不做重复校验的唯一索引，生成标签后在保存时去重。


create table tb_sys_message_template_action
(
    message_template_button_id varchar(32)  not null comment '按钮ID'
        primary key,
    message_template_id        varchar(32)  not null comment '模板ID',
    action_name                varchar(32)  not null comment '操作名称',
    action_mark                varchar(64)  null comment '操作标识',
    action_type                int          not null comment '操作类型（1-路由跳转；2-宏）',
    match_state                int          not null comment '匹配状态（0-全部；1-未处理；2-已处理）',
    sort_num                   int          null comment '排序',
    show_conditional           varchar(512) not null comment '按钮条件',
    macro_code                 varchar(256) null comment '宏编码',
    create_time                datetime     null comment '创建时间',
    create_user                varchar(32)  null comment '创建人'

)
    comment '模板操作配置';

alter table tb_sys_message_template_action
    add unique index udx_action_name (message_template_id, action_name);
alter table tb_sys_message_template_action
    add unique index udx_action_mark (action_mark);

create table tb_sys_message_template_action_route
(
    message_template_action_route_id varchar(32)  not null comment '路由ID' primary key,
    message_template_action_id       varchar(32)  not null comment '按钮ID',
    message_template_id              varchar(32)  not null comment '模板ID',
    router_type                      int          not null comment '路由类型(1-PC;2-Android;3-IOS;4-小程序;5-H5)',
    router_link                      varchar(512) not null comment '路由地址',
    create_time                      datetime     null comment '创建时间',
    create_user                      varchar(32)  null comment '创建人'
)
    comment '模板按钮路由';

create table tb_sys_notification
(
    notification_id           varchar(32)  not null comment '通知消息ID'
        primary key,
    message_template_group_id varchar(32)  not null comment '消息模板组ID',
    message_template_id       varchar(32)  not null comment '模板ID',
    message_title             varchar(512) not null comment '消息标题',
    message_content           longtext     not null comment '主要内容',
    to_user                   varchar(32)  not null comment '接收人',
    receive_time              datetime     not null comment '消息接收时间',
    service_mark              varchar(100) null comment '业务类型标识',
    service_code              varchar(32)  null comment '业务记录编码',
    service_id                varchar(32)  not null comment '业务ID',
    is_read                   tinyint(1)   not null default 0 comment '是否已读',
    read_time                 datetime     null comment '读消息时间',
    service_data              json         not null comment '业务数据',
    create_time               datetime     null comment '创建时间',
    create_user               varchar(32)  null comment '创建人',
    update_time               datetime     null comment '更新时间',
    update_user               varchar(32)  null comment '更新人'
)
    comment '通知消息';

create table tb_sys_notification_route
(
    notification_route_id varchar(32)  not null comment '路由ID'
        primary key,
    notification_id       varchar(32)  not null comment '消息通知ID',
    router_type           int          not null comment '路由类型(1-PC;2-Android;3-IOS;4-小程序;5-H5)',
    router_link           varchar(512) not null comment '路由地址',
    create_time           datetime     null comment '创建时间',
    create_user           varchar(32)  null comment '创建人'
)
    comment '消息通知路由';

create table tb_sys_todo
(
    todo_id                   varchar(32)  not null comment '待办ID'
        primary key,
    todo_type                 int          not null comment '待办类型（1-待办；2-我的；3-抄送）',
    message_type              int          not null comment '消息分类（1-审批类；2-业务类）',
    message_template_group_id varchar(32)  not null comment '消息模板组ID',
    message_template_id       varchar(32)  not null comment '模板ID',
    service_mark              varchar(100) null comment '业务类型标识',
    stage                     varchar(32)  null comment '步骤编码',
    service_code              varchar(32)  null comment '业务记录编码',
    service_id                varchar(32)  not null comment '业务ID',
    message_title             varchar(512) not null comment '消息标题',
    message_content           longtext     not null comment '消息内容',
    tags                      varchar(256) null comment '标签',
    state                     int          not null default 1 comment '状态（1-未处理；2-已处理）',
    complete_time             datetime     null comment '待办完成时间',
    starter                   varchar(32)  not null comment '发起人',
    to_user                   varchar(32)  not null comment '接收人',
    handle_user               varchar(32)  null comment '处理人',
    start_time                datetime     null comment '发起时间',
    receive_time              datetime     null comment '任务接受时间',
    process_instance_id       varchar(64)  null comment '流程ID',
    task_id                   varchar(64)  null comment '任务ID',
    service_state             varchar(64)  null comment '业务状态',
    service_state_name        varchar(64)  null comment '业务状态名称',
    end_time                  datetime     null comment '截止时间',
    service_data              json         not null comment '业务数据',
    create_time               datetime     null comment '创建时间',
    create_user               varchar(32)  null comment '创建人',
    update_time               datetime     null comment '更新时间',
    update_user               varchar(32)  null comment '更新人'
)
    comment '待办列表';

create table tb_sys_todo_action
(
    todo_action_id varchar(32) not null comment '操作ID'
        primary key,
    todo_id        varchar(32) not null comment '待办ID',
    action_name    varchar(32) not null comment '操作名称',
    action_mark    varchar(32) null comment '操作标识',
    action_type    int          not null comment '操作类型（1-路由跳转；2-宏）',
    match_state    int          null comment '匹配状态（0-全部；1-未处理；2-已处理）',
    sort_num       int          null comment '排序',
    macro_code     varchar(256) null comment '宏编码',
    create_time    datetime     null comment '创建时间',
    create_user    varchar(32) null comment '创建人'
)
    comment '待办消息操作';

create table tb_sys_todo_action_route
(
    todo_action_route_id varchar(32)  not null comment '待办操作路由ID'
        primary key,
    todo_action_id       varchar(32)  not null comment '操作ID',
    todo_id              varchar(32)  not null comment '待办ID',
    router_type          int          not null comment '路由类型(1-PC;2-Android;3-IOS;4-小程序;5-H5)',
    router_link          varchar(512) not null comment '路由地址',
    create_time          datetime     null comment '创建时间',
    create_user          varchar(32)  null comment '创建人'
)
    comment '待办操作路由';
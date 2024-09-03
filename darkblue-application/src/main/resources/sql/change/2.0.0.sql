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
    message_template_group_id   varchar(64) primary key not null comment '消息模板组ID',
    message_template_group_code varchar(64)             null comment '消息模板组编码',
    message_template_group_name varchar(256)            null comment '消息模板组名称',
    create_time                 datetime                null comment '创建时间',
    create_user                 varchar(64)             null comment '创建人',
    update_time                 datetime                null comment '更新时间',
    update_user                 varchar(64)             null comment '更新人'
) comment '消息模板组';

create table tb_sys_message_template
(
    message_template_id      varchar(64) primary key not null comment '消息模板ID',
    message_template_code    varchar(64)             null comment '消息模板编码',
    message_template_name    varchar(256)            null comment '消息模板名称',
    message_template_title   varchar(256)            null comment '消息模板名称',
    message_template_content text                    null comment '消息模板内容'
) comment '消息模板'
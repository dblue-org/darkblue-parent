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

create database darkblue character set utf8mb4 collate utf8mb4_general_ci;


create table tb_service_file
(
    file_service_id varchar(64)   not null comment '业务文件ID '
        primary key,
    file_id         varchar(64)   null comment '文件ID ',
    file_name       varchar(256)  null comment '文件名',
    file_path       varchar(1000) null comment '文件路径 ',
    file_type       tinyint       null comment '文件类型(1-图片；2-视频)',
    service_type    varchar(64)   null comment '业务类型 ',
    service_id      varchar(64)   null comment '业务ID ',
    service_mark    varchar(64)   null comment '业务标识 ',
    create_user     varchar(64)   null comment '创建人',
    create_time     datetime      null comment '创建时间'
)
    comment '业务文件信息' engine = InnoDB;

create table tb_sys_department
(
    dept_id        varchar(64)          not null comment '部门ID'
        primary key,
    dept_name      varchar(200)         null comment '部门名称',
    parent_id      varchar(64)          null comment '上级ID',
    master_user_id varchar(64)          null comment '部门主管',
    create_time    datetime             null comment '创建时间',
    create_user    varchar(64)          null comment '创建人',
    update_time    datetime             null comment '更新时间',
    update_user    varchar(64)          null comment '更新人',
    is_enable      tinyint(1) default 1 null comment '是否启用',
    is_del         tinyint(1) default 0 null comment '是否删除'
)
    comment '组织架构' engine = InnoDB;

create table tb_sys_dictionary
(
    dictionary_id   varchar(64)  not null comment '字典ID'
        primary key,
    dictionary_code varchar(64)  null comment '字典编码',
    dictionary_name varchar(64)  null comment '字典名称',
    dictionary_type int          null comment '字典类型（1-普通字典；2-树形字典）',
    is_delete       tinyint(1)   null comment '是否删除',
    create_time     datetime     null comment '创建时间',
    create_user     varchar(64)  null comment '创建人',
    update_time     datetime     null comment '更新时间',
    update_user     varchar(100) null comment '更新人'
)
    comment '数据字典' engine = InnoDB;

create table tb_sys_dictionary_item
(
    dictionary_item_id   varchar(64) default '1' not null comment '字典项目ID'
        primary key,
    dictionary_item_code varchar(64)             null comment '字典项识别码',
    dictionary_id        varchar(64)             null comment '字典ID',
    code                 int                     null comment '编码',
    name                 varchar(500)            null comment '名称',
    extension            varchar(500)            null comment '扩展信息',
    parent_id            varchar(64)             null comment '上级ID',
    order_num            int                     null comment '顺序',
    item_level           int                     null comment '级别',
    is_delete            tinyint(1)  default 0   null comment '是否删除',
    is_enabled           tinyint(1)  default 1   null comment '是否启用',
    create_time          datetime                null comment '创建时间',
    create_user          varchar(64)             null comment '创建人',
    update_time          datetime                null comment '更新时间',
    update_user          varchar(100)            null comment '更新人'
)
    comment '数据字典条目' engine = InnoDB;

create table tb_sys_menu
(
    menu_id               varchar(64)          not null comment '菜单ID'
        primary key,
    parent_id             varchar(64)          null comment '上级菜单ID',
    platform              int                  null comment '菜单适用平台(1-PC；2-APP)',
    menu_type             int                  null comment '菜单类型(1-目录;2-菜单)',
    menu_name             varchar(64)          null comment '菜单名称',
    url_name              varchar(256)         null comment '地址名',
    menu_url              varchar(128)         null comment '菜单url',
    level                 int                  null comment '菜单层级',
    sort_num              int                  null comment '显示顺序',
    menu_icon             varchar(128)         null comment '菜单图标',
    remark                varchar(500)         null comment '备注',
    is_enable             tinyint(1)           null comment '是否可用',
    create_time           datetime             null comment '创建时间',
    create_user           varchar(64)          null comment '创建人',
    update_time           datetime             null comment '更新时间',
    update_user           varchar(64)          null comment '更新人',
    is_visible            tinyint(1) default 1 not null comment '是否可见',
    is_production_visible tinyint(1) default 1 not null comment '是否生产环境可见'
)
    comment '菜单' engine = InnoDB;

create table tb_sys_permission
(
    permission_id   varchar(64)  not null comment '权限ID'
        primary key,
    menu_id         varchar(64)  null comment '菜单ID',
    platform        int          null comment '适用平台(1-PC；2-APP)从菜单代入',
    permission_name varchar(100) null comment '权限名称',
    permission_code varchar(64)  null comment '权限标识',
    create_time     datetime     null comment '创建时间',
    create_user     varchar(64)  null comment '创建人',
    update_time     datetime     null comment '更新时间',
    update_user     varchar(64)  null comment '更新人'
)
    comment '权限' engine = InnoDB;

create table tb_sys_permission_resource
(
    permission_resource_id varchar(64) not null comment '权限资源id'
        primary key,
    permission_id          varchar(64) null comment '权限id',
    resource_id            varchar(64) null comment '资源id',
    create_time            datetime    null comment '创建时间',
    create_user            varchar(64) null comment '创建人'
)
    comment '权限资源' engine = InnoDB;

create table tb_sys_resource
(
    resource_id      varchar(64)  not null comment '资源ID'
        primary key,
    service_id       varchar(64)  not null comment '服务ID',
    menu_id          varchar(64)  not null comment '菜单ID',
    resource_name    varchar(100) null comment '资源名称',
    resource_url     varchar(256) null comment '资源地址',
    is_authed_access tinyint(1)   null comment '是否登录即可访问',
    sort_num         int          null comment '排序字段',
    create_time      datetime     null comment '创建时间',
    create_user      varchar(64)  null comment '创建人',
    update_time      datetime     null comment '更新时间',
    update_user      varchar(64)  null comment '更新人'
)
    comment '资源' engine = InnoDB;

create table tb_sys_role
(
    role_id     varchar(64)  not null comment '角色id'
        primary key,
    role_name   varchar(64)  null comment '角色名称',
    role_code   varchar(64)  null comment '角色编码',
    remark      varchar(500) null comment '备注',
    is_enable   tinyint(1)   null comment '是否可用',
    is_built_in tinyint(1)   null comment '是否内置',
    create_time datetime     null comment '创建时间',
    create_user varchar(64)  null comment '创建人',
    update_time datetime     null comment '更新时间',
    update_user varchar(64)  null comment '更新人'
)
    comment '角色' engine = InnoDB;

create table tb_sys_role_menu
(
    role_menu_id varchar(64) not null comment '角色菜单id'
        primary key,
    role_id      varchar(64) null comment '角色id',
    menu_id      varchar(64) null comment '菜单id',
    create_time  datetime    null comment '创建时间',
    create_user  varchar(64) null comment '创建人'
)
    comment '角色菜单' engine = InnoDB;

create table tb_sys_role_permission
(
    role_permission_id varchar(64) not null comment '角色权限ID'
        primary key,
    role_id            varchar(64) null comment '角色ID',
    permission_id      varchar(64) null comment '权限ID',
    create_time        datetime    null comment '创建时间',
    create_user        varchar(64) null comment '创建人'
)
    comment '角色权限' engine = InnoDB;

create table tb_sys_user
(
    user_id              varchar(64)  not null comment '用户ID'
        primary key,
    username             varchar(64)  null comment '用户名',
    password             varchar(128) null comment '密码',
    name                 varchar(64)  null comment '姓名',
    sex                  int          null comment '性别（1-男；2-女）',
    dept_id              varchar(64)  null comment '所属部门',
    position_id          varchar(64)  null comment '职务ID',
    phone_number         varchar(20)  null comment '手机号',
    identity_no          varchar(20)  null comment '身份证号码',
    is_enable            tinyint(1)   null comment '是否可用',
    last_login_time      datetime     null comment '最后登录日期',
    password_update_time datetime     null comment '密码更新时间',
    is_admin             tinyint(1)   null comment '是否超级管理员',
    is_del               tinyint(1)   null comment '是否删除',
    create_time          datetime     null comment '创建时间',
    create_user          varchar(64)  null comment '创建人',
    update_time          datetime     null comment '更新时间',
    update_user          varchar(64)  null comment '更新人'
)
    comment '用户' engine = InnoDB;

create table tb_sys_user_role
(
    user_role_id varchar(64) not null comment '用户角色ID'
        primary key,
    user_id      varchar(64) null comment '用户ID',
    role_id      varchar(64) null comment '角色id',
    create_time  datetime    null comment '创建时间',
    create_user  varchar(64) null comment '创建人'
)
    comment '用户角色' engine = InnoDB;


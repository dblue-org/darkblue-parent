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

INSERT INTO darkblue.tb_sys_department (dept_id, dept_name, parent_id, master_user_id, create_time, create_user, update_time, update_user, is_enable, is_del)
VALUES ('5208464841823486061', '研发部', '', '', '2024-07-03 11:44:57', '000001', '2024-07-03 11:44:57', '000001', 1, 0);
INSERT INTO darkblue.tb_sys_department (dept_id, dept_name, parent_id, master_user_id, create_time, create_user, update_time, update_user, is_enable, is_del)
VALUES ('5208465053652615277', '总经办', null, null, '2024-07-03 11:45:09', '000001', '2024-07-08 17:02:15', '000001', 1, 0);

INSERT INTO darkblue.tb_sys_dictionary (dictionary_id, dictionary_code, dictionary_name, dictionary_type, is_delete, create_time, create_user, update_time,
                                        update_user)
VALUES ('5226191863953752249', 'TEST', '测试字典', 1, 0, '2024-07-15 17:15:10', '000001', '2024-07-15 17:15:10', '000001');
INSERT INTO darkblue.tb_sys_dictionary (dictionary_id, dictionary_code, dictionary_name, dictionary_type, is_delete, create_time, create_user, update_time,
                                        update_user)
VALUES ('5226219701113192513', 'TREE_DICT', '树型字典测试', 2, 0, '2024-07-15 17:42:49', '000001', '2024-07-15 17:43:03', '000001');

INSERT INTO darkblue.tb_sys_dictionary_item (dictionary_item_id, dictionary_id, code, name, extension, parent_id, order_num, item_level, is_delete, is_enable,
                                             create_time, create_user, update_time, update_user)
VALUES ('5226194825501475001', '5226191863953752249', 1, '字典项1', null, null, 1, 1, 0, 1, '2024-07-15 17:18:06', '000001', '2024-07-15 17:18:06', '000001');
INSERT INTO darkblue.tb_sys_dictionary_item (dictionary_item_id, dictionary_id, code, name, extension, parent_id, order_num, item_level, is_delete, is_enable,
                                             create_time, create_user, update_time, update_user)
VALUES ('5226220247261904961', '5226219701113192513', 101, '根节点1', '666', null, 1, 1, 0, 1, '2024-07-15 17:43:21', '000001', '2024-07-16 08:36:18',
        '000001');
INSERT INTO darkblue.tb_sys_dictionary_item (dictionary_item_id, dictionary_id, code, name, extension, parent_id, order_num, item_level, is_delete, is_enable,
                                             create_time, create_user, update_time, update_user)
VALUES ('5226225878165356609', '5226219701113192513', 102, '根节点2', null, null, 2, 1, 0, 1, '2024-07-15 17:49:01', '000001', '2024-07-15 17:49:01', '000001');
INSERT INTO darkblue.tb_sys_dictionary_item (dictionary_item_id, dictionary_id, code, name, extension, parent_id, order_num, item_level, is_delete, is_enable,
                                             create_time, create_user, update_time, update_user)
VALUES ('5226226211964846145', '5226219701113192513', 10101, '子节点1', null, '5226220247261904961', 2, 2, 0, null, '2024-07-15 17:49:17', '000001',
        '2024-07-15 18:03:58', '000001');
INSERT INTO darkblue.tb_sys_dictionary_item (dictionary_item_id, dictionary_id, code, name, extension, parent_id, order_num, item_level, is_delete, is_enable,
                                             create_time, create_user, update_time, update_user)
VALUES ('5226233718829482118', '5226219701113192513', 101002, '子节点2', null, '5226220247261904961', null, 2, 0, null, '2024-07-15 17:57:05', '000001',
        '2024-07-15 18:03:12', '000001');

INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208361429043773627', null, 1, 1, '仪表盘', null, '', 1, 1, 'dashboard', null, 1, '2024-07-03 10:02:13', '000001', '2024-07-03 10:02:13', '000001', 1,
        1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208365662975557878', '5208361429043773627', 1, 2, '首页', null, '/home', 2, 1, 'home', null, 1, '2024-07-03 10:06:25', '000001',
        '2024-07-03 10:06:25', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208366438099714294', null, 1, 1, '系统管理', null, '', 1, 2, 'setting', null, 1, '2024-07-03 10:07:11', '000001', '2024-07-03 10:07:11', '000001', 1,
        1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208366805017428214', '5208366438099714294', 1, 2, '用户管理', null, '/sys/user', 2, 1, 'user', null, 1, '2024-07-03 10:07:33', '000001',
        '2024-07-03 10:07:33', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208367148061163766', '5208366438099714294', 1, 2, '用户组管理', null, '/sys/user-group', 2, 2, 'iconify#mingcute:group-line', null, 1,
        '2024-07-03 10:07:54', '000001', '2024-07-09 09:06:02', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208368791020372214', '5208366438099714294', 1, 2, '菜单管理', null, '/sys/menu', 2, 5, 'bars', null, 1, '2024-07-03 10:09:32', '000001',
        '2024-07-03 13:32:25', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208369071787081974', '5208366438099714294', 1, 2, '权限管理', null, '/sys/permission', 2, 6, 'safety', null, 1, '2024-07-03 10:09:48', '000001',
        '2024-07-18 14:46:32', '000001', 1, 0);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208370996855177462', '5208366438099714294', 1, 2, '职位管理', null, '/sys/position', 2, 3, 'iconify#healthicons:city-worker-outline', null, 1,
        '2024-07-03 10:11:43', '000001', '2024-07-03 10:11:43', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208371439975006454', '5208366438099714294', 1, 2, '角色管理', null, '/sys/role', 2, 4, 'iconify#carbon:user-role', null, 1, '2024-07-03 10:12:10',
        '000001', '2024-07-03 13:32:10', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208372564820230390', '5208366438099714294', 1, 2, '资源管理', null, '/sys/resource', 2, 7, 'link', null, 1, '2024-07-03 10:13:17', '000001',
        '2024-07-03 10:13:17', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208375243722195102', '5208366438099714294', 1, 1, '系统日志', null, '', 2, 8, 'solution', null, 1, '2024-07-03 10:15:56', '000001',
        '2024-07-03 10:15:56', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208375438522450078', '5208366438099714294', 1, 1, '系统配置', null, '', 2, 9, 'profile', null, 1, '2024-07-03 10:16:08', '000001',
        '2024-07-03 13:45:46', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208377102402846878', null, 1, 1, '运维中心', null, '', 1, 5, 'iconify#tabler:server-bolt', null, 1, '2024-07-03 10:17:47', '000001',
        '2024-07-03 13:46:10', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208378615657398430', '5208375243722195102', 1, 2, '登录日志', null, '/logs/login', 3, 1, 'login', null, 1, '2024-07-03 10:19:17', '000001',
        '2024-07-05 17:07:17', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208379459048046750', '5208375243722195102', 1, 2, '操作日志', null, '/logs/operation', 3, 2, 'edit', null, 1, '2024-07-03 10:20:08', '000001',
        '2024-07-03 13:46:17', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208380587869470878', '5208375438522450078', 1, 2, '配置参数管理', null, '/setting/properties', 3, 1, 'iconify#carbon:parameter', null, 1,
        '2024-07-03 10:21:15', '000001', '2024-07-03 13:42:22', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208381474025242782', '5208375438522450078', 1, 2, '字典管理', null, '/setting/dict', 3, 2, 'iconify#streamline:dictionary-language-book', null, 1,
        '2024-07-03 10:22:08', '000001', '2024-07-03 13:46:13', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208382105167331486', '5208377102402846878', 1, 2, '缓存管理', null, '/ops/caching', 2, 1, 'iconify#octicon:cache-24', null, 1, '2024-07-03 10:22:45',
        '000001', '2024-07-03 13:46:08', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208384318786764958', null, 2, 1, '测试', null, '', 1, 1, 'up-circle', null, 1, '2024-07-03 10:24:57', '000001', '2024-07-03 10:24:57', '000001', 1,
        1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5208384955029127326', '5208384318786764958', 2, 2, '测试下级', null, '/test', 2, 1, 'question-circle', null, 1, '2024-07-03 10:25:35', '000001',
        '2024-07-03 10:25:35', '000001', 1, 1);
INSERT INTO darkblue.tb_sys_menu (menu_id, parent_id, platform, menu_type, menu_name, url_name, menu_url, level, sort_num, menu_icon, remark, is_enable,
                                  create_time, create_user, update_time, update_user, is_visible, is_production_visible)
VALUES ('5227216727657414811', '5208361429043773627', 1, 2, '自定义组件', null, '/dashboard/custom-components', 2, 2, 'form', null, 1, '2024-07-16 10:13:16',
        '000001', '2024-07-16 10:13:16', '000001', 1, 1);

INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5221631378507956214', '5208366805017428214', 1, '添加用户', 'USER_ADD', '2024-07-12 10:12:42', '000001', '2024-07-12 13:44:44', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5221631378507956314', '5208366805017428214', 1, '用户更新', 'USER_UPDATE', '2024-07-12 13:44:44', '000001', '2024-07-12 13:44:44', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5221633934751694938', '5208366805017428214', 1, '用户删除', 'USER_DELETE', '2024-07-12 13:47:16', '000001', '2024-07-12 13:47:16', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5221638644887978074', '5208384955029127326', 2, '测试权限', 'test-code', '2024-07-12 13:51:57', '000001', '2024-07-12 13:51:57', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5221639660731629658', '5208366805017428214', 1, '用户详情', 'USER_DETAILS', '2024-07-12 13:52:57', '000001', '2024-07-12 13:52:57', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5221641410544926810', '5208366805017428214', 1, '用户状态修改（启用/停用）', 'USER_STATE_CHANGE', '2024-07-12 13:54:41', '000001', '2024-07-12 13:54:41',
        '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230049801768272044', '5208366805017428214', 1, '重置密码', 'USER_PASSWORD_RESET', '2024-07-18 09:07:41', '000001', '2024-07-18 09:07:41', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230054400336068780', '5208367148061163766', 1, '新增用户组', 'USER_GROUP_ADD', '2024-07-18 09:12:15', '000001', '2024-07-18 09:12:15', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230054856777007276', '5208367148061163766', 1, '修改用户组', 'USER_GROUP_UPDATE', '2024-07-18 09:12:42', '000001', '2024-07-18 09:12:42', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230056219472822444', '5208367148061163766', 1, '删除用户组', 'USER_GROUP_DELETE', '2024-07-18 09:14:03', '000001', '2024-07-18 09:14:03', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230057120392544428', '5208367148061163766', 1, '为用户组分配角色', 'USER_GROUP_ASSIGN_ROLE', '2024-07-18 09:14:57', '000001', '2024-07-18 09:14:57',
        '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230057458503778476', '5208367148061163766', 1, '向用户组添加用户', 'USER_GROUP_ADD_USER', '2024-07-18 09:15:17', '000001', '2024-07-18 09:15:17',
        '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230058061325926572', '5208367148061163766', 1, '用户组查询', 'USER_GROUP_QUERY', '2024-07-18 09:15:53', '000001', '2024-07-18 09:15:53', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230058848009584812', '5208367148061163766', 1, '查看用户组详情', 'USER_GROUP_DETAILS', '2024-07-18 09:16:40', '000001', '2024-07-18 09:16:40',
        '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230059257390432428', '5208370996855177462', 1, '添加职位', 'POSITION_ADD', '2024-07-18 09:17:04', '000001', '2024-07-18 09:17:04', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230059750523142316', '5208370996855177462', 1, '更新职位', 'POSITION_UPDATE', '2024-07-18 09:17:34', '000001', '2024-07-18 09:17:34', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230070938493517996', '5208370996855177462', 1, '删除职位', 'POSITION_DELETE', '2024-07-18 09:28:40', '000001', '2024-07-18 09:28:40', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230077868758794412', '5208370996855177462', 1, '修改职位状态（禁用/启用）', 'POSITION_CHANGE_STATE', '2024-07-18 09:35:34', '000001',
        '2024-07-18 09:35:34', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230078255926608044', '5208370996855177462', 1, '职位详情', 'POSITION_DETAILS', '2024-07-18 09:35:57', '000001', '2024-07-18 09:35:57', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230078577243848876', '5208370996855177462', 1, '职位查询', 'POSITION_QUERY', '2024-07-18 09:36:16', '000001', '2024-07-18 09:36:16', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230079244792496300', '5208371439975006454', 1, '添加角色', 'PEM_ROLE_ADD', '2024-07-18 09:36:56', '000001', '2024-07-19 17:25:18', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230079433485844652', '5208371439975006454', 1, '修改角色', 'PEM_ROLE_UPDATE', '2024-07-18 09:37:07', '000001', '2024-07-19 17:26:08', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230080117476163756', '5208371439975006454', 1, '删除角色', 'PEM_ROLE_DELETE', '2024-07-18 09:37:48', '000001', '2024-07-19 17:26:11', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230083441059430572', '5208371439975006454', 1, '修改角色状态（启用/禁用）', 'PEM_ROLE_CHANGE_STATE', '2024-07-18 09:41:06', '000001',
        '2024-07-19 17:26:15', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230083861630681260', '5208371439975006454', 1, '为角色分配权限', 'PEM_ROLE_ASSIGN_PERMISSIONS', '2024-07-18 09:41:31', '000001',
        '2024-07-19 17:26:19', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230084130569453740', '5208371439975006454', 1, '角色详情', 'PEM_ROLE_DETAILS', '2024-07-18 09:41:47', '000001', '2024-07-19 17:26:23', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230084405514469548', '5208371439975006454', 1, '角色查询', 'PEM_ROLE_QUERY', '2024-07-18 09:42:03', '000001', '2024-07-19 17:26:28', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230085469810720940', '5208368791020372214', 1, '添加菜单', 'MENU_ADD', '2024-07-18 09:43:07', '000001', '2024-07-18 09:43:07', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230085696521240748', '5208368791020372214', 1, '修改菜单', 'MENU_UPDATE', '2024-07-18 09:43:20', '000001', '2024-07-18 09:43:20', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230086009181438124', '5208368791020372214', 1, '菜单删除', 'MENU_DELETE', '2024-07-18 09:43:39', '000001', '2024-07-18 09:43:39', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230086727615381676', '5208368791020372214', 1, '菜单状态修改', 'MENU_CHANGE_STATE', '2024-07-18 09:44:22', '000001', '2024-07-18 09:44:22', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230087315807797420', '5208368791020372214', 1, '菜单查询', 'MENU_QUERY', '2024-07-18 09:44:57', '000001', '2024-07-18 09:44:57', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230087795803947180', '5208369071787081974', 1, '添加权限', 'PERMISSION_ADD', '2024-07-18 09:45:25', '000001', '2024-07-18 09:45:25', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230088035986571436', '5208369071787081974', 1, '修改权限', 'PERMISSION_UPDATE', '2024-07-18 09:45:40', '000001', '2024-07-18 09:45:40', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230088326047858860', '5208369071787081974', 1, '删除权限', 'PERMISSION_DELETE', '2024-07-18 09:45:57', '000001', '2024-07-18 09:45:57', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230088652079497388', '5208369071787081974', 1, '绑定资源', 'PERMISSION_BIND_RESOURCE', '2024-07-18 09:46:16', '000001', '2024-07-18 09:46:16',
        '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230088946368643244', '5208369071787081974', 1, '权限详情', 'PERMISSION_DETAILS', '2024-07-18 09:46:34', '000001', '2024-07-18 09:46:34', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230089342730371244', '5208369071787081974', 1, '权限查询', 'PERMISSION_QUERY', '2024-07-18 09:46:57', '000001', '2024-07-18 09:46:57', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230090227560743084', '5208366805017428214', 1, '添加部门', 'DEPARTMENT_ADD', '2024-07-18 09:47:50', '000001', '2024-07-18 09:47:50', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230090909437132972', '5208366805017428214', 1, '更新部门', 'DEPARTMENT_UPDATE', '2024-07-18 09:48:31', '000001', '2024-07-18 09:48:31', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230091173191745708', '5208366805017428214', 1, '删除部门', 'DEPARTMENT_DELETE', '2024-07-18 09:48:47', '000001', '2024-07-18 09:48:47', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230092540987834540', '5208372564820230390', 1, '添加资源组', 'RESOURCE_GROUP_ADD', '2024-07-18 09:50:08', '000001', '2024-07-18 09:50:08', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230093403303182508', '5208372564820230390', 1, '更新资源组', 'RESOURCE_GROUP_UPDATE', '2024-07-18 09:50:59', '000001', '2024-07-18 09:50:59',
        '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230093683482689708', '5208372564820230390', 1, '删除资源组', 'RESOURCE_GROUP_DELETE', '2024-07-18 09:51:16', '000001', '2024-07-18 09:51:16',
        '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230093936114008236', '5208372564820230390', 1, '添加资源', 'RESOURCE_ADD', '2024-07-18 09:51:31', '000001', '2024-07-18 09:51:31', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230094124371148972', '5208372564820230390', 1, '更新资源', 'RESOURCE_UPDATE', '2024-07-18 09:51:42', '000001', '2024-07-18 09:51:42', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230094304222904492', '5208372564820230390', 1, '删除资源', 'RESOURCE_DELETE', '2024-07-18 09:51:53', '000001', '2024-07-18 09:51:53', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230096332537987244', '5208372564820230390', 1, '为资源分配权限', 'RESOURCE_ASSIGN_PERMISSION', '2024-07-18 09:53:54', '000001', '2024-07-18 09:53:54',
        '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230096604949643436', '5208372564820230390', 1, '资源查询', 'RESOURCE_QUERY', '2024-07-18 09:54:10', '000001', '2024-07-18 09:54:10', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230098361691930796', '5208380587869470878', 1, '添加参数', 'PROPERTY_ADD', '2024-07-18 09:55:55', '000001', '2024-07-18 09:55:55', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230098547063390380', '5208380587869470878', 1, '修改参数', 'PROPERTY_UPDATE', '2024-07-18 09:56:06', '000001', '2024-07-18 09:56:06', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230098888328741036', '5208380587869470878', 1, '删除参数', 'PROPERTY_DELETE', '2024-07-18 09:56:26', '000001', '2024-07-18 09:56:26', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230099186275319980', '5208380587869470878', 1, '修改参数值', 'PROPERTY_SET_VALUE', '2024-07-18 09:56:44', '000001', '2024-07-18 09:56:44', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230100061022585004', '5208380587869470878', 1, '查询参数', 'PROPERTY_QUERY', '2024-07-18 09:57:36', '000001', '2024-07-18 09:57:36', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230100327696433324', '5208381474025242782', 1, '添加字典', 'DICT_ADD', '2024-07-18 09:57:52', '000001', '2024-07-18 09:57:52', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230100451394846892', '5208381474025242782', 1, '修改字典', 'DICT_UPDATE', '2024-07-18 09:58:00', '000001', '2024-07-18 09:58:00', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230100629652766892', '5208381474025242782', 1, '删除字典', 'DICT_DELETE', '2024-07-18 09:58:10', '000001', '2024-07-18 09:58:10', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230101186220130476', '5208381474025242782', 1, '添加字典项', 'DICT_ITEM_ADD', '2024-07-18 09:58:43', '000001', '2024-07-18 09:58:43', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230101319297007788', '5208381474025242782', 1, '修改字典项', 'DICT_ITEM_UPDATE', '2024-07-18 09:58:51', '000001', '2024-07-18 09:58:51', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230101510876037292', '5208381474025242782', 1, '删除字典项', 'DICT_ITEM_DELETE', '2024-07-18 09:59:03', '000001', '2024-07-18 09:59:03', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230101894403195052', '5208381474025242782', 1, '修改字典项状态（启用/禁用）', 'DICT_ITEM_CHANGE_STATE', '2024-07-18 09:59:26', '000001',
        '2024-07-18 09:59:26', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230102374516785324', '5208381474025242782', 1, '字典项查询', 'DICT_ITEM_QUERY', '2024-07-18 09:59:54', '000001', '2024-07-18 09:59:54', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230102903200415916', '5208382105167331486', 1, '缓存信息查询', 'CACHE_QUERY', '2024-07-18 10:00:26', '000001', '2024-07-18 10:00:57', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230103320919539884', '5208382105167331486', 1, '刷新缓存信息', 'CACHE_REFRESH', '2024-07-18 10:00:51', '000001', '2024-07-18 10:00:51', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230103776488063148', '5208378615657398430', 1, '登录日志查询', 'LOGIN_LOG_QUERY', '2024-07-18 10:01:18', '000001', '2024-07-18 10:01:18', '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230104091195080876', '5208379459048046750', 1, '操作日志查询', 'OPERATION_LOG_QUERY', '2024-07-18 10:01:36', '000001', '2024-07-18 10:01:36',
        '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5230427131506327724', '5208367148061163766', 1, '修改用户组状态', 'USER_GROUP_CHANGE_STATE', '2024-07-18 15:22:31', '000001', '2024-07-18 15:22:31',
        '000001');
INSERT INTO darkblue.tb_sys_permission (permission_id, menu_id, platform, permission_name, permission_code, create_time, create_user, update_time, update_user)
VALUES ('5231949048956584108', '5208372564820230390', 1, '批量添加资源', 'RESOURCE_BATCH_ADD', '2024-07-19 16:34:25', '000001', '2024-07-19 16:34:25',
        '000001');

INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5232019132337094828', '5230102903200415916', '5230132092217589799', '2024-07-19 17:44:02', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5232019278735081644', '5230103320919539884', '5230132091932377127', '2024-07-19 17:44:11', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5232019735226351788', '5230100327696433324', '5230132063008456743', '2024-07-19 17:44:38', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5232019994535002284', '5230100451394846892', '5230132062689689639', '2024-07-19 17:44:53', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5232020085786280108', '5230100629652766892', '5230132063344001063', '2024-07-19 17:44:59', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5232021345855865004', '5230101186220130476', '5230132061599170599', '2024-07-19 17:46:14', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5232021433365823660', '5230101319297007788', '5230132060743532583', '2024-07-19 17:46:19', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5232027477559214252', '5230101510876037292', '5230132061196517415', '2024-07-19 17:52:19', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5232027703883858092', '5230101894403195052', '5230132060307324967', '2024-07-19 17:52:33', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5232029109193801900', '5230102374516785324', '5230132062018600999', '2024-07-19 17:53:57', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5232029109227356332', '5230102374516785324', '5230132059770454055', '2024-07-19 17:53:57', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235921914844676217', '5221631378507956214', '5230132089952665639', '2024-07-22 10:21:06', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235922016380387449', '5221631378507956314', '5230132089331908647', '2024-07-22 10:21:12', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235922096139272313', '5221633934751694938', '5230132090757972007', '2024-07-22 10:21:17', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235922208294961273', '5221641410544926810', '5230132091210956839', '2024-07-22 10:21:23', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235922340600086649', '5230090227560743084', '5230132057388089383', '2024-07-22 10:21:31', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235922440223195257', '5230090909437132972', '5230132057002213415', '2024-07-22 10:21:37', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235922503238418553', '5230091173191745708', '5230132057857851431', '2024-07-22 10:21:41', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235924501572616313', '5230054400336068780', '5230132087570300967', '2024-07-22 10:23:40', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235924568782143609', '5230056219472822444', '5230132087771627559', '2024-07-22 10:23:44', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235924703536742521', '5230054856777007276', '5230132087134093351', '2024-07-22 10:23:52', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235924853491499129', '5230057120392544428', '5230132085322154023', '2024-07-22 10:24:01', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235924968214102137', '5230057458503778476', '5230132084953055271', '2024-07-22 10:24:08', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235925389657768057', '5230058061325926572', '5230132084701397031', '2024-07-22 10:24:33', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235925651600441465', '5230427131506327724', '5230132085942911015', '2024-07-22 10:24:49', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235928028663513209', '5221639660731629658', '5230132088392384551', '2024-07-22 10:27:10', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235928028814508153', '5221639660731629658', '5230132064451297319', '2024-07-22 10:27:10', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235929824144392313', '5230058848009584812', '5230132085590589479', '2024-07-22 10:28:57', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235929824228278393', '5230058848009584812', '5230132086395895847', '2024-07-22 10:28:57', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235929824345718905', '5230058848009584812', '5230132086714662951', '2024-07-22 10:28:57', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235937848569364601', '5230059257390432428', '5230132072739242023', '2024-07-22 10:36:56', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235937927019626617', '5230059750523142316', '5230132072470806567', '2024-07-22 10:37:00', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235938016526073977', '5230070938493517996', '5230132073007677479', '2024-07-22 10:37:06', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235938136885821561', '5230077868758794412', '5230132073779429415', '2024-07-22 10:37:13', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235938284760203385', '5230078255926608044', '5230132072101707815', '2024-07-22 10:37:22', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235938440117223545', '5230078577243848876', '5230132073376776231', '2024-07-22 10:37:31', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235942365314678905', '5230079244792496300', '5230132080909746215', '2024-07-22 10:41:25', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235942452942078073', '5230079433485844652', '5230132080725196839', '2024-07-22 10:41:30', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235942526342398073', '5230080117476163756', '5230132081144627239', '2024-07-22 10:41:34', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235942615513301113', '5230083441059430572', '5230132081933156391', '2024-07-22 10:41:40', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235942965989343353', '5230084130569453740', '5230132079785672743', '2024-07-22 10:42:01', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235942966090006649', '5230084130569453740', '5230132080372875303', '2024-07-22 10:42:01', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235943055411904633', '5230084405514469548', '5230132071749386279', '2024-07-22 10:42:06', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235943055495790713', '5230084405514469548', '5230132079433351207', '2024-07-22 10:42:06', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235943391644090489', '5230085469810720940', '5230132067420864551', '2024-07-22 10:42:26', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235943492139614329', '5230085696521240748', '5230132067185983527', '2024-07-22 10:42:32', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235943575857922169', '5230086009181438124', '5230132067722854439', '2024-07-22 10:42:37', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235943718044827769', '5230086727615381676', '5230132068175839271', '2024-07-22 10:42:45', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235943794314051705', '5230087315807797420', '5230132066682667047', '2024-07-22 10:42:50', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235943794397937785', '5230087315807797420', '5230132066380677159', '2024-07-22 10:42:50', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235945291093377145', '5230083861630681260', '5230132081463394343', '2024-07-22 10:44:19', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235945291177263225', '5230083861630681260', '5230132066934325287', '2024-07-22 10:44:19', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235945291311480953', '5230083861630681260', '5230132069820006439', '2024-07-22 10:44:19', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235946010953384057', '5230087795803947180', '5230132070658867239', '2024-07-22 10:45:02', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235946079102435449', '5230088035986571436', '5230132070474317863', '2024-07-22 10:45:06', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235946169397411961', '5230088326047858860', '5230132070893748263', '2024-07-22 10:45:11', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235946393843007609', '5230088652079497388', '5230132069081808935', '2024-07-22 10:45:25', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235946393910116473', '5230088652079497388', '5230132068779819047', '2024-07-22 10:45:25', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235946761633136761', '5230088946368643244', '5230132070272991271', '2024-07-22 10:45:47', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235946761700245625', '5230088946368643244', '5230132069383798823', '2024-07-22 10:45:47', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235946863907045497', '5230089342730371244', '5230132070054887463', '2024-07-22 10:45:53', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235966404884168771', '5230092540987834540', '5230132078107951143', '2024-07-22 11:05:18', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235966741837774915', '5230093403303182508', '5230132077671743527', '2024-07-22 11:05:38', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235966882699280451', '5230093683482689708', '5230132078544158759', '2024-07-22 11:05:46', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235967216481992771', '5230093936114008236', '5230132075809472551', '2024-07-22 11:06:06', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235967316943962179', '5230094124371148972', '5230132075574591527', '2024-07-22 11:06:12', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235967380663828547', '5230094304222904492', '5230132076413452327', '2024-07-22 11:06:16', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235967516710273091', '5230096332537987244', '5230132076832882727', '2024-07-22 11:06:24', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235967716258480195', '5230096604949643436', '5230132074467295271', '2024-07-22 11:06:36', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235967905102823491', '5230103776488063148', '5230132064451297319', '2024-07-22 11:06:47', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235968129330315331', '5230104091195080876', '5230132065340489767', '2024-07-22 11:07:00', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235968129447755843', '5230104091195080876', '5230132065692811303', '2024-07-22 11:07:00', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235968376458707011', '5230098361691930796', '5230132083375996967', '2024-07-22 11:07:15', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235968473934331971', '5230098547063390380', '5230132083141115943', '2024-07-22 11:07:21', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235968531597623363', '5230098888328741036', '5230132083677986855', '2024-07-22 11:07:24', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235968603236335683', '5230099186275319980', '5230132082839126055', '2024-07-22 11:07:29', '000001');
INSERT INTO darkblue.tb_sys_permission_resource (permission_resource_id, permission_id, resource_id, create_time, create_user)
VALUES ('5235968649189130307', '5230100061022585004', '5230132082570690599', '2024-07-22 11:07:31', '000001');

INSERT INTO darkblue.tb_sys_position (position_id, position_code, position_name, is_enable, is_built_in, is_del, create_time, create_user, update_time,
                                      update_user)
VALUES ('5218747943439302808', 'Developer', '开发人员', 0, 0, 0, '2024-07-10 14:00:17', '000001', '2024-07-17 10:51:04', '000001');
INSERT INTO darkblue.tb_sys_position (position_id, position_code, position_name, is_enable, is_built_in, is_del, create_time, create_user, update_time,
                                      update_user)
VALUES ('5218751406776778904', 'Boss', '老板', 1, 0, 0, '2024-07-10 14:03:44', '000001', '2024-07-10 14:03:44', '000001');
INSERT INTO darkblue.tb_sys_position (position_id, position_code, position_name, is_enable, is_built_in, is_del, create_time, create_user, update_time,
                                      update_user)
VALUES ('5228697730309685435', 'TEST1', '测试', 1, 0, 0, '2024-07-17 10:44:31', '000001', '2024-07-17 10:46:54', '000001');

INSERT INTO darkblue.tb_sys_property_setting (property_id, property_code, property_name, remark, type, value_scope, default_value, value, unit, create_time,
                                              create_user, update_time, update_user)
VALUES ('5217424516057137224', 'accessTokenTTL', 'Access Token生命周期',
        '登录凭证的有效期，登录凭证的有效期会在使用系统的过程中自动刷新，此有效期是指在多长时间内没有使用系统，凭证作废', 2, '{
    "max": 7200,
    "min": 60
  }', '720', '721', '分钟', null, null, null, null);
INSERT INTO darkblue.tb_sys_property_setting (property_id, property_code, property_name, remark, type, value_scope, default_value, value, unit, create_time,
                                              create_user, update_time, update_user)
VALUES ('5217434303280971959', 'theme.list', '主题', '主题', 7, '[
  "dark",
  "cyan",
  "light"
]', 'light', 'light', null, null, null, null, null);
INSERT INTO darkblue.tb_sys_property_setting (property_id, property_code, property_name, remark, type, value_scope, default_value, value, unit, create_time,
                                              create_user, update_time, update_user)
VALUES ('5217509050677198910', 'user.type.default', '默认用户类型', '注册用户的默认用户类型', 8, '[
  {
    "label": "普通用户",
    "value": 1
  },
  {
    "label": "普通会员",
    "value": 2
  },
  {
    "label": "黄金会员",
    "value": 3
  },
  {
    "label": "铂金会员",
    "value": 4
  }
]', '2', '2', null, null, null, null, null);

INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132055861362727', '5230132053915205671', '单个获取', '/api/department/getOne/*', 'GET',
        'org.dblue.application.module.department.adpater.controller.DepartmentController', 'getOne', 1, 1, '2024-07-18 10:29:23', null, '2024-07-18 10:30:52',
        '000001');
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132057002213415', '5230132053915205671', '更新', '/api/department/update', 'PUT',
        'org.dblue.application.module.department.adpater.controller.DepartmentController', 'update', 0, 1, '2024-07-18 10:29:23', null, '2024-07-18 10:29:23',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132057388089383', '5230132053915205671', '新增', '/api/department/add', 'POST',
        'org.dblue.application.module.department.adpater.controller.DepartmentController', 'add', 0, 1, '2024-07-18 10:29:23', null, '2024-07-18 10:29:23',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132057857851431', '5230132053915205671', '删除', '/api/department/delete/*', 'DELETE',
        'org.dblue.application.module.department.adpater.controller.DepartmentController', 'delete', 0, 1, '2024-07-18 10:29:23', null, '2024-07-18 10:29:23',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132058562494503', '5230132053915205671', '获取部门树', '/api/department/getAll', 'GET',
        'org.dblue.application.module.department.adpater.controller.DepartmentController', 'getAll', 0, 1, '2024-07-18 10:29:23', null, '2024-07-18 10:29:23',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132059770454055', '5230132058998702119', '字典项分页', '/api/dictionary/page', 'GET',
        'org.dblue.application.module.dictionary.adapter.controller.DictionaryController', 'page', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132060307324967', '5230132058998702119', '字典项启用禁用', '/api/dictionary/enableItem', 'PATCH',
        'org.dblue.application.module.dictionary.adapter.controller.DictionaryController', 'enableItem', 0, 1, '2024-07-18 10:29:24', null,
        '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132060743532583', '5230132058998702119', '字典项更新', '/api/dictionary/updateItem', 'PUT',
        'org.dblue.application.module.dictionary.adapter.controller.DictionaryController', 'updateItem', 0, 1, '2024-07-18 10:29:24', null,
        '2024-07-19 17:45:50', '000001');
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132061196517415', '5230132058998702119', '字典项删除', '/api/dictionary/deleteItem/*', 'DELETE',
        'org.dblue.application.module.dictionary.adapter.controller.DictionaryController', 'deleteItem', 0, 1, '2024-07-18 10:29:24', null,
        '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132061599170599', '5230132058998702119', '字典项添加', '/api/dictionary/addItem', 'POST',
        'org.dblue.application.module.dictionary.adapter.controller.DictionaryController', 'addItem', 0, 1, '2024-07-18 10:29:24', null, '2024-07-19 17:45:41',
        '000001');
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132062018600999', '5230132058998702119', '获取树形字典项', '/api/dictionary/getItemTree/*', 'GET',
        'org.dblue.application.module.dictionary.adapter.controller.DictionaryController', 'getItemTree', 0, 1, '2024-07-18 10:29:24', null,
        '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132062404476967', '5230132058998702119', '获取字典数据（用于Select、SelectTree组件）', '/api/dictionary/getDictionaryForSelect', 'GET',
        'org.dblue.application.module.dictionary.adapter.controller.DictionaryController', 'getDictionaryForSelect', 1, 1, '2024-07-18 10:29:24', null,
        '2024-07-22 10:40:51', '000001');
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132062689689639', '5230132058998702119', '字典更新', '/api/dictionary/update', 'PUT',
        'org.dblue.application.module.dictionary.adapter.controller.DictionaryController', 'update', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132063008456743', '5230132058998702119', '字典添加', '/api/dictionary/add', 'POST',
        'org.dblue.application.module.dictionary.adapter.controller.DictionaryController', 'add', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132063344001063', '5230132058998702119', '字典删除', '/api/dictionary/delete/*', 'DELETE',
        'org.dblue.application.module.dictionary.adapter.controller.DictionaryController', 'delete', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132063763431463', '5230132058998702119', '获取全部字典信息', '/api/dictionary/getAll', 'GET',
        'org.dblue.application.module.dictionary.adapter.controller.DictionaryController', 'getAll', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132064451297319', '5230132063981535271', '登录日志分页查询', '/api/login-log/findByPage', 'GET',
        'org.dblue.application.module.logs.adapter.controller.LoginLogController', 'findByPage', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132065340489767', '5230132064686178343', '分页查询操作日志', '/api/operation-log/findByPage', 'GET',
        'org.dblue.application.module.logs.adapter.controller.OperationLogController', 'findByPage', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132065692811303', '5230132064686178343', '获取操作日志中的错误详情', '/api/operation-log/getErrorDetails/*', 'GET',
        'org.dblue.application.module.logs.adapter.controller.OperationLogController', 'getErrorDetails', 0, 1, '2024-07-18 10:29:24', null,
        '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132066380677159', '5230132065927692327', '获取所有APP端菜单', '/api/menu/findAllAppMenus', 'GET',
        'org.dblue.application.module.menu.adapter.controller.MenuController', 'findAllAppMenus', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132066682667047', '5230132065927692327', '获取所有PC端菜单', '/api/menu/findAllPcMenus', 'GET',
        'org.dblue.application.module.menu.adapter.controller.MenuController', 'findAllPcMenus', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132066934325287', '5230132065927692327', '根据角色ID获取菜单多选框树', '/api/menu/getMenuCheckBoxTree', 'GET',
        'org.dblue.application.module.menu.adapter.controller.MenuController', 'getMenuCheckBoxTree', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132067185983527', '5230132065927692327', '菜单更新', '/api/menu/update', 'PUT',
        'org.dblue.application.module.menu.adapter.controller.MenuController', 'update', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132067420864551', '5230132065927692327', '菜单添加', '/api/menu/add', 'POST',
        'org.dblue.application.module.menu.adapter.controller.MenuController', 'add', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132067722854439', '5230132065927692327', '菜单删除', '/api/menu/delete/*', 'DELETE',
        'org.dblue.application.module.menu.adapter.controller.MenuController', 'delete', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132068175839271', '5230132065927692327', '菜单启用禁用', '/api/menu/enable', 'PATCH',
        'org.dblue.application.module.menu.adapter.controller.MenuController', 'enable', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132068779819047', '5230132068377165863', '绑定资源', '/api/permission/setResource', 'POST',
        'org.dblue.application.module.permission.adapter.controller.PermissionController', 'setResource', 0, 1, '2024-07-18 10:29:24', null,
        '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132069081808935', '5230132068377165863', '获取权限资源信息', '/api/permission/findPermissionResource/*', 'GET',
        'org.dblue.application.module.permission.adapter.controller.PermissionController', 'findPermissionResource', 0, 1, '2024-07-18 10:29:24', null,
        '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132069383798823', '5230132068377165863', '获取权限关联的角色', '/api/permission/findPermissionRoles', 'GET',
        'org.dblue.application.module.permission.adapter.controller.PermissionController', 'findPermissionRoles', 0, 1, '2024-07-18 10:29:24', null,
        '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132069820006439', '5230132068377165863', '获取权限信息并标记是否选中', '/api/permission/getPermissionCheckBox', 'GET',
        'org.dblue.application.module.permission.adapter.controller.PermissionController', 'getPermissionCheckBox', 0, 1, '2024-07-18 10:29:24', null,
        '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132070054887463', '5230132068377165863', '分页查询权限信息', '/api/permission/findByPage', 'GET',
        'org.dblue.application.module.permission.adapter.controller.PermissionController', 'findByPage', 0, 1, '2024-07-18 10:29:24', null,
        '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132070272991271', '5230132068377165863', '权限信息', '/api/permission/getOne/*', 'GET',
        'org.dblue.application.module.permission.adapter.controller.PermissionController', 'getOne', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132070474317863', '5230132068377165863', '权限更新', '/api/permission/update', 'PUT',
        'org.dblue.application.module.permission.adapter.controller.PermissionController', 'update', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132070658867239', '5230132068377165863', '权限添加', '/api/permission/add', 'POST',
        'org.dblue.application.module.permission.adapter.controller.PermissionController', 'add', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132070893748263', '5230132068377165863', '权限删除', '/api/permission/delete/*', 'DELETE',
        'org.dblue.application.module.permission.adapter.controller.PermissionController', 'delete', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132071749386279', '5230132071162183719', '分页', '/api/position/page', 'GET',
        'org.dblue.application.module.position.adapter.controller.PositionController', 'page', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132072101707815', '5230132071162183719', '职位单个查询', '/api/position/getOne/*', 'GET',
        'org.dblue.application.module.position.adapter.controller.PositionController', 'getOne', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132072470806567', '5230132071162183719', '职位更新', '/api/position/update', 'PUT',
        'org.dblue.application.module.position.adapter.controller.PositionController', 'update', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132072739242023', '5230132071162183719', '职位添加', '/api/position/add', 'POST',
        'org.dblue.application.module.position.adapter.controller.PositionController', 'add', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132073007677479', '5230132071162183719', '职位删除', '/api/position/delete/*', 'DELETE',
        'org.dblue.application.module.position.adapter.controller.PositionController', 'delete', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132073376776231', '5230132071162183719', '查询所有职位信息', '/api/position/findAll', 'GET',
        'org.dblue.application.module.position.adapter.controller.PositionController', 'findAll', 1, 1, '2024-07-18 10:29:24', null, '2024-07-22 10:40:00',
        '000001');
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132073779429415', '5230132071162183719', '启用禁用', '/api/position/enable', 'PATCH',
        'org.dblue.application.module.position.adapter.controller.PositionController', 'enable', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132074467295271', '5230132074081419303', '分页查询', '/api/resource/page', 'GET',
        'org.dblue.application.module.resource.adapter.controller.ResourceController', 'page', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132074836394023', '5230132074081419303', '获取资源信息', '/api/resource/getResourceController', 'GET',
        'org.dblue.application.module.resource.adapter.controller.ResourceController', 'getResourceController', 0, 1, '2024-07-18 10:29:24', null,
        '2024-07-18 10:29:24', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132075574591527', '5230132074081419303', '资源更新', '/api/resource/update', 'PUT',
        'org.dblue.application.module.resource.adapter.controller.ResourceController', 'update', 0, 1, '2024-07-18 10:29:24', null, '2024-07-18 10:29:24',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132075809472551', '5230132074081419303', '资源添加', '/api/resource/add', 'POST',
        'org.dblue.application.module.resource.adapter.controller.ResourceController', 'add', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132076413452327', '5230132074081419303', '资源删除', '/api/resource/delete/*', 'DELETE',
        'org.dblue.application.module.resource.adapter.controller.ResourceController', 'delete', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132076832882727', '5230132074081419303', '设置权限', '/api/resource/setPermission', 'POST',
        'org.dblue.application.module.resource.adapter.controller.ResourceController', 'setPermission', 0, 1, '2024-07-18 10:29:25', null,
        '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132077671743527', '5230132077050986535', '资源组更新', '/api/resource/group/update', 'POST',
        'org.dblue.application.module.resource.adapter.controller.ResourceGroupController', 'update', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132078107951143', '5230132077050986535', '资源组添加', '/api/resource/group/add', 'POST',
        'org.dblue.application.module.resource.adapter.controller.ResourceGroupController', 'add', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132078544158759', '5230132077050986535', '资源组删除', '/api/resource/group/delete/*', 'DELETE',
        'org.dblue.application.module.resource.adapter.controller.ResourceGroupController', 'delete', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132078879703079', '5230132077050986535', '获取全部资源组信息', '/api/resource/group/getAll/*', 'GET',
        'org.dblue.application.module.resource.adapter.controller.ResourceGroupController', 'getAll', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132079433351207', '5230132079047475239', '分页查询角色信息', '/api/role/page', 'GET',
        'org.dblue.application.module.role.adapter.controller.RoleController', 'findByPage', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132079785672743', '5230132079047475239', '获取单个角色信息', '/api/role/getOne/*', 'GET',
        'org.dblue.application.module.role.adapter.controller.RoleController', 'getOne', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132080171548711', '5230132079047475239', '获取全部角色信息', '/api/role/getAllForSelect', 'GET',
        'org.dblue.application.module.role.adapter.controller.RoleController', 'getAllForSelect', 1, 1, '2024-07-18 10:29:25', null, '2024-07-22 10:40:07',
        '000001');
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132080372875303', '5230132079047475239', '分页查询角色关联的用户', '/api/role/findRefUsers', 'GET',
        'org.dblue.application.module.role.adapter.controller.RoleController', 'findRefUsers', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132080725196839', '5230132079047475239', '角色更新', '/api/role/update', 'PUT',
        'org.dblue.application.module.role.adapter.controller.RoleController', 'update', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132080909746215', '5230132079047475239', '角色添加', '/api/role/add', 'POST',
        'org.dblue.application.module.role.adapter.controller.RoleController', 'add', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132081144627239', '5230132079047475239', '角色删除', '/api/role/delete/*', 'DELETE',
        'org.dblue.application.module.role.adapter.controller.RoleController', 'delete', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132081463394343', '5230132079047475239', '设置权限', '/api/role/setPermission', 'POST',
        'org.dblue.application.module.role.adapter.controller.RoleController', 'setPermission', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132081933156391', '5230132079047475239', '启用禁用', '/api/role/enable', 'PATCH',
        'org.dblue.application.module.role.adapter.controller.RoleController', 'enable', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132082570690599', '5230132082117705767', '分页查询参数信息', '/api/property-setting/findByPage', 'GET',
        'org.dblue.application.module.setting.adapter.controller.PropertySettingController', 'findByPage', 0, 1, '2024-07-18 10:29:25', null,
        '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132082839126055', '5230132082117705767', '更新参数的值', '/api/property-setting/changeValue', 'PATCH',
        'org.dblue.application.module.setting.adapter.controller.PropertySettingController', 'changeValue', 0, 1, '2024-07-18 10:29:25', null,
        '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132083141115943', '5230132082117705767', '更新参数', '/api/property-setting/update', 'PUT',
        'org.dblue.application.module.setting.adapter.controller.PropertySettingController', 'update', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132083375996967', '5230132082117705767', '添加参数', '/api/property-setting/add', 'POST',
        'org.dblue.application.module.setting.adapter.controller.PropertySettingController', 'add', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132083677986855', '5230132082117705767', '删除参数', '/api/property-setting/delete/*', 'DELETE',
        'org.dblue.application.module.setting.adapter.controller.PropertySettingController', 'delete', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132084181303335', '5230132083879313447', '用户组用户删除', '/api/user/group/deleteUserRef/*/*', 'DELETE',
        'org.dblue.application.module.usergroup.adapter.controller.UserGroupController', 'deleteUser', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132084466516007', '5230132083879313447', '用户组角色删除', '/api/user/group/deleteRoleRef/*/*', 'DELETE',
        'org.dblue.application.module.usergroup.adapter.controller.UserGroupController', 'deleteRole', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132084701397031', '5230132083879313447', '分页', '/api/user/group/page', 'GET',
        'org.dblue.application.module.usergroup.adapter.controller.UserGroupController', 'page', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132084953055271', '5230132083879313447', '用户组用户添加', '/api/user/group/addUser', 'POST',
        'org.dblue.application.module.usergroup.adapter.controller.UserGroupController', 'addUser', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132085322154023', '5230132083879313447', '用户组角色添加', '/api/user/group/addRole', 'POST',
        'org.dblue.application.module.usergroup.adapter.controller.UserGroupController', 'addRole', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132085590589479', '5230132083879313447', '获取单独一个', '/api/user/group/getOne/*', 'GET',
        'org.dblue.application.module.usergroup.adapter.controller.UserGroupController', 'getOne', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132085942911015', '5230132083879313447', '用户组启用/禁用', '/api/user/group/toggleState', 'PATCH',
        'org.dblue.application.module.usergroup.adapter.controller.UserGroupController', 'toggleState', 0, 1, '2024-07-18 10:29:25', null,
        '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132086395895847', '5230132083879313447', '获取用户组对应的角色', '/api/user/group/findUserGroupRoles', 'GET',
        'org.dblue.application.module.usergroup.adapter.controller.UserGroupController', 'findUserGroupRoles', 0, 1, '2024-07-18 10:29:25', null,
        '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132086714662951', '5230132083879313447', '获取用户组对应的用户', '/api/user/group/findUserGroupUsers', 'GET',
        'org.dblue.application.module.usergroup.adapter.controller.UserGroupController', 'findUserGroupUsers', 0, 1, '2024-07-18 10:29:25', null,
        '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132087134093351', '5230132083879313447', '用户组更新', '/api/user/group/update', 'PUT',
        'org.dblue.application.module.usergroup.adapter.controller.UserGroupController', 'update', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132087570300967', '5230132083879313447', '用户组添加', '/api/user/group/add', 'POST',
        'org.dblue.application.module.usergroup.adapter.controller.UserGroupController', 'add', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132087771627559', '5230132083879313447', '用户组删除', '/api/user/group/delete/*', 'DELETE',
        'org.dblue.application.module.usergroup.adapter.controller.UserGroupController', 'delete', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25',
        null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132088140726311', '5230132087889068071', '分页查询', 'api/user/page', 'GET', 'org.dblue.application.module.user.adapter.controller.UserController',
        'page', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132088392384551', '5230132087889068071', '单个信息获取', 'api/user/getOne/*', 'GET',
        'org.dblue.application.module.user.adapter.controller.UserController', 'getOne', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132088727928871', '5230132087889068071', '下拉列表使用', 'api/user/selectByNameOrUserName', 'GET',
        'org.dblue.application.module.user.adapter.controller.UserController', 'selectByNameOrUserName', 1, 1, '2024-07-18 10:29:25', null,
        '2024-07-18 14:06:10', '000001');
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132089029918759', '5230132087889068071', '获取用户菜单权限', 'api/user/getUserMenu/pc', 'GET',
        'org.dblue.application.module.user.adapter.controller.UserController', 'getUserMenu', 1, 1, '2024-07-18 10:29:25', null, '2024-07-18 14:06:20',
        '000001');
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132089331908647', '5230132087889068071', '用户更新', 'api/user/update', 'PUT',
        'org.dblue.application.module.user.adapter.controller.UserController', 'update', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132089952665639', '5230132087889068071', '用户添加', 'api/user/add', 'POST', 'org.dblue.application.module.user.adapter.controller.UserController',
        'add', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132090757972007', '5230132087889068071', '用户删除', 'api/user/delete/*', 'DELETE',
        'org.dblue.application.module.user.adapter.controller.UserController', 'delete', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132091210956839', '5230132087889068071', '用户启用/禁用', 'api/user/enable', 'PATCH',
        'org.dblue.application.module.user.adapter.controller.UserController', 'enable', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132091932377127', '5230132091529723943', '刷新缓存', '/api/ops/caching/refreshCache', 'GET', 'org.dblue.core.caching.controller.CachingController',
        'refreshCache', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);
INSERT INTO darkblue.tb_sys_resource (resource_id, resource_group_id, resource_name, resource_url, request_method, controller, method, is_authed_access,
                                      platform, create_time, create_user, update_time, update_user)
VALUES ('5230132092217589799', '5230132091529723943', '获取缓存列表', '/api/ops/caching/getCacheList', 'GET',
        'org.dblue.core.caching.controller.CachingController', 'getCacheList', 0, 1, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);

INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132053915205671', '部门管理', 1, 1, '2024-07-18 10:29:23', null, '2024-07-18 10:33:11', '000001');
INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132058998702119', '字典管理', 1, 32, '2024-07-18 10:29:24', null, '2024-07-18 13:55:57', '000001');
INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132063981535271', '登录日志', 1, 21, '2024-07-18 10:29:24', null, '2024-07-18 13:55:31', '000001');
INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132064686178343', '操作日志', 1, 22, '2024-07-18 10:29:24', null, '2024-07-18 13:55:38', '000001');
INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132065927692327', '菜单管理', 1, 6, '2024-07-18 10:29:24', null, '2024-07-18 13:53:32', '000001');
INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132068377165863', '权限管理', 1, 7, '2024-07-18 10:29:24', null, '2024-07-18 13:53:45', '000001');
INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132071162183719', '职位管理', 1, 4, '2024-07-18 10:29:24', null, '2024-07-18 10:32:37', '000001');
INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132074081419303', '资源管理', 1, 9, '2024-07-18 10:29:24', null, '2024-07-18 13:56:27', '000001');
INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132077050986535', '资源组管理', 1, 8, '2024-07-18 10:29:25', null, '2024-07-18 13:56:46', '000001');
INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132079047475239', '角色管理', 1, 5, '2024-07-18 10:29:25', null, '2024-07-18 13:52:05', '000001');
INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132082117705767', '系统参数配置', 1, 31, '2024-07-18 10:29:25', null, '2024-07-18 13:55:47', '000001');
INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132083879313447', '用户组管理', 1, 3, '2024-07-18 10:29:25', null, '2024-07-18 10:32:26', '000001');
INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132087889068071', '用户管理', 1, 2, '2024-07-18 10:29:25', null, '2024-07-18 10:32:05', '000001');
INSERT INTO darkblue.tb_sys_resource_group (resource_group_id, group_name, platform, sort_num, create_time, create_user, update_time, update_user)
VALUES ('5230132091529723943', '缓存处理', 1, 99, '2024-07-18 10:29:25', null, '2024-07-18 10:29:25', null);

INSERT INTO darkblue.tb_sys_role (role_id, role_name, role_code, remark, is_enable, is_built_in, create_time, create_user, update_time, update_user)
VALUES ('1', '超级管理员', 'ADMIN', '拥有系统内所有权限', 0, 1, '2024-07-12 17:31:50', null, '2024-07-22 11:27:32', '000001');
INSERT INTO darkblue.tb_sys_role (role_id, role_name, role_code, remark, is_enable, is_built_in, create_time, create_user, update_time, update_user)
VALUES ('5230386166628352172', '子管理员', 'SUB_ADMIN', '子管理员，拥有系统中大部分权限，但是不能对菜单、权限、资源以及权限与资源的对应关系进行修改', 1, 1,
        '2024-07-18 14:41:50', '000001', '2024-07-18 14:41:50', '000001');

INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382927602253996', '1', '5208361429043773627', '2024-07-18 14:38:36', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382927904243884', '1', '5208365662975557878', '2024-07-18 14:38:36', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382927954575532', '1', '5227216727657414811', '2024-07-18 14:38:36', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928021684396', '1', '5208366438099714294', '2024-07-18 14:38:36', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928105570476', '1', '5208366805017428214', '2024-07-18 14:38:36', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928206233772', '1', '5208367148061163766', '2024-07-18 14:38:36', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928256565420', '1', '5208370996855177462', '2024-07-18 14:38:36', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928357228716', '1', '5208371439975006454', '2024-07-18 14:38:36', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928407560364', '1', '5208368791020372214', '2024-07-18 14:38:36', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928508223660', '1', '5208369071787081974', '2024-07-18 14:38:36', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928575332524', '1', '5208372564820230390', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928642441388', '1', '5208375243722195102', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928709550252', '1', '5208375438522450078', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928776659116', '1', '5208378615657398430', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928826990764', '1', '5208379459048046750', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928910876844', '1', '5208380587869470878', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382928977985708', '1', '5208381474025242782', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382929045094572', '1', '5208377102402846878', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382929128980652', '1', '5208382105167331486', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382929179312300', '1', '5208384318786764958', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230382929246421164', '1', '5208384955029127326', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388642307899564', '5230386166628352172', '5208361429043773627', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388642391785644', '5230386166628352172', '5208365662975557878', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388642492448940', '5230386166628352172', '5227216727657414811', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388642576335020', '5230386166628352172', '5208366438099714294', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388642643443884', '5230386166628352172', '5208366805017428214', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388642710552748', '5230386166628352172', '5208367148061163766', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388642811216044', '5230386166628352172', '5208370996855177462', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388642895102124', '5230386166628352172', '5208371439975006454', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388642962210988', '5230386166628352172', '5208368791020372214', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388643012542636', '5230386166628352172', '5208369071787081974', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388643079651500', '5230386166628352172', '5208372564820230390', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388643280978092', '5230386166628352172', '5208375243722195102', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388643364864172', '5230386166628352172', '5208375438522450078', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388643431973036', '5230386166628352172', '5208378615657398430', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388643532636332', '5230386166628352172', '5208379459048046750', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388643633299628', '5230386166628352172', '5208380587869470878', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388643717185708', '5230386166628352172', '5208381474025242782', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388643834626220', '5230386166628352172', '5208377102402846878', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_menu (role_menu_id, role_id, menu_id, create_time, create_user)
VALUES ('5230388643901735084', '5230386166628352172', '5208382105167331486', '2024-07-18 14:44:17', '000001');

INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382929565188268', '1', '5221631378507956214', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382929783292076', '1', '5221631378507956314', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382929951064236', '1', '5221633934751694938', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930051727532', '1', '5221639660731629658', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930102059180', '1', '5221641410544926810', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930185945260', '1', '5230049801768272044', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930286608556', '1', '5230090227560743084', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930353717420', '1', '5230090909437132972', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930420826284', '1', '5230091173191745708', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930504712364', '1', '5230054400336068780', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930571821228', '1', '5230054856777007276', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930655707308', '1', '5230056219472822444', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930722816172', '1', '5230057120392544428', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930773147820', '1', '5230057458503778476', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930840256684', '1', '5230058061325926572', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930924142764', '1', '5230058848009584812', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382930991251628', '1', '5230085469810720940', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382931041583276', '1', '5230085696521240748', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382931091914924', '1', '5230086009181438124', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382931159023788', '1', '5230086727615381676', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382931293241516', '1', '5230087315807797420', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382931427459244', '1', '5230087795803947180', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382931494568108', '1', '5230088035986571436', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382931561676972', '1', '5230088326047858860', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382931662340268', '1', '5230088652079497388', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382931712671916', '1', '5230088946368643244', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382931796557996', '1', '5230089342730371244', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382931880444076', '1', '5230059257390432428', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382931964330156', '1', '5230059750523142316', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382932064993452', '1', '5230070938493517996', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382932215988396', '1', '5230077868758794412', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382932299874476', '1', '5230078255926608044', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382932383760556', '1', '5230078577243848876', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382932450869420', '1', '5230079244792496300', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382932517978284', '1', '5230079433485844652', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382932585087148', '1', '5230080117476163756', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382932652196012', '1', '5230083441059430572', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382932719304876', '1', '5230083861630681260', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382932769636524', '1', '5230084130569453740', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382932836745388', '1', '5230084405514469548', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382932903854252', '1', '5230092540987834540', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382932987740332', '1', '5230093403303182508', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382933071626412', '1', '5230093683482689708', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382933138735276', '1', '5230093936114008236', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382933222621356', '1', '5230094124371148972', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382933272953004', '1', '5230094304222904492', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382933340061868', '1', '5230096332537987244', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382933440725164', '1', '5230096604949643436', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382933524611244', '1', '5230103776488063148', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382933608497324', '1', '5230104091195080876', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382933709160620', '1', '5230098361691930796', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382933793046700', '1', '5230098547063390380', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382933860155564', '1', '5230098888328741036', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382933927264428', '1', '5230099186275319980', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382933994373292', '1', '5230100061022585004', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382934078259372', '1', '5230100327696433324', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382934162145452', '1', '5230100451394846892', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382934212477100', '1', '5230100629652766892', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382934296363180', '1', '5230101186220130476', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382934380249260', '1', '5230101319297007788', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382934447358124', '1', '5230101510876037292', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382934531244204', '1', '5230101894403195052', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382934615130284', '1', '5230102374516785324', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382934699016364', '1', '5230102903200415916', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382934766125228', '1', '5230103320919539884', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230382934850011308', '1', '5221638644887978074', '2024-07-18 14:38:37', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388644086284460', '5230386166628352172', '5221631378507956214', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388644254056620', '5230386166628352172', '5221631378507956314', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388644337942700', '5230386166628352172', '5221633934751694938', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388644388274348', '5230386166628352172', '5221639660731629658', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388644455383212', '5230386166628352172', '5230090227560743084', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388644556046508', '5230386166628352172', '5230090909437132972', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388644623155372', '5230386166628352172', '5230091173191745708', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388644707041452', '5230386166628352172', '5230054400336068780', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388644790927532', '5230386166628352172', '5230054856777007276', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388644874813612', '5230386166628352172', '5230057120392544428', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388644975476908', '5230386166628352172', '5230057458503778476', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388645025808556', '5230386166628352172', '5230058061325926572', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388645109694636', '5230386166628352172', '5230058848009584812', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388645176803500', '5230386166628352172', '5230087315807797420', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388645260689580', '5230386166628352172', '5230088946368643244', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388645327798444', '5230386166628352172', '5230089342730371244', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388645394907308', '5230386166628352172', '5230059257390432428', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388645495570604', '5230386166628352172', '5230059750523142316', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388645579456684', '5230386166628352172', '5230070938493517996', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388645629788332', '5230386166628352172', '5230077868758794412', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388645713674412', '5230386166628352172', '5230078255926608044', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388645780783276', '5230386166628352172', '5230078577243848876', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388645847892140', '5230386166628352172', '5230079244792496300', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388645965332652', '5230386166628352172', '5230079433485844652', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388646082773164', '5230386166628352172', '5230080117476163756', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388646250545324', '5230386166628352172', '5230083441059430572', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388646435094700', '5230386166628352172', '5230083861630681260', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388646535757996', '5230386166628352172', '5230084130569453740', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388646619644076', '5230386166628352172', '5230084405514469548', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388646904856748', '5230386166628352172', '5230096604949643436', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388647223623852', '5230386166628352172', '5230103776488063148', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388647391396012', '5230386166628352172', '5230104091195080876', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388647492059308', '5230386166628352172', '5230098361691930796', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388647559168172', '5230386166628352172', '5230098547063390380', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388647609499820', '5230386166628352172', '5230098888328741036', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388647777271980', '5230386166628352172', '5230099186275319980', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388647844380844', '5230386166628352172', '5230100061022585004', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388647928266924', '5230386166628352172', '5230100327696433324', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388648012153004', '5230386166628352172', '5230100451394846892', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388648079261868', '5230386166628352172', '5230100629652766892', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388648163147948', '5230386166628352172', '5230101186220130476', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388648230256812', '5230386166628352172', '5230101319297007788', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388648314142892', '5230386166628352172', '5230101510876037292', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388648398028972', '5230386166628352172', '5230101894403195052', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388648481915052', '5230386166628352172', '5230102374516785324', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388648549023916', '5230386166628352172', '5230102903200415916', '2024-07-18 14:44:17', '000001');
INSERT INTO darkblue.tb_sys_role_permission (role_permission_id, role_id, permission_id, create_time, create_user)
VALUES ('5230388648632909996', '5230386166628352172', '5230103320919539884', '2024-07-18 14:44:17', '000001');

INSERT INTO darkblue.tb_sys_user (user_id, username, password, name, sex, dept_id, position_id, phone_number, identity_no, is_enable, last_login_time,
                                  password_update_time, is_admin, is_del, create_time, create_user, update_time, update_user)
VALUES ('000001', 'admin', '{noop}123456', '超级管理员', null, '5208465053652615277', '5218751406776778904', '17899999999', null, 1, '2024-07-22 10:02:29',
        null, 1, 0, null, null, '2024-07-22 10:02:29', '000001');
INSERT INTO darkblue.tb_sys_user (user_id, username, password, name, sex, dept_id, position_id, phone_number, identity_no, is_enable, last_login_time,
                                  password_update_time, is_admin, is_del, create_time, create_user, update_time, update_user)
VALUES ('5218807135655165978', 'haosky', '{noop}123456', '昊天大帝', null, '5208464841823486061', '5218747943439302808', '13578945621', null, 1, null, null, 0,
        0, '2024-07-10 14:59:05', '000001', '2024-07-17 10:44:18', '000001');
INSERT INTO darkblue.tb_sys_user (user_id, username, password, name, sex, dept_id, position_id, phone_number, identity_no, is_enable, last_login_time,
                                  password_update_time, is_admin, is_del, create_time, create_user, update_time, update_user)
VALUES ('5230419127398564012', 'subadmin', '{noop}123456', '子管理员', null, '5208465053652615277', '5228697730309685435', '13645658888', null, 1,
        '2024-07-22 09:15:43', null, 0, 0, '2024-07-18 15:14:34', '000001', '2024-07-22 09:15:43', '5230419127398564012');

INSERT INTO darkblue.tb_sys_user_group (user_group_id, user_group_name, sort_num, is_enable, create_time, create_user, update_time, update_user)
VALUES ('5225711113248702531', '管理员', 1, 1, '2024-07-15 09:17:35', '000001', '2024-07-15 09:17:35', '000001');
INSERT INTO darkblue.tb_sys_user_group (user_group_id, user_group_name, sort_num, is_enable, create_time, create_user, update_time, update_user)
VALUES ('5225711571904233539', '财务组', 2, 0, '2024-07-15 09:18:02', '000001', '2024-07-15 11:09:14', '000001');

INSERT INTO darkblue.tb_sys_user_group_role (user_group_role_id, user_group_id, role_id, create_time, create_user)
VALUES ('5225798978129363123', '5225711113248702531', '5208659712643432700', '2024-07-15 10:44:52', '000001');

INSERT INTO darkblue.tb_sys_user_group_user (user_group_user_id, user_group_id, user_id, create_time, create_user)
VALUES ('5225799193766920371', '5225711113248702531', '000001', '2024-07-15 10:45:05', '000001');

INSERT INTO darkblue.tb_sys_user_role (user_role_id, user_id, role_id, create_time, create_user)
VALUES ('5218806407238778906', '5218806407121338394', '5218803358566973466', '2024-07-10 14:58:22', '000001');
INSERT INTO darkblue.tb_sys_user_role (user_role_id, user_id, role_id, create_time, create_user)
VALUES ('5227619430736855234', '5218807135655165978', '5218803068707012634', '2024-07-16 16:53:19', '000001');
INSERT INTO darkblue.tb_sys_user_role (user_role_id, user_id, role_id, create_time, create_user)
VALUES ('5227619645921427650', '000001', '1', '2024-07-16 16:53:32', '000001');
INSERT INTO darkblue.tb_sys_user_role (user_role_id, user_id, role_id, create_time, create_user)
VALUES ('5230419127516004524', '5230419127398564012', '5230386166628352172', '2024-07-18 15:14:34', '000001');

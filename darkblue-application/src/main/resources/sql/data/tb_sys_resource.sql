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

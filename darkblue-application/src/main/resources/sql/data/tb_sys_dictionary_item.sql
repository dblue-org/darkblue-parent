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

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

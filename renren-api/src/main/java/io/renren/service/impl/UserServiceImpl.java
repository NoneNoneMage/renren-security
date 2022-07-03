/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.validator.Assert;
import io.renren.dao.UserDao;
import io.renren.entity.TokenEntity;
import io.renren.entity.UserEntity;
import io.renren.form.LoginForm;
import io.renren.service.TokenService;
import io.renren.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
	@Autowired
	private TokenService tokenService;

	@Override
	public UserEntity queryByMobile(String mobile) {
		return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("mobile", mobile));
	}

	@Override
	public UserEntity queryByName(String name) {
		return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", name));
	}

	@Override
	public Map<String, Object> login(LoginForm form) {
		UserEntity user = queryByName(form.getUsername());
		Assert.isNull(user, "用户名或密码错误");

		//密码错误
//		DigestUtils.sha256Hex(form.getPassword());
		if(!user.getPassword().equals(form.getPassword())){
			throw new RRException("用户名或密码错误");
		}

		//获取登录token
		TokenEntity tokenEntity = tokenService.createToken(user.getUserId());

		Map<String, Object> map = new HashMap<>(2);
		map.put("token", tokenEntity.getToken());
		map.put("userName", user.getUsername());
		map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());

		return map;
	}

}

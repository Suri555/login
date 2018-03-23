package com.etree.tms.mapper.user;

import org.springframework.stereotype.Component;

import com.etree.tms.domain.user.User;
import com.etree.tms.mapper1.AbstractModelMapper;
import com.etree.tms.model.user.UserModel;

@Component
public class UserMapper extends AbstractModelMapper<UserModel, User> {

	@Override
	public Class<UserModel> entityType() {
		// TODO Auto-generated method stub
		return UserModel.class;
	}

	@Override
	public Class<User> modelType() {
		// TODO Auto-generated method stub
		return User.class;
	}

}

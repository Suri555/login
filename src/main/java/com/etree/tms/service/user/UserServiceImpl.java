package com.etree.tms.service.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etree.tms.response.Response;
import com.etree.tms.util.CommonUtils;
import com.etree.tms.constant.Constants;
import com.etree.tms.dao.user.UserDAO;
import com.etree.tms.domain.user.User;
import com.etree.tms.mapper.user.UserMapper;
import com.etree.tms.model.user.UserModel;

@Service("userService")
public class UserServiceImpl implements UserService, Constants {

	@Autowired
	UserDAO userDAO;

	@Autowired
	UserMapper userMapper;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public UserServiceImpl() {
	}

	public Response addUser(UserModel userModel) {
		try {
			User user = new User();
			BeanUtils.copyProperties(userModel, user);
			user.setUserId(userModel.getUserId());
			user.setPassword(CommonUtils.encriptString(user.getPassword()));
			/*user.setOrganizationId(userModel.getOrganizationId());
			user.setRoleId(userModel.getRoleId());*/
			
			Response response = userDAO.addUser(user);
			return response;
		} catch (Exception ex) {
			logger.info("Exception Service:" + ex.getMessage());
		}
		return null;
	}

	public UserModel getUser(String userId) {
		try {
			User user = userDAO.getUser(userId);
			UserModel userModel = new UserModel();
			if (user == null)
				return null;
			BeanUtils.copyProperties(user, userModel);
			return userModel;
		} catch (Exception e) {
			logger.info("Exception getUser:", e);
			return null;
		}
	}

	public Response deleteUser(String userId) {
		try {
			return userDAO.deleteUser(userId);
		} catch (Exception e) {
			logger.info("Exception deleteUser:", e);
			return null;
		}
	}

	public List<UserModel> getUsers() throws Exception {
		try {
			List<User> users = userDAO.getUsers();
			return userMapper.entityList(users);
		} catch (Exception ex) {
			logger.info("Exception getUsers:", ex);
		}
		return null;
	}

	public UserModel authenticate(UserModel userModel) throws Exception {
		userModel.setPassword(CommonUtils.encriptString(userModel.getPassword()));
		User user = new User();
		BeanUtils.copyProperties(userModel, user);
		user = userDAO.auteneticate(user);
		if (user == null)
			return null;
	/*		Organization organization = organizationDAO.getOrganization(user.getOrganizationId());
			//updateOrganizationUnit(organization, userModel.getUserName());
			user.setOrgName(organization.getName());
			user.setOrgAlias(organization.getAlias());
			user.setOrganizationId(organization.getOrganizationId());
			
			*//**
			 * Get users corresponding organizationUnits
			 */

		BeanUtils.copyProperties(user, userModel);
		return userModel;
	}
	
	/*userModel.setPassword(CommonUtils.encriptString(userModel.getPassword()));
	User user = new User();
	BeanUtils.copyProperties(userModel, user);
	user = userDAO.authenticate(user);
	if (user == null)
		return null;
	if (user.getOrganizationId() != null && user.getOrganizationId().length() > 0) {
		Organization organization = organizationDAO.getOrganization(user.getOrganizationId(),
				userModel.getUserName());
		updateOrganizationUnit(organization, userModel.getUserName());
		user.setOrgName(organization.getName());
		user.setOrgAlias(organization.getAlias());
		user.setOrganizationId(organization.getOrganizationId());
		user.setPackageId(organization.getPackageId());
		*//**
		 * Get users corresponding organizationUnits
		 *//*

	}
	BeanUtils.copyProperties(user, userModel);
	return userModel;*/

	public boolean isUserNameExist(String userName) {
		try {
			return userDAO.isUserNameExist(userName);
		} catch (Exception e) {
			logger.info("Exception isUserNameExist:", e);
			return false;
		}
	}

	@Override
	public Response updateUser(UserModel userModel) {
		try {
			User user = new User();
			BeanUtils.copyProperties(userModel, user);
			Response response = userDAO.updateUser(user);
			return response;
		} catch (Exception ex) {
			logger.info("Exception Service:" + ex.getMessage());
		}
		return null;
	}

	@Override
	public List<UserModel> getUsersByOrg(String organizationId) {
		try {
			List<User> user = userDAO.getRolesByOrg(organizationId);
			return userMapper.entityList(user);
		} catch (Exception ex) {
			logger.info("Exception getRoles:", ex);
		}
		return null;
	
	}
}

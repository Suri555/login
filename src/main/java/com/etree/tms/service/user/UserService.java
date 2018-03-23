package com.etree.tms.service.user;

import java.util.List;

import com.etree.tms.model.user.UserModel;
import com.etree.tms.response.Response;

public interface UserService {

	public Response addUser(UserModel user) throws Exception;

	public Response updateUser(UserModel user) throws Exception;

	public Response deleteUser(String userId) throws Exception;

	public UserModel getUser(String userId) throws Exception;

	public List<UserModel> getUsers() throws Exception;

	public UserModel authenticate(UserModel user) throws Exception;

	public boolean isUserNameExist(String userName) throws Exception;

	public List<UserModel> getUsersByOrg(String organizationId);

}

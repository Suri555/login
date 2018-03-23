package com.etree.tms.dao.user;

import java.util.List;

import com.etree.tms.domain.user.User;
import com.etree.tms.response.Response;

public interface UserDAO {

	public Response addUser(User user) throws Exception;

	public Response updateUser(User user) throws Exception;

	public User getUser(String userId) throws Exception;

	public Response deleteUser(String userId) throws Exception;

	public List<User> getUsers() throws Exception;

	public User auteneticate(User user) throws Exception;

	public boolean isUserNameExist(String userName) throws Exception;

	public List<User> getRolesByOrg(String organizationId);

}
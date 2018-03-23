package com.etree.tms.dao.role;

import java.util.List;

import com.etree.tms.domain.role.Role;
import com.etree.tms.model.role.RoleModel;
import com.etree.tms.response.Response;

public interface RoleDAO {

	

	 public Response addRole(RoleModel roleModel)throws Exception;

	public List<Role> getRoles()throws Exception;

	public Role getRole(String roleId)throws Exception;

	public Response deleteRole(String roleId)throws Exception;

	Response updateRole(RoleModel RoleModel)throws Exception;

	public List<Role> getRolesByOrg(String organizationId)throws Exception;

	public Response saveAdminRole(Role role)throws Exception;

}

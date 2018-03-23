package com.etree.tms.service.role;

import java.util.List;

import com.etree.tms.model.role.RoleModel;
import com.etree.tms.response.Response;

public interface RoleService {

	public Response addRole(RoleModel roleModel)throws Exception;

	public List<RoleModel> getRoles()throws Exception;

	public RoleModel getRole(String roleId)throws Exception;

	public Response updateRole(RoleModel roleModel)throws Exception;

	public Response deleteRole(String roleId)throws Exception;

	public List<RoleModel> getRolesByOrg(String organizationId) throws Exception;
}

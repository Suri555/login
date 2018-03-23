package com.etree.tms.service.role;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etree.tms.response.Response;
import com.etree.tms.util.CommonUtils;
import com.etree.tms.constant.Constants;
import com.etree.tms.dao.role.RoleDAO;
import com.etree.tms.domain.role.Role;
import com.etree.tms.mapper.role.RoleMapper;
import com.etree.tms.model.role.RoleModel;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("roleService")
public class RoleServiceImpl implements RoleService,Constants{

	@Autowired
	RoleDAO roleDAO;

	@Autowired
	RoleMapper roleMapper;
	
	ObjectMapper mapper = new ObjectMapper();

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Override
	public Response addRole(RoleModel roleModel) throws Exception{
		try {
			Response response = roleDAO.addRole(roleModel);
			return response;
			/*Role role = new Role();
			BeanUtils.copyProperties(roleModel, role);
			role.setRoleId(CommonUtils.generateRandomId());
			//role.setOrganizationId(CommonUtils.generateRandomId());
			byte[] bytes = CommonUtils.getJson(roleModel.getMenu()).getBytes();
			Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			blob.setBytes(5, bytes);
			role.setMenu(blob);
			role.setCreatedDate(DateUtility.getDateByStringFormat(new Date() , DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
			role.setModifiedDate(DateUtility.getDateByStringFormat(new Date() , DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
			Response response = roleDAO.addRole(role);
			return response;*/
		} catch (Exception ex) {
			logger.info("Exception in addRole:" + ex.getMessage());
		}
		return null;
	}

	@Override
	public List<RoleModel> getRoles() throws Exception{
		try {
			List<Role> roles = roleDAO.getRoles();
			return roleMapper.entityList(roles);
		} catch (Exception ex) {
			logger.info("Exception getRoles:", ex);
		}
		return null;
	}
	
	@Override
	public RoleModel getRole(String roleId)throws Exception{
		try {
			Role role = roleDAO.getRole(roleId);
			RoleModel roleModel = new RoleModel();
			if (role == null)
				return null;
			BeanUtils.copyProperties(role, roleModel);
		
		return roleModel;
			
		} catch (Exception e) {
			logger.info("Exception in getRole:", e);
			return null;
		}
	}

	@Override
	public Response updateRole(RoleModel roleModel)throws Exception{
		try {
			return roleDAO.updateRole(roleModel);
		} catch (Exception ex) {
			logger.info("Exception in updateRole:" + ex.getMessage());
		}
		return null;
	}

	@Override
	public Response deleteRole(String roleId)throws Exception {
		try {
			return roleDAO.deleteRole(roleId);
		} catch (Exception ex) {
			logger.info("Exception in deleteRole:" + ex.getMessage());
		}
		return null;

	}

	@Override
	public List<RoleModel> getRolesByOrg(String organizationId) throws Exception {
		try {
			List<Role> roles = roleDAO.getRolesByOrg(organizationId);
			return roleMapper.entityList(roles);
		} catch (Exception ex) {
			logger.info("Exception getRoles:", ex);
		}
		return null;
	}

}

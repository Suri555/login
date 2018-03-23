package com.etree.tms.dao.role;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.etree.tms.response.Response;
import com.etree.tms.util.CommonUtils;
import com.etree.tms.util.DateUtility;
import com.etree.tms.constant.Constants;
import com.etree.tms.constant.StatusCode;
import com.etree.tms.domain.role.Role;
import com.etree.tms.model.role.RoleModel;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@Repository
public class RoleDAOImpl implements RoleDAO,Constants{
	private static final Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);

	@PersistenceContext 
	EntityManager entityManager; 
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public Response addRole(RoleModel roleModel)throws Exception{
		Response response = CommonUtils.getResponseObject("Add Role");
		
		try {
			
			Role role = new Role();
			
			BeanUtils.copyProperties(roleModel, role);
			role.setRoleId(CommonUtils.generateRandomId());
			/*role.setIsActive(true);
			byte[] bytes = CommonUtils.getJson(roleModel.getMenu()).getBytes();
			Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			blob.setBytes(1, bytes);
			role.setMenu(blob);
			role.setCreatedDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
			role.setModifiedDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
	*/		
			entityManager.persist(role);
			System.out.println("role name2:"+role.getName());
			response.setStatus(StatusCode.SUCCESS.name());
				RoleModel roleModel1 = new RoleModel();
				BeanUtils.copyProperties(role, roleModel1);
				
			
			/*entityManager.persist(role);
			response.setStatus(StatusCode.SUCCESS.name());
			RoleModel roleModel1 = new RoleModel();
			BeanUtils.copyProperties(role, roleModel1);
			MenuTree menuTree = mapper.readValue(CommonUtils.getBlobData(role.getMenu()), MenuTree.class);
			roleModel1.setMenu(menuTree);
			response.setData(roleModel1);*/
		}catch(Exception e){
			
			logger.error("Exception in addRole" + e.getMessage());
			response.setStatus(StatusCode.ERROR.name());
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles()throws Exception {
		try
		{
			String hql="From Role";
			return(List<Role>) entityManager.createQuery(hql).getResultList();
		}catch(Exception e)
		{
			logger.error("Exception in getRole", e);
		}
		return null;
	}

	@Override
	public Role getRole(String roleId) throws Exception{
		Response response = CommonUtils.getResponseObject("get Role by id");
		try{
			return entityManager.find(Role.class, roleId);
		}catch(Exception e){
			response.setStatus(StatusCode.SUCCESS.name());
			logger.info("Exception in getRoleById");
			response.setStatus(e.getMessage());
		}
		return null;
	}

	@Override
	public Response updateRole(RoleModel roles)throws Exception {
		Response response=CommonUtils.getResponseObject("update role data");
		try{
			Role role = new Role();
			role = getRole(roles.getRoleId());
			role.setName(roles.getName());
		/*	role.setIsActive(roles.getIsActive());
			role.setModifiedDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
		*/	entityManager.flush();
			response.setStatus(StatusCode.SUCCESS.name());
		}catch(Exception e){
			logger.info("exception in update role"+e);
			response.setStatus(StatusCode.ERROR.name());
			response.setStatus(e.getMessage());
		}
		return response;
	}

	@Override
	public Response deleteRole(String roleId)throws Exception {
		Response response=CommonUtils.getResponseObject("delete role data");
		try{
		entityManager.remove(getRole(roleId));	
		response.setStatus(StatusCode.SUCCESS.name());
		}
		catch(Exception e){
		response.setStatus(StatusCode.ERROR.name());
		logger.info("exception in delete role"+e);
		response.setStatus(e.getMessage());
		}
		return response;
	}

	

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRolesByOrg(String organizationId)throws Exception {
		Response response = CommonUtils.getResponseObject("get Role by id");
		try{
			String hql = "FROM Role where organizationId=?";
			return (List<Role>) entityManager.createQuery(hql).setParameter(1,organizationId).getResultList();
		}catch(Exception e){
			response.setStatus(StatusCode.SUCCESS.name());
			logger.info("Exception in getRolesByOrg");
			response.setStatus(e.getMessage());
		}
		return null;
		
		
		/*try {
			String hql = "FROM User where userName=? and password=?";
			return (User) entityManager.createQuery(hql).setParameter(1, user.getUserName())
					.setParameter(2, user.getPassword()).getSingleResult();
		} catch (Exception e) {
			logger.error("Exception in auteneticate", e);
		}
		return null;*/
	}

	@Override
	public Response saveAdminRole(Role role) throws Exception {
		Response response = CommonUtils.getResponseObject("Add Admin Role");
		try {
				entityManager.persist(role);
				response.setStatus(StatusCode.SUCCESS.name());
			} catch (Exception e) {
			logger.error("Exception in saveRole", e);
			response.setStatus(StatusCode.ERROR.name());
			
		}
		return response;

	}
}

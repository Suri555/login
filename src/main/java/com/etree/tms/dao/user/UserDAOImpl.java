package com.etree.tms.dao.user;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.etree.tms.response.Response;
import com.etree.tms.util.CommonUtils;
import com.etree.tms.util.DateUtility;
import com.etree.tms.constant.Constants;
import com.etree.tms.constant.StatusCode;
import com.etree.tms.domain.user.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO, Constants {
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Response addUser(User user) {
		Response response = CommonUtils.getResponseObject("Add user data");
		try {
			entityManager.persist(user);
			response.setStatus(StatusCode.SUCCESS.name());
		} catch (Exception e) {
			logger.error("Exception in addUser", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return response;
	}

	@Override
	public Response updateUser(User user) {
			Response response = CommonUtils.getResponseObject("Update user data");
		try {
			User usr = getUser(user.getUserId());
			/*usr.setApiKey(user.getApiKey());*/
			usr.setEmail(user.getEmail());
			usr.setMobileNo(user.getMobileNo());
			usr.setFirstName(user.getFirstName());
			usr.setLastName(user.getLastName());
			usr.setPassword(user.getPassword());
			usr.setUserName(user.getUserName());
			usr.setRoleId(user.getRoleId());
			
			entityManager.flush();
			response.setStatus(StatusCode.SUCCESS.name());
		} catch (Exception e) {
			logger.error("Exception in updateUser", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return  null/*response*/;
	}

	public User getUser(String userId) {
		try {
			return entityManager.find(User.class, userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			logger.error("Exception in getUser", e);
			return null;
		}
	}

	@Override
	public Response deleteUser(String userId) {
		Response response = CommonUtils.getResponseObject("Delete user data");
		try {
			entityManager.remove(getUser(userId));
			response.setStatus(StatusCode.SUCCESS.name());
		} catch (Exception e) {
			logger.error("Exception in updateUser", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() throws Exception {
		try {
			String hql = "FROM User ORDER BY modifiedDate";
			return (List<User>) entityManager.createQuery(hql).getResultList();
		} catch (Exception e) {
			logger.error("Exception in getUsers", e);
		}
		return null;
	}

	public User auteneticate(User user) throws Exception {
		try {
			String hql = "FROM User where firstName=? and password=?";
			return (User) entityManager.createQuery(hql).setParameter(1, user.getFirstName())
					.setParameter(2, user.getPassword()).getSingleResult();
		} catch (Exception e) {
			logger.error("Exception in auteneticate", e);
		}
		return null;
	}

	@Override
	public boolean isUserNameExist(String userName) throws Exception {
		try {
			String hql = "FROM User WHERE userName=?";
			int count = entityManager.createQuery(hql).setParameter(1, userName).getResultList().size();
			return count > 0 ? true : false;
		} catch (Exception e) {
			logger.error("Exception in isUserNameExist: ", e);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getRolesByOrg(String organizationId) {
		Response response = CommonUtils.getResponseObject("get User by id");
		try{
			String hql = "FROM User where organizationId=?";
			return (List<User>) entityManager.createQuery(hql).setParameter(1,organizationId).getResultList();
		}catch(Exception e){
			response.setStatus(StatusCode.SUCCESS.name());
			logger.info("Exception in getRolesByOrg");
			response.setStatus(e.getMessage());
		}
		return null;
	}
}
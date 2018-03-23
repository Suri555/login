package com.etree.tms.controller.role;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.etree.tms.constant.StatusCode;
import com.etree.tms.model.role.RoleModel;
import com.etree.tms.response.ErrorObject;
import com.etree.tms.response.Response;
import com.etree.tms.service.role.RoleService;
import com.etree.tms.util.CommonUtils;

@RestController
@RequestMapping("/rol")
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value="/addrol", method=RequestMethod.POST, produces="application/json")
	public Response addRole(@RequestBody RoleModel roleModel, HttpServletRequest request, HttpServletResponse response)throws Exception{
		logger.info("addRole: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("addRole: Received request: " + CommonUtils.getJson(roleModel));
		return roleService.addRole(roleModel);
		
	}
	
	@RequestMapping(value = "/getallrol", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getRoles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("getOrders: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<RoleModel> roles = roleService.getRoles();
		Response res = CommonUtils.getResponseObject("List of roles");
		if (roles == null) {
			ErrorObject err = CommonUtils.getErrorResponse("roles Not Found", "roles Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
		} else {
			res.setData(roles);
		}		logger.info("getRoles: Sent response");
		return CommonUtils.getJson(res);
	}
	
	@RequestMapping(value = "/getrol/{roleId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getrole(@PathVariable("roleId") String roleId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("getRole: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		RoleModel roleModel = roleService.getRole(roleId);
		Response res = CommonUtils.getResponseObject("getRole Details");
		if (roleModel == null) {
			ErrorObject err = CommonUtils.getErrorResponse("role Not Found", "role Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
		} else {
			res.setData(roleModel);
		}
		logger.info("getRole: Sent response");
		return CommonUtils.getJson(res);
	}

	@RequestMapping(value = "/updaterol", method = RequestMethod.PUT, produces = "application/json")
	public Response updateRole(@RequestBody RoleModel roleModel, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("updateRole: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("updateSupplier: Received request: " + CommonUtils.getJson(roleModel));
		return roleService.updateRole(roleModel);
	}

	@RequestMapping(value = "/deleterol/{roleId}", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public Response deleteRole(@PathVariable("roleId") String roleId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("deleteRole: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("deleteRole: Received request: " + CommonUtils.getJson(roleId));
		return roleService.deleteRole(roleId);
	}
	
	@RequestMapping(value = "/getrol/byorg/{organizationId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getRolesByOrg(@PathVariable("organizationId") String organizationId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("getOrders: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<RoleModel> roles = roleService.getRolesByOrg(organizationId);
		Response res = CommonUtils.getResponseObject("List of roles By Org");
		if (roles == null) {
			ErrorObject err = CommonUtils.getErrorResponse("roles Not Found", "roles Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
		} else {
			res.setData(roles);
		}
		logger.info("getRoles: Sent response");
		return CommonUtils.getJson(res);
	}



}

package com.etree.tms.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.etree.tms.constant.Constants;
import com.etree.tms.constant.StatusCode;
import com.etree.tms.dao.user.UserDAO;
import com.etree.tms.model.user.UserModel;
import com.etree.tms.response.ErrorObject;
import com.etree.tms.response.Response;
import com.etree.tms.service.user.UserService;
import com.etree.tms.util.CommonUtils;

@RestController
@RequestMapping("/us")
public class UserController implements Constants {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/adduser", method = RequestMethod.POST, produces = "application/json")
	public Response addUser(@RequestBody UserModel user, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("addUser: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("addUser: Received request: " + CommonUtils.getJson(user));
		return userService.addUser(user);
	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.PUT, produces = "application/json")
	public Response updateUser(@RequestBody UserModel user, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("updateUser: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("updateUser: Received request: " + CommonUtils.getJson(user));
		return userService.updateUser(user);
	}

	@RequestMapping(value = "/getuser/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getUser(@PathVariable("userId") String userId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("getUser: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		UserModel userModel = userService.getUser(userId);
		Response res = CommonUtils.getResponseObject("User Details");
		if (userModel == null) {
			ErrorObject err = CommonUtils.getErrorResponse("Users Not Found", "Users Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			logger.info("getUser: Sent response");

			return CommonUtils.getJson(res);
			
		} else {
			res.setData(userModel);
			return CommonUtils.getJson(res);
		}
		
	}

	@RequestMapping(value = "/deleteuser/{userId}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody Response deleteUser(@PathVariable("userId") String userId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("getUser: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		return userService.deleteUser(userId);
	}

	@RequestMapping(value = "/userExist/{userName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String isUserNameExist(@PathVariable("userName") String userName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("getUser: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		boolean isUserNameExist = userService.isUserNameExist(userName);
		Response res = CommonUtils.getResponseObject("User Exist");
		Map<String, Boolean> obj = new HashMap<String, Boolean>();
		obj.put("isUserNameExist", isUserNameExist);
		res.setData(obj);
		if (!isUserNameExist) {
			res.setStatus(StatusCode.ERROR.name());
		}
		logger.info("getUser: Sent response");
		return CommonUtils.getJson(res);
	}

	@RequestMapping(value = "/getallusers", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("getUsers: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<UserModel> users = userService.getUsers();
		Response res = CommonUtils.getResponseObject("List of Users");
		if (users == null) {
			ErrorObject err = CommonUtils.getErrorResponse("Users Not Found", "Users Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
		} else {
			res.setData(users);
		}
		logger.info("getUsers: Sent response");
		return CommonUtils.getJson(res);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String authenticate(@RequestBody UserModel user, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("authenticate: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("authenticate :Received request: " + CommonUtils.getJson(user));
		user = userService.authenticate(user);
		Response res = CommonUtils.getResponseObject("authenticate user");
		if (user == null) {
			ErrorObject err = CommonUtils.getErrorResponse("Invalid Username or Password",
					"Invalid Username or Password");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
		} else {
			res.setData(user);
		}
		logger.info("authenticate: Sent response");
		return CommonUtils.getJson(res);
	}
	
	@RequestMapping(value = "/users/org/{organizationId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getUsersByOrg(@PathVariable("organizationId") String organizationId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("getUsersByOrg: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<UserModel> users = userService.getUsersByOrg(organizationId);
		Response res = CommonUtils.getResponseObject("List of users By Org");
		if (users == null) {
			ErrorObject err = CommonUtils.getErrorResponse("users Not Found", "users Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
		} else {
			res.setData(users);
		}
		logger.info("getRoles: Sent response");
		return CommonUtils.getJson(res);
	}


}

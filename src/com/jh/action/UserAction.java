package com.jh.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.jh.bean.User;
import com.jh.common.bean.ControllerResult;
import com.jh.common.bean.Pager4EasyUI;
import com.jh.common.web.WebUtil;
import com.jh.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1527623699365829740L;

	private HttpServletRequest req;
	
	private UserService userService;
	private List<User> rows;
	private long total;
	private ControllerResult result;
	
	private User user;
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getRows() {
		return rows;
	}

	public long getTotal() {
		return total;
	}

	public ControllerResult getResult() {
		return result;
	}

	public String id() {
		user = userService.queryById(User.class, "98bb0882c11211e6808a3065ec373466");
		System.out.println(user);
		return "id";
	}
	
	public String pager() {
		Pager4EasyUI<User> pager = new Pager4EasyUI<User>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = userService.queryByPager("User", pager);
		rows = pager.getRows();
		total = pager.getTotal();
		return "pager";
	}
	
	public String all() {
		rows = userService.queryAll("User");
		return "all";
	}
	
	public String save() {
		User user = new User();
		user.setEmail("ccc@qq.com");
		user.setPwd("123456");
		userService.save(user);
		result = ControllerResult.setSuccessResult("add success");
		return "save";
	}
	
	public String update() {
		User user = new User();
		user.setId("98bb0882c11211e6808a3065ec373466");
		user.setEmail("update@qq.com");
		user.setPwd("654321");
		userService.update(user);
		result = ControllerResult.setSuccessResult("update success");
		return "update";
	}
	
	public String delete() {
		User user = new User();
		user.setId("98da7587c11211e6808a3065ec373466");
		userService.delete(user);
		result = ControllerResult.setSuccessResult("delete success");
		return "del";
	}

}

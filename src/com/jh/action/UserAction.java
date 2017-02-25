package com.jh.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.jh.bean.User;
import com.jh.bean.Users;
import com.jh.common.bean.ControllerResult;
import com.jh.common.bean.Pager4EasyUI;
import com.jh.common.web.WebUtil;
import com.jh.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1527623699365829740L;

	private HttpServletRequest req;
	
	private Logger logger = Logger.getLogger(UserAction.class);
	
	private UserService userService;
	private List<User> rows;
	private long total;
	private ControllerResult result;
	private Users users;
	
	private User user;
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
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
		logger.info("根据id查询用户信息");
		user = userService.queryById(User.class, "98bb0882c11211e6808a3065ec373466");
		System.out.println(user);
		return "id";
	}
	
	public String pager() {
		logger.info("分页查找用户信息");
		Pager4EasyUI<User> pager = new Pager4EasyUI<User>();
		pager.setPageNo(WebUtil.getPageNo(req));
		pager.setPageSize(WebUtil.getPageSize(req));
		pager = userService.queryByPager("User", pager);
		rows = pager.getRows();
		total = pager.getTotal();
		return "pager";
	}
	
	public String all() {
		logger.info("查询所有用户信息");
		rows = userService.queryAll("User");
		return "all";
	}
	
	public String save() {
		logger.info("保存用户信息");
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
	
	public String loginPage() {
		return "login_page";
	}
	
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(), users.getPassword());
		try {  
	        subject.login(token);  
	        Session session = subject.getSession();
	        session.setAttribute("username", subject.getPrincipal().toString());
	        String role = "admin";
	        System.out.println("判断当前登录的用户是否有" + role + "的角色：" + subject.hasRole(role));
//	        String permission = "customer:query";
//	        subject.checkPermission(permission);
//	        System.out.println(permission + "有权限");
//	        String permission1 = "customer:delete";
//	        subject.checkPermission(permission1);
//	        System.out.println(permission1 + "有权限");
	        return "login";
	    } catch (AuthorizationException e) { // 没有权限
	    	req.setAttribute("error", "没有权限");
	    } catch (UnknownAccountException e) { // 未知的账户异常
	    	req.setAttribute("error", "没有该账户");
	    } catch (IncorrectCredentialsException e) { // 不正确的凭证异常
	    	req.setAttribute("error", "密码错误");
	    } catch (AuthenticationException e) { // 账号验证失败
	    	req.setAttribute("error", "账号验证失败");
	    } 
		return "login_page";
	}
	
	public String loginOut() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		req.setAttribute("error", "您已经安全退出");
		return "login_page";
	}

}

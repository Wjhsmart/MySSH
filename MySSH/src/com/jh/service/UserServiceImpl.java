package com.jh.service;

import java.io.Serializable;
import java.util.List;

import com.jh.bean.User;
import com.jh.common.bean.Pager4EasyUI;
import com.jh.dao.UserDAO;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public void save(User t) {
		userDAO.save(t);
	}

	@Override
	public void delete(User t) {
		userDAO.delete(t);
	}

	@Override
	public void update(User t) {
		userDAO.update(t);
	}

	@Override
	public User queryById(Class<?> clazz, Serializable s) {
		return userDAO.queryById(clazz, s);
	}

	@Override
	public Pager4EasyUI<User> queryByPager(String beanName, Pager4EasyUI<User> pager) {
		pager = userDAO.queryByPager(beanName, pager);
		pager.setTotal(userDAO.count("User"));
		return pager;
	}

	@Override
	public List<User> queryAll(String beanName) {
		return userDAO.queryAll(beanName);
	}

	@Override
	public long count(String beanName) {
		return userDAO.count(beanName);
	}

}

package com.jh.service;

import java.io.Serializable;
import java.util.List;

import com.jh.common.bean.Pager4EasyUI;

public interface BaseService<T> {

	/**
	 * 根据传递进来的对象保存对象到数据库
	 * @param t
	 */
	public void save(T t);
	
	/**
	 * 根据传递进来对象删除数据库中的数据
	 * @param t
	 */
	public void delete(T t);
	
	/**
	 * 根据传递进来的对象更新数据库中的数据
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * 根据Id查询数据库中的数据
	 * @param clazz
	 * @param s
	 * @return
	 */
	public T queryById(Class<?> clazz, Serializable s);
	
	/**
	 * MySQL的方式分页查询数据库中的数据
	 * @param beanName
	 * @param pager
	 * @return
	 */
	public Pager4EasyUI<T> queryByPager(String beanName, Pager4EasyUI<T> pager);
	
	/**
	 * 根据传递进来的简单类名查询数据库中的所有数据
	 * @return
	 */
	public List<T> queryAll(String beanName);
	
	/**
	 * 计算传递进来对象的所有数据的总个数
	 * @param clazz
	 * @return
	 */
	public long count(String beanName);
}

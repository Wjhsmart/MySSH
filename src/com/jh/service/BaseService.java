package com.jh.service;

import java.io.Serializable;
import java.util.List;

import com.jh.common.bean.Pager4EasyUI;

public interface BaseService<T> {

	/**
	 * ���ݴ��ݽ����Ķ��󱣴�������ݿ�
	 * @param t
	 */
	public void save(T t);
	
	/**
	 * ���ݴ��ݽ�������ɾ�����ݿ��е�����
	 * @param t
	 */
	public void delete(T t);
	
	/**
	 * ���ݴ��ݽ����Ķ���������ݿ��е�����
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * ����Id��ѯ���ݿ��е�����
	 * @param clazz
	 * @param s
	 * @return
	 */
	public T queryById(Class<?> clazz, Serializable s);
	
	/**
	 * MySQL�ķ�ʽ��ҳ��ѯ���ݿ��е�����
	 * @param beanName
	 * @param pager
	 * @return
	 */
	public Pager4EasyUI<T> queryByPager(String beanName, Pager4EasyUI<T> pager);
	
	/**
	 * ���ݴ��ݽ����ļ�������ѯ���ݿ��е���������
	 * @return
	 */
	public List<T> queryAll(String beanName);
	
	/**
	 * ���㴫�ݽ���������������ݵ��ܸ���
	 * @param clazz
	 * @return
	 */
	public long count(String beanName);
}

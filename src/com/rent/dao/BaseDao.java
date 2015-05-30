package com.rent.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;


public interface BaseDao {

	public Session getMySession();

    public void close(Session session);
    //ʵ�����

    /**
     * ����ʵ��
     * @param t
     * @param <T>
     * @return
     */
    public <T> boolean saveEntity(T t);

    /**
     * ɾ��ʵ��
     * @param t 
     * @param <T>
     * @return
     */
    public <T> boolean deleteEntity(T t);

    /**
     * ����ʵ��
     * @param t 
     * @param <T>
     * @return
     */
    public <T> boolean updateEntity(T t);

    /**
     * ���ʵ��
     * @param t
     * @param id 
     * @param <T>
     * @return
     */
    public <T> T getEntity(Class t,Serializable id);

    //��������

    /**
     * һ���ѯ
     * @param hql 
     * @param objects
     * @return
     */
    public <T> List<T> findListByHQL(String hql, Object... objects);
    
    /**
     * ���ʵ������
     * @param hql
     * @return
     */
    public int queryForPages(String hql,Object...objects);

    /**
     * ��ҳ��ѯ
     * @param hql 
     * @return
     */
    public <T> List<T> queryForPages(final String hql, final Object[] params,
                                     final int start, final int limit);
}

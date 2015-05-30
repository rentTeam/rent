package com.rent.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;


public interface BaseDao {

	public Session getMySession();

    public void close(Session session);
    //实体操作

    /**
     * 保存实体
     * @param t
     * @param <T>
     * @return
     */
    public <T> boolean saveEntity(T t);

    /**
     * 删除实体
     * @param t 
     * @param <T>
     * @return
     */
    public <T> boolean deleteEntity(T t);

    /**
     * 更新实体
     * @param t 
     * @param <T>
     * @return
     */
    public <T> boolean updateEntity(T t);

    /**
     * 获得实体
     * @param t
     * @param id 
     * @param <T>
     * @return
     */
    public <T> T getEntity(Class t,Serializable id);

    //批量处理

    /**
     * 一般查询
     * @param hql 
     * @param objects
     * @return
     */
    public <T> List<T> findListByHQL(String hql, Object... objects);
    
    /**
     * 获得实体总数
     * @param hql
     * @return
     */
    public int queryForPages(String hql,Object...objects);

    /**
     * 分页查询
     * @param hql 
     * @return
     */
    public <T> List<T> queryForPages(final String hql, final Object[] params,
                                     final int start, final int limit);
}

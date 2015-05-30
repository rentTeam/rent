package com.rent.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/4/28.
 */
public interface BaseService<T> {

    //实体操作
    public void save(T t);

    public void delete(T t);

    public void update(T t);

    public T getEntity(Class t,Serializable id);

    //批量处理

    public  List<T> findListByHql(String hql, Object...objects);
    
    
    public int queryForCount(String hql, Object...objects);

    public  List<T> queryForPages(final String hql, final Object[] params,
                                     final int start, final int limit);
}

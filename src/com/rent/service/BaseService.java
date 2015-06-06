package com.rent.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/4/28.
 */
public interface BaseService<T> {

    //实体操作
    public boolean save(T t);

    public boolean delete(T t);

    public boolean update(T t);

    public T getEntity(Class t,Serializable id);
    
    public Object getCarMax();

    //批量处理

    public  List<T> findListByHql(String hql, Object...objects);
    
    
    public int queryForCount(String hql, Object...objects);

    public  List<T> queryForPages(final String hql, final Object[] params,
                                     final int start, final int limit);
}

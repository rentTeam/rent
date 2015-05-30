package com.rent.service.impl;

import com.rent.dao.BaseDao;
import com.rent.service.BaseService;

import javax.annotation.Resource;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/4/28.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    @Resource
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public  void save(T t) {
        getBaseDao().saveEntity(t);
    }

    @Override
    public void delete(T t) {
        getBaseDao().deleteEntity(t);
    }

    @Override
    public void update(T t) {
        getBaseDao().updateEntity(t);
    }

    @Override
    public T getEntity(Class t, Serializable id) {
        return getBaseDao().getEntity(t,id);
    }

    public List<T> findListByHql(String hql,Object...objects) {
        return baseDao.findListByHQL(hql, objects);
    }

    @Override
	public int queryForCount(String hql, Object... objects) {
		return baseDao.queryForPages(hql, objects);
	}

	@Override
    public List<T> queryForPages(String hql, Object[] params, int start, int limit) {
        return baseDao.queryForPages(hql,params,start,limit);
    }
}

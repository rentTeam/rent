package com.rent.dao.impl;

import com.rent.dao.BaseDao;

import org.hibernate.*;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/4/28.
 */
@Scope("prototype")
@Repository
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
	
	@Resource
    private SessionFactory sessionFactory;

    @Resource
    private JdbcTemplate jdbcTemplate;
    
    @Resource
    public final void setSessionFactoryDi(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public Session getMySession() {
        return sessionFactory.openSession();
    }

    @Override
    public void close(Session session) {
        if(null!=session)
            session.close();
    }

    @Override
    public <T> boolean saveEntity(T t) {
        try {
            sessionFactory.getCurrentSession().save(t);
        }catch(JDBCException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public <T> boolean deleteEntity(T t) {
        try{
            sessionFactory.getCurrentSession().delete(t);
        }catch(JDBCException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public <T> boolean updateEntity(T t) {
        try{
            sessionFactory.getCurrentSession().update(t);
        }catch(JDBCException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public <T> T getEntity(Class t, Serializable id) {
        return (T) this.getHibernateTemplate().get(t,id);
    }

    @Override
    public <T> List<T> findListByHQL(String hql, Object... objects) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for(int i = 0 ; i < objects.length ; i ++){
			query.setParameter(i, objects[i]);
		}
		return query.list();
	}
    
    
    @Override
	public int queryForPages(String hql, Object... objects) {
    	Query query = sessionFactory.getCurrentSession().createQuery(hql);
        for(int i = 0 ; i < objects.length ; i ++){
            query.setParameter(i, objects[i]);
        }
		return query.list().size();
	}
	@Override
    public <T> List<T> queryForPages(final String hql, final Object[] params,
                                     final int start, final int limit) {
		 return (List<T>) super.getHibernateTemplate().executeWithNativeSession(
	                new org.springframework.orm.hibernate4.HibernateCallback<Object>() {

	                    public Object doInHibernate(Session session)
	                            throws HibernateException {
	                        Query query = session.createQuery(hql);
	                        if (params != null && params.length > 0) {
	                            for (int i = 0; i < params.length; i++) {
	                                query.setParameter(i, params[i]);
	                            }
	                        }
	                        // 表示是分页查询
	                        if (start != -1 && limit != -1) {
	                            query.setFirstResult(start);
	                            query.setMaxResults(limit);
	                        }
	                        return query.list();
	                    }
	                });
    }
}

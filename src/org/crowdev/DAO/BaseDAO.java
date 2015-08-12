package org.crowdev.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.crowdev.model.HibernateSessionFactory;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDAO {
	
	public List query(int pageNum, int pageSize, String hql, Object ...args) {
		// TODO Auto-generated method stub
		List list=new ArrayList();
		Session session=HibernateSessionFactory.getSession();
		Transaction ts=null;
		try{
			Query query=session.createQuery(hql);
			for (int i = 0; i < args.length; i++)
				query.setParameter(i, args[i]);
			query.setFirstResult(pageNum * pageSize);
			query.setMaxResults(pageSize);
			ts=session.beginTransaction();
			list=query.list();
			ts.commit();
			if(!Hibernate.isInitialized(list)){Hibernate.initialize(list);}
		}catch(Exception e){
			if(ts!=null)ts.rollback();
			System.out.println("【系统错误】在查询满足条件的持久化对象时出错，原因：");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return list;
	}
	
	public List listAll(Class clazz, int pageNo, int pageSize) {
		List list=new ArrayList();
		String hql="from " + clazz.getName();
		Session session=HibernateSessionFactory.getSession();
		Transaction ts=null;
		try{
			Query query=session.createQuery(hql);
			query.setFirstResult(pageNo * pageSize);
			query.setMaxResults(pageSize);
			ts=session.beginTransaction();
			list=query.list();
			ts.commit();
			if(!Hibernate.isInitialized(list)){Hibernate.initialize(list);}
		}catch(Exception e){
			if(ts!=null)ts.rollback();
			System.out.println("【系统错误】在加载满足条件的持久化对象时出错，原因：");
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return list;
	}
	
	public void add(Object obj) throws Exception{
		Session session = null;
		Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSession();
            transaction = session.beginTransaction();
            session.save(obj);
            transaction.commit();
            if(session!=null){
            	session.close();
            }
        } catch (Exception e) {
        	if (transaction != null)
        		transaction.rollback();
            if(session!=null)
            	session.close();
            throw e;
        }
        
    }
	
	public Object findByKeyObj(Class c, Serializable keyObj) throws Exception
	{
		Session session = null;
		Object result = null;
        try {
            session = HibernateSessionFactory.getSession();
            result = session.get(c, keyObj);
            session.beginTransaction().commit();
            if(session!=null)
            	session.close();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            if(session!=null)
            	session.close();
            throw e;
        }
        return result;
	}
	
	public int countQuery(String hql) {
		Long count = (long) 0;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			query.setMaxResults(1);
			count = (Long) query.uniqueResult();
			transaction.commit();
			if(session!=null)
            	session.close();
		}catch(Exception e){
			if(transaction!=null)
				transaction.rollback();
			if(session!=null)
            	session.close();
			e.printStackTrace();
		}
		return count.intValue();
	}
	
	public void saveOrUpdate(Object obj) throws Exception{
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSession();
            session.saveOrUpdate(obj);
            transaction = session.beginTransaction();
            transaction.commit();
            if(session!=null)
            	session.close();
        } catch (Exception e) {
        	if (transaction != null)
        		transaction.rollback();
            if(session!=null)
            	session.close();
            throw e;
        }
    }
	
	public void update(Object obj) throws Exception{
        Session session = null;
        try {
            session = HibernateSessionFactory.getSession();
            session.update(obj);
            session.beginTransaction().commit();
            if(session!=null)
            	session.close();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            if(session!=null)
            	session.close();
            throw e;
        }
    }
	
	public List<?> findByHQL(String hql) throws Exception{
		Session session = null;
        try {
        	session = HibernateSessionFactory.getSession();
            Query queryObject = session.createQuery(hql);
            List<?> result = queryObject.list();
            if (session != null)
            	session.close();
            return result;
        } catch (Exception e) {
        	if(session!=null)
                session.close();
            throw e;
        }
    }
	
	public void executeByHQL(String hql) throws Exception
	{
		Session session = null; 
		try {
			session = HibernateSessionFactory.getSession();
			Query queryObject = session.createQuery(hql);
			queryObject.executeUpdate();
			session.beginTransaction().commit();
			if(session!=null)
				session.close();
		} catch (Exception e) {
			session.beginTransaction().rollback();
			if(session!=null)
				session.close();
            throw e;
		}
	}
	
	public List<?> findByHQL(String hql, int maxResultCount) throws Exception{
        Session session = null;
		try {
        	session = HibernateSessionFactory.getSession();
            Query queryObject = session.createQuery(hql);
            if (maxResultCount > 0)
            	queryObject.setMaxResults(maxResultCount);
            List<?> result = queryObject.list();
            if(session!=null)
				session.close();
            return result;
        } catch (Exception e) {
        	if(session!=null)
				session.close();
            throw e;
        }
    }
}

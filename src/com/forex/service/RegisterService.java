package com.forex.service;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.forex.pojo.Client;
import com.forex.util.HibernateUtil;
public class RegisterService {
    
	/**
	 * @author sravani
	 * @method register
	 * @param user:Client
	 * @description this will to insertion into client table
	 * @return boolen
	 */
	
	public boolean register(Client user){
	     Session session = HibernateUtil.openSession();
//	     if(isUserExists(user)) return false;  
	     
	     Transaction tx = null;
	     try {
	         tx = session.getTransaction();
	         tx.begin();
	         session.save(user);
	         System.out.println(user.getUsername());
	         tx.commit();
	     } catch (Exception e) {
	         if (tx != null) {
	             tx.rollback();
	         }
	         e.printStackTrace();
	         return false;
	     } finally {
	         session.close();
	     } 
	     return true;
	}
 
	/**
	 * @author sravani
	 * @method isUserExists
	 * @param user:Client
	 * @description this will check wheater a user is exists in client table or not
	 * @return boolen
	 */
	public boolean isUserExists(Client user){
	     Session session = HibernateUtil.openSession();
	     boolean result = false;
	     Transaction tx = null;
	     try{
	         tx = session.getTransaction();
	         tx.begin();
	         Query query = session.createQuery("from client where cid='"+user.getClientId()+"'");
	         Client u = (Client)query.uniqueResult();
	         tx.commit();
	         if(u!=null) result = true;
	     }catch(Exception ex){
	         if(tx!=null){
	             tx.rollback();
	         }
	         return false;
	     }finally{
	         session.close();
	     }
	     return result;
	}
}
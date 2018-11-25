package com.forex.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.forex.pojo.Client;
import com.forex.pojo.CurrencyPair;
import com.forex.pojo.Order;
import com.forex.util.HibernateUtil;

public class OrderMatchingService {
	/**
	 * @author ajay
	 * @param orderid
	 * @description this will update the order status to success after completion of transaction
	 * @return boolean
	 */
	public static  boolean updateOrderStatus(String orderid){
		Session session = HibernateUtil.openSession();
        Transaction tx = null;
     
        try {
            tx = session.getTransaction();
            tx.begin();
            
            Query query = session.createQuery("update order_board set status='success'"+" where orderid="+orderid);
            query.executeUpdate();
            tx.commit();
            System.out.println("Order table statud update after transaction completion");
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            System.out.println("Exception raised in updateOrderStatus method");
            return false;
        } finally {
            session.close();
        }
        return true;
	}
	
	/**
	 * @author ajay
	 * @param orderid
	 * @description this will give the client details based on client id
	 * @return client
	 */
	public static  Client getClientDetails(int clientId){
		Session session = HibernateUtil.openSession();
        Transaction tx = null;
        Client user=null;
        try {
        	tx = session.getTransaction();
            tx.begin();
            
            Query query = session.createQuery("from com.forex.pojo.Client where client_id ="+ clientId);
            user = (Client)query.uniqueResult();
            
            System.out.println("heyy "+user.getClientId());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            System.out.println("Exception raised in OrderMatchingService.getClientDetails() method");
            return null;
        } finally {
            session.close();
        }
        return user;
	}
	
	
}





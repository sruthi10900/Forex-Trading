package com.forex.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.forex.pojo.Order;
import com.forex.util.HibernateUtil;

public class OrderService {
	static boolean[][] dp;
    static List<Order> buyList=new ArrayList<Order>();
	//method to place order
	/**
	 * @author sravani
	 * @method placeOrder
	 * @param order
	 * @description this will insert date into order_board table
	 * @return boolean
	 */
	public boolean placeOrder(Order order){
	     Session session = HibernateUtil.openSession();
//	     if(isUserExists(user)) return false;  
	     
	     Transaction tx = null;
	     try {
	         tx = session.getTransaction();
	         tx.begin();
	         session.save(order);
	         System.out.println(order.getOrderid());
	         tx.commit();
	     } catch (Exception e) {
	         if (tx != null) {
	             tx.rollback();
	         }
	         e.printStackTrace();
	     } finally {
	         session.close();
	     } 
	     return true;
	}
	
	/**
	 * @author sravani
	 * @method display
	 * @param List<Order>
	 * @description this will disply the order table tupel in console
	 * @return void
	 */
    static void display(List<Order> v)
    {
       System.out.println(v);
       buyList.addAll(v);
    }

    /**
	 * @author sravani
	 * @method updateDatabase
	 * @param orderid
	 * @param notionalValue
	 * @description this will disply the order table tupel in console
	 * @return void
	 */
    public boolean updateOrderBoard(int orderid, int notionalValue) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
        Transaction tx = null;       
        try {
            tx = session.getTransaction();
            tx.begin();
            String sql="update order_board set notional_value="+notionalValue+" where orderid="+orderid ;
            System.out.println(sql);
            Query query = session.createQuery(sql);
            int rr = query.executeUpdate();
     
            tx.commit();
            System.out.println("done");
            //res.sendRedirect("orderBlotter.jsp");
          
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
		return true;
        
		
	}
	
	
	
}

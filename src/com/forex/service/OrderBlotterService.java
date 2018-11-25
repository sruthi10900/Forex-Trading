package com.forex.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.forex.pojo.Client;
import com.forex.pojo.Order;
import com.forex.pojo.Trans_board;
import com.forex.util.HibernateUtil;
public class OrderBlotterService {

	/**
	 * @author sravani
	 * @param k
	 * @description this will give all the list of orders
	 * @return List<Order>
	 */
	public List<Order> getListOfOrders(int k){
		System.out.println("cid:"+k);
		
        List<Order> list = new ArrayList<Order>();
        Session session = HibernateUtil.openSession();
        Transaction tx = null;       
        try {
            tx = session.getTransaction();
            tx.begin();
            list = session.createQuery("from order_board where cid="+k+" order by order_date desc").list();                       
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}

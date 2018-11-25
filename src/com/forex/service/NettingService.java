package com.forex.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.forex.pojo.Order;
import com.forex.util.HibernateUtil;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class NettingService {
	/**
	 * @author sravani
	 * @param CurrencyPair
	 * @param date
	 * @description this will return list of users form order_board with status success
	 * @return List<Order>
	 */
public List<Order> getListOfUsers(String CurrencyPair,String date){
       List<Order> list = new ArrayList<Order>();
       Session session = HibernateUtil.openSession();
       Transaction tx = null;       
       try {
           tx = session.getTransaction();
           tx.begin();
           list = session.createQuery("from order_board where currency_pair='"+CurrencyPair+"' and status='success' and value_date like '"+date+"%'").list();     
          
//           System.out.println("heyy  "+list.get(0).getValue_date());
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
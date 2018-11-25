package com.forex.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.forex.pojo.Client;
import com.forex.pojo.Trans_board;
import com.forex.util.HibernateUtil;

public class TradeBlotterService {

	/**
	 * @author sravani
	 * @method getListOfTrans
	 * @description this will get the listOfTrans from trans_board table
	 * @return List<Trans_board>
	 */
public List<Trans_board> getListOfTrans(){
       List<Trans_board> list = new ArrayList<Trans_board>();
       Session session = HibernateUtil.openSession();
       Transaction tx = null;       
       try {
           tx = session.getTransaction();
           tx.begin();
           list = session.createQuery("from trans_board").list();                       
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
	/**
	 * @author sravani
	 * @param date
	 * @description get the transactions details based on date selected
	 * @return
	 */
	public static List<Trans_board> getTransByDate(String date){
		Session session = HibernateUtil.openSession();
		       Transaction tx = null;
		       List<Trans_board> transList = new ArrayList<Trans_board>();
		       try {
		           tx = session.getTransaction();
		           tx.begin();
		           
		           transList = session.createQuery("from trans_board where trans_date like '"+date+"%'").list();
		       } catch (Exception e) {
		           if (tx != null) {
		               tx.rollback();
		           }
		           e.printStackTrace();
		       } finally {
		           session.close();
		       } 
		       if(transList.size()>0)
		       	return transList;
		       else
		       	return transList;
	}
}
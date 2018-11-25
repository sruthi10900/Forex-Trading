package com.forex.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.forex.pojo.Trans_board;
import com.forex.util.HibernateUtil;

public class TransBoardService {
	
	Transaction tx = null;
	Session session = HibernateUtil.openSession();
	/**
	 * @author sravani
	 * @method insert
	 * @param tb:Trans_board
	 * @description this will insertion task into Trans_board table
	 * @return boolean
	 */
	public boolean insert(Trans_board tb) {
		int c=0;
		try {
	         tx = session.getTransaction();
	         tx.begin();
	         session.save(tb);
	         System.out.println("session got saved in insert mtd of TransBoardService");
	         tx.commit();
	     } catch (Exception e) {
	         if (tx != null) {
	             tx.rollback();
	         }
	         e.printStackTrace();
	         c++;
	     } finally {
	         session.close();
	     } 
		if(c==0)
			return true;
		else
			return false;
	}

}

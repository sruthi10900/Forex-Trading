package com.forex.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.forex.pojo.CurrencyPair;
import com.forex.util.HibernateUtil;

public class CurrencyService {
	
	static HashMap<String, String> range=new HashMap<String, String>(); 
	
	public CurrencyService() {
		// TODO Auto-generated constructor stub
		range.put("EUR/GBP", "1.4473,11.7117");
		range.put("EUR/USD", "1.5634,13.5678");
		range.put("EUR/CHF", "1.0073,12.0099");
		range.put("GBP/USD", "0.0094,21.0078");
		range.put("EUR/AUD", "1.3003,14.3017");
		range.put("GBP/NZD", "1.0012,61.0017");
		range.put("NZD/CAD", "1.8973,18.8999");
		range.put("TRY/JPY", "0.0073,2.0099");
		
	}
	
	/**
	 * @author sravani
	 * @param currencyPair
	 * @param type
	 * @description this will calculate the rate of currency pair based on database table values
	 * @return double
	 */
	public double getRate(String currencyPair,String type){
		Session session = HibernateUtil.openSession();
        Transaction tx = null;
        CurrencyPair c=null;
        try {
            tx = session.getTransaction();
            tx.begin();
            
            Query query = session.createQuery("from com.forex.pojo.CurrencyPair where currency_pair = '"+currencyPair+"'");
            c = (CurrencyPair)query.uniqueResult();
           
            System.out.println("heyy "+c.getCurrency_pair());
            tx.commit();
            if(type.equals("buy"))
            	return c.getBid();
            else return c.getAsk_price();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return 1.233;
        
	}
	
	/**
	 * @author sravani
	 * @description this will get the list of all currency pairs
	 * @return List<CurrencyPair>
	 */
	public List<CurrencyPair> getListOfCurrencies(){
		Session session = HibernateUtil.openSession();
        Transaction tx = null;
      
        List<CurrencyPair> list = new ArrayList<CurrencyPair>();
        try {
            tx = session.getTransaction();
            tx.begin();
            System.out.println("hieee");
            list = session.createQuery("from currency").list();
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
	 * @description this is used to update the bid and ask price of all currency pairs dynamically
	 * @return void
	 */
	public void updateCurrencies(){
		Session session = HibernateUtil.openSession();
        Transaction tx = null;
        List<CurrencyPair> list = new ArrayList<CurrencyPair>();
        try {
            tx = session.getTransaction();
            tx.begin();
            list = session.createQuery("from currency").list();
            double bid,askPrice,bidTemp,bidRounded,askTemp,askRounded;
            double min,max;
            for (CurrencyPair u : list) {
            	
            	String currencyRange[]=range.get(u.getCurrency_pair()).split(",");
    
//		        min = Double.parseDouble(currencyRange[0]); //  Set To Your Desired Min Value
//		        max =  Double.parseDouble(currencyRange[1]); //    Set To Your Desired Max Value
//		        System.out.println("min value:"+min);
//		        System.out.println("max value:"+max);
		        
		        
		        min=1.4345;
		        max=4.6687;
		        bidTemp = (int)(Math.random()*((max-min)+1))+min; //    This Will Create 
		        bidRounded = Math.round(bidTemp * 10000.0) / 10000.0; // Creates Answer To 

		        askTemp = (int)(Math.random()*((max-min)+1))+min; //    This Will Create 
		        askRounded = Math.round(askTemp * 10000.0) / 10000.0; // Creates Answer To 

			       //The Nearest 100th, You Can Modify This To Change How It Rounds.
			       System.out.println("bid round value:"+bidRounded);
			       System.out.println("ask round value:"+askRounded);
		       
			       String hql = "UPDATE currency set bid = :bid,ask_price = :ask_price "  + 
		                    "WHERE id = :currency";
				   Query query = session.createQuery(hql);
		       
		       u.setBid(bidRounded);
		       u.setAsk_price(askRounded);
		       
		       query.setParameter("bid", u.getBid());
		       query.setParameter("ask_price", u.getAsk_price());
		       query.setParameter("currency", u.getCurrency_pair());
		       int result = query.executeUpdate();
		       System.out.println("Rows affected: " + result);
            }
            tx.commit();
            
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
   
        
	}
}
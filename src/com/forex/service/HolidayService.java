package com.forex.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.forex.pojo.Holidays;
import com.forex.util.HibernateUtil;

public class HolidayService {
	/**
	 * @author Ajay
	 * @param orderDate
	 * @param valueDate
	 * @param currencyPair
	 * @description this will check in holiday table for list of holidays and gives result
	 * @return boolean
	 */
	public static boolean isHoliday(String orderDate, String valueDate, String currencyPair) {
		boolean holidayResult=holidayInDbCheck(orderDate,valueDate,currencyPair);
		return holidayResult;
	}
	
	/**
	 * @author Ajay
	 * @param days
	 * @description this will add no.days given as parameter to the current date
	 * @return String
	 */
	public static String getvalueDate(int days) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		return sd.format(cal.getTime());
	}
	
	/**
	 * @author ajay
	 * @description this will give the current date
	 * @return String
	 */
	public static String getorderDate() {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		return sd.format(cal.getTime());
	}
	/**
	 * @author ajay
	 * @param date
	 * description this will say given date is weekend or not
	 * @return boolean
	 */
	public static boolean isWeekend(String date) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		Date day=null;
		try {
			day=sd.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.setTime(day);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			return true;
		else
			return false;
	}
	/**
	 * @author ajay
	 * @param date
	 * @description this will say given date is saturday or not 
	 * @return boolean
	 */
	public static boolean isSaturday(String date) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		Date day=null;
		try {
			day=sd.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.setTime(day);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			return true;
		else
			return false;
	}
	/**
	 * @author ajay
	 * @param date
	 * @description this will say given date is sunday or not 
	 * @return boolean
	 */
	public static boolean isSunday(String date) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		Date day=null;
		try {
			day=sd.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.setTime(day);
		System.out.println(day);
		System.out.println(cal.getTime());
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			return true;
		else
			return false;
	}
	/**
	 * @author ajay
	 * @param date
	 * @param days
	 * @description this will add no.of days given to the given date
	 * @return String
	 */
	public static String getvalueDate(String date, int days) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		Date day=null;
		try {
			day=sd.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal=Calendar.getInstance();
		cal.setTime(day);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return sd.format(cal.getTime());
	}
	/**
	 * @author ajay
	 * @param date
	 * @description this will tell given date is weekend or not
	 * @return String
	 */
	public static String dateWeekendCheck(String date) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		Date day=null;
		try {
			day=sd.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal=Calendar.getInstance();
		cal.setTime(day);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
			return "saturday";
		}
		else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			return "sunday";
		}
		else
			return "weekday";
	}
	/**
	 * @author ajay
	 * @param date
	 * @param currencyPair
	 * @description this will check for holidays for given currencypairs in db
	 * @return boolean
	 */
	public static boolean dayHolidayDbCheck(String date, String currencyPair) {
		Session session = HibernateUtil.openSession();
        Transaction tx = null;
        List<Holidays> holidays= new ArrayList<Holidays>();
        try {
        	tx = session.getTransaction();
            tx.begin();
            String first= currencyPair.toString().substring(0,3);
            String second= currencyPair.toString().substring(4,7);
            holidays = session.createQuery("from com.forex.pojo.Holidays where currency in ('"+first+"','"+second+"') and holiday_date='"+date+"'").list();
            if(holidays.size()>0)
            	return true;
            else
            	return false;
        }
        catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("This exception is from HolidayService.dayHolidayDbCheck() method");
        }
        finally {
        	session.close();
        }
		return false;	
	}
	/**
	 * @author ajay
	 * @param orderDate
	 * @param valueDate
	 * @param currencyPair
	 * @description this will check for holidays for given currencypairs lies in b/w dates
	 * @return boolean
	 */
	public static boolean holidayInDbCheck(String orderDate,String valueDate, String currencyPair) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        Holidays ob=new Holidays();
        int count=0;
        try {
            tx = session.getTransaction();
            tx.begin();
            System.out.println("entered into Holiday Service");
            
            String first= currencyPair.toString().substring(0,3);
            String second= currencyPair.toString().substring(4,7);
            
            Query query = session.createQuery("from com.forex.pojo.Holidays where currency in ('"+first+"','"+second+"') and holiday_date between '"+orderDate+"' and '"+valueDate+"'");
            count = query.getFirstResult();
            ob= (Holidays)query.uniqueResult();
            if(ob!=null) return true;
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
       return false;
       
	}
	
	/**
	 * @author ajay
	 * @param cur1
	 * @param cur2
	 * @description this will give list of holidays for given currency pairs
	 * @return List<Holidays
	 */
	public List<Holidays> getListOfHolidays(String cur1, String cur2){
		
		Session session = HibernateUtil.openSession();
        Transaction tx = null;
        List<Holidays> list = new ArrayList<Holidays>();
        try {
            tx = session.getTransaction();
            tx.begin();
            System.out.println("hiee1e");
           String sql="from com.forex.pojo.Holidays where currency = '"+cur1+"'"+" or currency= '"+cur2+"'" ;
        
           System.out.println(sql);
           list=session.createQuery(sql).list();
           System.out.println("this is testing "+list.get(2).getHoliday_date());
            //list = session.createQuery("from com.forex.pojo.Holidays where currency in ('\"+cur1+\"','\"+cur2+\"')").list();                       
            tx.commit();
            System.out.println(list);
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

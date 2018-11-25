package com.forex.service;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.forex.encryption.Sha512Encryption;
import com.forex.pojo.Client;
import com.forex.util.HibernateUtil;
 
public class LoginService {
 
	/**
	 * @author sravani
	 * @param clientId
	 * @param password
	 * @description this will authenticate Admin
	 * @return boolean
	 */
	public boolean authenticateAdmin(String clientId, String password) {
        if( clientId.equals("admin") && password.equals("admin")){
            return true;
        }else{
            return false;
        }
    }
	
	/**
	 * @author sravani
	 * @param clientId
	 * @param password
	 * @description this will authenticate user
	 * @return boolean
	 */
    public boolean authenticateUser(String clientId, String password) {
    	try {
	        Client user = getUserByUserId(clientId);      
	        String decryptPassword = Sha512Encryption.decryptString(password, user.getSalt());
	        if(user!=null && String.valueOf(user.getClientId()).equals(clientId) && user.getPassword().equals(decryptPassword)){
	            return true;
	        }else{
	            return false;
	        }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    /**
	 * @author sravani
	 * @param clientId
	 * @description this gives the user tuple based on client id
	 * @return Client
	 */
    public Client getUserByUserId(String clientId) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        Client user = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            
            Query query = session.createQuery("from com.forex.pojo.Client where client_id = '"+clientId+"'");
            user = (Client)query.uniqueResult();
            
            System.out.println("heyy "+user.getClientId());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
    /**
	 * @author sravani
	 * @description this gives list of users in client table from db
	 * @return List<Client>
	 */
    public List<Client> getListOfUsers(){
        List<Client> list = new ArrayList<Client>();
        Session session = HibernateUtil.openSession();
        Transaction tx = null;       
        try {
            tx = session.getTransaction();
            tx.begin();
            list = session.createQuery("from client").list();                       
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
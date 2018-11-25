package com.forex.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.forex.encryption.AcknowledgementMail;
import com.forex.pojo.Broker;
import com.forex.pojo.Client;
import com.forex.pojo.Order;
import com.forex.pojo.Trans_board;
import com.forex.service.CurrencyService;
import com.forex.service.OrderMatchingService;
import com.forex.service.TransBoardService;
import com.forex.util.HibernateUtil;


@WebServlet("/OrderMatchingServlet")
public class OrderMatchingServlet extends HttpServlet{
	private static final int THRESHOLD = -500;
//	static int count=0;
	static int tid=1;
	static HttpServletRequest request=null;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.request=req;
		super.doPost(req, resp);
	}
	
	/**
	 * @author: ajay, sravani
	 * @method:orderMatching
	 * @description: takes the order details from db and calls matching methods
	 * @return: void
	 */
	public static void orderMatching() {
		
		List<Order> sell = new ArrayList<Order>();
		List<Order> buy=new ArrayList<Order>();
		List<Order> list=new ArrayList<Order>();
		Session session = HibernateUtil.openSession();
		Transaction tx=null;
		
		try {
			tx = session.getTransaction();
	        tx.begin();
	         
	        sell = session.createQuery("from com.forex.pojo.Order where status='pending' and t_ype='sell'").list();
	        buy = session.createQuery("from com.forex.pojo.Order where status='pending' and t_ype='buy'").list();
	         
	       if( oneToOneMatching(sell,buy)) {
		        System.out.println("calling plain vennila case method");	    	   
	       }
	    
	    	   System.out.println("calling manytomany method"); 
	    	   list = session.createQuery("from com.forex.pojo.Order where status='pending' and t_ype in ('sell','buy')").list();
		       
		       manyToManyMatching(list);
	    	   
	      
	        
		}
		catch(Exception e) {
			System.out.println("this is not running");
			e.printStackTrace();
			if(tx!=null){
	             tx.rollback();
	         }
		}
		finally {
			session.close();
		}
	}
	
	
	/**
	 * @author ajay
	 * @method matching
	 * @param sell
	 * @param buy
	 * @description it will do one to one matching
	 * @return boolean
	 */
	static boolean oneToOneMatching(List<Order> sell, List<Order> buy) {
		String temp="";
		TransBoardService trans=new TransBoardService();
		System.out.println("entered into matching method");
		for(int i=0;i<sell.size();i++) {
			
			for(int j=0;j<buy.size();j++) {
				
				if((sell.get(i).currency_pair.equals(buy.get(j).currency_pair)) && (sell.get(i).notional_value==buy.get(j).notional_value)) {

					String currency_pair=buy.get(j).currency_pair;
					int volume=buy.get(i).notional_value;
					String type=buy.get(i).getT_ype();
					CurrencyService currencyService = new CurrencyService();
				    
					double rate=currencyService.getRate(buy.get(i).currency_pair,type);
					double commission=volume*rate;
				
					String oid1=String.valueOf(sell.get(i).orderid);
					String oid2=String.valueOf(buy.get(j).orderid);
					
					boolean status1=OrderMatchingService.updateOrderStatus(oid1);
					boolean status2=OrderMatchingService.updateOrderStatus(oid2);
					System.out.println("status1 value is"+status1);
					System.out.println("status2 value is"+status2);
					if(status1 && status2) {
						//HttpSession session=request.getSession();
						System.out.println("Enter into Plain venniala mail");
						Client client1=OrderMatchingService.getClientDetails(sell.get(i).getCid());
						Client client2=OrderMatchingService.getClientDetails(buy.get(j).getCid());
						AcknowledgementMail.sendMail(client1.getEmailId(), sell.get(i).orderid, client1.getUsername());
						AcknowledgementMail.sendMail(client2.getEmailId(), buy.get(j).orderid, client2.getUsername());
					}
					else {
						
					}
					System.out.println("Insert into trans table");
					return doTransInsertion(currency_pair, volume, commission);
					
				}
			
			}
		}
		return false;
		
	}
	
	/**
	 * @author ajay
	 * @method doTransInsertion
	 * @param currency_pair
	 * @param volume
	 * @param commission
	 * @description it will do insertion into trans_board table after successful matching
	 * @return boolean
	 */
	public static boolean doTransInsertion(String currency_pair,int volume,double commission) {
		Date d=new Date();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String trans_date=String.valueOf(sd.format(d));
		Trans_board tb=new Trans_board(tid++, currency_pair, volume, commission, trans_date);
		
		TransBoardService t=new TransBoardService();
		boolean result=t.insert(tb);
		
		if(result==true) {
			System.out.println("Insertion done in trans_board table");
			return true;
		}
		else {
			System.out.println("Insertion exception in trans_board table");
			return false;
		}
			
	}
	
	/**
	 * @author sravani
	 * @method manyToManyMatching
	 * @param orderList
	 * @description it will do many to many matching
	 * @return void
	 */
	public static void manyToManyMatching(List<Order> orderList) {
		String temp="";
		TransBoardService trans=new TransBoardService();
		
		System.out.println("entered into many-to-many mapping");
		
		Broker b = Broker.getInstance();
		
//		System.out.println("initial broker count: "+b.getCount());
//		System.out.println("orderList size: "+orderList.size());
		
		for(int i=0;i<orderList.size();i++) {
				String currency_pair=orderList.get(i).currency_pair;
				int volume=orderList.get(i).notional_value;
				String type=orderList.get(i).getT_ype();
				
				CurrencyService currencyService = new CurrencyService();
			    
				double rate=currencyService.getRate(orderList.get(i).currency_pair,type);
				double commission=volume*rate;
			
				String oid1=String.valueOf(orderList.get(i).orderid);
				
				
				System.out.println("currency pair value: "+b.hm.get(currency_pair));
				
				
				if(orderList.get(i).getT_ype().equals("buy")) {
					
					if((b.hm.get(currency_pair) - orderList.get(i).getNotional_value())>=THRESHOLD) {
						b.hm.put(currency_pair,b.hm.get(currency_pair)-orderList.get(i).getNotional_value());
						
						if(doTransInsertion(currency_pair, volume, commission)) {
							boolean orderUpdateStatus=OrderMatchingService.updateOrderStatus(String.valueOf(orderList.get(i).getOrderid()));
							if(orderUpdateStatus) {
								//HttpSession session=request.getSession();
								Client client=OrderMatchingService.getClientDetails(orderList.get(i).getCid());
								AcknowledgementMail.sendMail(client.getEmailId(), orderList.get(i).orderid, client.getUsername());
							}
						}
						
					}
					
				}
				else {
					b.hm.put(currency_pair,b.hm.get(currency_pair)+orderList.get(i).getNotional_value());
					
					if(doTransInsertion(currency_pair, volume, commission)) {
						boolean orderUpdateStatus=OrderMatchingService.updateOrderStatus(String.valueOf(orderList.get(i).getOrderid()));
						if(orderUpdateStatus) {
							//HttpSession session=request.getSession();
							Client client=OrderMatchingService.getClientDetails(orderList.get(i).getCid());
							AcknowledgementMail.sendMail(client.getEmailId(), orderList.get(i).orderid, client.getUsername());
						}
					}
					
				}
			
		}// for loop

		System.out.println("EUR/USD value: "+b.hm.get("EUR/USD"));
	}//manyto many
}




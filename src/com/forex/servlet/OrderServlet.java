package com.forex.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forex.pojo.Client;
import com.forex.pojo.Order;
import com.forex.service.CurrencyService;
import com.forex.service.HolidayService;
import com.forex.service.OrderService;

@WebServlet("OrderServlet")
public class OrderServlet extends HttpServlet{
	static String currencyPair=null;
	static int notionalValue;
	static String orderDate=null;
	static String valueDate=null;
	//static String orderDate="2018-08-31";
	static HttpServletRequest request=null;
	static HttpServletResponse response=null;
	/**
	 * @author ajay
	 * @method doPost
	 * @param request: HttpServletRequest, response: HttpServletResponse
	 * @description: takes the values from order.jsp and calculates orderDate and valueDate
	 * @return void
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	
	    request=req;
	    response=res;
	    PrintWriter out = response.getWriter();
	    
	    currencyPair = request.getParameter("currency-pair");
	    notionalValue = Integer.parseInt(request.getParameter("volume"));
	    
	    System.out.println("my currency is : "+currencyPair);
	    
	    SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		//orderDate=HolidayService.getorderDate();
		
		System.out.println(orderDate);
		
	
		valueDate=HolidayService.getvalueDate(orderDate,2);
		System.out.println("calculated Value date "+ valueDate);
		
		if(HolidayService.isWeekend(orderDate) || HolidayService.dayHolidayDbCheck(orderDate, currencyPair)) {
			out.println("<script>if(confirm('order cant be placed on holidays')){window.location.href='order.jsp'}else{window.location.href='order.jsp'}</script>");			
		}
		else {
			if(HolidayService.isSaturday(valueDate)) {
				valueDate = HolidayService.getvalueDate(4);
			}
			else if(HolidayService.isSunday(valueDate)) {
				valueDate = HolidayService.getvalueDate(3);
			}
			if(HolidayService.isHoliday(orderDate,valueDate,currencyPair)) {
				System.out.println("entered into holiday method checking from database");
				valueDate = HolidayService.getvalueDate(3);
				
				if(HolidayService.isSunday(valueDate)) {
					valueDate = HolidayService.getvalueDate(valueDate,1);
				}
				
				if(HolidayService.isSaturday(valueDate)) {
					valueDate = HolidayService.getvalueDate(valueDate,3);
				}
			}
			
			System.out.println("this is the updated value date "+valueDate);
			
			placeOrder();
		}
	}
	
	/**
	 * @author ajay
	 * @method palceOrder
	 * @description: place the order given by the client in order_board table
	 * @return void
	 */
	
	public static void placeOrder() throws IOException {
		String type = request.getParameter("type");
		PrintWriter out = response.getWriter();
			
	    CurrencyService currencyService = new CurrencyService();
	    double rate = currencyService.getRate(currencyPair,type);
		 
	    Client client = (Client)request.getSession().getAttribute("client");  
		    
		    
	    Order order = new Order(client.getClientId(),currencyPair,orderDate,valueDate,notionalValue,rate,"pending",type);
		             
	     try { 
	        
	    	 OrderService orderService = new OrderService();
	         boolean result = orderService.placeOrder(order);      
	          
	         if(result){
	             response.sendRedirect("orderBlotter.jsp");  
	         }
	         else{
	             out.println("<h1>Order Placement Failed because of Order insertion exception</h1>");
	             out.println("Try again to place order: <a href=order.jsp>Try Again</a>");
	         }
	         out.println("</center>");
	         out.println("</body>");
	         out.println("</html>");
	     } finally {       
	         out.close();
	     }
	}	
}
package com.forex.servlet;
	import java.io.IOException;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import com.forex.service.OrderService;
@WebServlet("OrderModifyServlet")
public class OrderModifyServlet extends HttpServlet {
		
	/**
	 * @author: sruthi
	 * @method: doPost
	 * @param:  request: HttpServletRequest, response:  HttpServletResponse
	 * @description: will takes the orderId and notionalValue from frontend and update the order tuple of orderId(database)
	 * @return void
	 */
	    public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	System.out.println("hello");
	    String k= (request.getParameter("orderId"));   
		//     String k="15";
//		     time to get the current value date
		    String p= request.getParameter("notionalValue");
		    System.out.println(k+" "+p);
		    int orderid=Integer.parseInt(k);
		    int notionalValue=Integer.parseInt(p);
		    System.out.println(orderid);
		    OrderService orderModifyService=new OrderService();
		    boolean result=orderModifyService.updateOrderBoard(orderid,notionalValue);
		    if(result){
	            
	             response.sendRedirect("orderBlotter.jsp");  
	         }
	        
	
}
	    
	    public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    }
	    
}
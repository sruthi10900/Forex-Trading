package com.forex.servlet;


import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forex.pojo.Client;
import com.forex.service.CurrencyService;
import com.forex.service.LoginService;
 
@WebServlet("LoginServlet")
public class LoginServlet extends HttpServlet implements Runnable{
	
	  long lastprime = 0;                    
	  Date lastprimeModified = new Date();   
	  Thread searcher;
	  
	  
	  /****
	   * author: sravani
	   * method: init()
	   * description: to initilse the thread with min priority
	   */
	  
	  public void init() throws ServletException {
	    searcher = new Thread(this);
	    searcher.setPriority(Thread.MIN_PRIORITY);
	    searcher.start();
	  }

	  
	  public void run() {
	    long candidate = 2;
	    while (true) {     
	      candidate += 2;                    
	      try {
	        searcher.sleep(5000);
	        System.out.println("entered thread: "+candidate);
	        CurrencyService orderService = new CurrencyService();
		   	orderService.updateCurrencies();
	      }
	      catch (InterruptedException ignored) { }
	      lastprime = 1;   
	    }
	  }
	  
	  public void destroy() {
		 searcher.stop();
	  }
	
	  
	  /****
	   * @author: sravani
	   * @method: service()
	   * @parameters: HttpServletRequest request, HttpServletResponse response
	   * @description: it will take the parameters frm login.jsp page and do login redirection actions
	   * @return: void
	   */
	  
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
	     String clientId = request.getParameter("userId");   
	     String password = request.getParameter("password");
	     LoginService loginService = new LoginService();
	     
	     if(loginService.authenticateAdmin(clientId, password)) {
	    	 request.getSession().setAttribute("admin", "admin");      
	         response.sendRedirect("tradeBlotter.jsp");
	     }
	     else {
	    	 boolean result = loginService.authenticateUser(clientId, password);
	         Client client = loginService.getUserByUserId(clientId);
	         
	         if(result == true){
	        	 System.out.println("client userid"+client.getClientId());
	             request.getSession().setAttribute("client", client);      
	             response.sendRedirect("order.jsp");
	         }
	         else{
	        	 request.getSession().setAttribute("loginFail", "&nbsp;&nbsp;Login is not done, Please try Again else register"); 
	             response.sendRedirect("login.jsp");
	         }
	     }
	     
    }
 
}
package com.forex.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.forex.pojo.Client;
import com.forex.pojo.Order;
import com.forex.service.LoginService;
import com.forex.service.NettingService;

@WebServlet("/NettingServlet")

public class NettingServlet  extends HttpServlet{
	static String flag="";
	
	/**
	 * @author sravani
	 * @doPost
	 * @description will take the values from netting.jsp and calls currencyNettingResult and sets the result netting.jsp
	 * @return void
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
	     String currencyPair = request.getParameter("currency-pair");   
	     String valueDate = request.getParameter("value-date");
	     
	     System.out.println("date value: "+valueDate);  
	     System.out.println("pair: "+currencyPair);  
	     

	     NettingService nettingService = new NettingService();
	     List<Order> orderList = nettingService.getListOfUsers(currencyPair,valueDate);
	  
//	     System.out.println("Size of netting orders: "+orderList.get(0).status);
	     MyResult result = currencyNettingResult(orderList);
	     
	     
	     System.out.println("first: "+result.getFirst());
	     System.out.println("second: "+result.getSecond());
	     System.out.println("pair: "+currencyPair);
		 double f=result.getFirst();
		 String k=f+"";
		  
		 double s=result.getSecond();
		 String l=s+"";
		 request.setAttribute("first", k);
	     request.setAttribute("second", l);
	     request.setAttribute("dat", valueDate);
	     request.setAttribute("pair", currencyPair);
	     
	     flag=flag+"a";
	     request.setAttribute("bool", flag);
	    //System.out.println( request.getAttribute("first"));
	     
	     RequestDispatcher view = getServletContext().getRequestDispatcher("/netting.jsp"); 
	    view.forward(request,response);
//	     
	     //response.sendRedirect("/netting.jsp");
	   
	}
	
	/**
	 * @author sravani
	 * @method currencyNettingResult
	 * @description it will do netting logic
	 * @return void
	 */
	
	public MyResult currencyNettingResult(List<Order> orderList) {
		int sign;
		double quote=0,base=0;

		double rate;
		System.out.println("hello");
		for(Order o: orderList) {
			sign=1;
			if(o.t_ype.toString().equals("sell")) {
				
				sign=-1;
			}
			rate=o.rate;
			base+=sign*o.notional_value;
			quote+=(-1)*sign* rate * o.notional_value;
		}
		MyResult ob=new MyResult(base,quote);
		return ob;
		
	}
}

final class MyResult {
    private final double first;
    private final double second;

    public MyResult(double first, double quote) {
        this.first = first;
        this.second = quote;
    }

    public double getFirst() {
        return first;
    }

    public double getSecond() {
        return second;
    }
}
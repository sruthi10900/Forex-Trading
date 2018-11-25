package com.forex.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forex.pojo.Holidays;
import com.forex.pojo.Order;
import com.forex.service.HolidayService;
import com.forex.service.OrderService;
	 @WebServlet("HolidayClientServlet")
	public class HolidayClientServlet extends HttpServlet {
		 
		/**
		 * @author sruthi
		 * @description takes the values from holidays.jsp and get the holiday list from db and shows result in holidayClient.jsp
		 */
	    public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    
	    	String k= (request.getParameter("currency1")); 
	    	request.setAttribute("cur1", k);
	    	String l= (request.getParameter("currency2")); 
	    	request.setAttribute("cur2", l);
		    HolidayService holidayService=new HolidayService();
		    List<Holidays> orderList= holidayService.getListOfHolidays(k,l); 
		    RequestDispatcher view = getServletContext().getRequestDispatcher("/holidayClient.jsp"); 
		    view.forward(request,response);
}
	    public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    }
	    
}
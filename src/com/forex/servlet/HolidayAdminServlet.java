package com.forex.servlet;
import java.io.*;
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
	 @WebServlet("HolidayAdminServlet")
	public class HolidayAdminServlet extends HttpServlet {
		
		 /**
		  * @author sruthi
		  * @description get the currency data from holidays.jsp and fecth holiday data from db and puts results in holidayAdmin.jsp
		  */
	    public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    
	    	String k= (request.getParameter("currency1")); 
	    	request.setAttribute("cur1", k);
	    	String l= (request.getParameter("currency2")); 
	    	request.setAttribute("cur2", l);
		    HolidayService holidayService=new HolidayService();
		    List<Holidays> orderList= holidayService.getListOfHolidays(k,l); 
		    RequestDispatcher view = getServletContext().getRequestDispatcher("/holidayAdmin.jsp"); 
		    view.forward(request,response);
}
	    public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    }
	    
}
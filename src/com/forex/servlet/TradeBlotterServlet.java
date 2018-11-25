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
import com.forex.pojo.Trans_board;
import com.forex.service.*;
	 @WebServlet("TradeBlotterServlet")
	public class TradeBlotterServlet extends HttpServlet {
		/**
		 * @author sravani
		 * @description this will get the trans details from db  and dispatch to tradeBlotter.jsp
		 */
	    public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    
	    	String date= request.getParameter("value-date"); 
	    	System.out.println("This is the date format from tradeblotter "+date);
	    	request.setAttribute("date", date);
	    	
	    	
		    List<Trans_board> orderList=  TradeBlotterService.getTransByDate(date); 
		    RequestDispatcher view = getServletContext().getRequestDispatcher("/tradeBlotter.jsp"); 
		    view.forward(request,response);
}
	    public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    }
	    
}

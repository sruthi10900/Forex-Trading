/*package com.forex.encryption;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.forex.service.HolidayService;

public class DateSample {

	public static void main(String[] args) {
		
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		//String orderDate=HolidayService.getorderDate();
		String orderDate="2018-08-29";
		System.out.println(orderDate);
		
	
		String valueDate=HolidayService.getvalueDate(orderDate,2);
		System.out.println("calculated Value date "+ valueDate);
		
		if(HolidayService.isWeekend(orderDate) || HolidayService.dayHolidayDbCheck(orderDate, "EUR/USD")) {
			System.out.println("<h1>Order can't place on holidays</h1>");
			System.out.println("Try again to place order: <a href=order.jsp>Try Again</a>");
		}
		else {
			if(HolidayService.isSaturday(valueDate)) {
				valueDate = HolidayService.getvalueDate(4);
			}
			else if(HolidayService.isSunday(valueDate)) {
				valueDate = HolidayService.getvalueDate(3);
			}
			if(HolidayService.isHoliday(orderDate,valueDate,"EUR/USD")) {
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
			
		}
	}
}*/
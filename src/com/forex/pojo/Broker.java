package com.forex.pojo;

import java.util.HashMap;

public class Broker {
	 
	private static Broker single_instance = null;
	 
    // variable of type String
    public int count;
    public HashMap<String,Integer> hm;
    // private constructor restricted to this class itself
    private Broker()
    {
    	hm=new HashMap<String,Integer>();
    	hm.put("EUR/USD", 0);
    	hm.put("EUR/GBP", 0);
    	hm.put("EUR/CHF", 0);
    	hm.put("GBP/USD", 0);
    	hm.put("GBP/NZD", 0);
    	hm.put("EUR/AUD", 0);
    	hm.put("NZD/CAD", 0);
    	hm.put("TRY/JPY", 0);
    }
 
	// static method to create instance of Broker class
    public static Broker getInstance()
    {
        if (single_instance == null)
            single_instance = new Broker();
 
        return single_instance;
    }
    
}
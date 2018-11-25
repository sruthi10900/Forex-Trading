package com.forex.encryption;
import java.io.*;
import com.forex.servlet.OrderMatchingServlet;

public class MatchingTrigger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MatchThread thread = new MatchThread();
		thread.start();
	}

}
/**********
 * @author: Ajay
 * @method run()
 * @description: This is to match the orders for every 1 minute
 * @return: void
 */
class MatchThread extends Thread{
	public void run() {
		while(1==1) {
			System.out.println("This is a thread");
			try {
				OrderMatchingServlet.orderMatching();
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
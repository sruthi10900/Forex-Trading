package com.forex.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forex.encryption.Sha512Encryption;
import com.forex.pojo.Client;
import com.forex.service.RegisterService;
 
@WebServlet("RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
   /*******
    * @author: sravani
    * @method: doPost()
    * @parameters: HttpServletRequest request, HttpServletResponse response
    * @description: This will take the parameters from register.jsp and insert the data into client table
    * @return : void
    */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
	     response.setContentType("text/html;charset=UTF-8");
	     PrintWriter out = response.getWriter();
	     try { 
	     String username = request.getParameter("userName");
	     String password = request.getParameter("password");
	     String email = request.getParameter("emailId");
	     long phoneNo = Long.parseLong(request.getParameter("phoneNo"));
	     int govtId = Integer.parseInt(request.getParameter("govtId"));
	     int bankNo =Integer.parseInt(request.getParameter("bankNo"));
	     
	     String encryptedPassword=Sha512Encryption.get_SHA_512_SecurePassword(password);
	     System.out.println("password encrypted "+encryptedPassword);
	     String saltvalue=new String(Sha512Encryption.saltvalue);
	     System.out.print("salt value is "+saltvalue);
	     
	     /*InsertEncryption ie=new InsertEncryption();
	     ie.Insert(govtId, bankNo, email, encryptedPassword, phoneNo, username);*/
	     
	     Client user = new Client(govtId,username,encryptedPassword,email,phoneNo,bankNo,saltvalue);
	             
	      
	         RegisterService registerService = new RegisterService();
	         
	         boolean result = registerService.register(user);      
	        
	         if(result == true){
	        	 request.getSession().setAttribute("registerSuccess", "&nbsp;&nbsp;Registration Successful, Please Login");
	             response.sendRedirect("login.jsp");
	         }
	         else{
	        	 request.getSession().setAttribute("registerFail", "&nbsp;&nbsp;Registration is not done, Please try Again"); 
	        	 response.sendRedirect("login.jsp");
	         }
	     } finally {       
	         out.close();
	     }
	} 
 
}
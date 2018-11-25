package com.forex.encryption;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Sha512Encryption {
	
	private static final int SALT_SIZE = 64;	
	public static String saltvalue;
	
	/**
	 * @author Ajay
	 * @method get_SHA_512_SecurePassword
	 * @param passwordToHash: String
	 * @return String
	 * @description this will do sha512 hashing encryption to the given string
	 */
	public static String get_SHA_512_SecurePassword(String passwordToHash){
		String generatedPassword = null;
		    try {
		         MessageDigest md = MessageDigest.getInstance("SHA-512");
		         md.update(generateSalt());
		         byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
		         StringBuilder sb = new StringBuilder();
		         for(int i=0; i< bytes.length ;i++){
		            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		         }
		         generatedPassword = sb.toString();
		        } 
		       catch (NoSuchAlgorithmException e){
		        e.printStackTrace();
		       }
		    return generatedPassword;
		}
	
	/**
	 * @author Ajay
	 * @method generateSalt
	 * @return byte[]
	 * @description this will generate a random byte array & appends array values into a string(saltvalue)
	 */
	private static byte[] generateSalt() { 
		StringBuffer str=new StringBuffer();
		
		SecureRandom random = new SecureRandom(); 
		byte[] salt = new byte[SALT_SIZE]; 
        random.nextBytes(salt); 
        
        System.out.println("This is salt string");
        
        for(int i=0;i<salt.length;i++) {
        	//System.out.print(salt[i]);
        	str.append(String.valueOf(salt[i])).append(",");
        }
        saltvalue=str.substring(0,str.length()-1);
        System.out.println(saltvalue);
        return salt; 
    } 
	
	
	/**
	 * @author Ajay
	 * @method decryptString
	 * @param password: String, salt: String
	 * @return String
	 * @description this will do sha512 hashing decryption to the given string and salt 
	 */
	public static String decryptString(String password, String salt) {
		String str[]=salt.split(",");
		byte[] saltvalue=new byte[str.length];
		int k=0;
		for(String s:str)
			saltvalue[k++]=Byte.parseByte(s);
		
		String generatedPassword = null;
	    try {
	         MessageDigest md = MessageDigest.getInstance("SHA-512");
	         md.update(saltvalue);
	         byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
	         StringBuilder sb = new StringBuilder();
	         for(int i=0; i< bytes.length ;i++){
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	         }
	         generatedPassword = sb.toString();
	        } 
	       catch (NoSuchAlgorithmException e){
	        e.printStackTrace();
	       }
	    return generatedPassword;
	}
}
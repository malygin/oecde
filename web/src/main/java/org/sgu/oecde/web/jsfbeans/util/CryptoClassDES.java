/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.web.jsfbeans.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author MS
 */
public class CryptoClassDES {
    
 //   private final static byte[] KEY = { 11, 32, 73, 24, 5, 66, 7, 18};
    
      private final static byte[] keyBytes = { 11, 32, 73, 24, 5, 66, 7, 18};
      private final static SecretKey key = new SecretKeySpec(keyBytes, "DES");
      private static Cipher cipher;
//    
//    CryptoClassOTP() throws NoSuchAlgorithmException, NoSuchPaddingException{
//        cipher = Cipher.getInstance("DES");
//    }
//    
//    public static String encrypt(String encryptMe) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException{
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//        return new String(cipher.doFinal(encryptMe.getBytes()));
//    }
//    
//    public static String decrypt(String decryptMe) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        return new String(cipher.doFinal(decryptMe.getBytes()));
//    }
    
    public static String encrypt(String encryptMe) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        String encrypted = new String(cipher.doFinal(encryptMe.getBytes()));
        String result="";
        for(int a=0;a<encrypted.length();a++){
            String strpart="";
            strpart+=encrypted.charAt(a);
            byte[] part = strpart.getBytes();
            //int j = 0;
            for(int i=0;i<part.length;i++){
                result += part[i];
                
            }
            if(a!=encrypted.length()-1)result += ":";
        }
        return result;
    }
    
    public static String decrypt(String decryptMe) throws InvalidKeyException, 
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        String result;
        String[] strpart= decryptMe.split(":");
        byte[] bytepart = new byte[strpart.length];
        for(int a=0;a<strpart.length;a++){
           
               bytepart[a] = Byte.parseByte(strpart[a]);
           
            
        }
        result = new String(bytepart);
        cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        String returnMe = new String(cipher.doFinal(result.getBytes()));
        return returnMe;
    }
}

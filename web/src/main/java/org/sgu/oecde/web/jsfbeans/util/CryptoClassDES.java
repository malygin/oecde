/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.web.jsfbeans.util;

import java.io.UnsupportedEncodingException;
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
 // кодирование полученной строки     
//разбиение кодированной строки на символы получение байтов
//      байты одного символа разделены ~
//      между байтами разных символов :
    public static String encrypt(String encryptMe) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, UnsupportedEncodingException{
        cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(encryptMe.getBytes("UTF-8"));
        String codeForUrl=new String();
        
        for(int i=0;i<encrypted.length;i++){
         codeForUrl+=encrypted[i]+":";
        }       
        
       return codeForUrl;
           }
        
    public static String decrypt(String decryptMe) throws InvalidKeyException, 
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, 
            BadPaddingException, UnsupportedEncodingException{
       String url=decryptMe;
        byte[] decodeFromUrl=new byte[url.split(":").length];
        for(int i=0;i<url.split(":").length;i++){
          decodeFromUrl[i]=(byte) Integer.parseInt(url.split(":")[i]);
        }
        cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] returnMe = cipher.doFinal(decodeFromUrl);
        return new String(returnMe);
    }
}

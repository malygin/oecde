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
        String encrypted = new String(cipher.doFinal(encryptMe.getBytes("UTF-8")));
        String result="";
        for(int a=0;a<encrypted.length();a++){
            String strpart="";
            strpart+=encrypted.charAt(a);
            byte[] part = strpart.getBytes();
            for(int i=0;i<part.length;i++){
                result += part[i];
                if(i!=part.length-1)result += "~";
                
            }
            if(a!=encrypted.length()-1)result += ":";
        }
        return result;
    }
    
    public static String decrypt(String decryptMe) throws InvalidKeyException, 
            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, 
            BadPaddingException, UnsupportedEncodingException{
        String result="";
        //разбиваем полученную строку на строки в которых содержатся
//        байты символа разделенные  ~
        String[] strpart= decryptMe.split(":");
        
        for(int a=0;a<strpart.length;a++){
//            разделяем каждую строку на строчки с байтами
            String[] bytesstr= strpart[a].split("~");
            byte[] bytepart = new byte[bytesstr.length];
           for(int i=0;i<bytesstr.length;i++){
                bytepart[i] = Byte.parseByte(bytesstr[i]);
           }
            result += new String(bytepart,"UTF-8");
        }
        
        cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        String returnMe = new String(cipher.doFinal(result.getBytes()));
        return returnMe;
    }
}

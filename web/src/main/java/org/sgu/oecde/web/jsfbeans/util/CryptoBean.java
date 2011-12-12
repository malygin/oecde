/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.web.jsfbeans.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author MS
 */

@ManagedBean(name="CryptoBean")
@ViewScoped
public class CryptoBean {
    private String username="";
    private String password="";
    private String encryptedValue = "";
 //   private static byte[] keyBytes = { 110, 32, 73, 24, 125, 66, 75, 18 };
    
    public void setEncryptedValue(String encryptedValue) {
        this.encryptedValue=encryptedValue;
    }

    public String getEncryptedValue() throws UnsupportedEncodingException {
        return encryptedValue;
        
    }
//    public String ISOtoUTF(String convertMe) throws UnsupportedEncodingException{
//        return new String(convertMe.getBytes("ISO-8859-1"),"UTF8");
//    }
    
//    public String UTFtoISO(String convertMe) throws UnsupportedEncodingException{
//        return new String(convertMe.getBytes("UTF8"),"ISO-8859-1");
//    }
    
    public void encrypt() throws IOException, IllegalBlockSizeException, BadPaddingException,
            InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        encryptedValue = username+":"+password;
        FacesContext.getCurrentInstance().getExternalContext().redirect("decryptpage.xhtml?id="+CryptoClassDES.encrypt(encryptedValue));
        
//and redirect
//        SecretKey key = new SecretKeySpec(keyBytes, "DES");
//        Cipher cipher = Cipher.getInstance("DES");
//        String encryptMe = username+":"+password;
//        
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//
//        byte[] inputBytes = encryptMe.getBytes();
//
//        encryptedValue = new String( cipher.doFinal(inputBytes) );
//       //
//     FacesContext.getCurrentInstance().getExternalContext().redirect("CryptoServlet?id="+UTFtoISO(encryptedValue));
    }
     public String getDecrypt() throws InvalidKeyException, IllegalBlockSizeException,
             BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
        return CryptoClassDES.decrypt(encryptedValue);
    }
    
//    public String getExternalDecrypt() throws NoSuchAlgorithmException, NoSuchPaddingException,
//            InvalidKeyException, IllegalBlockSizeException,  UnsupportedEncodingException, BadPaddingException{
//       
//        SecretKey key = new SecretKeySpec(keyBytes, "DES");
//        Cipher cipher = Cipher.getInstance("DES");
//        
//        cipher.init(Cipher.DECRYPT_MODE, key);
//      
//        return new String(cipher.doFinal(ISOtoUTF(encryptedValue).getBytes()));
//      
//    }
//    
//    public String getDecrypt() throws NoSuchAlgorithmException, NoSuchPaddingException,
//            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
//       
//        SecretKey key = new SecretKeySpec(keyBytes, "DES");
//        Cipher cipher = Cipher.getInstance("DES");
//        
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        
//        return new String(cipher.doFinal((encryptedValue).getBytes()));
//    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CryptoBean() {
    }
    
}

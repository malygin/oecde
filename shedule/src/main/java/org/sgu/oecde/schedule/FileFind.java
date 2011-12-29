/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.schedule;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileFind {
    
    private static FileFind instance;
    private URL PATH;
    private URLConnection connection;
    private String content;

    public static FileFind getInstance() {
        return instance;
    }
    
    private void getContent() throws MalformedURLException, IOException{
      
        PATH = new URL("http://oec-nginx.main.sgu.ru/get_files/dir_read.php");
        connection = PATH.openConnection();
        connection.setDoInput(true);
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line ="";
        while ((line = reader.readLine()) != null) {
            content += line;
        }
        
    }
   
//     private static File topDir;
//     
//    static class FileListFilter implements FilenameFilter {
//      private String name; 
//      private String extension; 
//      FileListFilter(String name, String extension) {
//        this.name = name;
//        this.extension = extension;
//      }
//        @Override
//      public boolean accept(File directory, String filename) {
//        boolean fileOK = true;
//        if (name != null) {
//          fileOK &= filename.startsWith(name);
//        }
//        if (extension != null) {
//          fileOK &= filename.endsWith("." + extension);
//        }
//        return fileOK;
//      }
//    }
    
    public boolean findConference(String id) throws MalformedURLException, IOException{
        getContent();
        boolean exists=content.contains(";"+id+"roomVideoStream");
        return exists;
//        FilenameFilter filter = new FileListFilter(id,"flv");
//      //  File topDir = new File(PATH);
//       // String[] files = topDir.list(filter);
//        return topDir.isDirectory();
       // if (files!=null) return true;
      //  return false;
    }
}
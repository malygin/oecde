
package org.sgu.oecde.web.jsfbeans.util.fileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 13.07.2010
 * 
 */
public class FileUploadUtil {

    public static String Upload(UploadFile uf,MultipartRequestWrapper multi, String type) throws IOException{
               Random random = new Random();
               String name =type+"_"+Math.abs(random.nextInt())+uf.getFileName().substring(uf.getFileName().lastIndexOf("."));
             // System.out.println("m"+name);
               File someFile = new File(multi.getRequest().getServletContext().getRealPath("/resources/userFiles/"+type+"/"+name));
		if(!someFile.exists())someFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(someFile);
		fos.write(uf.getFileData());
		fos.flush();
		fos.close();
                return name;
    }

}

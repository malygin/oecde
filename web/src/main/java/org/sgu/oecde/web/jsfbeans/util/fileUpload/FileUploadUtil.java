
package org.sgu.oecde.web.jsfbeans.util.fileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import javax.activation.MimetypesFileTypeMap;
import org.springframework.util.ObjectUtils;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 13.07.2010
 * 
 */
public class FileUploadUtil {

    public final static String[]mimetypes = new String[]{"application/pdf"
                                                    ,"application/vnd.ms-powerpoint"
                                                    ,"text/rtf"
                                                    ,"text/plain"
                                                    ,"application/zip"
                                                    ,"application/msword"
                                                    ,"application/x-rar-compressed"
                                                    ,"application/x-7z-compressed"
                                                    ,"application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                                                    ,"application/vnd.ms-powerpoint.slideshow.macroEnabled.12"
                                                    ,"application/vnd.openxmlformats-officedocument.presentationml.slideshow"
                                                    ,"application/vnd.ms-powerpoint.presentation.macroEnabled.12"
                                                    ,"application/vnd.openxmlformats-officedocument.presentationml.presentation"
                                                    ,"application/vnd.ms-excel.sheet.binary.macroEnabled.12"
                                                    ,"application/vnd.ms-excel.sheet.macroEnabled.12"
                                                    ,"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                                                    ,"application/vnd.ms-excel.template.macroEnabled.12"
                                                    ,"application/vnd.openxmlformats-officedocument.spreadsheetml.template"
                                                    ,"application/vnd.openxmlformats-officedocument.wordprocessingml.template"
                                                    ,"application/vnd.ms-word.document.macroEnabled.12"
                                                    ,"application/vnd.ms-word.template.macroEnabled.12"
                                                    ,"application/vnd.ms-excel"
                                                    ,"application/octet-stream"
                                                    ,"application/vnd.ms-powerpoint"};

    public static String Upload(UploadFile uf,MultipartRequestWrapper multi, String type, boolean checkMime) throws IOException{
        Random random = new Random();
        String name =type+"_"+Math.abs(random.nextInt())+uf.getFileName().substring(uf.getFileName().lastIndexOf("."));
        File someFile = new File(multi.getRequest().getServletContext().getRealPath("/resources/userFiles/"+type+"/"+name));
        String mime = new MimetypesFileTypeMap().getContentType(someFile);
        if(checkMime&&!ObjectUtils.containsElement(mimetypes, mime))
            return null;
        if(!someFile.exists())
            someFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(uf.getFileData());
        fos.flush();
        fos.close();
        return name;
    }
}

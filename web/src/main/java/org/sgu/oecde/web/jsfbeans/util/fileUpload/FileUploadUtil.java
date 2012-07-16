package org.sgu.oecde.web.jsfbeans.util.fileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Random;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import org.springframework.util.ObjectUtils;
import javax.servlet.http.Part;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.UploadFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.sgu.oecde.web.jsfbeans.util.ArrayListUtil;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 13.07.2010
 * 
 */
public class FileUploadUtil {

    public final static String[]mimetypes = new String[]{"application/pdf"
                                                    ,"application/vnd.ms-powerpoint"
                                                    ,"text/rtf"
                                                    ,"image/jpeg"
                                                    ,"text/plain"
                                                    ,"application/zip"
                                                    ,"application/msword"
                                                    ,"image/tif"
                                                    ,"image/x-tif"
                                                    ,"image/tiff"
                                                    ,"image/x-tiff"
                                                    ,"image/vnd.djvu"
                                                    ,"image/vnd.djvu"
                                                    ,"application/tif"
                                                    ,"application/x-tif"
                                                    ,"application/tiff"
                                                    ,"application/x-tiff"
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
    
    public static String Upload(UploadFile uf, HttpServletRequest multi, String type, boolean checkMime) throws IOException {
        Random random = new Random();
        String name = type + "_" + Math.abs(random.nextInt()) + uf.getFileName().substring(uf.getFileName().lastIndexOf("."));
        File someFile = new File(System.getProperty("catalina.base")+"/storage/"+multi.getServletContext().getInitParameter("storage") +"/userFiles/" + type + "/" + name);
       // File someFile = new File(multi.getServletContext().getRealPath("/resources/userFiles/" + type + "/" + name));        
        String mime = new MimetypesFileTypeMap().getContentType(someFile);
        if (checkMime && !ArrayListUtil.containsElement(mimetypes, mime)) {
            return null;
        }
        if (!someFile.exists()) {
            someFile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(uf.getFileData());
        fos.flush();
        fos.close();
        return name;
    }
    private static final String CONTENT_DISPOSITION = "content-disposition";
    private static final String CONTENT_DISPOSITION_FILENAME = "filename";

    public static UploadFile findFile(HttpServletRequest req, String name) throws ServletException {
        UploadFile uf = null;
        try {
            Part p = (Part) req.getPart(name);           

            if(p == null)
                return uf;
            String fileName = getFilename(p);
            byte[] b = new byte[(int) p.getSize()];
            p.getInputStream().read(b);
            uf = new UploadFile(fileName, p.getContentType(), b);
        } catch (IOException ex) {
            Logger.getLogger(FileUploadUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uf;
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader(CONTENT_DISPOSITION).split(";")) {
            if (cd.trim().startsWith(CONTENT_DISPOSITION_FILENAME)) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}

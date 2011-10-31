
package org.sgu.oecde.web.jsfbeans.util.fileUpload;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;


/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date 17.08.2010
 */
public class MultiPart {
    private static final String CONTENT_DISPOSITION = "content-disposition";
    private static final String CONTENT_DISPOSITION_FILENAME = "filename";

    private HttpServletRequest request;

    protected MultiPart(){

    }

    public UploadFile findFile(String attrName){
        this.setRequest(FacesUtil.getRequest());
        UploadFile uf = null;
        try {

            Part p = findPart(attrName);
            if(p != null){
                String fileName = getFilename(p);
               // System.out.println("Filename : " + fileName + ", contentType " + p.getContentType());
                byte[] b = new byte[(int) p.getSize()];
                p.getInputStream().read(b);
                uf = new UploadFile(fileName, p.getContentType(), b);
            }

        } catch (IOException ex) {
            Logger.getLogger(MultiPart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uf;
    }


    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    private Part findPart(String name){
        Part p = null;
        try {
            for (Part part : request.getParts()) {
                if(part.getName().equals(name)){
                    p = part;
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MultiPart.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(MultiPart.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    private String getFilename(Part part) {
        for (String cd : part.getHeader(CONTENT_DISPOSITION).split(";")) {
            if (cd.trim().startsWith(CONTENT_DISPOSITION_FILENAME)) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}


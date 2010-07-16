package org.sgu.oecde.web.jsfbeans.util.fileUpload;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

/**
 *
 * @author theironicprogrammer@gmail.com
 */
public class MultipartRequestWrapper extends HttpServletRequestWrapper {
    private static final String CONTENT_DISPOSITION = "content-disposition";
    private static final String CONTENT_DISPOSITION_FILENAME = "filename";

    private Hashtable<String, String[]> params = new Hashtable<String, String[]>();

    public MultipartRequestWrapper(HttpServletRequest request) throws UnsupportedEncodingException {
        super(request);
        request.setCharacterEncoding("UTF-8");
        System.out.println("Created multipart wrapper....");
        try {
            System.out.println("Looping parts");            
            for (Part p : request.getParts()) {

                System.out.println(String.format("Part name: %1$s, contentType : %2$s", p.getName(), p.getContentType()));
                for(String header : p.getHeaderNames()){
                    System.out.println("Header name : " + header + ", value : " + p.getHeader(header));
                    System.out.println("!"+p.toString());                    
                }
            
                byte[] b = new byte[(int) p.getSize()];
                p.getInputStream().read(b);
                params.put(p.getName(), new String[]{new String(b,"UTF-8")});
            }
        } catch (IOException ex) {
            Logger.getLogger(MultipartRequestWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(MultipartRequestWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public UploadFile findFile(String attrName){
        UploadFile uf = null;
        try {

            Part p = findPart(attrName);
            if(p != null){
                String fileName = getFilename(p);
                System.out.println("Filename : " + fileName + ", contentType " + p.getContentType());
                byte[] b = new byte[(int) p.getSize()];
                p.getInputStream().read(b);
                params.put(p.getName(), new String[]{new String(b)});
                uf = new UploadFile(fileName, p.getContentType(), b);
            }

        } catch (IOException ex) {
            Logger.getLogger(MultiPart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uf;
    }

    private Part findPart(String name){
        HttpServletRequest request = (HttpServletRequest)getRequest();
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

    @Override
    public String getParameter(String name) {
      String [] values = getParameterValues(name);
      if(values == null || values.length == 0) {
        return null;
      }

      return values[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
      return params;
    }

    @Override
    public Enumeration<String> getParameterNames() {
      return params.keys();
    }

    @Override
    public String[] getParameterValues(String name) {
      return params.get(name);
    }

}

package org.sgu.oecde.web.authentication;

import java.io.IOException;
import java.util.Enumeration;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SwitchedUserCheker;
import org.sgu.oecde.journal.EventType;
import org.sgu.oecde.journal.JournalService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author ShihovMY
 */
public class CustomAuthenticationSuccessHandler implements  AuthenticationSuccessHandler {

    @Resource
    private JournalService journalService;

    protected CustomAuthenticationSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        doRedirect(request,response,true);

        HttpSession s = request.getSession();
        for (Enumeration<String>e = s.getAttributeNames(); e.hasMoreElements();){
            String a = e.nextElement();
            if(a.toLowerCase().endsWith("bean"))
                s.removeAttribute(a);
        }
        
        if(!SwitchedUserCheker.check(authentication.getAuthorities())){
            String browser = null;
            String ag = request.getHeader("User-Agent");
            ag = ag.toLowerCase();
            if (ag.contains("msie")) browser = "IE";
            else if (ag.contains("opera")) browser = "Opera";
            else if (ag.contains("chrome")) browser = "Chrome";
            else if (ag.contains("firefox")) browser = "Firefox";
            else if (ag.contains("safari") && ag.contains("version")) browser = "Safari";
            journalService.save(EventType.SYSTEM_LOGIN,SecurityContextHandler.getUser(), request.getRemoteAddr(),browser);
        }
    }

    public static void doRedirect(HttpServletRequest request, HttpServletResponse response, boolean returnToIndex)throws IOException, ServletException {
         UserType type = SecurityContextHandler.getUserType();
//         String path="";
//         if (request.getContextPath().equals("")) path="";
//            else path=request.getContextPath();
       //  System.out.println("context "+request.getContextPath());
      //   System.out.println("path "+path);
         if(type!=null){
       //      System.out.println(path+"23/"+type.toNameFolder());
             response.sendRedirect(request.getContextPath()+"/"+type.toNameFolder()+"/");
         }else
             if(returnToIndex)
                response.sendRedirect("index.xhtml");
    }
}
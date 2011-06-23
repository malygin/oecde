
package org.sgu.oecde.web.jsfbeans.util.fileUpload;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date 17.08.2010
 */
@WebFilter(urlPatterns="/*")
public class MultipartRequestFilter implements  Filter{
    private static final String REQUEST_METHOD_POST = "POST";
    private static final String CONTENT_TYPE_MULTIPART = "multipart/";


    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if(isMultipartRequest(httpRequest)){
            request = new MultipartRequestWrapper(httpRequest);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
        
    }

    public static final boolean isMultipartRequest(HttpServletRequest request) {
        return REQUEST_METHOD_POST.equalsIgnoreCase(request.getMethod())
            && request.getContentType() != null
            && request.getContentType().toLowerCase().startsWith(CONTENT_TYPE_MULTIPART);
    }

}

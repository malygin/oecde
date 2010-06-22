package org.sgu.oecde.core.authentication;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UsersInCache;
import org.sgu.oecde.core.util.SecurityContextHandler;

/**
 *
 * @author ShihovMY
 */
public class EventFilter implements  Filter{

    @Resource
    private UsersInCache userCache;

    protected void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        AbstractUser u = SecurityContextHandler.getUser();
        if(u!=null)
            userCache.putUserInCache(u);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}

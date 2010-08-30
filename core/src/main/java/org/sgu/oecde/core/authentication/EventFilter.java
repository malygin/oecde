package org.sgu.oecde.core.authentication;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UsersInCache;
import org.sgu.oecde.core.util.SecurityContextHandler;

/**
 * фильтр, вносящий текущего пользователя в кеш пользователей онлайн при каждом запросе.
 * @author ShihovMY
 */
public class EventFilter implements  Filter,Serializable{

    @Resource
    private UsersInCache userCache;

    private static final long serialVersionUID = 135L;

    /**
     * {@inheritDoc}
     */
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * если пользователь не равен null, то он вносится в кеш пользователей онлайн
     * {@inheritDoc}
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        AbstractUser u = SecurityContextHandler.getUser();
        if(u!=null)
            userCache.putUserInCache(u);
        chain.doFilter(request, response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.util;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 *
 * @author ShihovMY
 */
public abstract class HqlConstructor {

    private HqlConstructor() {
        throw new AssertionError();
    }

    public static final Query makeQuery(Session session, String prefix, String mainHql, String[] fetch, String whereHql, String postfix, String orderBy){
        StringBuilder query = new StringBuilder();

        if(StringUtils.hasText(prefix))
            query.append("select ").append(prefix).append(" ");

        query.append(mainHql);

        if(!ObjectUtils.isEmpty(fetch)){
            for(String s:fetch){
                query.append(" join fetch ").append(s).append(" ");
            }
        }

        query.append(whereHql);

        if(StringUtils.hasText(postfix))
            query.append(" and ").append(postfix).append(" ");

        if(StringUtils.hasText(orderBy))
            query.append(" order by ").append(orderBy).append(" ");

        return session.createQuery(query.toString());
    }
}

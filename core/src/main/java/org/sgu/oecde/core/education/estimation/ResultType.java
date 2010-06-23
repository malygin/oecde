package org.sgu.oecde.core.education.estimation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.sgu.oecde.core.education.work.AbstractResult;

/**
 * тип результата, который обрабатывается фильтром
 * @author ShihovMY
 * @see IResultFilter
 * @see org.sgu.oecde.core.education.work.AbstractResult
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ResultType {
    Class<? extends AbstractResult> type();
}

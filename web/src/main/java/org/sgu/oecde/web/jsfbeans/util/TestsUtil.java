package org.sgu.oecde.web.jsfbeans.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.sgu.oecde.web.jsfbeans.tests.TestPassingBean;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author malyginav    
 */
@ManagedBean(name="testUtil")
@ApplicationScoped
public class TestsUtil {
    public String checkForFormulaOrLink(String set) throws MalformedURLException, IOException{
           return TestPassingBean.checkForFormulaOrLink(set);
    }
}

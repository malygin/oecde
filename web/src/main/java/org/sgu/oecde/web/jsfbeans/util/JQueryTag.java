
package org.sgu.oecde.web.jsfbeans.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author verborghs
 */
@FacesComponent("jquery")
@ResourceDependencies({
    @ResourceDependency(name = "jsf.js", target = "head", library="javax.faces"),
    @ResourceDependency(name = "jquery.js", target = "head"),
    @ResourceDependency(name = "jquery-ui.js", target = "head")
})
public class JQueryTag extends UIComponentBase implements ClientBehaviorHolder {

    private static final String FAMILY = "jquery";

    private static final String TEMPLATE_EVENT = "\n$('#%s').bind('%s', function(event, ui) { %s; });\n";
    private static final String TEMPLATE_PLUGIN = "\n$('#%s').%s(%s);\n";
    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {

        String clientId = getParent().getClientId();
        String jQueryId = clientId.replace(":", "\\\\:");

        ResponseWriter responseWriter = context.getResponseWriter();
        responseWriter.startElement("script", null);
        responseWriter.writeAttribute("type", "text/javascript", null);

        Map<String, List<ClientBehavior>> behaviors = getClientBehaviors();
        for (String eventName : behaviors.keySet()) {
            if (getEventNames().contains(eventName)) {

                ClientBehaviorContext behaviorContext = ClientBehaviorContext
                        .createClientBehaviorContext(context, this, eventName, clientId, null);

                String jsfJsCode = behaviors.get(eventName)
                        .get(0).getScript(behaviorContext);

                String jsCode = String.format(TEMPLATE_EVENT, jQueryId, eventName, jsfJsCode);

                responseWriter.writeText(jsCode, null);
            }

        }
        String plugin = (String) getAttributes().get("plugin");
        if(null != plugin) {
            String options = (String) getAttributes().get("options");
            if(options == null) options = "";
            String jsCode = String.format(TEMPLATE_PLUGIN, jQueryId, plugin, options);
            responseWriter.writeText(jsCode, null);
        }

        responseWriter.endElement("script");
    }

    @Override
    public void decode(FacesContext context) {
        Map<String, List<ClientBehavior>> behaviors = getClientBehaviors();
        if (behaviors.isEmpty()) {
            return;
        }

        ExternalContext external = context.getExternalContext();
        Map<String, String> params = external.getRequestParameterMap();
        String behaviorEvent = params.get("javax.faces.behavior.event");
        if (behaviorEvent != null) {
            List<ClientBehavior> behaviorsForEvent = behaviors.get(behaviorEvent);
            if (behaviors.size() > 0) {
                String behaviorSource = params.get("javax.faces.source");
                String clientId = getParent().getClientId(context);
                if (behaviorSource != null && behaviorSource.equals(clientId)) {
                    for (ClientBehavior behavior : behaviorsForEvent) {
                        behavior.decode(context, this);
                    }
                }
            }
        }
    }

    @Override
    public Collection<String> getEventNames() {
        return Arrays.<String>asList(((String) getAttributes().get("events")).split(","));
    }

    @Override
    public String getDefaultEventName() {
        return (String) getAttributes().get("default");
    }
}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.02 at 01:06:22 PM MSD 
//


package org.imsglobal.xsd.imsss;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for preConditionRuleType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="preConditionRuleType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.imsglobal.org/xsd/imsss}sequencingRuleType">
 *       &lt;sequence>
 *         &lt;element name="ruleAction">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="action" use="required" type="{http://www.imsglobal.org/xsd/imsss}preConditionRuleActionType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "preConditionRuleType", propOrder = {
        "ruleAction"
})
public class PreConditionRuleType
        extends SequencingRuleType {

    @XmlElement(required = true)
    protected PreConditionRuleType.RuleAction ruleAction;

    /**
     * Gets the value of the ruleAction property.
     *
     * @return possible object is
     *         {@link PreConditionRuleType.RuleAction }
     */
    public PreConditionRuleType.RuleAction getRuleAction() {
        return ruleAction;
    }

    /**
     * Sets the value of the ruleAction property.
     *
     * @param value allowed object is
     *              {@link PreConditionRuleType.RuleAction }
     */
    public void setRuleAction(PreConditionRuleType.RuleAction value) {
        this.ruleAction = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * <p/>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p/>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="action" use="required" type="{http://www.imsglobal.org/xsd/imsss}preConditionRuleActionType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class RuleAction {

        @XmlAttribute(required = true)
        protected PreConditionRuleActionType action;

        /**
         * Gets the value of the action property.
         *
         * @return possible object is
         *         {@link PreConditionRuleActionType }
         */
        public PreConditionRuleActionType getAction() {
            return action;
        }

        /**
         * Sets the value of the action property.
         *
         * @param value allowed object is
         *              {@link PreConditionRuleActionType }
         */
        public void setAction(PreConditionRuleActionType value) {
            this.action = value;
        }

    }

}

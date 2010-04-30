//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.02 at 01:06:22 PM MSD 
//


package org.imsglobal.xsd.imsss;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rollupActionType.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="rollupActionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="satisfied"/>
 *     &lt;enumeration value="notSatisfied"/>
 *     &lt;enumeration value="completed"/>
 *     &lt;enumeration value="incomplete"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "rollupActionType")
@XmlEnum
public enum RollupActionType {

    @XmlEnumValue("satisfied")
    SATISFIED("satisfied"),
    @XmlEnumValue("notSatisfied")
    NOT_SATISFIED("notSatisfied"),
    @XmlEnumValue("completed")
    COMPLETED("completed"),
    @XmlEnumValue("incomplete")
    INCOMPLETE("incomplete");
    private final String value;

    RollupActionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RollupActionType fromValue(String v) {
        for (RollupActionType c : RollupActionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.02 at 01:06:19 PM MSD 
//


package org.ieee.ltsc.xsd.lom;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for kindValues.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="kindValues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ispartof"/>
 *     &lt;enumeration value="haspart"/>
 *     &lt;enumeration value="isversionof"/>
 *     &lt;enumeration value="hasversion"/>
 *     &lt;enumeration value="isformatof"/>
 *     &lt;enumeration value="hasformat"/>
 *     &lt;enumeration value="references"/>
 *     &lt;enumeration value="isreferencedby"/>
 *     &lt;enumeration value="isbasedon"/>
 *     &lt;enumeration value="isbasisfor"/>
 *     &lt;enumeration value="requires"/>
 *     &lt;enumeration value="isrequiredby"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "kindValues")
@XmlEnum
public enum KindValues {

    @XmlEnumValue("ispartof")
    ISPARTOF("ispartof"),
    @XmlEnumValue("haspart")
    HASPART("haspart"),
    @XmlEnumValue("isversionof")
    ISVERSIONOF("isversionof"),
    @XmlEnumValue("hasversion")
    HASVERSION("hasversion"),
    @XmlEnumValue("isformatof")
    ISFORMATOF("isformatof"),
    @XmlEnumValue("hasformat")
    HASFORMAT("hasformat"),
    @XmlEnumValue("references")
    REFERENCES("references"),
    @XmlEnumValue("isreferencedby")
    ISREFERENCEDBY("isreferencedby"),
    @XmlEnumValue("isbasedon")
    ISBASEDON("isbasedon"),
    @XmlEnumValue("isbasisfor")
    ISBASISFOR("isbasisfor"),
    @XmlEnumValue("requires")
    REQUIRES("requires"),
    @XmlEnumValue("isrequiredby")
    ISREQUIREDBY("isrequiredby");
    private final String value;

    KindValues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static KindValues fromValue(String v) {
        for (KindValues c : KindValues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.02 at 01:06:19 PM MSD 
//


package org.ieee.ltsc.xsd.lom;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for structure complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="structure">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ltsc.ieee.org/xsd/LOM}structureVocab">
 *       &lt;attGroup ref="{http://ltsc.ieee.org/xsd/LOM/unique}structure"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "structure")
public class Structure
        extends StructureVocab {

    @XmlAttribute
    @XmlSchemaType(name = "anySimpleType")
    protected String uniqueElementName;

    /**
     * Gets the value of the uniqueElementName property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getUniqueElementName() {
        if (uniqueElementName == null) {
            return "structure";
        } else {
            return uniqueElementName;
        }
    }

    /**
     * Sets the value of the uniqueElementName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUniqueElementName(String value) {
        this.uniqueElementName = value;
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.02 at 01:06:19 PM MSD 
//


package org.ieee.ltsc.xsd.lom;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for resource complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="resource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM}identifier"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM}description"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM/extend}customElements"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://ltsc.ieee.org/xsd/LOM/extend}customAttributes"/>
 *       &lt;attGroup ref="{http://ltsc.ieee.org/xsd/LOM/unique}resource"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resource", propOrder = {
        "identifierOrDescriptionOr"
})
public class Resource {

    @XmlElements({
            @XmlElement(name = "identifier", type = Identifier.class),
            @XmlElement(name = "description", type = Description.class)
    })
    protected List<Object> identifierOrDescriptionOr;
    @XmlAttribute
    @XmlSchemaType(name = "anySimpleType")
    protected String uniqueElementName;

    /**
     * Gets the value of the identifierOrDescriptionOr property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identifierOrDescriptionOr property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentifierOrDescriptionOr().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Identifier }
     * {@link Description }
     */
    public List<Object> getIdentifierOrDescriptionOr() {
        if (identifierOrDescriptionOr == null) {
            identifierOrDescriptionOr = new ArrayList<Object>();
        }
        return this.identifierOrDescriptionOr;
    }

    /**
     * Gets the value of the uniqueElementName property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getUniqueElementName() {
        if (uniqueElementName == null) {
            return "resource";
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

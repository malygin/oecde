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
 * <p>Java class for typeVocab complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="typeVocab">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="source" type="{http://ltsc.ieee.org/xsd/LOM}sourceValue"/>
 *         &lt;element name="value" type="{http://ltsc.ieee.org/xsd/LOM}typeValue"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM/extend}customElements"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://ltsc.ieee.org/xsd/LOM/extend}customAttributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeVocab", propOrder = {
        "sourceOrValueOr"
})
@XmlSeeAlso({
        Type.class
})
public class TypeVocab {

    @XmlElements({
            @XmlElement(name = "value", type = TypeValue.class),
            @XmlElement(name = "source", type = SourceValue.class)
    })
    protected List<Object> sourceOrValueOr;

    /**
     * Gets the value of the sourceOrValueOr property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sourceOrValueOr property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSourceOrValueOr().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeValue }
     * {@link SourceValue }
     */
    public List<Object> getSourceOrValueOr() {
        if (sourceOrValueOr == null) {
            sourceOrValueOr = new ArrayList<Object>();
        }
        return this.sourceOrValueOr;
    }

}

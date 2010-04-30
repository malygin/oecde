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
 * <p>Java class for educational complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="educational">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM}interactivityType"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM}learningResourceType"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM}interactivityLevel"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM}semanticDensity"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM}intendedEndUserRole"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM}context"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM}typicalAgeRange"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM}difficulty"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM}typicalLearningTime"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM}descriptionUnbounded"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM}language"/>
 *         &lt;group ref="{http://ltsc.ieee.org/xsd/LOM/extend}customElements"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://ltsc.ieee.org/xsd/LOM/extend}customAttributes"/>
 *       &lt;attGroup ref="{http://ltsc.ieee.org/xsd/LOM/unique}educational"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "educational", propOrder = {
        "interactivityTypeOrLearningResourceTypeOrInteractivityLevel"
})
public class Educational {

    @XmlElements({
            @XmlElement(name = "description", type = LanguageString.class),
            @XmlElement(name = "interactivityLevel", type = InteractivityLevel.class),
            @XmlElement(name = "semanticDensity", type = SemanticDensity.class),
            @XmlElement(name = "language", type = LanguageId.class),
            @XmlElement(name = "typicalLearningTime", type = TypicalLearningTime.class),
            @XmlElement(name = "learningResourceType", type = LearningResourceType.class),
            @XmlElement(name = "typicalAgeRange", type = TypicalAgeRange.class),
            @XmlElement(name = "intendedEndUserRole", type = IntendedEndUserRole.class),
            @XmlElement(name = "difficulty", type = Difficulty.class),
            @XmlElement(name = "interactivityType", type = InteractivityType.class),
            @XmlElement(name = "context", type = Context.class)
    })
    protected List<Object> interactivityTypeOrLearningResourceTypeOrInteractivityLevel;

    /**
     * Gets the value of the interactivityTypeOrLearningResourceTypeOrInteractivityLevel property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interactivityTypeOrLearningResourceTypeOrInteractivityLevel property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInteractivityTypeOrLearningResourceTypeOrInteractivityLevel().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link LanguageString }
     * {@link InteractivityLevel }
     * {@link SemanticDensity }
     * {@link LanguageId }
     * {@link TypicalLearningTime }
     * {@link LearningResourceType }
     * {@link TypicalAgeRange }
     * {@link IntendedEndUserRole }
     * {@link Difficulty }
     * {@link InteractivityType }
     * {@link Context }
     */
    public List<Object> getInteractivityTypeOrLearningResourceTypeOrInteractivityLevel() {
        if (interactivityTypeOrLearningResourceTypeOrInteractivityLevel == null) {
            interactivityTypeOrLearningResourceTypeOrInteractivityLevel = new ArrayList<Object>();
        }
        return this.interactivityTypeOrLearningResourceTypeOrInteractivityLevel;
    }

}

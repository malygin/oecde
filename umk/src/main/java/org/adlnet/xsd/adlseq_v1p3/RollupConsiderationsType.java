//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.02 at 01:06:21 PM MSD 
//


package org.adlnet.xsd.adlseq_v1p3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rollupConsiderationsType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="rollupConsiderationsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="requiredForSatisfied" type="{http://www.adlnet.org/xsd/adlseq_v1p3}rollupConsiderationType" default="always" />
 *       &lt;attribute name="requiredForNotSatisfied" type="{http://www.adlnet.org/xsd/adlseq_v1p3}rollupConsiderationType" default="always" />
 *       &lt;attribute name="requiredForCompleted" type="{http://www.adlnet.org/xsd/adlseq_v1p3}rollupConsiderationType" default="always" />
 *       &lt;attribute name="requiredForIncomplete" type="{http://www.adlnet.org/xsd/adlseq_v1p3}rollupConsiderationType" default="always" />
 *       &lt;attribute name="measureSatisfactionIfActive" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rollupConsiderationsType")
public class RollupConsiderationsType {

    @XmlAttribute
    protected RollupConsiderationType requiredForSatisfied;
    @XmlAttribute
    protected RollupConsiderationType requiredForNotSatisfied;
    @XmlAttribute
    protected RollupConsiderationType requiredForCompleted;
    @XmlAttribute
    protected RollupConsiderationType requiredForIncomplete;
    @XmlAttribute
    protected Boolean measureSatisfactionIfActive;

    /**
     * Gets the value of the requiredForSatisfied property.
     *
     * @return possible object is
     *         {@link RollupConsiderationType }
     */
    public RollupConsiderationType getRequiredForSatisfied() {
        if (requiredForSatisfied == null) {
            return RollupConsiderationType.ALWAYS;
        } else {
            return requiredForSatisfied;
        }
    }

    /**
     * Sets the value of the requiredForSatisfied property.
     *
     * @param value allowed object is
     *              {@link RollupConsiderationType }
     */
    public void setRequiredForSatisfied(RollupConsiderationType value) {
        this.requiredForSatisfied = value;
    }

    /**
     * Gets the value of the requiredForNotSatisfied property.
     *
     * @return possible object is
     *         {@link RollupConsiderationType }
     */
    public RollupConsiderationType getRequiredForNotSatisfied() {
        if (requiredForNotSatisfied == null) {
            return RollupConsiderationType.ALWAYS;
        } else {
            return requiredForNotSatisfied;
        }
    }

    /**
     * Sets the value of the requiredForNotSatisfied property.
     *
     * @param value allowed object is
     *              {@link RollupConsiderationType }
     */
    public void setRequiredForNotSatisfied(RollupConsiderationType value) {
        this.requiredForNotSatisfied = value;
    }

    /**
     * Gets the value of the requiredForCompleted property.
     *
     * @return possible object is
     *         {@link RollupConsiderationType }
     */
    public RollupConsiderationType getRequiredForCompleted() {
        if (requiredForCompleted == null) {
            return RollupConsiderationType.ALWAYS;
        } else {
            return requiredForCompleted;
        }
    }

    /**
     * Sets the value of the requiredForCompleted property.
     *
     * @param value allowed object is
     *              {@link RollupConsiderationType }
     */
    public void setRequiredForCompleted(RollupConsiderationType value) {
        this.requiredForCompleted = value;
    }

    /**
     * Gets the value of the requiredForIncomplete property.
     *
     * @return possible object is
     *         {@link RollupConsiderationType }
     */
    public RollupConsiderationType getRequiredForIncomplete() {
        if (requiredForIncomplete == null) {
            return RollupConsiderationType.ALWAYS;
        } else {
            return requiredForIncomplete;
        }
    }

    /**
     * Sets the value of the requiredForIncomplete property.
     *
     * @param value allowed object is
     *              {@link RollupConsiderationType }
     */
    public void setRequiredForIncomplete(RollupConsiderationType value) {
        this.requiredForIncomplete = value;
    }

    /**
     * Gets the value of the measureSatisfactionIfActive property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public boolean isMeasureSatisfactionIfActive() {
        if (measureSatisfactionIfActive == null) {
            return true;
        } else {
            return measureSatisfactionIfActive;
        }
    }

    /**
     * Sets the value of the measureSatisfactionIfActive property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setMeasureSatisfactionIfActive(Boolean value) {
        this.measureSatisfactionIfActive = value;
    }

}

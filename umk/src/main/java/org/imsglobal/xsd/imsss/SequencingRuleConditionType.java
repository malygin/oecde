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
 * <p>Java class for sequencingRuleConditionType.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;simpleType name="sequencingRuleConditionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="satisfied"/>
 *     &lt;enumeration value="objectiveStatusKnown"/>
 *     &lt;enumeration value="objectiveMeasureKnown"/>
 *     &lt;enumeration value="objectiveMeasureGreaterThan"/>
 *     &lt;enumeration value="objectiveMeasureLessThan"/>
 *     &lt;enumeration value="completed"/>
 *     &lt;enumeration value="activityProgressKnown"/>
 *     &lt;enumeration value="attempted"/>
 *     &lt;enumeration value="attemptLimitExceeded"/>
 *     &lt;enumeration value="timeLimitExceeded"/>
 *     &lt;enumeration value="outsideAvailableTimeRange"/>
 *     &lt;enumeration value="always"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "sequencingRuleConditionType")
@XmlEnum
public enum SequencingRuleConditionType {

    @XmlEnumValue("satisfied")
    SATISFIED("satisfied"),
    @XmlEnumValue("objectiveStatusKnown")
    OBJECTIVE_STATUS_KNOWN("objectiveStatusKnown"),
    @XmlEnumValue("objectiveMeasureKnown")
    OBJECTIVE_MEASURE_KNOWN("objectiveMeasureKnown"),
    @XmlEnumValue("objectiveMeasureGreaterThan")
    OBJECTIVE_MEASURE_GREATER_THAN("objectiveMeasureGreaterThan"),
    @XmlEnumValue("objectiveMeasureLessThan")
    OBJECTIVE_MEASURE_LESS_THAN("objectiveMeasureLessThan"),
    @XmlEnumValue("completed")
    COMPLETED("completed"),
    @XmlEnumValue("activityProgressKnown")
    ACTIVITY_PROGRESS_KNOWN("activityProgressKnown"),
    @XmlEnumValue("attempted")
    ATTEMPTED("attempted"),
    @XmlEnumValue("attemptLimitExceeded")
    ATTEMPT_LIMIT_EXCEEDED("attemptLimitExceeded"),
    @XmlEnumValue("timeLimitExceeded")
    TIME_LIMIT_EXCEEDED("timeLimitExceeded"),
    @XmlEnumValue("outsideAvailableTimeRange")
    OUTSIDE_AVAILABLE_TIME_RANGE("outsideAvailableTimeRange"),
    @XmlEnumValue("always")
    ALWAYS("always");
    private final String value;

    SequencingRuleConditionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SequencingRuleConditionType fromValue(String v) {
        for (SequencingRuleConditionType c : SequencingRuleConditionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

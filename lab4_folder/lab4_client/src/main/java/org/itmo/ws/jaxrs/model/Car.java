package org.itmo.ws.jaxrs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "car", propOrder = {
        "carbrand",
        "carmodel",
        "enginetype",
        "perfomance",
        "year"
})
public class Car {

    protected String carbrand;
    protected String carmodel;
    protected String enginetype;
    protected int perfomance;
    protected int year;

    /**
     * Gets the value of the carbrand property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCarbrand() {
        return carbrand;
    }

    /**
     * Sets the value of the carbrand property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCarbrand(String value) {
        this.carbrand = value;
    }

    /**
     * Gets the value of the carmodel property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCarmodel() {
        return carmodel;
    }

    /**
     * Sets the value of the carmodel property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCarmodel(String value) {
        this.carmodel = value;
    }

    /**
     * Gets the value of the enginetype property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEnginetype() {
        return enginetype;
    }

    /**
     * Sets the value of the enginetype property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEnginetype(String value) {
        this.enginetype = value;
    }

    /**
     * Gets the value of the perfomance property.
     *
     */
    public int getPerfomance() {
        return perfomance;
    }

    /**
     * Sets the value of the perfomance property.
     *
     */
    public void setPerfomance(int value) {
        this.perfomance = value;
    }

    /**
     * Gets the value of the year property.
     *
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     *
     */
    public void setYear(int value) {
        this.year = value;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carbrand='" + carbrand + '\'' +
                ", carmodel='" + carmodel + '\'' +
                ", year=" + year +
                ", enginetype='" + enginetype + '\'' +
                ", perfomance=" + perfomance +
                '}';
    }

}

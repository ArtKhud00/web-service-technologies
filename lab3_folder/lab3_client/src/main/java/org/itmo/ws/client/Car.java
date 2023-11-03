
package org.itmo.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for car complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="car"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="carbrand" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="carmodel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="enginetype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="perfomance" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
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

}

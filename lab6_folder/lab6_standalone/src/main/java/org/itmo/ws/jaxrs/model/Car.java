package org.itmo.ws.jaxrs.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Car {
    private String carbrand;
    private String carmodel;
    private int year;
    private String enginetype;
    private int perfomance;

    public Car() {
        this.year = 0;
        this.perfomance = 0;
    }

    public Car(String carbrand, String carmodel, int year, String enginetype, int perfomance) {
        this.carbrand = carbrand;
        this.carmodel = carmodel;
        this.year = year;
        this.enginetype = enginetype;
        this.perfomance = perfomance;
    }

    public String getCarbrand() {
        return carbrand;
    }

    public void setCarbrand(String carbrand) {
        this.carbrand = carbrand;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEnginetype() {
        return enginetype;
    }

    public void setEnginetype(String enginetype) {
        this.enginetype = enginetype;
    }

    public int getPerfomance() {
        return perfomance;
    }

    public void setPerfomance(int perfomance) {
        this.perfomance = perfomance;
    }

    public Boolean isValid() {
        return carbrand != null &
                carmodel != null &
                enginetype != null &
                year != 0 &
                perfomance != 0;
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

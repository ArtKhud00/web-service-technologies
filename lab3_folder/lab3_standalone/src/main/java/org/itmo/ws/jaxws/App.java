package org.itmo.ws.jaxws;

import org.itmo.ws.jaxws.service.CarWebService;

import javax.xml.ws.Endpoint;

public class App {
    public static void main(String[] args) {
        System.setProperty("com.sun.xml.ws.fault.SOAPFaultBuilder.disableCaptureStackTrace",
                            "false");

        String url = "http://0.0.0.0:8082/CarService";
        Endpoint.publish(url, new CarWebService());
    }
}
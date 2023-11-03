package org.itmo.ws.jaxws.exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "org.itmo.ws.jaxws.exception.CarServiceFault")
public class ThrottlingException extends Exception {
    private final CarServiceFault fault;

    public ThrottlingException(CarServiceFault fault) {
        this.fault = fault;
    }

    public ThrottlingException(String message, CarServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public ThrottlingException(String message, Throwable cause, CarServiceFault fault) {
        super(message, cause);
        this.fault = fault;
    }

}

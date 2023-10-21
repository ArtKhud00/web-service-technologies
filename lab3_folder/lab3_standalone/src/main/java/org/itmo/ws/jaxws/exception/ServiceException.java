package org.itmo.ws.jaxws.exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "org.itmo.ws.jaxws.exception.CarServiceFault")
public class ServiceException extends Exception {
    private final CarServiceFault fault;

    public ServiceException(String message, CarServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public ServiceException(String message, Throwable cause, CarServiceFault fault) {
        super(message, cause);
        this.fault = fault;
    }
}

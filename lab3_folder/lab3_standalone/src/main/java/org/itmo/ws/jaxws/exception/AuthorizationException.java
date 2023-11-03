package org.itmo.ws.jaxws.exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "org.itmo.ws.jaxws.exception.CarServiceFault")
public class AuthorizationException extends Exception{
    private final CarServiceFault fault;

    public AuthorizationException(String message, CarServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public AuthorizationException(String message, Throwable cause, CarServiceFault fault) {
        super(message, cause);
        this.fault = fault;
    }
}

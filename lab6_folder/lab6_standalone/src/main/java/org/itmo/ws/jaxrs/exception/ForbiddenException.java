package org.itmo.ws.jaxrs.exception;

public class ForbiddenException extends Exception{

    public static ForbiddenException DEFAULT_INSTANCE = new ForbiddenException("Invalid username/password");
    public ForbiddenException(String message){
        super(message);
    }
}

package org.itmo.ws.jaxrs.exception;

public class AuthorizationException extends Exception {
    public static AuthorizationException DEFAULT_INSTANCE = new AuthorizationException("Authorization required");
    public AuthorizationException(String message){
        super(message);
    }
}

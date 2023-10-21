package org.itmo.ws.jaxws.exception;

public class CarServiceFault {
    protected String message;

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public static CarServiceFault defaultInstance(String message) {
        CarServiceFault fault = new CarServiceFault();
        fault.setMessage(message);
        return fault;
    }

}

package org.itmo.ws.jaxrs.exception;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Provider
public class CarServiceExceptionMapper implements ExceptionMapper<CarServiceException> {
    @Override
    public Response toResponse(CarServiceException e) {
        return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}

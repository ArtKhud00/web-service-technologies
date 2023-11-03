package org.itmo.ws.jaxrs.service;

import org.itmo.ws.jaxrs.exception.AuthorizationException;
import org.itmo.ws.jaxrs.exception.ForbiddenException;
import org.itmo.ws.jaxrs.model.Car;
import org.itmo.ws.jaxrs.model.PostgreSQLDAO;
import org.itmo.ws.jaxrs.exception.CarServiceException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.Base64;
import java.util.List;

@Path("/cars")
@Produces({MediaType.APPLICATION_JSON})
public class CarsResource {
    @GET
    public List<Car> getCarsByParams(@QueryParam("brand") String brand,
                                     @QueryParam("model") String model,
                                     @QueryParam("year") int year,
                                     @QueryParam("engine") String engineType,
                                     @QueryParam("perfomance") int perfomance) {
        List<Car> cars = new PostgreSQLDAO().getCarsByParams(brand, model, year, engineType, perfomance);
        return cars;
    }

    @POST
    public String createNewCar(@Context HttpHeaders headers,
                               @QueryParam("brand") String brand,
                               @QueryParam("model") String model,
                               @QueryParam("year") int year,
                               @QueryParam("engine") String engineType,
                               @QueryParam("perfomance") int perfomance) throws CarServiceException,
                                                                                AuthorizationException,
                                                                                ForbiddenException {
        if(!isUserAuthorized(headers)) {
            throw AuthorizationException.DEFAULT_INSTANCE;
        }
        if(!isAccessAllowed(headers)) {
            throw ForbiddenException.DEFAULT_INSTANCE;
        }
        if (brand == null || model == null || year == 0 || engineType == null || perfomance == 0){
            throw new CarServiceException("Values are not provided to all fields");
        }
        Long id = new PostgreSQLDAO().createNewCar(brand, model, year, engineType, perfomance);
        return String.valueOf(id);
    }

    @Path("/{id}")
    @PUT
    public String updateCar(@Context HttpHeaders headers,
                            @PathParam("id") Long id,
                            @QueryParam("brand") String brand,
                            @QueryParam("model") String model,
                            @QueryParam("year") int year,
                            @QueryParam("engine") String engineType,
                            @QueryParam("perfomance") int perfomance) throws CarServiceException,
                                                                             AuthorizationException,
                                                                             ForbiddenException {
        if(!isUserAuthorized(headers)) {
            throw AuthorizationException.DEFAULT_INSTANCE;
        }
        if(!isAccessAllowed(headers)) {
            throw ForbiddenException.DEFAULT_INSTANCE;
        }
        if (id == null || id <= 0) {
            throw new CarServiceException("Provided incorrect id");
        }
        if(brand == null && model == null && year == 0 && engineType == null && perfomance == 0){
            throw new CarServiceException("Nothing was provided to update");
        }
        Boolean result = new PostgreSQLDAO().updateCar(id, brand, model, year, engineType, perfomance);
        return String.valueOf(result);
    }

    @Path("/{id}")
    @DELETE
    public String deleteCar(@Context HttpHeaders headers, @PathParam("id") Long id) throws CarServiceException,
                                                                                           AuthorizationException,
                                                                                           ForbiddenException {
        if(!isUserAuthorized(headers)) {
            throw AuthorizationException.DEFAULT_INSTANCE;
        }
        if(!isAccessAllowed(headers)) {
            throw ForbiddenException.DEFAULT_INSTANCE;
        }
        if (id == null || id <= 0) {
            throw new CarServiceException("Provided incorrect id");
        }
        Boolean result = new PostgreSQLDAO().deleteCar(id);
        return String.valueOf(result);
    }

    private boolean isUserAuthorized(HttpHeaders headers) {
        List<String> authHeader = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.size() > 0) {
            return true;
        }
        return false;
    }

    private boolean isAccessAllowed(HttpHeaders headers) {
        List<String> authHeader = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        String authToken = authHeader.get(0);
        authToken = authToken.replaceFirst("[Bb]asic ", "");
        String decodedString = new String(Base64.getDecoder().decode(authToken));
        String[] credentials = decodedString.split(":", 2);
        return "username".equals(credentials[0]) && "password".equals(credentials[1]);
    }

}

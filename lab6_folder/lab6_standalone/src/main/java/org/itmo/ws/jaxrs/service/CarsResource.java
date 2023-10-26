package org.itmo.ws.jaxrs.service;

import org.itmo.ws.jaxrs.model.Car;
import org.itmo.ws.jaxrs.model.PostgreSQLDAO;
import org.itmo.ws.jaxrs.exception.CarServiceException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public String createNewCar(@QueryParam("brand") String brand,
                               @QueryParam("model") String model,
                               @QueryParam("year") int year,
                               @QueryParam("engine") String engineType,
                               @QueryParam("perfomance") int perfomance) throws CarServiceException {
        Long id = new PostgreSQLDAO().createNewCar(brand, model, year, engineType, perfomance);
        return String.valueOf(id);
    }

    @Path("/{id}")
    @PUT
    public String updateCar(@PathParam("id") Long id,
                            @QueryParam("brand") String brand,
                            @QueryParam("model") String model,
                            @QueryParam("year") int year,
                            @QueryParam("engine") String engineType,
                            @QueryParam("perfomance") int perfomance) throws CarServiceException {

        Boolean result = new PostgreSQLDAO().updateCar(id, brand, model, year, engineType, perfomance);
        return String.valueOf(result);
    }

    @Path("/{id}")
    @DELETE
    public String deleteCar(@PathParam("id") Long id) throws CarServiceException {

        Boolean result = new PostgreSQLDAO().deleteCar(id);
        return String.valueOf(result);
    }

}

package org.itmo.ws.jaxrs.service;

import org.itmo.ws.jaxrs.Model.Car;
import org.itmo.ws.jaxrs.Model.PostgreSQLDAO;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
        //List<Car> cars = new PostgreSQLDAO().getCars();
        return cars;
    }
}

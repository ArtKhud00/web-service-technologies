package org.itmo.ws.jaxrs.service;

import org.itmo.ws.jaxrs.Car;
import org.itmo.ws.jaxrs.PostgreSQLDAO;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@RequestScoped
@Path("/cars")
@Produces({MediaType.APPLICATION_JSON})
public class CarRestService {

    private static DataSource dataSource;

    private static final String JNDI_LOOK = "java:/comp/env/jdbc/datasource";
    static {
        try {
            Context context = new InitialContext();
            Object lookup = context.lookup(JNDI_LOOK);
            if(lookup != null){
                dataSource = (DataSource) lookup;
            }else{
                new RuntimeException("JNDI lookup issue");
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    public List<Car> getCarsByParams(@QueryParam("brand") String brand,
                                     @QueryParam("model") String model,
                                     @QueryParam("year") int year,
                                     @QueryParam("engine") String engineType,
                                     @QueryParam("perfomance") int perfomance) {
        List<Car> cars = new PostgreSQLDAO(getConnection()).getCarsByParams(brand, model,
                                                                            year, engineType, perfomance);
        return cars;
    }

    private Connection getConnection() {
        Connection result = null;
        try{
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(CarRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}

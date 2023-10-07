package org.itmo.ws.jaxws.service;



import org.itmo.ws.jaxws.Car;
import org.itmo.ws.jaxws.PostgreSQLDAO;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebService(serviceName = "car-service")
public class CarWebService {

    //@Resource(lookup = "jdbc/datasource")
    private static DataSource dataSource;

    private static final String JNDI_LOOK = "java:/comp/env/jdbc/datasource";
    static {
        try {
            Context context = new InitialContext();
            Object lookup = context.lookup(JNDI_LOOK);
            if(lookup!=null){
                dataSource = (DataSource) lookup;
            }else{
                new RuntimeException("JNDI lookup issue");
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }



    @WebMethod(operationName = "get-cars")
    public List<Car> getCars(){
        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        List<Car> cars = dao.getCars();
        return cars;
    }

    @WebMethod(operationName = "get-cars-by-params")
    public List<Car> getCarsByParams(Car carparams){
        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        List<Car> cars = dao.getCarsByParams(carparams);
        return cars;
    }

    private Connection getConnection() {
        Connection result=null;
        try{


            result= dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(CarWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}

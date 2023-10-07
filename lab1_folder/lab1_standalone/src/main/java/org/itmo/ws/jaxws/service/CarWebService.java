package org.itmo.ws.jaxws.service;

import org.itmo.ws.jaxws.model.PostgreSQLDAO;
import org.itmo.ws.jaxws.model.Car;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
@WebService(serviceName = "car-service")
public class CarWebService {
    @WebMethod(operationName = "get-cars")
    public List<Car> getCars(){
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Car> cars = dao.getCars();
        return cars;
    }

    @WebMethod(operationName = "get-cars-by-params")
    public List<Car> getCarsByParams(Car carparams){
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Car> cars = dao.getCarsByParams(carparams);
        return cars;
    }
}

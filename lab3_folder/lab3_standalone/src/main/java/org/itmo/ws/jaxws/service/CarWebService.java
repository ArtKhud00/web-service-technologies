package org.itmo.ws.jaxws.service;

import org.itmo.ws.jaxws.exception.CarServiceFault;
import org.itmo.ws.jaxws.exception.ServiceException;
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

    @WebMethod(operationName = "create-new-car")
    public Long createNewCar(Car car) throws ServiceException{
        if(!car.isValid()){
            CarServiceFault fault = CarServiceFault.defaultInstance("Values are not provided for all fields");
            throw new ServiceException("Values are not provided for all fields", fault);
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        Long id = dao.createNewCar(car);
        if(id == -1L){
            CarServiceFault fault = CarServiceFault.defaultInstance("Unable to add new record in table");
            throw new ServiceException("Unable to add new record in table", fault);
        }
        return id;
    }

    @WebMethod(operationName = "delete-car")
    public Boolean deleteCar(Long id) throws ServiceException{
        if(id == null || id <= 0){
            CarServiceFault fault = CarServiceFault.defaultInstance("Provided incorrect id");
            throw new ServiceException("Provided incorrect id", fault);
        }
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.deleteCar(id);
    }

    @WebMethod(operationName = "update-car")
    public Boolean updateCar(Long id, Car car) throws ServiceException {
        if(id == null || id <= 0){
            CarServiceFault fault = CarServiceFault.defaultInstance("Provided incorrect id");
            throw new ServiceException("Provided incorrect id", fault);
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        Boolean result = dao.updateCar(id,car);
        return result;
    }
}

package org.itmo.ws.jaxws.service;

import org.itmo.ws.jaxws.exception.CarServiceFault;
import org.itmo.ws.jaxws.exception.ServiceException;
import org.itmo.ws.jaxws.exception.ThrottlingException;
import org.itmo.ws.jaxws.model.PostgreSQLDAO;
import org.itmo.ws.jaxws.model.Car;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.jws.WebMethod;
import javax.jws.WebService;
@WebService(serviceName = "car-service")
public class CarWebService {
    private static int MAX_CONCURRENT_REQUESTS = 5;
    private static AtomicInteger currentRequestsNumber = new AtomicInteger(0);

    @WebMethod(operationName = "get-cars")
    public List<Car> getCars() throws ThrottlingException {
        if(currentRequestsNumber.incrementAndGet() > MAX_CONCURRENT_REQUESTS){
            currentRequestsNumber.decrementAndGet();
            CarServiceFault fault = CarServiceFault.defaultInstance("Too many requests, try later");
            throw new ThrottlingException("Too many requests, try later", fault);
        }
        try {
            PostgreSQLDAO dao = new PostgreSQLDAO();
            List<Car> cars = dao.getCars();
            return cars;
        } finally {
            currentRequestsNumber.decrementAndGet();
        }
    }

    @WebMethod(operationName = "get-cars-by-params")
    public List<Car> getCarsByParams(Car carparams) throws ThrottlingException {
        if(currentRequestsNumber.incrementAndGet() > MAX_CONCURRENT_REQUESTS){
            currentRequestsNumber.decrementAndGet();
            CarServiceFault fault = CarServiceFault.defaultInstance("Too many requests, try later");
            throw new ThrottlingException("Too many requests, try later", fault);
        }
        try {
            PostgreSQLDAO dao = new PostgreSQLDAO();
            List<Car> cars = dao.getCarsByParams(carparams);
            return cars;
        } finally {
            currentRequestsNumber.decrementAndGet();
        }
    }

    @WebMethod(operationName = "create-new-car")
    public Long createNewCar(Car car) throws ServiceException, ThrottlingException{
        if(currentRequestsNumber.incrementAndGet() > MAX_CONCURRENT_REQUESTS){
            currentRequestsNumber.decrementAndGet();
            CarServiceFault fault = CarServiceFault.defaultInstance("Too many requests, try later");
            throw new ThrottlingException("Too many requests, try later", fault);
        }
        try {
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
        } finally {
            currentRequestsNumber.decrementAndGet();
        }
    }

    @WebMethod(operationName = "delete-car")
    public Boolean deleteCar(Long id) throws ServiceException, ThrottlingException {
        if(currentRequestsNumber.incrementAndGet() > MAX_CONCURRENT_REQUESTS){
            currentRequestsNumber.decrementAndGet();
            CarServiceFault fault = CarServiceFault.defaultInstance("Too many requests, try later");
            throw new ThrottlingException("Too many requests, try later", fault);
        }
        try {
            if (id == null || id <= 0) {
                CarServiceFault fault = CarServiceFault.defaultInstance("Provided incorrect id");
                throw new ServiceException("Provided incorrect id", fault);
            }
            PostgreSQLDAO dao = new PostgreSQLDAO();
            return dao.deleteCar(id);
        } finally {
            currentRequestsNumber.decrementAndGet();
        }
    }

    @WebMethod(operationName = "update-car")
    public Boolean updateCar(Long id, Car car) throws ServiceException, ThrottlingException {
        if(currentRequestsNumber.incrementAndGet() > MAX_CONCURRENT_REQUESTS){
            currentRequestsNumber.decrementAndGet();
            CarServiceFault fault = CarServiceFault.defaultInstance("Too many requests, try later");
            throw new ThrottlingException("Too many requests, try later", fault);
        }
        try {
        if(id == null || id <= 0){
            CarServiceFault fault = CarServiceFault.defaultInstance("Provided incorrect id");
            throw new ServiceException("Provided incorrect id", fault);
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        Boolean result = dao.updateCar(id,car);
        return result;
        } finally {
            currentRequestsNumber.decrementAndGet();
        }
    }
}

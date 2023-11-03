package org.itmo.ws.jaxws.service;

import org.itmo.ws.jaxws.exception.*;
import org.itmo.ws.jaxws.model.PostgreSQLDAO;
import org.itmo.ws.jaxws.model.Car;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService(serviceName = "car-service")
public class CarWebService {
    private static int MAX_CONCURRENT_REQUESTS = 5;
    private static AtomicInteger currentRequestsNumber = new AtomicInteger(0);
    @Resource
    private WebServiceContext wsContext;

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
    public Long createNewCar(Car car) throws ServiceException,
                                            ThrottlingException,
                                            AuthorizationException,
                                            ForbiddenException {
        if(currentRequestsNumber.incrementAndGet() > MAX_CONCURRENT_REQUESTS){
            currentRequestsNumber.decrementAndGet();
            CarServiceFault fault = CarServiceFault.defaultInstance("Too many requests, try later");
            throw new ThrottlingException("Too many requests, try later", fault);
        }
        try {
            if(!isUserAuthorized()){
                CarServiceFault fault = CarServiceFault
                                        .defaultInstance("Unauthorized: Missing or invalid Authorization header");
                throw new AuthorizationException("Unauthorized", fault);
            }
            if(!isAccessAllowed()){
                CarServiceFault fault = CarServiceFault.defaultInstance("Forbidden: Incorrect username or password");
                throw new ForbiddenException("Forbidden", fault);
            }
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
    public Boolean deleteCar(Long id) throws ServiceException,
                                            ThrottlingException,
                                            AuthorizationException,
                                            ForbiddenException {
        if (currentRequestsNumber.incrementAndGet() > MAX_CONCURRENT_REQUESTS) {
            currentRequestsNumber.decrementAndGet();
            CarServiceFault fault = CarServiceFault.defaultInstance("Too many requests, try later");
            throw new ThrottlingException("Too many requests, try later", fault);
        }
        try {
            if (!isUserAuthorized()){
                CarServiceFault fault = CarServiceFault
                        .defaultInstance("Unauthorized: Missing or invalid Authorization header");
                throw new AuthorizationException("Unauthorized", fault);
            }
            if (!isAccessAllowed()) {
                CarServiceFault fault = CarServiceFault.defaultInstance("Forbidden: Incorrect username or password");
                throw new ForbiddenException("Forbidden", fault);
            }
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
    public Boolean updateCar(Long id, Car car) throws ServiceException,
                                                    ThrottlingException,
                                                    AuthorizationException,
                                                    ForbiddenException {
        if (currentRequestsNumber.incrementAndGet() > MAX_CONCURRENT_REQUESTS) {
            currentRequestsNumber.decrementAndGet();
            CarServiceFault fault = CarServiceFault.defaultInstance("Too many requests, try later");
            throw new ThrottlingException("Too many requests, try later", fault);
        }
        try {
            if (!isUserAuthorized()) {
                CarServiceFault fault = CarServiceFault
                        .defaultInstance("Unauthorized: Missing or invalid Authorization header");
                throw new AuthorizationException("Unauthorized", fault);
            }
            if (!isAccessAllowed()) {
                CarServiceFault fault = CarServiceFault.defaultInstance("Forbidden: Incorrect username or password");
                throw new ForbiddenException("Forbidden", fault);
            }
            if (id == null || id <= 0) {
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

    private boolean isUserAuthorized() {
        MessageContext messageContext = wsContext.getMessageContext();
        Map http_headers = (Map) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
        List authHeaderList = (List) http_headers.get("Authorization");
        if(authHeaderList == null || authHeaderList.isEmpty()){
            return false;
        }
        String authHeader = (String) authHeaderList.get(0);
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return false;
        }
        return true;
    }

    private boolean isAccessAllowed() {
        MessageContext messageContext = wsContext.getMessageContext();
        Map http_headers = (Map) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
        List authHeaderList = (List) http_headers.get("Authorization");
        String authHeader = (String) authHeaderList.get(0);
        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        String[] parts = credentials.split(":", 2);

        String expectedUsername = "username";
        String expectedPassword = "password";

        if (parts[0].equals(expectedUsername) && parts[1].equals(expectedPassword)) {
            return true;
        }
        return false;
    }
}

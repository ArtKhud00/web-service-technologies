package org.itmo.ws.client;

import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class WebServiceClient {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8082/CarService?wsdl");
        CarService carService = new CarService(url);
        CarWebService carWebService = carService.getCarWebServicePort();
        Map<String, Object> requestHeaders = ((BindingProvider) carWebService).getRequestContext();
        String authString = USERNAME + ":" + PASSWORD;
        String base64Auth = java.util.Base64.getEncoder().encodeToString(authString.getBytes());
        requestHeaders.put(BindingProvider.USERNAME_PROPERTY, USERNAME);
        requestHeaders.put(BindingProvider.PASSWORD_PROPERTY, PASSWORD);
        requestHeaders.put("Authorization", "Basic " + base64Auth);
        try {
            List<Car> cars = carWebService.getCars();
            System.out.println("Select all rows in db");
            printCars(cars);
        } catch (ThrottlingException ex){
            System.out.println("Throttling error " + ex.getMessage());
        }

        System.out.println("Insert new car");
        Car insert_car = new Car();
        insert_car.setCarbrand("BMW");
        insert_car.setCarmodel("M5");
        insert_car.setYear(2006);
        insert_car.setEnginetype("gas");
        insert_car.setPerfomance(507);
        Long id = -1L;
        try {
            id = carWebService.createNewCar(insert_car);
        } catch (Exception ex) {
            System.out.println("Internal service ERROR: " + ex.getMessage());
        }
        System.out.println("inserted id = " + id);

        System.out.println("Trying to insert car with not all fields");
        Car notAllFields = new Car();
        notAllFields.setCarbrand("Subaru");
        Long id1 = -1L;
        try{
            id1 = carWebService.createNewCar(notAllFields);
        } catch (Exception ex) {
            System.out.println("Internal service ERROR: " + ex.getMessage());
        }
        System.out.println("\n");
        System.out.println("Update inserted car");
        Car update_car = new Car();
        update_car.setYear(2007);
        update_car.setCarmodel("M6");
        Boolean isUpdated = false;
        try {
            isUpdated = carWebService.updateCar(id, update_car);
        } catch (Exception ex) {
            System.out.println("Internal service ERROR: "+ ex.getMessage());
        }
        System.out.println("isUpdated:" + isUpdated);
        try {
            List<Car> cars = carWebService.getCars();
            printCars(cars);
        } catch (ThrottlingException ex){
            System.out.println("Throttling error " + ex);
        }
        System.out.println("\n");

        isUpdated = false;
        System.out.println("Trying to update non-existent record");
        Car nonexist = new Car();
        nonexist.setCarbrand("Mercedes");
        nonexist.setCarbrand("C class");
        try {
            isUpdated = carWebService.updateCar(13L,nonexist);
        } catch (Exception ex){
            System.out.println("Internal service ERROR: " + ex.getMessage());
        }
        System.out.println("isUpdated: " + isUpdated);
        System.out.println("\n");
        System.out.println("Delete BMW M5");
        Boolean isDeleted = false;
        try {
            isDeleted =carWebService.deleteCar(id);
        } catch(Exception ex) {
            System.out.println("Internal service ERROR: " + ex.getMessage());
        }
        System.out.println("isDeleted:" + isDeleted);

        System.out.println("\nTrying to delete record with incorrect id");
        isDeleted = false;
        try {
            isDeleted = carWebService.deleteCar(id1);
        } catch (Exception ex){
            System.out.println("Internal service ERROR: " + ex.getMessage());
        }
    }

    private static void printCars (List<Car> cars){
        for (Car car : cars){
            System.out.println("carbrand: " + car.getCarbrand()+
                    ", carmodel: " + car.getCarmodel() +
                    ", year: " + car.getYear() +
                    ", enginetype: " + car.getEnginetype() +
                    ", perfomance: " + car.getPerfomance());
        }
        System.out.println("Total cars: " + cars.size());
    }
}
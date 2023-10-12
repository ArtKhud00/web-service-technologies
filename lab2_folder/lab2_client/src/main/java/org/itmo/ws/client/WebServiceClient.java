package org.itmo.ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8082/CarService?wsdl");
        CarService carService = new CarService(url);
        List<Car> cars = carService.getCarWebServicePort().getCars();
        System.out.println("Select all rows in db");
        printCars(cars);
        System.out.println("\n");

        System.out.println("Insert new car");
        Car insert_car = new Car();
        insert_car.setCarbrand("BMW");
        insert_car.setCarmodel("M5");
        insert_car.setYear(2006);
        insert_car.setEnginetype("gas");
        insert_car.setPerfomance(507);
        Long id = carService.getCarWebServicePort().createNewCar(insert_car);
        System.out.println("inserted id = "+id);
        cars = carService.getCarWebServicePort().getCars();
        printCars(cars);
        System.out.println("\n");

        System.out.println("Update inserted car");
        Car update_car = new Car();
        update_car.setYear(2007);
        update_car.setPerfomance(-1);
        update_car.setCarmodel("M6");
        cars = carService.getCarWebServicePort().getCars();
        Boolean isUpdated = carService.getCarWebServicePort().updateCar(id,update_car);
        System.out.println("isUpdated:" + isUpdated);
        cars = carService.getCarWebServicePort().getCars();
        printCars(cars);
        System.out.println("\n");

        System.out.println("Delete BMW M5");
        Boolean isDeleted = carService.getCarWebServicePort().deleteCar(id);
        System.out.println("isDeleted:" + isDeleted);
        cars = carService.getCarWebServicePort().getCars();
        printCars(cars);
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
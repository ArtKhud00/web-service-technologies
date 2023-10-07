package org.itmo.ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {
    public static void main(String[] args) throws MalformedURLException {
//        URL url = new URL("http://localhost:8082/CarService?wsdl");
        URL url = new URL("http://localhost:8083/lab1_j2ee/ws/service?wsdl");
        CarService carService = new CarService(url);
        List<Car> cars = carService.getCarWebServicePort().getCars();
        System.out.println("Select all rows in db");
        printCars(cars);
        System.out.println("\n");
        System.out.println("Select electric cars");
        Car search = new Car();
        search.setEnginetype("electric");
        search.setYear(-1);
        search.setPerfomance(-1);
        cars = carService.getCarWebServicePort().getCarsByParams(search);
        printCars(cars);
        System.out.println("\n");
        System.out.println("Select diesel cars or perfomance=150");
        search = new Car();
        search.setEnginetype("diesel");
        search.setYear(-1);
        search.setPerfomance(150);
        cars = carService.getCarWebServicePort().getCarsByParams(search);
        printCars(cars);
        System.out.println("\n");
        System.out.println("Select cars year=2013 or brand=Toyota");
        search = new Car();
        search.setCarbrand("Toyota");
        search.setYear(2013);
        search.setPerfomance(-1);
        cars = carService.getCarWebServicePort().getCarsByParams(search);
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
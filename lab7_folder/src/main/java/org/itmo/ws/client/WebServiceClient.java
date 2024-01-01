package org.itmo.ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class WebServiceClient {

    public void processActions(String accessPoint) throws MalformedURLException {
        URL url = new URL(accessPoint);
        CarService carService = new CarService(url);
        Scanner scanner = new Scanner(System.in);
        boolean isWork = true;
        while (isWork) {
            System.out.println("Выберите метод CRUD (введите CREATE, READ, UPDATE или DELETE) или введите \"exit\" для выхода:");
            String choosenMethod = scanner.nextLine();
            switch (choosenMethod){
                case "CREATE": {
                    System.out.println("Enter the data for creating new car");
                    System.out.print("Carbrand: ");
                    String carBrand = scanner.nextLine();
                    System.out.print("\nCarmodel: ");
                    String carModel = scanner.nextLine();
                    System.out.print("\nYear: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("\nEngineType: ");
                    String engineType = scanner.nextLine();
                    System.out.print("\nPerformance: ");
                    int performance = Integer.parseInt(scanner.nextLine());
                    Car insert_car = new Car();
                    insert_car.setCarbrand(carBrand);
                    insert_car.setCarmodel(carModel);
                    insert_car.setYear(year);
                    insert_car.setEnginetype(engineType);
                    insert_car.setPerfomance(performance);
                    Long id = carService.getCarWebServicePort().createNewCar(insert_car);
                    System.out.println("inserted id = "+id + "\n");
                    break;
                }
                case "READ":{
                    List<Car> cars = carService.getCarWebServicePort().getCars();
                    printCars(cars);
                    System.out.println("\n");
                    break;
                }
                case "UPDATE": {
                    System.out.print("Enter id of car to update: ");
                    //int id = Integer.parseInt(scanner.nextLine());
                    Long id = Long.parseLong(scanner.nextLine());
                    System.out.println("Enter the data for updating car");
                    System.out.print("Carbrand: ");
                    String carBrand = scanner.nextLine();
                    System.out.print("\nCarmodel: ");
                    String carModel = scanner.nextLine();
                    System.out.print("\nYear: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("\nEngineType: ");
                    String engineType = scanner.nextLine();
                    System.out.print("\nPerformance: ");
                    int performance = Integer.parseInt(scanner.nextLine());
                    Car update_car = new Car();
                    update_car.setYear(year);
                    update_car.setPerfomance(performance);
                    update_car.setCarmodel(carModel);
                    update_car.setCarbrand(carBrand);
                    update_car.setEnginetype(engineType);
                    Boolean isUpdated = carService.getCarWebServicePort().updateCar(id,update_car);
                    System.out.println("isUpdated:" + isUpdated + "\n");
                    break;
                }
                case "DELETE": {
                    System.out.print("Enter id of car to delete: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    Boolean isDeleted = carService.getCarWebServicePort().deleteCar(id);
                    System.out.println("isDeleted:" + isDeleted + "\n");
                    break;
                }
                case "EXIT" : {
                    isWork = false;
                    break;
                }
            }
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
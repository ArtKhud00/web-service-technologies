package org.itmo.ws;

import org.itmo.ws.client.WebServiceClient;

import java.util.Scanner;

public class JuddiApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your juddi login (default: uddiadmin):");
        String userName = scanner.nextLine();
        if (userName.trim().isEmpty()) {
            userName = "uddiadmin";
            System.out.println(userName);
        }
        System.out.println("Please enter your juddi password (default: da_password1)?");
        String userPass = scanner.nextLine();
        if (userPass.trim().isEmpty()) {
            userPass = "da_password1";
        }
        boolean isWork = true;
        while(isWork) {
            System.out.println("What do you want to do?\n1 - Register new business and service\n2- Find existing service\n3- Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                    System.out.println("Please enter your business name (default: ITMOUnivercity):");
                    String businessName = scanner.nextLine();
                    if (businessName.trim().isEmpty()) {
                        businessName = "ITMOUnivercity";
                        System.out.println(businessName);
                    }

                    System.out.println("Please enter your service name (default: CarService)?");
                    String registeredServiceName = scanner.nextLine();
                    if (registeredServiceName.trim().isEmpty()) {
                        registeredServiceName = "CarService";
                        System.out.println(registeredServiceName);
                    }
                    System.out.println("Please specify access point to your service (default: http://localhost:8082/CarService?wsdl):");
                    String registeredServiceURL = scanner.nextLine();
                    if (registeredServiceURL.trim().isEmpty()) {
                        registeredServiceURL = "http://localhost:8082/CarService?wsdl";
                        System.out.println(registeredServiceURL);
                    }
                    SimplePublish simplePublish = new SimplePublish();
                    simplePublish.publish(userName, userPass, businessName, registeredServiceName, registeredServiceURL);
                    System.out.println();
                    break;
                }
                case "2": {
                    System.out.println("Please provide service name to search (default: CarService)?");
                    String searchServiceName = scanner.nextLine();
                    if (searchServiceName.trim().isEmpty()) {
                        searchServiceName = "CarService ";
                    }
                    SimpleBrowse simpleBrowse = new SimpleBrowse();
                    String accessPoint = null;
                    try {
                        //System.out.println("Here");
                        accessPoint = simpleBrowse.searchService(searchServiceName, userName, userPass);
                        if(!accessPoint.isEmpty() && accessPoint != null) {
                            System.out.println("Found service \'" + searchServiceName + "\', access point:" + accessPoint);
                            System.out.println("Do you want to use service right now?");
                            String agree = scanner.nextLine();
                            if(agree.equals("y")){
                                WebServiceClient serviceClient = new WebServiceClient();
                                serviceClient.processActions(accessPoint);
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println("An exception occured, " + ex);
                    }
                    System.out.println();
                    break;
                }
                case "3": {
                    isWork = false;
                    break;
                }
            }
        }
    }

}

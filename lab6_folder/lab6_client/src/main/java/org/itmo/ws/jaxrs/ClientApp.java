package org.itmo.ws.jaxrs;

import org.itmo.ws.jaxrs.Model.Car;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.util.List;
import javax.ws.rs.core.MediaType;

public class ClientApp {
    private static final String URL = "http://localhost:8084/rest/cars";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String INVALID_USERNAME = "login";

    public static void main(String[] args) {
        Client client = Client.create();
        String authHeader = "Basic " + java.util.Base64.getEncoder()
                            .encodeToString((USERNAME + ":" + PASSWORD).getBytes());
        String authHeader1 = "Basic " + java.util.Base64.getEncoder()
                            .encodeToString((INVALID_USERNAME + ":" + PASSWORD).getBytes());
        printList(getCars(client,null,null,0,null,0));
        System.out.println();
        System.out.println("New query");
        printList(getCars(client,null,null,0,"electric",0));
        Long id = -1L;
        try {
            id = createCar(client, "Ford", "Focus", 2013, "gas", 115, authHeader);
            System.out.println("Created id: " + id);
        } catch (IllegalStateException ex){
            System.out.println("Internal service ERROR: " + ex.getMessage());
        }
        Long id1 = -1L;
        try{
            id1 = createCar(client, "Subaru",null,0,null,0, authHeader);
            System.out.println("Created id: " + id);
        } catch(IllegalStateException ex) {
            System.out.println("Internal service ERROR: " + ex.getMessage());
        }
        String status = null;
        try{
            status = updateCar(client, id, null,"Mondeo",0,"diesel",155, authHeader1);
            System.out.println("Is updated " + status);
        } catch (IllegalStateException ex) {
            System.out.println("Internal service ERROR: " + ex.getMessage());
        }
        String status1 = null;
        try{
            status1 = updateCar(client, -1L, "Toyota", "Supra",0,null,0, authHeader);
            System.out.println("Is updated " + status1);
        } catch (Exception ex) {
            System.out.println("Internal service ERROR: " + ex.getMessage());
        }
        status = null;
        try {
            status = deleteCar(client, id, authHeader);
            System.out.println("Is deleted " + status);
        } catch (IllegalStateException ex) {
            System.out.println("Internal service ERROR: " + ex.getMessage());
        }
        status1 = null;
        try {
            status1 = deleteCar(client, 120L, authHeader);
            System.out.println("Is deleted " + status1);
        } catch (IllegalStateException ex) {
            System.out.println("Internal service ERROR: " + ex.getMessage());
        }
    }

    private static List<Car> getCars(Client client, String brand,
                                     String model, int year,
                                     String engineType, int perfomance){
        WebResource webResource = client.resource(URL);
        if(brand != null) {
            webResource = webResource.queryParam("brand", brand);
        }
        if(model != null) {
            webResource = webResource.queryParam("model", model);
        }
        if(year != 0){
            webResource = webResource.queryParam("year", String.valueOf(year));
        }
        if(engineType != null){
            webResource = webResource.queryParam("engine", engineType);
        }
        if(perfomance != 0){
            webResource = webResource.queryParam("perfomance", String.valueOf(perfomance));
        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Car>> type = new GenericType<List<Car>>(){};
        return response.getEntity(type);
    }

    private static Long createCar(Client client, String brand,
                                    String model, int year,
                                    String engineType, int perfomance,
                                    String authHeader){
        WebResource webResource = client.resource(URL);
        if(brand != null) {
            webResource = webResource.queryParam("brand", brand);
        }
        if(model != null) {
            webResource = webResource.queryParam("model", model);
        }
        if(year != 0){
            webResource = webResource.queryParam("year", String.valueOf(year));
        }
        if(engineType != null){
            webResource = webResource.queryParam("engine", engineType);
        }
        if(perfomance != 0){
            webResource = webResource.queryParam("perfomance", String.valueOf(perfomance));
        }
        ClientResponse response = webResource.header("Authorization", authHeader)
                                  .accept(MediaType.APPLICATION_JSON)
                                  .post(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Create request failed");
        }
        GenericType<String> type = new GenericType<String>(){};
        return Long.decode(response.getEntity(type));
    }

    private static String updateCar(Client client, Long id,String brand,
                                    String model, int year,
                                    String engineType, int perfomance,
                                    String authHeader){
        WebResource webResource = client.resource(URL);
        webResource = webResource.path(String.valueOf(id));
        if(brand != null) {
            webResource = webResource.queryParam("brand", brand);
        }
        if(model != null) {
            webResource = webResource.queryParam("model", model);
        }
        if(year != 0){
            webResource = webResource.queryParam("year", String.valueOf(year));
        }
        if(engineType != null){
            webResource = webResource.queryParam("engine", engineType);
        }
        if(perfomance != 0){
            webResource = webResource.queryParam("perfomance", String.valueOf(perfomance));
        }
        ClientResponse response = webResource.header("Authorization", authHeader)
                                  .accept(MediaType.APPLICATION_JSON)
                                  .put(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Update request failed, id= " + id);
        }
        GenericType<String> type = new GenericType<String>(){};
        return response.getEntity(type);
    }

    private static String deleteCar(Client client, Long id, String authHeader){
        WebResource webResource = client.resource(URL);
        webResource = webResource.path(String.valueOf(id));
        ClientResponse response = webResource.header("Authorization", authHeader)
                                  .accept(MediaType.APPLICATION_JSON)
                                  .delete(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.NO_CONTENT.getStatusCode() &&
                response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Delete request failed, id= " + id);
        }
        GenericType<String> type = new GenericType<String>(){};
        return response.getEntity(type);
    }

    private static void printList(List<Car> cars){
        for(Car car : cars){
            System.out.println(car);
        }
    }
}
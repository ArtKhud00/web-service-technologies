package org.itmo.ws.jaxrs;

import org.itmo.ws.jaxrs.model.Car;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.util.List;
import javax.ws.rs.core.MediaType;

public class ClientApp {

    // standalone realization
    //private static final String URL = "http://localhost:8084/rest/cars";
    // j2ee realization
    private static final String URL = "http://localhost:8083/lab4_j2ee/rest/cars";
    public static void main(String[] args) {
        Client client = Client.create();
        printList(getCars(client,null,null,0,null,0));
        System.out.println();
        System.out.println("New query");
        printList(getCars(client,null,null,0,"electric",0));
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

    private static void printList(List<Car> cars){
        for(Car car : cars){
            System.out.println(car);
        }
    }
}
package api.tests;

import api.endpoints.PetEndpoints;
import api.endpoints.StoreEndPoints;
import api.entitites.Order;
import api.entitites.Pet;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class StoreTests {

    Pet pet;
    Order order;
    Order orderResponse;
    Order[] ordersResponse;

    @BeforeEach
    public void init() {
        int value = new Random().nextInt(10000000 - 1) + 1;
        pet = new Pet(value);
        Response response = PetEndpoints.addPet(pet);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        order = new Order(value, pet.getId());
    }

    @Test
    @Description("Test Description : Returns a map of status codes to quantities")
    public void testGetInventory() {
        Response response = StoreEndPoints.addOrder(order);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        response = StoreEndPoints.getOrderByStatus();
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode(), "Cannot get orders");
    }

    @Test
    @Description("Test Description : Place a new order in the store")
    public void testAddOrder() {
        Response response = StoreEndPoints.addOrder(order);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        orderResponse = response.getBody().as(Order.class);
        Assertions.assertEquals(order.getId(), orderResponse.getId(), "Id is not the expected.");
        Assertions.assertEquals(order.getPetId(), orderResponse.getPetId(), "Pet Id is not the expected.");
        Assertions.assertEquals(order.getQuantity(), orderResponse.getQuantity(), "Quantity is not the expected.");
        Assertions.assertEquals(order.getShipDate(), orderResponse.getShipDate(), "Ship date was not updated.");
        Assertions.assertEquals(order.getStatus(), orderResponse.getStatus(), "Status is not the expected.");
        Assertions.assertEquals(order.isComplete(), orderResponse.isComplete(), "Complete status is not the expected.");

        response = StoreEndPoints.getOrder(order.getId());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        orderResponse = response.getBody().as(Order.class);
        Assertions.assertEquals(order.getId(), orderResponse.getId(), "Id is not the expected.");
        Assertions.assertEquals(order.getPetId(), orderResponse.getPetId(), "Pet Id is not the expected.");
    }

    @Test
    @Description("Test Description : Find purchase order by Id")
    public void testGetOrder() {
        Response response = StoreEndPoints.addOrder(order);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        orderResponse = response.getBody().as(Order.class);
        Assertions.assertEquals(order.getId(), orderResponse.getId(), "Id is not the expected.");
        Assertions.assertEquals(order.getPetId(), orderResponse.getPetId(), "Pet Id is not the expected.");


        response = StoreEndPoints.getOrder(order.getId());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        orderResponse = response.getBody().as(Order.class);
        Assertions.assertEquals(order.getId(), orderResponse.getId(), "Id is not the expected.");
        Assertions.assertEquals(order.getPetId(), orderResponse.getPetId(), "Pet Id is not the expected.");
        Assertions.assertEquals(order.getQuantity(), orderResponse.getQuantity(), "Quantity is not the expected.");
        Assertions.assertEquals(order.getStatus(), orderResponse.getStatus(), "Status is not the expected.");
        Assertions.assertEquals(order.isComplete(), orderResponse.isComplete(), "Complete status is not the expected.");
    }

    @Test
    @Description("Test Description : Delete order by Id")
    public void testDeleteOrder() {
        Response response = StoreEndPoints.addOrder(order);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        orderResponse = response.getBody().as(Order.class);

        response = StoreEndPoints.deleteOrder(order.getId());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        response = StoreEndPoints.getOrder(order.getId());
        response.then().log().all();
        Assertions.assertEquals(404, response.getStatusCode());
    }
}

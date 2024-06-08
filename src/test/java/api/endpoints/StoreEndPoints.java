package api.endpoints;

import api.entitites.Order;
import api.entitites.Pet;
import api.payloads.OrderPayload;
import api.payloads.PetPayload;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {

    public static Response getOrderByStatus() {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(Paths.GET_STORE_INVENTORY);
    }

    public static Response addOrder(Order order) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(OrderPayload.orderPayload(order))
                .when()
                .post(Paths.POST_PLACE_ORDER);
    }

    public static Response getOrder(int id) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("orderId", id)
                .when()
                .get(Paths.GET_FIND_ORDER_BY_ID);
    }

    public static Response deleteOrder(int id) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .pathParam("orderId", id)
                .when()
                .delete(Paths.DELETE_ORDER_BY_ID);
    }
}

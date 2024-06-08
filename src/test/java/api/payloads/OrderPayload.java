package api.payloads;

import api.entitites.Order;
import api.entitites.Pet;
import api.entitites.User;

public class OrderPayload {

    public static String orderPayload(Order order) {
        return "{\n" +
                "  \"id\": " + order.getId() + ",\n" +
                "  \"petId\": " + order.getPetId() + ",\n" +
                "  \"quantity\": " + order.getQuantity() + ",\n" +
                "  \"shipDate\": \"" + order.getShipDate() + "\",\n" +
                "  \"status\": \"" + order.getStatus() + "\",\n" +
                "  \"complete\": " + order.isComplete() + "\n" +
                "}";
    }
}

package api.payloads;

import api.entitites.Pet;
import api.entitites.User;

public class PetPayload {

    public static String petPayload(Pet pet) {
        return "{\n" +
                "  \"id\": " + pet.getId() + ",\n" +
                "  \"name\": \"" + pet.getName() + "\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Cats\"\n" +
                "  },\n" +
                "  \"photoUrls\": [\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"tag" + pet.getId() + "\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \""+pet.getStatus()+"\"\n" +
                "}";
    }
}

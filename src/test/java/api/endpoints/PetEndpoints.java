package api.endpoints;

import api.entitites.Pet;
import api.entitites.User;
import api.payloads.PetPayload;
import api.payloads.UserPayload;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class PetEndpoints {

    public static Response addPet(Pet pet) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(PetPayload.petPayload(pet))
                .when()
                .post(Paths.POST_ADD_PET);
    }

    public static Response getPet(int id) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("petId", id)
                .when()
                .get(Paths.GET_FIND_PET_BY_ID);
    }

    public static Response getPetByStatus(String status) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParams("status", status)
                .when()
                .get(Paths.GET_FIND_PET_BY_STATUS);
    }

    public static Response getPetByTag(String tag) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .accept(ContentType.JSON)
                .queryParam("tags", tag)
                .when()
                .get(Paths.GET_FIND_PET_BY_TAG);
    }

    public static Response updatePetWithFormData(Pet pet) {
        Map<String, String> params = new HashMap<>();
        params.put("name", pet.getName() + "Updated");
        params.put("status", "pending");
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("petId", pet.getId())
                .queryParams(params)
                .when()
                .post(Paths.POST_UPDATE_PET);
    }

    public static Response updatePet(Pet pet) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(PetPayload.petPayload(pet))
                .when()
                .put(Paths.PUT_UPDATE_PET);
    }

    public static Response deletePet(int id) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .pathParam("petId", id)
                .header("api_key", "1245")
                .when()
                .delete(Paths.DELETE_PET_BY_ID);
    }


}

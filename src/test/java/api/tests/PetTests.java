package api.tests;

import api.endpoints.PetEndpoints;
import api.entitites.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

public class PetTests {
    Pet pet;
    Pet petResponse;
    Pet[] petsResponse;

    @BeforeEach
    public void init() {
        int value = new Random().nextInt(10000000 - 1) + 1;
        pet = new Pet(value);
    }

    @Test
    @Description("Test Description : Update an existing pet by Id")
    public void testUpdatePet() {
        Response response = PetEndpoints.addPet(pet);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        pet.setName(pet.getName() + "Updated");
        pet.setStatus("pending");
        response = PetEndpoints.updatePet(pet);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        petResponse = response.getBody().as(Pet.class);
        Assertions.assertEquals(pet.getId(), petResponse.getId(), "Id is not the expected.");
        Assertions.assertEquals(pet.getName(), pet.getName(), "Name was not updated.");
        Assertions.assertEquals(pet.getStatus(), petResponse.getStatus(), "Status is not the expected.");

        response = PetEndpoints.getPet(pet.getId());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        petResponse = response.getBody().as(Pet.class);
        Assertions.assertEquals(pet.getId(), petResponse.getId(), "Id is not the expected.");
        Assertions.assertEquals(pet.getName(), petResponse.getName(), "Username is not the expected.");
    }

    @Test
    @Description("Test Description : Add a new pet to the store")
    public void testAddPet() {
        Response response = PetEndpoints.addPet(pet);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        petResponse = response.getBody().as(Pet.class);
        Assertions.assertEquals(pet.getId(), petResponse.getId(), "Id is not the expected.");
        Assertions.assertEquals(pet.getName(), petResponse.getName(), "Name was not updated.");
        Assertions.assertEquals(pet.getStatus(), petResponse.getStatus(), "Status is not the expected.");

        response = PetEndpoints.getPet(pet.getId());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        petResponse = response.getBody().as(Pet.class);
        Assertions.assertEquals(pet.getId(), petResponse.getId(), "Id is not the expected.");
        Assertions.assertEquals(pet.getName(), petResponse.getName(), "Username is not the expected.");
    }

    @Test
    @Description("Test Description : Find pet by status")
    public void testFindPetByStatus() throws JsonProcessingException {
        Response response = PetEndpoints.addPet(pet);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        petResponse = response.getBody().as(Pet.class);
        Assertions.assertEquals(pet.getId(), petResponse.getId(), "Id is not the expected.");

        response = PetEndpoints.getPetByStatus("pending");
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        petsResponse = mapper.readValue(response.getBody().asPrettyString(), Pet[].class);
        Assertions.assertTrue(petsResponse.length > 0, "Array is empty");
        Assertions.assertFalse(petsResponse[0].getName().isEmpty(), "Username is not the expected.");
    }

    @Test
    @Description("Test Description : Find pet by tag")
    public void testFindPetByTag() throws JsonProcessingException {
        Response response = PetEndpoints.addPet(pet);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        petResponse = response.getBody().as(Pet.class);
        Assertions.assertEquals(pet.getId(), petResponse.getId(), "Id is not the expected.");

        response = PetEndpoints.getPetByTag("tag" + pet.getId());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        petsResponse = mapper.readValue(response.getBody().asPrettyString(), Pet[].class);
        Assertions.assertEquals(pet.getId(), petsResponse[0].getId(), "Id is not the expected.");
        Assertions.assertEquals(pet.getName(), petsResponse[0].getName(), "Username is not the expected.");
    }

    @Test
    @Description("Test Description : Get a pet by Id")
    public void testGetPet() {
        Response response = PetEndpoints.addPet(pet);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        response = PetEndpoints.getPet(pet.getId());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        petResponse = response.getBody().as(Pet.class);
        Assertions.assertEquals(pet.getId(), petResponse.getId(), "Id is not the expected.");
        Assertions.assertEquals(pet.getName(), petResponse.getName(), "Name was not updated.");
        Assertions.assertEquals(pet.getStatus(), petResponse.getStatus(), "Status is not the expected.");
    }

    @Test
    @Description("Test Description : Update a pet with form data")
    public void testUpdatePetWithFormData() {
        Response response = PetEndpoints.addPet(pet);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        response = PetEndpoints.updatePetWithFormData(pet);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        petResponse = response.getBody().as(Pet.class);
        Assertions.assertEquals(pet.getId(), petResponse.getId(), "Id is not the expected.");
        Assertions.assertEquals(pet.getName() + "Updated", petResponse.getName(), "Name was not updated.");
        Assertions.assertEquals("pending", petResponse.getStatus(), "Status is not the expected.");
        Assertions.assertEquals(pet.getCategory().getName(), petResponse.getCategory().getName(), "Category is not the expected.");
    }

    @Test
    @Description("Test Description : Delete a pet by Id")
    public void testDeletePet() {
        Response response = PetEndpoints.addPet(pet);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        response = PetEndpoints.deletePet(pet.getId());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertTrue(response.getBody().asString().contains("Pet deleted"));

        response = PetEndpoints.getPet(pet.getId());
        Assertions.assertEquals(404, response.getStatusCode());
    }
}

package api.tests;

import api.endpoints.UserEndpoints;
import api.entitites.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class UserTests extends FunctionalTest{
    User user;
    User userResponse;
    User[] usersResponse;

    @BeforeEach
    public void init() {
        int value = new Random().nextInt(10000000-1)+1;
        user = new User(value);
    }

    @Test
    @Description("Test Description : Create user")
    public void testAddUser() {
        Response response = UserEndpoints.addUser(user);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        userResponse = response.getBody().as(User.class);
        Assertions.assertEquals(user.getId(), userResponse.getId(),"Id is not the expected.");
        Assertions.assertEquals(user.getUsername(), userResponse.getUsername(),"Username is not the expected.");
        Assertions.assertEquals(user.getFirstName(), userResponse.getFirstName(),"First name is not the expected.");
        Assertions.assertEquals(user.getLastName(), userResponse.getLastName(),"Lastname is not the expected.");
        Assertions.assertEquals(user.getEmail(), userResponse.getEmail(),"Email is not the expected.");
        Assertions.assertEquals(user.getPhone(), userResponse.getPhone(),"Phone is not the expected.");
        Assertions.assertEquals(user.getUserStatus(), userResponse.getUserStatus(),"User status is not the expected.");

        response = UserEndpoints.getUser(user.getUsername());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        userResponse = response.getBody().as(User.class);
        Assertions.assertEquals(user.getId(), userResponse.getId(),"Id is not the expected.");
        Assertions.assertEquals(user.getUsername(), userResponse.getUsername(),"Username is not the expected.");
    }

    @Test
    @Description("Test Description : Creates list of users with given input array")
    public void testAddUserWithList() throws JsonProcessingException {

        User[] users = new User[3];
        users[0]=user;
        User secondUser = new User(user.getId()+1);
        users[1]=secondUser;
        User thirdUser = new User(user.getId()+2);
        users[2]=thirdUser;

        Response response = UserEndpoints.addUserWithList(users);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        usersResponse = mapper.readValue(response.getBody().asPrettyString(), User[].class);
        Assertions.assertEquals(user.getId(), usersResponse[0].getId(),"Id is not the expected.");
        Assertions.assertEquals(user.getUsername(), usersResponse[0].getUsername(),"Username is not the expected.");
        Assertions.assertEquals(secondUser.getId(), usersResponse[1].getId(),"Id is not the expected.");
        Assertions.assertEquals(secondUser.getUsername(), usersResponse[1].getUsername(),"Username is not the expected.");
        Assertions.assertEquals(thirdUser.getId(), usersResponse[2].getId(),"Id is not the expected.");
        Assertions.assertEquals(thirdUser.getUsername(), usersResponse[2].getUsername(),"Username is not the expected.");

        response = UserEndpoints.getUser(user.getUsername());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        userResponse = response.getBody().as(User.class);
        Assertions.assertEquals(user.getId(), userResponse.getId(),"Id is not the expected.");
        Assertions.assertEquals(user.getUsername(), userResponse.getUsername(),"Username is not the expected.");

        response = UserEndpoints.getUser(secondUser.getUsername());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        userResponse = response.getBody().as(User.class);
        Assertions.assertEquals(secondUser.getId(), userResponse.getId(),"Id is not the expected.");
        Assertions.assertEquals(secondUser.getUsername(), userResponse.getUsername(),"Username is not the expected.");

        response = UserEndpoints.getUser(thirdUser.getUsername());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        userResponse = response.getBody().as(User.class);
        Assertions.assertEquals(thirdUser.getId(), userResponse.getId(),"Id is not the expected.");
        Assertions.assertEquals(thirdUser.getUsername(), userResponse.getUsername(),"Username is not the expected.");
    }

    @Test
    @Description("Test Description : Logs user into the system")
    public void testUserLogin() {
        Response response = UserEndpoints.addUser(user);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        response = UserEndpoints.userLogin(user.getUsername(), user.getPassword());
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertTrue(response.getBody().asString().contains("Logged in user session"));
    }

    @Test
    @Description("Test Description : Logs out current logged in user session")
    public void testUserLogout() {
        Response response = UserEndpoints.addUser(user);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        response = UserEndpoints.userLogin(user.getUsername(), user.getPassword());
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertTrue(response.getBody().asString().contains("Logged in user session"));

        response = UserEndpoints.userLogout();
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(response.getBody().asString(), "User logged out");
    }

    @Test
    @Description("Test Description : Get user by username")
    public void testGetUser() {
        Response response = UserEndpoints.addUser(user);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        userResponse = response.getBody().as(User.class);
        Assertions.assertEquals(user.getId(), userResponse.getId(),"Id is not the expected.");
        Assertions.assertEquals(user.getUsername(), userResponse.getUsername(),"Username is not the expected.");

        response = UserEndpoints.getUser(user.getUsername());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        userResponse = response.getBody().as(User.class);
        Assertions.assertEquals(user.getId(), userResponse.getId(),"Id is not the expected.");
        Assertions.assertEquals(user.getUsername(), userResponse.getUsername(),"Username is not the expected.");
        Assertions.assertEquals(user.getFirstName(), userResponse.getFirstName(),"First name is not the expected.");
        Assertions.assertEquals(user.getLastName(), userResponse.getLastName(),"Lastname is not the expected.");
        Assertions.assertEquals(user.getEmail(), userResponse.getEmail(),"Email is not the expected.");
        Assertions.assertEquals(user.getPhone(), userResponse.getPhone(),"Phone is not the expected.");
        Assertions.assertEquals(user.getUserStatus(), userResponse.getUserStatus(),"User status is not the expected.");
    }

    @Test
    @Description("Test Description : Update user")
    public void testUpdateUser() {
        Response response = UserEndpoints.addUser(user);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        user.setFirstName(user.getLastName());
        response = UserEndpoints.updateUser(user);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        userResponse = response.getBody().as(User.class);
        Assertions.assertEquals(user.getId(), userResponse.getId(),"Id is not the expected.");
        Assertions.assertEquals(user.getLastName(), userResponse.getFirstName(),"First name was not updated.");
        Assertions.assertEquals(user.getUsername(), userResponse.getUsername(),"Username is not the expected.");
        Assertions.assertEquals(user.getLastName(), userResponse.getLastName(),"Lastname is not the expected.");
        Assertions.assertEquals(user.getEmail(), userResponse.getEmail(),"Email is not the expected.");
        Assertions.assertEquals(user.getUserStatus(), userResponse.getUserStatus(),"User status is not the expected.");

        response = UserEndpoints.getUser(user.getUsername());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());
        userResponse = response.getBody().as(User.class);
        Assertions.assertEquals(user.getId(), userResponse.getId(),"Id is not the expected.");
        Assertions.assertEquals(user.getUsername(), userResponse.getUsername(),"Username is not the expected.");
        Assertions.assertEquals(user.getFirstName(), userResponse.getFirstName(),"First name is not the expected.");
    }

    @Test
    @Description("Test Description : Delete user")
    public void testDeleteUser() {
        Response response = UserEndpoints.addUser(user);
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        response = UserEndpoints.deleteUser(user.getUsername());
        response.then().log().all();
        Assertions.assertEquals(200, response.getStatusCode());

        response = UserEndpoints.getUser(user.getUsername());
        response.then().log().all();
        Assertions.assertEquals(404, response.getStatusCode());
    }
}

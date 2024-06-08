package api.endpoints;

import api.entitites.User;
import api.payloads.UserPayload;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class UserEndpoints {

    public static Response addUser(User user){
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(UserPayload.userPayload(user))
                .when()
                .post(Paths.POST_CREATE_USER);
    }

    public static Response addUserWithoutId(User user){
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(UserPayload.userPayloadWithoutId(user))
                .when()
                .post(Paths.POST_CREATE_USER);
    }

    public static Response getUser(String username){
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .when()
                .get(Paths.GET_USER_BY_USERNAME);
    }

    public static Response updateUser(User user){
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", user.getUsername())
                .body(UserPayload.userPayload(user))
                .when()
                .put(Paths.PUT_UPDATE_USER_BY_USERNAME);
    }

    public static Response deleteUser(String username){
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .pathParam("username", username)
                .when()
                .delete(Paths.DELETE_USER_BY_USERNAME);
    }

    public static Response addUserWithList(User[] users){
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(UserPayload.userListPayload(users))
                .when()
                .post(Paths.POST_CREATE_LIST_USERS);
    }

    public static Response userLogin(String username, String password){
        Map<String,String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .queryParams(params)
                .when()
                .get(Paths.GET_USER_LOGIN);
    }

    public static Response userLogout(){
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .when()
                .get(Paths.GET_USER_LOGOUT);
    }
}

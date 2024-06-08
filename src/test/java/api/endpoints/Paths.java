package api.endpoints;

public class Paths {

    public static String BASE_URL = "http://localhost:8080/api/v3";

    //PET MODULE
    public static String POST_ADD_PET = BASE_URL + "/pet";
    public static String PUT_UPDATE_PET = BASE_URL + "/pet";
    public static String GET_FIND_PET_BY_STATUS = BASE_URL + "/pet/findByStatus";
    public static String GET_FIND_PET_BY_TAG = BASE_URL + "/pet/findByTags";
    public static String GET_FIND_PET_BY_ID = BASE_URL + "/pet/{petId}";
    public static String POST_UPDATE_PET = BASE_URL + "/pet/{petId}";
    public static String DELETE_PET_BY_ID = BASE_URL + "/pet/{petId}";
    public static String POST_UPLOAD_PET_IMAGE = BASE_URL + "/pet/{petId}/uploadImage";

    //STORE MODULE
    public static String GET_STORE_INVENTORY = BASE_URL + "/store/inventory";
    public static String POST_PLACE_ORDER = BASE_URL + "/store/order";
    public static String GET_FIND_ORDER_BY_ID = BASE_URL + "/store/order/{orderId}";
    public static String DELETE_ORDER_BY_ID = BASE_URL + "/store/order/{orderId}";

    //USER MODULE
    public static String POST_CREATE_USER = BASE_URL + "/user";
    public static String POST_CREATE_LIST_USERS = BASE_URL + "/user/createWithList";
    public static String GET_USER_LOGIN = BASE_URL + "/user/login";
    public static String GET_USER_LOGOUT = BASE_URL + "/user/logout";
    public static String GET_USER_BY_USERNAME = BASE_URL + "/user/{username}";
    public static String PUT_UPDATE_USER_BY_USERNAME = BASE_URL + "/user/{username}";
    public static String DELETE_USER_BY_USERNAME = BASE_URL + "/user/{username}";
}


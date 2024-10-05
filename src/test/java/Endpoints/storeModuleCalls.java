package Endpoints;

import Payloads.Store;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class storeModuleCalls {

    public static Response Inventory_getcCall(String url){

        Response response =given()
                .header("accept","application/json")
                .when()
                .get(url);

        return response;
    }

    public static Response PlaceOrder_PostCall(Store requestBody, String url){


        Response response =given()
                .header("Content-Type","application/json")
                .body(requestBody)
                .when()
                .post(url);

        return response;
    }

    public static Response DeleteOrderCall(String url, String orderID){

        Response response=given()
                .header("accept", "application/json")
                .pathParam("orderID",orderID)
                .when()
                .delete(url);

        return response;
    }
}

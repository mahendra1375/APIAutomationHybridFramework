package Endpoints;

import Payloads.User;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.*;

public class userModuleCalls {


    public static ResourceBundle getUrl(){
        ResourceBundle rs=ResourceBundle.getBundle("config");
        return rs;
    }



    // create user call

    public static Response createUser_call(User payload) {
        String create_user =getUrl().getString("create_user");
        System.out.println("Url is: "+create_user);
        Response response = given()
                .headers("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(create_user);

        return response;

    }

    // Read user call

    public static Response readUser_call(String Username) {

        String get_user =getUrl().getString("get_user");
        System.out.println("readUserurl: "+get_user);
        System.out.println("Username is: "+Username);

        Response response = given()
                .pathParam("username", Username)
                .when()
                .get(get_user);
        return response;

    }

    //Update user call

    public static Response updateUser_call(User payload, String Username){

        String update_url =getUrl().getString("update_url");
        Response response=given()
                .headers("Content-Type", "application/json")
                .pathParam("username", Username)
                .body(payload)
                .when()
                .put(update_url);

        return response;

    }

    //Delete user call

    public static Response deleteUser_call(String Username){

        String delete_url =getUrl().getString("delete_url");
        System.out.println("Delete url: "+delete_url);
        System.out.println("Username is: "+Username);
        Response response=given()
                .pathParam("username", Username)
                .when()
                .delete(delete_url);


        return response;
    }


}

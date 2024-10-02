package Tests;

import Endpoints.userModuleCalls;
import Payloads.User;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;

import static org.hamcrest.Matchers.*;

public class UserTest {

    User createUserPaylocad;
    User updateUserPaylocad;

    @BeforeClass
    public void CreateUserData() throws IOException, ParseException {
        String path = "./src/test/resources/data.json";
        JSONParser parser = new JSONParser();
        Reader reader= new FileReader(path);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        createUserPaylocad = new User();
        createUserPaylocad.setId(Integer.parseInt((String) jsonObject.get("ID")));
        createUserPaylocad.setUsername((String) jsonObject.get("Username"));
        createUserPaylocad.setFirstName((String) jsonObject.get("FirstName"));
        createUserPaylocad.setLastName((String) jsonObject.get("LastName"));
        createUserPaylocad.setEmail((String) jsonObject.get("Email"));
        createUserPaylocad.setPassword((String) jsonObject.get("Password"));
        createUserPaylocad.setPhone(Integer.parseInt((String) jsonObject.get("PhoneNo")));
        createUserPaylocad.setUserStatus(Integer.parseInt((String) jsonObject.get("Status")));
    }


    // Create user test
    @Test(priority = 1)
    public void createUser_test() {
        System.out.println(createUserPaylocad);

        Response response = userModuleCalls.createUser_call(createUserPaylocad);
        System.out.println("Status code is: " + response.statusCode());

    }


    // Get UserDetails

    @Test(priority = 2)
    public void getUserDetails() {
        Response response = userModuleCalls.readUser_call(createUserPaylocad.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void updateuser() throws IOException, ParseException {
        String path = "./src/test/resources/data.json";
        JSONParser parser = new JSONParser();
        Reader reader= new FileReader(path);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        updateUserPaylocad= new User();
        updateUserPaylocad.setId(Integer.parseInt((String) jsonObject.get("ID")));
        updateUserPaylocad.setUsername((String) jsonObject.get("Username"));
        updateUserPaylocad.setFirstName((String) jsonObject.get("FirstName"));
        updateUserPaylocad.setLastName((String) jsonObject.get("LastName"));
        updateUserPaylocad.setEmail((String) jsonObject.get("Email"));
        updateUserPaylocad.setPassword((String) jsonObject.get("Password"));
        updateUserPaylocad.setPhone(Integer.parseInt((String) jsonObject.get("PhoneNo")));
        updateUserPaylocad.setUserStatus(Integer.parseInt((String) jsonObject.get("Status")));


        Response response = userModuleCalls.updateUser_call(updateUserPaylocad, createUserPaylocad.getUsername());
        System.out.println("Status code is: " + response.statusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        // Validate updated email value
        String updated_email = updateUserPaylocad.getEmail();
        Response readUpdatedUser = userModuleCalls.readUser_call(createUserPaylocad.getUsername());
        readUpdatedUser.then().assertThat()
                .statusCode(200)
                .body("email", equalTo(updated_email));
        System.out.println("Updated email is: " + readUpdatedUser.jsonPath().getString("email"));
    }


    //Delete user
    @Test(priority = 4)
    public void deleteUser() {
        Response response = userModuleCalls.deleteUser_call(createUserPaylocad.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
    }


}

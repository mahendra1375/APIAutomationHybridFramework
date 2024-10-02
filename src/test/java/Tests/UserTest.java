package Tests;

import Endpoints.userModuleCalls;
import Payloads.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class UserTest {

    User createUserPaylocad;
    User updateUserPaylocad;

    @BeforeClass
    public void CreateUserData(){
        createUserPaylocad= new User();
        createUserPaylocad.setId(101);
        createUserPaylocad.setUsername("Mahendra1234");
        createUserPaylocad.setFirstName("Mahendra");
        createUserPaylocad.setLastName("Gaikwad");
        createUserPaylocad.setEmail("Mahendra.04@gamil.com");
        createUserPaylocad.setPassword("Mahendra@123");
        createUserPaylocad.setPhone(985726885);
        createUserPaylocad.setUserStatus(0);
    }



    // Create user test
    @Test(priority = 1)
    public void createUser_test(){

        Response response=userModuleCalls.createUser_call(createUserPaylocad);
        System.out.println("Status code is: "+response.statusCode());

    }


    // Get UserDetails

    @Test(priority = 2)
    public void getUserDetails(){
        Response response=userModuleCalls.readUser_call(createUserPaylocad.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void updateuser(){
        updateUserPaylocad= new User();
        updateUserPaylocad.setId(101);
        updateUserPaylocad.setUsername("Mahendra1234");
        updateUserPaylocad.setFirstName("Mahendra");
        updateUserPaylocad.setLastName("Gaikwad");
        updateUserPaylocad.setEmail("Mahendra.03@gamil.com");
        updateUserPaylocad.setPassword("Mahendra@123");
        updateUserPaylocad.setPhone(985726885);
        updateUserPaylocad.setUserStatus(0);


        Response response=userModuleCalls.updateUser_call(updateUserPaylocad, createUserPaylocad.getUsername());
        System.out.println("Status code is: "+response.statusCode());
        Assert.assertEquals(response.getStatusCode(),200);

        // Validate updated email value
        String updated_email= updateUserPaylocad.getEmail();
        Response readUpdatedUser=userModuleCalls.readUser_call(createUserPaylocad.getUsername());
        readUpdatedUser.then().assertThat()
                        .statusCode(200)
                                .body("email", equalTo(updated_email));
        System.out.println("Updated email is: "+readUpdatedUser.jsonPath().getString("email"));
    }


    //Delete user
    @Test(priority = 4)
    public void deleteUser(){
        Response response=userModuleCalls.deleteUser_call(createUserPaylocad.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
    }











}

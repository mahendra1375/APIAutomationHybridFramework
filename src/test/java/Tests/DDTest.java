package Tests;

import Endpoints.userModuleCalls;
import Payloads.User;
import Utilities.dataprovider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTest {
    User createUserPaylocad;

    @Test(priority = 1, dataProvider = "userData", dataProviderClass = dataprovider.class)
    public void createUser_test(String ID, String userName, String firstName, String lastName, String Email, String password, String phone){
        createUserPaylocad= new User();
        createUserPaylocad= new User();
        createUserPaylocad.setId(Integer.parseInt(ID));
        createUserPaylocad.setUsername(userName);
        createUserPaylocad.setFirstName(firstName);
        createUserPaylocad.setLastName(lastName);
        createUserPaylocad.setEmail(Email);
        createUserPaylocad.setPassword(password);
        createUserPaylocad.setPhone(Integer.parseInt(phone));
        createUserPaylocad.setUserStatus(0);

        Response response= userModuleCalls.createUser_call(createUserPaylocad);
        response.then().log().all();
        System.out.println("Status code is: "+response.statusCode());

        Assert.assertEquals(response.getStatusCode(),200);
    }
}

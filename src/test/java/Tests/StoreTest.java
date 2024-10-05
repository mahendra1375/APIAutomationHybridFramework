package Tests;

import Endpoints.storeModuleCalls;
import Payloads.Store;
import Utilities.BaseClass;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoreTest extends BaseClass {

    Store requestBody;
    String orderId;

    @Test(priority = 1)
    public void checkPetInventry() {
        String url = getConfigData().getString("Get_Petdetails");

        System.out.println("Urls is: " + url);

        Response response = storeModuleCalls.Inventory_getcCall(url);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 2)
    public void placeOrder() {

        requestBody = new Store();
        requestBody.setId(0);
        requestBody.setPetID(3);
        requestBody.setQuantity(2);
        requestBody.setStatus("Placed");
        requestBody.setComplete("true");
        String url = getConfigData().getString("Post_PlacePetOrder");
        System.out.println("URL is" + url);
        Response response = storeModuleCalls.PlaceOrder_PostCall(requestBody, url);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
        orderId=response.jsonPath().getString("id");

    }

    @Test(priority = 3)
    public void deleteOrder(){
        String url=getConfigData().getString("Delete_orderurl");
        System.out.println("Delete url is: "+url);
        System.out.println("orderId is: "+orderId);
        Response response=storeModuleCalls.DeleteOrderCall(url, orderId);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }


}

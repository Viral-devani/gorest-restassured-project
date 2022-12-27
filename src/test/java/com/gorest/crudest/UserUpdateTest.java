package com.gorest.crudest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserUpdateTest extends TestBase {
    @Test
    public void verifyUserUpdateSuccessfully(){
        UserPojo userPojo=new UserPojo();
        userPojo.setName("Viral1D");
        userPojo.setEmail("viral1235@gmail.com");
        userPojo.setGender("female");
        userPojo.setStatus("active");

        Response response=given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .when()
                .body(userPojo)
                .put("/users/10594");
        response.then().log().all().statusCode(200);
        response.prettyPrint();

    }
}

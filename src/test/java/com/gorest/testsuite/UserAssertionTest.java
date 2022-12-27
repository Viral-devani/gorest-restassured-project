package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        // RestAssured.port = 3030;
        response = given()
                .when()
                .queryParam("page","1")
                .queryParam("per_page","20")
                .get("/users")
                .then().statusCode(200);
    }
    // 1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size()", equalTo(20));
    }
    //2. Verify the if the name of id = 5322 is equal to ”Rudra Ganaka VM”

    @Test
    public void test002() {
        response.body("findAll{it.id==5320}.name",hasItem("Deeptimoyee Sharma"));

    }
    //3. Check the single ‘Name’ in the Array list (Dhana Kaul)
    @Test
    public void test003() {

        // List<String> names = response.extract().path("name");
        //System.out.println(names);
        // names.contains("Dhana Kaul");
        response.body("name", hasItem("Dhana Kaul"));

    }
    //4. Check the multiple ‘Names’ in the ArrayList (Vimala Kakkar, Aashritha Bhattathiri
    //Kashyap Prajapat
    @Test
    public void test004() {
        response.body("name",hasItems("Vimala Kakkar","Aashritha Bhattathiri","Kashyap Prajapat"));
    }
    //5. Verify the emai of userid = 5320 is equal “deeptimoyee_sharma@mcclure.name”
    @Test
    public void test005() {
        response.body("findAll{it.id==5320}.email",hasItem("deeptimoyee_sharma@mcclure.name"));

    }
    //6. Verify the status is “Active” of user name is “Trilochan Gupta”
    @Test
    public void test006() {

        response.body("findAll{it.status=='active'}.name",hasItem("Trilochan Gupta"));

    }
    //7. Verify the Gender = male of user name is “Trilochan Gupta”
    @Test
    public void test007() {

        response.body("findAll{it.name=='Trilochan Gupta'}.gender",hasItem("male"));

    }
}

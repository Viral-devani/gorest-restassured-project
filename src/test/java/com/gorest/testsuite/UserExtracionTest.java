package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtracionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        // RestAssured.port = 3030;
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
    }

    //1. Extract the All Ids
    @Test
    public void test001() {
        List<Integer> allIds = response.extract().path("id");
        System.out.println("------------------List of all Ids---------------------------");
        System.out.println("List of Ids are : " + allIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the all Names
    @Test
    public void test002() {
        List<String> allnames = response.extract().path("name");
        System.out.println("------------------List of all names---------------------------");
        System.out.println("List of all names are : " + allnames);
        System.out.println("------------------End of Test---------------------------");

    }

    //3. Extract the name of 5th object
    @Test
    public void test003() {
        String object = response.extract().path("[4].name");
        System.out.println("------------------Answer 3---------------------------");
        System.out.println("the name of 5th object " + object);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<HashMap<String, ?>> values = response.extract().path("findAll{it.status=='inactive'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the names of all object whose status = inactive: " + values);
        System.out.println("------------------End of Test---------------------------");
    }
    //5. Extract the gender of all the object whose status = active

    @Test
    public void test005() {
        List<HashMap<String, ?>> values = response.extract().path("findAll{it.status=='active'}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the names of all object whose status = active: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<HashMap<String, ?>> gender = response.extract().path("findAll{it.gender=='female'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the names of the object whose gender = female " + gender);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<HashMap<String, ?>> email = response.extract().path("findAll{it.status=='inactive'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the emails of the object where status = inactive " + email);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<HashMap<String, ?>> genderM = response.extract().path("findAll{it.gender=='female'}.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" the ids of the object where gender = male" + genderM);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void test009() {

        List<String> statusAll = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the status are : " + statusAll);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Karthik Dubashi IV
    @Test
    public void test010() {

        List<String> emailAdd = response.extract().path("findAll{it.name=='Laal Kaul'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("email of the object where name = Laal Kaul" + emailAdd.get(0));
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get gender of id = 5471
    @Test
    public void test011() {

        List<String> genderID = response.extract().path("findAll{it.id==5313}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get gender of id = 5313 :" + genderID.get(0));
        System.out.println("------------------End of Test---------------------------");
    }

}

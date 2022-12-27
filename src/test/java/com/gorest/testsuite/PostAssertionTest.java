package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        // RestAssured.port = 3030;
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size()", equalTo(25));
    }

    //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto
    //centum
    @Test
    public void test002() {
        response.body("findAll{it.id==2730}.title", hasItem("Ad ipsa coruscus ipsam eos demitto centum."));

    }

    //3. Check the single user_id in the Array list (2665)
    @Test
    public void test003() {
        response.body("id", hasItem(2665));
    }

    //4.Check the multiple ids in the ArrayList "2673","2665","2622")
    @Test
    public void test004() {
        response.body("id", hasItems(2673, 2665, 2622));
    }

    //5. Verify the body of userid = 2678 is equal “Totidem desolo tabula. Confero tabula laborum. Sed confugo cilicium. Cur tantillus et. Suadeo defessus distinctio. Perferendis speciosus somniculosus. Id trado maiores. Tunc aeternus charisma. Cauda territo defluo. Thymum amplus ustilo. Decet caecus concido. Canonicus aeneus cito. Vulgivagus stultus non. Et confero corporis. Cribro ducimus thesaurus. Amplexus vomer aliquam. Laudantium amplexus debeo. Celebrer unde ademptio. Unus vulnero suscipit. Antea approbo tollo. Curto iusto ultio.
    @Test
    public void test005() {
        response.body("findAll{it.id==2731}.body", hasItem("Totidem desolo tabula. Confero tabula laborum. Sed confugo cilicium. Cur tantillus et. Suadeo defessus distinctio. Perferendis speciosus somniculosus. Id trado maiores. Tunc aeternus charisma. Cauda territo defluo. Thymum amplus ustilo. Decet caecus concido. Canonicus aeneus cito. Vulgivagus stultus non. Et confero corporis. Cribro ducimus thesaurus. Amplexus vomer aliquam. Laudantium amplexus debeo. Celebrer unde ademptio. Unus vulnero suscipit. Antea approbo tollo. Curto iusto ultio."));
    }


}

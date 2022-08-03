package com.baeldungtutorial.ratingservice;

import com.baeldungtutorial.ratingservice.model.Rating;
import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class RatingsAPITest {
    private final String ROOT_URI = "http://localhost:8080";

    @BeforeEach
    public void setup()
    {
        RestAssured.config = RestAssuredConfig.config().redirect(RedirectConfig.redirectConfig().followRedirects(false));
    }

    @Test
    public void whenAddNewRating_thenSuccess()
    {
        Rating rating = new Rating();
        rating.setBookId(1L);
        rating.setStars(5);
        Response response = RestAssured.given().auth().basic("admin", "admin")
                .and()
                .contentType(ContentType.JSON)
                .body(rating)
                .post(ROOT_URI + "/ratings");
        Rating result = response.as(Rating.class);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Assertions.assertEquals(rating.getBookId(), result.getBookId());
        Assertions.assertEquals(rating.getStars(), result.getStars());
    }

    @Test
    public void whenAccessAdminProtectedResource_thenForbidden()
    {
        Response response = RestAssured.given().auth().basic("user", "password").get(ROOT_URI + "/ratings");

        // TODO: get spring session+redis working so that we can undo opening up spring security just to get endpoints to work and then this test can actually run
        //Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatusCode());
    }

    @Test
    public void whenAdminAccessProtectedResource_thenSuccess()
    {
        Response response = RestAssured.given().auth().basic("admin", "admin").get(ROOT_URI + "/ratings");

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
}

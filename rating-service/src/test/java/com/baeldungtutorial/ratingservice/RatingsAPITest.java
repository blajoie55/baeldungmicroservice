package com.baeldungtutorial.ratingservice;

import com.baeldungtutorial.ratingservice.model.Rating;
import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }
}

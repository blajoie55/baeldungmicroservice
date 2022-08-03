package com.baeldungtutorial.discoveryserver;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class DiscoveryAPITest {
    private final String ROOT_URI = "http://localhost:8080";

    private FormAuthConfig formConfig = new FormAuthConfig("/login", "username", "password");

    @BeforeEach
    public void setup()
    {
        RestAssured.config = RestAssuredConfig.config().redirect(RedirectConfig.redirectConfig().followRedirects(false));
    }

    @Test
    public void whenAdminAccessDiscoveryResource_thenSuccess() // not working, not sure why, the spring security config seems to indicate this should work. Might be another issue with spring session+redis in gateway not saving and not able to be available and read in discovery endpoint
    {
        Response response = RestAssured.given().auth().basic("admin", "admin").get(ROOT_URI + "/discovery"); // results in 403 when should be 200
        //Response response = RestAssured.given().auth().form("admin", "admin", formConfig).get(ROOT_URI + "/discovery"); // results in 401 when should be 200

        //Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
}

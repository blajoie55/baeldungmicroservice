package com.baeldungtutorial.bookservice;

import com.baeldungtutorial.bookservice.model.Book;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class BooksAPITest {

    private final String ROOT_URI = "http://localhost:8080";
    private FormAuthConfig formConfig = new FormAuthConfig("/login", "username", "password");

    @BeforeEach
    public void setup()
    {
        RestAssured.config = RestAssuredConfig.config().redirect(RedirectConfig.redirectConfig().followRedirects(false));
    }

    @Test
    public void whenGetAllBooks_thenSuccess()
    {
        Response response = RestAssured.given().auth().basic("user", "password").get(ROOT_URI + "/books");
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

//    @Test
//    public void whenAccessProtectedResourcesWithoutLogin_thenRedirectToLogin() // we are using basic auth mostly, form login is on, but with basic auth on, we get 401 instead of redirect to form login
//    {
//        Response response = RestAssured.get(ROOT_URI + "/books/1");
//
//        Assertions.assertEquals(HttpStatus.FOUND.value(), response.getStatusCode());
//        Assertions.assertEquals("http://localhost:8080/login", response.getHeader("Location"));
//    }

    @Test
    public void whenAccessProtectedResourcesWithoutLogin_then401()
    {
        Response response = RestAssured.get(ROOT_URI + "/books");
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusCode());

        response = RestAssured.get(ROOT_URI + "/books/1");
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusCode());
    }

    @Test
    public void whenAddNewBook_thenSuccess()
    {
        Book book = new Book();
        book.setAuthor("Baeldung");
        book.setTitle("How to spring cloud");

        /*
        Response bookResponse = RestAssured.given()
            .auth()
            .form("admin", "admin", formConfig)
            .and()
            .contentType(ContentType.JSON)
            .body(book)
            .post(ROOT_URI + "/book-service/books");
         */
        Response response = RestAssured.given().auth().basic("admin", "admin")
                .and()
                .contentType(ContentType.JSON)
                .body(book)
                .post(ROOT_URI + "/books");
        Book result = response.as(Book.class);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Assertions.assertEquals(book.getAuthor(), result.getAuthor());
        Assertions.assertEquals(book.getTitle(), result.getTitle());
        Assertions.assertNotNull(result.getId());
        Assertions.assertNotEquals(0L, result.getId());
    }

    @Test
    public void whenAccessProtectedResource_thenSuccess()
    {
        Response response = RestAssured.given().auth().basic("user", "password").get(ROOT_URI + "/books/1");
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
}

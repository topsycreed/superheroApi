package org.marvel.controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.marvel.Constants;

import static io.restassured.RestAssured.given;
import static org.marvel.testdata.TestConstants.DEFAULT_HERO;

public class SuperheroController {

    RequestSpecification requestSpecification = given();

    public SuperheroController() {
        RestAssured.defaultParser = Parser.JSON;
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.baseUri(Constants.BASE_URL);
        requestSpecification.body(DEFAULT_HERO);
    }

    public Response addHero() {
        return given(requestSpecification).post("superheroes").andReturn();
    }

    public Response getHeroById(int id) {
        return given(requestSpecification).get(String.format("superheroes/%s", id)).andReturn();
    }
}

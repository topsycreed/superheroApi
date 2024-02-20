package controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.marvel.Constants;

import static io.restassured.RestAssured.given;
import static org.marvel.testdata.TestConstants.DEFAULT_HERO;
import static org.marvel.testdata.TestConstants.UPDATED_HERO;

public class SimpleController {
    RequestSpecification requestSpecification = given();

    public SimpleController() {
        RestAssured.defaultParser = Parser.JSON;
        this.requestSpecification.contentType(ContentType.JSON);
        this.requestSpecification.baseUri(Constants.BASE_URL);
    }

    public HttpResponse addHero() {
        this.requestSpecification.body(DEFAULT_HERO);
        return new HttpResponse(given(requestSpecification)
                .post("superheroes")
                .then());
    }

    public HttpResponse updateHeroById(int id) {
        this.requestSpecification.body(UPDATED_HERO);
        return new HttpResponse(given(requestSpecification)
                .put(String.format("superheroes/%s", id))
                .then());
    }
}

package controller;

import config.TestConfig;
import config.TestConfigOld;
import config.TestPropertiesConfig;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.marvel.Constants;

import static io.restassured.RestAssured.given;
import static org.marvel.testdata.TestConstants.DEFAULT_HERO;

public class SuperheroController {

    RequestSpecification requestSpecification = given();
//    TestConfigOld config = new TestConfigOld();
//    TestConfig configNew = ConfigFactory.create(TestConfig.class, System.getProperties());
    TestPropertiesConfig configProperties = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    public SuperheroController() {
        RestAssured.defaultParser = Parser.JSON;
        this.requestSpecification.contentType(ContentType.JSON);
//        this.requestSpecification.baseUri(Constants.BASE_URL);
//        this.requestSpecification.baseUri(config.getBaseUrl());
//        this.requestSpecification.baseUri(configNew.getBaseUrl());
        this.requestSpecification.baseUri(configProperties.getBaseUrl());
    }

    @Step("Add default hero")
    public Response addHero() {
        this.requestSpecification.body(DEFAULT_HERO);
        return given(this.requestSpecification).post("superheroes").andReturn();
    }

    public Response getHeroById(int id) {
        return given(this.requestSpecification).get(String.format("superheroes/%s", id)).andReturn();
    }

    public Response getHeroByInvalidId() {
        int invalidId = -1;
        return this.getHeroById(invalidId);
    }
}

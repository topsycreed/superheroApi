package controller;

import config.TestConfig;
import config.TestConfigOld;
import config.TestPropertiesConfig;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.marvel.Constants;

import static io.restassured.RestAssured.given;
import static org.marvel.testdata.TestConstants.DEFAULT_HERO;
import static org.marvel.testdata.TestConstants.UPDATED_HERO;

public class SimpleController {
    RequestSpecification requestSpecification = given();
//    TestConfigOld config = new TestConfigOld();
//    TestConfig configNew = ConfigFactory.create(TestConfig.class, System.getProperties());
    TestPropertiesConfig configProperties = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    public SimpleController() {
        RestAssured.defaultParser = Parser.JSON;
        this.requestSpecification.contentType(ContentType.JSON);
//        this.requestSpecification.baseUri(Constants.BASE_URL);
//        this.requestSpecification.baseUri(config.getBaseUrl()); //old config
//        this.requestSpecification.baseUri(configNew.getBaseUrl());
        this.requestSpecification.baseUri(configProperties.getBaseUrl());
        this.requestSpecification.filter(new AllureRestAssured());
    }

    @Step("Create default superhero")
    public HttpResponse addHero() {
        this.requestSpecification.body(DEFAULT_HERO);
        return new HttpResponse(given(requestSpecification)
                .post("superheroes")
                .then());
    }

    @Step("Delete default superhero")
    public HttpResponse updateHeroById(int id) {
        this.requestSpecification.body(UPDATED_HERO);
        return new HttpResponse(given(requestSpecification)
                .put(String.format("superheroes/%s", id))
                .then());
    }
}

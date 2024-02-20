import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.marvel.Constants;
import controller.SuperheroController;
import org.marvel.models.Superhero;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.marvel.models.SuperheroError;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.marvel.testdata.TestConstants.DEFAULT_HERO;
import static org.marvel.testdata.TestConstants.UPDATED_HERO;

class NewTest {

    @Test
    void checkGetHeroContentTypeTest() {
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification requestSpecification = given();
        requestSpecification.accept(ContentType.ANY).baseUri(Constants.BASE_URL);
        Response response = given(requestSpecification).get("superheroes").andReturn();
        String bodyStr = response.asPrettyString();
        String actualContentType = response.getContentType();
        System.out.println(bodyStr);
        assertEquals("application/json;charset=UTF-8", actualContentType);
        assertEquals(200, response.statusCode());
    }

    @Test
    void addHeroTest() {
        Response response = new SuperheroController().addHero();
        Superhero actualHero = response.as(Superhero.class);

        assertEquals(DEFAULT_HERO, actualHero);
        assertEquals(200, response.statusCode());
    }

    @Disabled("Just example")
    @RepeatedTest(value = 5, name = "{displayName} - повторение {currentRepetition} из {totalRepetitions}")
    @DisplayName("Check getSuperhero by id")
    void getHeroByIdHardcodedTest() {
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.baseUri(Constants.BASE_URL);
        Response response = given(requestSpecification).get("superheroes/75").andReturn();
        String bodyStr = response.asPrettyString();
        System.out.println(bodyStr);
        Superhero actualHero = response.as(Superhero.class);
        System.out.println("Id = " + actualHero.id);
        assertEquals(DEFAULT_HERO, actualHero);
        assertEquals(75, actualHero.id);
        assertEquals(200, response.statusCode());
    }

    @RepeatedTest(value = 5, name = "{displayName} - повторение {currentRepetition} из {totalRepetitions}")
    @DisplayName("Check getSuperhero by id")
    void getHeroByIdTest() {
        Response addHeroResponse = createDefaultHero();
        Superhero createdHero = addHeroResponse.as(Superhero.class);
        int id = createdHero.getId();

        Response response = new SuperheroController().getHeroById(id);
        Superhero actualHero = response.as(Superhero.class);

        assertEquals(DEFAULT_HERO, actualHero);
        assertEquals(200, response.statusCode());
    }

//    @RepeatedTest(value = 5, name = "{displayName} - повторение {currentRepetition} из {totalRepetitions}")
//    @DisplayName("Check getSuperhero by id")
    @Test
    void updateHeroByIdTest() {
        Response addHeroResponse = createDefaultHero();
        Superhero createdHero = addHeroResponse.as(Superhero.class);
        int id = createdHero.getId();

        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.baseUri(Constants.BASE_URL);
        requestSpecification.body("""
                {
                    "id": 1,
                    "fullName": "Kirill Java AQA",
                    "birthDate": "2022-02-21",
                    "city": "New York",
                    "mainSkill": "Magic",
                    "gender": "M",
                    "phone": null
                  }""");
        Response response = given(requestSpecification).put(String.format("superheroes/%s", id)).andReturn();
        String bodyStr = response.asPrettyString();
        System.out.println(bodyStr);
        assertEquals(200, response.statusCode());
        Response getResponse = getHeroById(id);
        System.out.println(getResponse.asPrettyString());
        Superhero actualHero = getResponse.as(Superhero.class);
        assertEquals(UPDATED_HERO, actualHero);
        assertEquals(id, actualHero.getId());
    }

    @Test
    void deleteHeroByIdTest() {
        Response addHeroResponse = createDefaultHero();
        Superhero createdHero = addHeroResponse.as(Superhero.class);
        int id = createdHero.getId();

        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.baseUri(Constants.BASE_URL);
        Response response = given(requestSpecification).delete(String.format("superheroes/%s", id)).andReturn();
        System.out.println(response.asPrettyString());
        assertEquals(200, response.statusCode());
    }

    @Test
    void invalidGetHeroTest() {
        Response response = new SuperheroController().getHeroByInvalidId();
        assertEquals(response.statusCode(), 400);
        SuperheroError parsedResponse = response.as(SuperheroError.class);
        assertEquals(parsedResponse.code, "NOT_FOUND");
        assertEquals(parsedResponse.message, "Superhero with id '-1' was not found");
        System.out.println(response.statusCode());
        response.prettyPrint();
    }

    private Response getHeroById(int id) {
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.baseUri(Constants.BASE_URL);
        return given(requestSpecification).get(String.format("superheroes/%s", id)).andReturn();
    }

    private Response createDefaultHero() {
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.baseUri(Constants.BASE_URL);
        requestSpecification.body("""
                {
                    "id": 1,
                    "fullName": "Gena Chursov",
                    "birthDate": "2022-02-21",
                    "city": "New York",
                    "mainSkill": "Magic",
                    "gender": "M",
                    "phone": null
                  }""");
        return given(requestSpecification).post("superheroes").andReturn();
    }
}

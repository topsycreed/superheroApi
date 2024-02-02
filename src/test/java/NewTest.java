import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Superhero;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static testdata.TestConstants.DEFAULT_HERO;
import static testdata.TestConstants.UPDATED_HERO;

public class NewTest {

    @Test
    public void checkGetHeroContentTypeTest() {
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
    public void addHeroTest() {
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.baseUri(Constants.BASE_URL);
        requestSpecification.body("{\n" +
                "    \"id\": 1,\n" +
                "    \"fullName\": \"Gena Chursov\",\n" +
                "    \"birthDate\": \"2022-02-21\",\n" +
                "    \"city\": \"New York\",\n" +
                "    \"mainSkill\": \"Magic\",\n" +
                "    \"gender\": \"M\",\n" +
                "    \"phone\": null\n" +
                "  }");
        Response response = given(requestSpecification).post("superheroes").andReturn();
        String bodyStr = response.asPrettyString();
        Superhero actualHero = response.as(Superhero.class);
        System.out.println(bodyStr);
        System.out.println("Id = " + actualHero.id);
        assertEquals(DEFAULT_HERO, actualHero);
        assertEquals(200, response.statusCode());
    }

    @Disabled("Just example")
    @RepeatedTest(value = 5, name = "{displayName} - повторение {currentRepetition} из {totalRepetitions}")
    @DisplayName("Check getSuperhero by id")
    public void getHeroByIdHardcodedTest() {
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
    public void getHeroByIdTest() {
        Response addHeroResponse = createDefaultHero();
        Superhero createdHero = addHeroResponse.as(Superhero.class);
        int id = createdHero.getId();

        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.baseUri(Constants.BASE_URL);
        Response response = given(requestSpecification).get(String.format("superheroes/%s", id)).andReturn();
        String bodyStr = response.asPrettyString();
        System.out.println(bodyStr);
        Superhero actualHero = response.as(Superhero.class);
        assertEquals(DEFAULT_HERO, actualHero);
        assertEquals(id, actualHero.getId());
        assertEquals(200, response.statusCode());
    }

//    @RepeatedTest(value = 5, name = "{displayName} - повторение {currentRepetition} из {totalRepetitions}")
//    @DisplayName("Check getSuperhero by id")
    @Test
    public void updateHeroByIdTest() {
        Response addHeroResponse = createDefaultHero();
        Superhero createdHero = addHeroResponse.as(Superhero.class);
        int id = createdHero.getId();

        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.baseUri(Constants.BASE_URL);
        requestSpecification.body("{\n" +
                "    \"id\": 1,\n" +
                "    \"fullName\": \"Kirill Java AQA\",\n" +
                "    \"birthDate\": \"2022-02-21\",\n" +
                "    \"city\": \"New York\",\n" +
                "    \"mainSkill\": \"Magic\",\n" +
                "    \"gender\": \"M\",\n" +
                "    \"phone\": null\n" +
                "  }");
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
    public void deleteHeroByIdTest() {
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
        requestSpecification.body("{\n" +
                "    \"id\": 1,\n" +
                "    \"fullName\": \"Gena Chursov\",\n" +
                "    \"birthDate\": \"2022-02-21\",\n" +
                "    \"city\": \"New York\",\n" +
                "    \"mainSkill\": \"Magic\",\n" +
                "    \"gender\": \"M\",\n" +
                "    \"phone\": null\n" +
                "  }");
        return given(requestSpecification).post("superheroes").andReturn();
    }
}

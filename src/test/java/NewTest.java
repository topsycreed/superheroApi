import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewTest {

    @Test
    public void checkGetHeroContentType() {
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification requestSpecification = given();
        requestSpecification.accept(ContentType.ANY).baseUri("https://superhero.qa-test.csssr.com/");
        Response response = given(requestSpecification).get("superheroes").andReturn();
        String bodyStr = response.asPrettyString();
        String actualContentType = response.getContentType();
        System.out.println(bodyStr);
        assertEquals("application/json;charset=UTF-8", actualContentType);
    }
}

import controller.SuperheroController;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import controller.SimpleController;
import org.marvel.models.Superhero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.marvel.testdata.TestConstants.DEFAULT_HERO;

class FluentApiTests {
    SimpleController simpleController = new SimpleController();

    @Test
    void addHeroTest() {
        simpleController.addHero()
                .statusCodeIs(HttpStatus.SC_OK)
                .jsonValueIs("fullName", "Gena Chursov")
                .jsonValueIs("birthDate", "2022-02-21")
                .jsonValueIs("city", "New York")
                .jsonValueIs("mainSkill", "Magic")
                .jsonValueIs("gender", "M")
                .jsonValueIsNull("phone");
    }

    @Test
    void addHeroTest2() {
        Response response = new SuperheroController().addHero();
        Superhero actualHero = response.as(Superhero.class);

        assertEquals(DEFAULT_HERO, actualHero);
        assertEquals(200, response.statusCode());
    }

    @Test
    void deleteHeroByIdTest() {
        int id = Integer.parseInt(simpleController.addHero()
                .statusCodeIs(HttpStatus.SC_OK)
                .getJsonValue("id"));

        simpleController.updateHeroById(id)
                .statusCodeIs(HttpStatus.SC_OK)
                .jsonValueIs("fullName", "Kirill Java AQA");
    }
}

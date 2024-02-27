import controller.SimpleController;
import controller.SuperheroController;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.marvel.models.Superhero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.marvel.testdata.TestConstants.DEFAULT_HERO;

class FluentApiWithAllureTests {
    SimpleController simpleController = new SimpleController();

    @Test
    @DisplayName("Add hero test with default superhero")
    @Description("Add hero test with default superhero")
    @Epic("Epic test")
    @Feature("Feature test")
    @Story("Story test")
    @Owner("Gena Chursov")
    @Link("Jira-123")
    @Issue("Jira-321")
    @Severity(SeverityLevel.CRITICAL)
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
    void updateHeroByIdTest() {
        int id = Integer.parseInt(simpleController.addHero()
                .statusCodeIs(HttpStatus.SC_OK)
                .getJsonValue("id"));

        simpleController.updateHeroById(id)
                .statusCodeIs(HttpStatus.SC_OK)
                .jsonValueIs("fullName", "Kirill Java AQA");
    }
}

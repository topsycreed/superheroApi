package controller;

import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import org.assertj.core.api.Assertions;

@Getter
public class HttpResponse {
    private final ValidatableResponse response;

    public HttpResponse(ValidatableResponse response) {
        this.response = response;
    }

    public HttpResponse statusCodeIs(int status) {
        this.response.statusCode(status);
        return this;
    }

    public HttpResponse jsonValueIs(String path, String expectedValue) {
        String actualValue = this.response.extract().jsonPath().getString(path);
        Assertions.assertThat(actualValue).as("Actual value '%s' is not equals to expected '%s' for the path '%s' and response: \n%s", actualValue, expectedValue, path, this.response.extract().response().andReturn().asPrettyString()).isEqualTo(expectedValue);
        return this;
    }

    public HttpResponse jsonValueIsNotNull(String path) {
        String actualValue = this.response.extract().jsonPath().getString(path);
        Assertions.assertThat(actualValue).isNotNull();
        return this;
    }

    public HttpResponse jsonValueIsNull(String path) {
        String actualValue = this.response.extract().jsonPath().getString(path);
        Assertions.assertThat(actualValue).isNull();
        return this;
    }

    public String getJsonValue(String path) {
        String value = this.response.extract().jsonPath().getString(path);
        Assertions.assertThat(value).isNotNull();
        return value;
    }
}

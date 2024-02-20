package org.marvel.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message",
        "code"
})
public class SuperheroError {

    @JsonProperty("message")
    public String message;
    @JsonProperty("code")
    public String code;

}
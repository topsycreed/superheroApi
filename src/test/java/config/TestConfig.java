package config;

public interface TestConfig extends org.aeonbits.owner.Config {
    @Key("baseUrl")
    @DefaultValue("https://superhero.qa-test.csssr.com/")
    String getBaseUrl();
}

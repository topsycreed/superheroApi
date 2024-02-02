package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.annotation.processing.Generated;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "fullName",
        "birthDate",
        "city",
        "mainSkill",
        "gender",
        "phone"
})
@Generated("jsonschema2pojo")
@Builder
@NoArgsConstructor
@ToString
public class Superhero {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("fullName")
    public String fullName;
    @JsonProperty("birthDate")
    public String birthDate;
    @JsonProperty("city")
    public String city;
    @JsonProperty("mainSkill")
    public String mainSkill;
    @JsonProperty("gender")
    public String gender;
    @JsonProperty("phone")
    public String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMainSkill() {
        return mainSkill;
    }

    public void setMainSkill(String mainSkill) {
        this.mainSkill = mainSkill;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Superhero(Integer id, String fullName, String birthDate, String city, String mainSkill, String gender, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.city = city;
        this.mainSkill = mainSkill;
        this.gender = gender;
        this.phone = phone;
    }

    //need to fix
//    public static final Superhero DEFAULT_HERO = Superhero.builder
//            .fullName("Gena Chursov")
//            .birthDate("2022-02-21")
//            .city("New York")
//            .mainSkill("Magic")
//            .gender("M")
//            .build();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Superhero superhero = (Superhero) o;
        return Objects.equals(fullName, superhero.fullName) && Objects.equals(birthDate, superhero.birthDate) && Objects.equals(city, superhero.city) && Objects.equals(mainSkill, superhero.mainSkill) && Objects.equals(gender, superhero.gender) && Objects.equals(phone, superhero.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, birthDate, city, mainSkill, gender, phone);
    }
}

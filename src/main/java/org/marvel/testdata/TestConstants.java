package org.marvel.testdata;

import org.marvel.models.Superhero;

public class TestConstants {
    public static final Superhero DEFAULT_HERO = Superhero.builder()
            .id(1)
            .fullName("Gena Chursov")
            .birthDate("2022-02-21")
            .city("New York")
            .mainSkill("Magic")
            .gender("M")
            .phone(null)
            .build();
    public static final Superhero UPDATED_HERO = new Superhero(1, "Kirill Java AQA", "2022-02-21", "New York", "Magic", "M", null);
}

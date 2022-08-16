package nl.novi.eindopdracht.models;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Level {
    BEGINNER ("BEGINNER", "Beginner (A1)"),
    ELEMENTARY ("ELEMENTARY", "Beginner (A2)"),
    INTERMEDIATE ("INTERMEDIATE", "Gevorderd (B1)"),
    UPPER_INTERMEDIATE ("UPPER_INTERMEDIATE", "Gevorderd (B2)"),
    ADVANCED ("ADVANCED", "Vergevorderd (C1)"),
    PROFICIENT ("PROFICIENT", "Vergevorderd (C2)");

    private String key;
    private String value;

    Level(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

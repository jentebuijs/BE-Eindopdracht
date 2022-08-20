package nl.novi.eindopdracht.models;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Level {
    BEGINNER ("BEGINNER", "Beginner (A1)", 1),
    ELEMENTARY ("ELEMENTARY", "Beginner (A2)", 2),
    INTERMEDIATE ("INTERMEDIATE", "Gevorderd (B1)", 3),
    UPPER_INTERMEDIATE ("UPPER_INTERMEDIATE", "Gevorderd (B2)", 4),
    ADVANCED ("ADVANCED", "Vergevorderd (C1)", 5),
    PROFICIENT ("PROFICIENT", "Vergevorderd (C2)", 6);

    private final String key;
    private final String value;
    private final int score;

    Level(String key, String value, int score) {
        this.key = key;
        this.value = value;
        this.score = score;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int getScore() {
        return score;
    }
}

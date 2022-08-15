package nl.novi.eindopdracht.models;

public enum Level {
    BEGINNER ("Beginner (A1)"),
    ELEMENTARY ("Beginner (A2)"),
    INTERMEDIATE ("Gevorderd (B1)"),
    UPPER_INTERMEDIATE ("Gevorderd (B2)"),
    ADVANCED ("Vergevorderd (C1)"),
    PROFICIENT ("Vergevorderd (C2)");

    private String string;

    Level(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}

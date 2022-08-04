package nl.novi.eindopdracht.models;

public enum Level {
    ABSOLUTE_BEGINNER(1),
    ROOKIE(2),
    MEDIUM_RARE(3),
    WELL_ON_THE_WAY(4),
    HEADSHOT(5);

    private int score;

    Level(int score) {
        this.score = score;
    }

}

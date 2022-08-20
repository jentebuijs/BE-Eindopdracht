package nl.novi.eindopdracht.models;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Frequency {
    EVERY_DAY ("EVERY_DAY", "Elke dag", 1),
    FEW_TIMES_A_WEEK ("FEW_TIMES_A_WEEK", "Een paar keer per week",2),
    ONCE_A_WEEK ("ONCE_A_WEEK", "Een keer per week", 3),
    FEW_TIMES_A_MONTH ("FEW_TIMES_A_MONTH", "Een paar keer per maand", 4),
    ONCE_A_MONTH ("ONCE_A_MONTH", "Een keer per maand", 5);

    private final String key;
    private final String value;
    private final int score;

    Frequency(String key, String value, int score) {
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

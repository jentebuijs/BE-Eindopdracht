package nl.novi.eindopdracht.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Frequency {
    EVERY_DAY ("EVERY_DAY", "Elke dag"),
    FEW_TIMES_A_WEEK ("FEW_TIMES_A_WEEK", "Een paar keer per week"),
    ONCE_A_WEEK ("ONCE_A_WEEK", "Een keer per week"),
    FEW_TIMES_A_MONTH ("FEW_TIMES_A_MONTH", "Een paar keer per week"),
    ONCE_A_MONTH ("ONCE_A_MONTH", "Een keer per maand");

    private String key;
    private String value;

    Frequency(String key, String value) {
        this.value = value;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

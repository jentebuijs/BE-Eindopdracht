package nl.novi.eindopdracht.models;

public enum Frequency {
    EVERY_DAY ("Elke dag"),
    FEW_TIMES_A_WEEK ("Een paar keer per week"),
    ONCE_A_WEEK ("Een keer per week"),
    FEW_TIMES_A_MONTH ("Een paar keer per week"),
    ONCE_A_MONTH ("Een keer per maand");

    private String string;

    Frequency(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}

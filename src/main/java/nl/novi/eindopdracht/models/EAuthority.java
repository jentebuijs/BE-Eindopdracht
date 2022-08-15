package nl.novi.eindopdracht.models;

public enum EAuthority {
    ROLE_STUDENT ("Student"),
    ROLE_BUDDY ("Buddy"),
    ROLE_ADMIN ("Admin");

    private String string;

    EAuthority(String string) {

        this.string = string;
    }

    public String getString() {
        return string;
    }
}

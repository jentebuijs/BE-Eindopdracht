package nl.novi.eindopdracht.models;

public enum EAuthority {
    ROLE_STUDENT(1),
    ROLE_BUDDY(2),
    ROLE_ADMIN(3);

    private int roleNumber;

    EAuthority(int roleNumber) {
        this.roleNumber = roleNumber;
    }
}

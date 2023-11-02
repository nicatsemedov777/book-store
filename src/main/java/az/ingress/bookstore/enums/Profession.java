package az.ingress.bookstore.enums;

import lombok.Getter;

@Getter
public enum Profession {
    ADMIN("Admin"),
    STUDENT("Student"),
    TEACHER("Teacher");

    private final String label;

    Profession(String label) {
        this.label = label;
    }
}

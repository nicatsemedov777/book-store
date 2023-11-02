package az.ingress.bookstore.enums;

import lombok.Getter;

@Getter
public enum Profession {
    ADMIN("ADMIN"),
    STUDENT("STUDENT"),
    AUTHOR("AUTHOR");

    private final String label;

    Profession(String label) {
        this.label = label;
    }
}

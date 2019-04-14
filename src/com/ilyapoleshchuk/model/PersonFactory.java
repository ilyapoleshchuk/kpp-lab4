package com.ilyapoleshchuk.model;

public class PersonFactory {

    private PersonFactory() {
    }

    public static Person createPerson(String firstName, String lastName, Generation generation) {
        switch (generation) {
            case NEW_SCHOOL:
                return new NewSchoolPerson(firstName, lastName);
            case OLD_SCHOOL:
                return new OldSchoolPerson(firstName, lastName);
            default:
                throw new IllegalArgumentException();
        }
    }
}

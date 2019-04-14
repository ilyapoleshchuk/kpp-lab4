package com.ilyapoleshchuk.model;

public class NewSchoolPerson extends Person {

    public NewSchoolPerson(String firstName, String lastName) {
        super(firstName, lastName, Generation.NEW_SCHOOL);
    }

    @Override
    public void performAction() {
        //todo
    }
}

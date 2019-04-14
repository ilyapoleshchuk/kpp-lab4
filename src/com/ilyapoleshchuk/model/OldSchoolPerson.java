package com.ilyapoleshchuk.model;

public class OldSchoolPerson extends Person {

    public OldSchoolPerson(String firstName, String lastName) {
        super(firstName, lastName, Generation.OLD_SCHOOL);
    }

    @Override
    public void performAction() {
        //todo
    }
}

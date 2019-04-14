package com.ilyapoleshchuk.model;

import com.ilyapoleshchuk.util.Log;

public class NewSchoolPerson extends Person {

    NewSchoolPerson(String firstName, String lastName) {
        super(firstName, lastName, Generation.NEW_SCHOOL);
    }

    @Override
    public void performAction() {
        useInternet();
    }

    private void useInternet() {
        Log.i("Using internet...");
    }
}

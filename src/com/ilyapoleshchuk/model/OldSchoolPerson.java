package com.ilyapoleshchuk.model;

import com.ilyapoleshchuk.util.Log;

public class OldSchoolPerson extends Person {

    OldSchoolPerson(String firstName, String lastName) {
        super(firstName, lastName, Generation.OLD_SCHOOL);
    }

    @Override
    public void performAction() {
        useTelephone();
    }

    private void useTelephone() {
        Log.i("Using telephone...");
    }
}

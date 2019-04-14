package com.ilyapoleshchuk.model;

public abstract class Person extends Thread {

    private String firstName;
    private String lastName;
    private Generation generation;

    public Person(String firstName, String lastName, Generation generation) {
        super(prettyString(firstName, lastName, generation));

        this.firstName = firstName;
        this.lastName = lastName;
        this.generation = generation;

        start();
    }

    @Override
    public final void run() {
        super.run();
        performAction();
    }

    private static String prettyString(String firstName, String lastName, Generation generation) {
        return "Person: " + firstName + ", " + lastName + ", " + generation.toString();
    }

    @Override
    public final String toString() {
        return prettyString(firstName, lastName, generation);
    }

    /*
    * Depends on generation;
    *
    * If old generation - use telephone;
    * If new generation - use internet;
    */
    public abstract void performAction();
}

package com.ilyapoleshchuk;

import com.ilyapoleshchuk.model.Generation;
import com.ilyapoleshchuk.model.Person;
import com.ilyapoleshchuk.model.PersonFactory;
import com.ilyapoleshchuk.service.AccessPoint;
import com.ilyapoleshchuk.service.Accessible;
import com.ilyapoleshchuk.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    private static long COMMUNICATION_TIME = AccessPoint.COMMUNICATION_TIME;

    private static int GRANDAD_COUNTER = 0;
    private static int BOY_COUNTER = 0;
    private static int GIRL_COUNTER = 0;

    public static void main(String[] args) throws InterruptedException {

        //create access point and use proxy to limit access

        AccessPoint accessPoint = AccessPoint.getInstance();
        AccessPoint.ProtectiveProxy accessProxy = accessPoint.createProtectiveProxy();

        //try to use access point in different combinations

        Log.h("Try to access network via internet and telephone simultaneously");

        /*
        * Trying to use access point simultaneously by grandad and boy logs an exception when boy tries
        */
        createGrandad(accessProxy).start();
        createBoy(accessProxy).start();

        /*
        * Try again after grandad stopped usage
        */
        Thread.sleep(COMMUNICATION_TIME * 2);

        Log.h("Try to access network via internet after usage delay");

        /*
        * Should work now
        */
        createGirl(accessProxy).start();

        /*
        * Await again
        */

        Thread.sleep(COMMUNICATION_TIME * 2);

        Log.h("Try to access network via internet using multiple connections");

        /*
        * Simultaneous access to internet is allowed
        */
        createBoy(accessProxy).start();
        createGirl(accessProxy).start();

        //that's all
        postponeTask(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, COMMUNICATION_TIME * 2);

    }

    private static Person createGrandad(Accessible device) {
        if (device == null) {
            throw new IllegalStateException();
        }
        Person person = PersonFactory.createPerson("Vasiliy", "Ivanov#" + GRANDAD_COUNTER++, Generation.OLD_SCHOOL);
        person.linkWithGlobalNetwork(device);
        return person;
    }

    private static Person createBoy(Accessible device) {
        if (device == null) {
            throw new IllegalStateException();
        }
        Person person = PersonFactory.createPerson("Petya", "Sidorov#" + BOY_COUNTER++, Generation.NEW_SCHOOL);
        person.linkWithGlobalNetwork(device);
        return person;
    }

    private static Person createGirl(Accessible device) {
        if (device == null) {
            throw new IllegalStateException();
        }
        Person person = PersonFactory.createPerson("Anya", "Petrova#" + GIRL_COUNTER++, Generation.NEW_SCHOOL);
        person.linkWithGlobalNetwork(device);
        return person;
    }

    private static void postponeTask(TimerTask task, long delay) {
        new Timer().schedule(task, delay);
    }
}

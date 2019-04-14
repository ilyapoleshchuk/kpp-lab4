package com.ilyapoleshchuk.service;

import com.ilyapoleshchuk.exception.AccessUnavailableException;
import com.ilyapoleshchuk.util.Log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class AccessPoint implements Accessible {

    public static final long COMMUNICATION_TIME = 2000L;
    private static volatile AccessPoint instance;

    private AccessPoint() {
    }

    /*
     * Thread-safe singleton;
     */
    public static AccessPoint getInstance() {
        if (instance == null) {
            synchronized (AccessPoint.class) {
                if (instance == null) {
                    instance = new AccessPoint();
                }
            }
        }
        return instance;
    }

    @Override
    public void communicate(AccessType accessType) {
        Log.i("Enter the global network via " + accessType.toString() + "...");
        postponeTask(new TimerTask() {
            @Override
            public void run() {
                Log.i("Log out of the global network via " + accessType.toString() + "...");
            }
        }, COMMUNICATION_TIME);
    }

    public ProtectiveProxy createProtectiveProxy() {
        return new ProtectiveProxy();
    }

    public class ProtectiveProxy implements Accessible {

        private AtomicBoolean isUsingTelephone;
        private AtomicBoolean isUsingInternet;

        private ProtectiveProxy() {
            this.isUsingTelephone = new AtomicBoolean(false);
            this.isUsingInternet = new AtomicBoolean(false);
        }

        @Override
        public synchronized void communicate(AccessType accessType) throws AccessUnavailableException {
            if (
                (accessType.equals(AccessType.TELEPHONE) && (isUsingInternet.get() || isUsingTelephone.get()))
                    || (accessType.equals(AccessType.INTERNET) && isUsingTelephone.get())
            ) {

                Log.e("Trying to access busy access point at thread:" + Thread.currentThread().getName() + " via " + accessType.toString());
                throw new AccessUnavailableException();
            }

            /*
             * Mark access point as busy
             */

            switch (accessType) {
                case TELEPHONE:
                    isUsingTelephone.compareAndSet(false, true);
                case INTERNET:
                    isUsingInternet.compareAndSet(false, true);
            }

            /*
             * Perform an action
             */

            AccessPoint.this.communicate(accessType);

            /*
             * Mark device as available after delay
             */

            postponeTask(new TimerTask() {
                @Override
                public void run() {
                    switch (accessType) {
                        case TELEPHONE:
                            isUsingTelephone.compareAndSet(true, false);
                        case INTERNET:
                            isUsingInternet.compareAndSet(true, false);
                    }
                }
            }, COMMUNICATION_TIME);
        }
    }

    private static void postponeTask(TimerTask task, long delay) {
        new Timer().schedule(task, delay);
    }
}

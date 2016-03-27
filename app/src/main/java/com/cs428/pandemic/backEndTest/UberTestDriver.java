package com.cs428.pandemic.backEndTest;

import test.LiteDriver;

/**
 * Created by brandt on 3/2/16.
 */
public class UberTestDriver {

    public static void main(String... args){

        LiteDriver mainDriver = new LiteDriver("app.src.main.java","com.cs428.pandemic.backEndTest");

        // the first flag controls whether we scan recursively
        // the second flag controls whether we look for tests in classes that aren't marked as tests
        mainDriver.queueTests(true, false);

        mainDriver.executeQueuedTests();

        // the flag controls whether we print a full stack trace
        mainDriver.prettyPrint(false);



    }

}

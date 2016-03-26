package com.cs428.pandemic.backEndTest.model.disease;

import test.LiteDriver;

public class DiseaseTestsMain {
    public static void main(String... args){

        LiteDriver mainDriver = new LiteDriver("src", "com.cs428.pandemic.backEndTest.model.disease.DiseaseTestsMain");

        // the first flag controls whether we scan recursively
        // the second flag controls whether we look for tests in classes that aren't marked as tests
        mainDriver.queueTests(true, false);

        mainDriver.executeQueuedTests();

        // the flag controls whether we print a full stack trace
        mainDriver.prettyPrint(false);



    }
}

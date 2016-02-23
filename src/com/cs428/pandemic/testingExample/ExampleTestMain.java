package com.cs428.pandemic.testingExample;

import test.LiteDriver;

/*
==== THE JAVADOCS EXPLAIN HOW ALL THE TESTING STUFF WORKS ====
==== The docs can be found here: http://elison22.github.io/LiteUnit/docs/ ====
 */
public class ExampleTestMain {

    // **** NOTE ****
    // Almost all of the functions below have a bunch of overloads. See the JavaDocs.
    public static void main(String... args){

        // The first param to the constructor is the path to the content root. See the JavaDocs
        // The second param is optional and is the path within the content root to the root of the test files
        LiteDriver manualDriver = new LiteDriver("src", "com.cs428.pandemic.testingExample");

        // ====================
        // === THE LONG WAY === (the fast way is further (farther?) down)
        // ====================
        // The long way of setting up a test is to create a LiteDriver,
        // queue up some tests, then manually execute and print them.

        // scan these packages for tests. you can point at specific classes directly
        manualDriver.queueTests("exampleTests");
        manualDriver.queueTests("exampleTests.exampleInnerTests");
        manualDriver.queueTests("exampleTests2");
        /*
        You could also accomplish this by doing:
        manualDriver.queueTests("exampleTests, true); // recurses on exampleTests
        manualDriver.queueTests("exampleTests2");
        OR
        manualDriver.queueTests();  // which recurses on the root testing directory that you
                                    // set up in the constructor
         */

        // run the tests. the only thing that will print is any System.out statements that
        // you put in the tests themselves or the code they execute
        manualDriver.executeQueuedTests();

        // now you have to call a print function to print the results
        manualDriver.prettyPrint();
        // manualDriver.prettyPrint(true); would display more of or the stack trace for the errors

        // after executing tests, the TestDriver needs to be reset before it can run new tests
        manualDriver.reset();
        // now you are ready to repeat the process

        // ====================
        // === THE FAST WAY ===
        // ====================
        // the runTests() function automates everything that you saw above
        // that includes the reset call at the end, so you can reuse the driver
        LiteDriver autoDriver = new LiteDriver("src", "com.cs428.pandemic.testingExample");

        autoDriver.runTests();          // runs all the tests in the root testing folder
        autoDriver.runTests(true);      // runs all the tests in the root and all subdirectories (recursive)
        autoDriver.runTests("exampleTests", true);              // recurses only in the exampleTests folder
        autoDriver.runTests("exampleTests.ExampleTestClass");   // runs all of the tests in ExampleTestClass
        autoDriver.runTests(true, true, true); // the parameters indicate the following:
        // -- recursively run all tests from the root test folder
        // -- print out more verbose stack trace for errors
        // -- look for tests even in classes NOT marked with the @LiteClass annotation

        // ONE MAJOR DOWN SIDE TO THIS METHOD
        // if you want to run a few specific groups of tests using a few calls to
        // runTests, you will get uglier output. The results will be spread out throughout
        // the output, so you will have to scroll a lot to find all of the results.
        // In that case it is much better to queue them manually, then run them and print them later

    }

}

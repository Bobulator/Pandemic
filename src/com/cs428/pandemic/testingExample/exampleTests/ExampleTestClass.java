package com.cs428.pandemic.testingExample.exampleTests;

import asserts.LiteAssertFailedException;
import test.annotations.LiteClass;
import test.annotations.LiteTest;
import static asserts.LiteAsserts.*;

@LiteClass
public class ExampleTestClass {

    @LiteTest
    public void passingTest(){}

    @LiteTest
    public void failingTest() throws LiteAssertFailedException {
        assertEquals("expected", "actual", "These don't match unfortunately...");
    }

    // All of the following are disqualified.

    @LiteTest
    private void thisWontGetRun(){}

    @LiteTest
    public void thisWontGetRun(String someParam){}

    public void thisWontGetRunEither(){}

}

package com.cs428.pandemic.testingExample.exampleTests.exampleInnerTests;

import test.annotations.LiteClass;
import test.annotations.LiteTest;
import static asserts.LiteAsserts.*;

@LiteClass
public class ExampleInnerTestClass {

    @LiteTest
    public void runWhenRecurseIsTrue() throws Exception{
        String nullString = null;
        assertNotNull(nullString);
    }

}

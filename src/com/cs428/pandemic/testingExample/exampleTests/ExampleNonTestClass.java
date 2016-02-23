package com.cs428.pandemic.testingExample.exampleTests;

import test.annotations.LiteTest;

// not annotated as a test class
public class ExampleNonTestClass {

    @LiteTest
    public void canRunWhenScanAllFlagTrue(){
        String blah = null;
        blah.charAt(0);
    }

}

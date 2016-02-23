package com.cs428.pandemic.testingExample.exampleTests2;

import test.annotations.LiteClass;
import test.annotations.LiteTest;
import static asserts.LiteAsserts.*;

/**
 * Created by brandt on 2/23/16.
 */
@LiteClass
public class TestFromDifferentPackage {

    @LiteTest
    public void failingFromSecondPackage() throws Exception {
        assertTrue(false);
    }

    @LiteTest
    public void passingFromSecondPackage() throws Exception {}

}

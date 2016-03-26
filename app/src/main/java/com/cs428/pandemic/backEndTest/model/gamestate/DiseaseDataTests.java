/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEndTest.model.gamestate;

import com.cs428.pandemic.backEnd.model.gamestate.implementation.MappedDiseaseData;
import test.LiteDriver;
import asserts.LiteAssertFailedException;
//import static org.junit.Assert.assertEquals;

import com.cs428.pandemic.backEnd.model.gamestate.*;
import java.util.Random;
//import org.junit.*;
import test.annotations.*;
import static asserts.LiteAsserts.*;

/**
 *
 * @author James
 */
@LiteClass
public class DiseaseDataTests 
{
    private IDiseaseData data;
    
    public void setup() throws LiteAssertFailedException
    {
        this.data = new MappedDiseaseData();
        int testNum = 1;
        DiseaseType[] allTypes = DiseaseType.values();
        for(int i = 0; i < allTypes.length; ++i)
        {
            DiseaseType type = allTypes[i];
            assertEquals(false,this.data.isCured(type),
                    "Test setup " + testNum++ + ": " + type.toString() + " should NOT be considered cured ");
            assertEquals(false,this.data.isEradicated(type),
                    "Test setup " + testNum++ + ": " + type.toString() + " should NOT be considered eradicated ");
        }
        assertEquals(this.data.allCured(),false, 
                "Test setup " + testNum++ + ": not all of the viruses should be considered cured ");
    }
    
    boolean allTrue(boolean[] vals)
    {
        for(boolean val:vals)
        {
            if(!val)
            {
                return false;
            }
        }
        return true;
    }
    
    boolean allFalse(boolean[] vals)
    {
        for(boolean val:vals)
        {
            if(!val)
            {
                return true;
            }
        }
        return false;
    }
    
    @LiteTest
    public void testCured() throws LiteAssertFailedException
    {
        this.setup();
        int testNum = 1;
        Random rand = new Random();
        DiseaseType[] values = DiseaseType.values();
        boolean[] cured = new boolean[values.length];
        boolean continuing = true;
        while(continuing)
        {
            int index = rand.nextInt(values.length);
            if(!cured[index])
            {
                DiseaseType type = values[index];
                cured[index] = true;
                assertEquals(false,this.data.isCured(type),
                        "Test Cured " + testNum++ + ": " + type.toString() + " should NOT be cured ");
                this.data.cure(type);
                assertEquals(true,this.data.isCured(type),
                        "Test Cured " + testNum++ + ": " + type.toString() + " should be cured ");
                boolean allCure = allTrue(cured);
                assertEquals(allCure,this.data.allCured(),
                        "Test Cured " + testNum++ + ": all cured is supposed to be " + allCure);
                continuing = !allCure;
            }
        }
        assertEquals(true,this.data.allCured(),
                "Test Cured " + testNum++ + ": all cured is supposed to be TRUE");
    }
    
    @LiteTest
    public void testDeCured() throws LiteAssertFailedException
    {
        this.setup();
        int testNum = 1;
        Random rand = new Random();
        DiseaseType[] values = DiseaseType.values();
        boolean[] cured = new boolean[values.length];
        for(int i = 0; i < values.length; ++i)
        {
            DiseaseType type = values[i];
            cured[i] = true;
            assertEquals(false,this.data.isCured(type),
                    "Test DeCured " + testNum++ + ": " + type.toString() + " should NOT be cured ");
            this.data.cure(type);
            assertEquals(true,this.data.isCured(type),
                    "Test DeCured " + testNum++ + ": " + type.toString() + " should be cured ");
            boolean allCure = allTrue(cured);
            assertEquals(allCure,this.data.allCured(),
                    "Test Cured " + testNum++ + ": all cured is supposed to be " + allCure);
        }
        assertEquals(true,this.data.allCured(),
                "Test DeCured " + testNum++ + ": all cured is supposed to be TRUE");
        boolean continuing = true;
        while(continuing)
        {
            int index = rand.nextInt(values.length);
            if(cured[index])
            {
                DiseaseType type = values[index];
                cured[index] = false;
                assertEquals(true,this.data.isCured(type),
                        "Test DeCured " + testNum++ + ": " + type.toString() + " should be cured ");
                this.data.decure(type);
                assertEquals(false,this.data.isCured(type),
                        "Test DeCured " + testNum++ + ": " + type.toString() + " should NOT be cured ");
                boolean allCure = allTrue(cured);
                assertEquals(allCure,this.data.allCured(),
                        "Test DeCured " + testNum++ + ": all cured is supposed to be " + allCure);
                continuing = !allFalse(cured);
            }
        }
        assertEquals(false,this.data.allCured(),
                "Test DeCured " + testNum++ + ": all cured is supposed to be FALSE");
    }
    
    @LiteTest
    public void testEradicated() throws LiteAssertFailedException
    {
        this.setup();
        int testNum = 1;
        Random rand = new Random();
        DiseaseType[] values = DiseaseType.values();
        boolean[] eradicated = new boolean[values.length];
        boolean continuing = true;
        while(continuing)
        {
            int index = rand.nextInt(values.length);
            if(!eradicated[index])
            {
                DiseaseType type = values[index];
                eradicated[index] = true;
                assertEquals(false,this.data.isEradicated(type),
                        "Test Eradicated " + testNum++ + ": " + type.toString() + " should NOT be eradicated ");
                this.data.eradicate(type);
                assertEquals(true,this.data.isEradicated(type),
                        "Test Eradicated " + testNum++ + ": " + type.toString() + " should be eradicated ");
                continuing = !allTrue(eradicated);
            }
        }
    }
    
    @LiteTest
    public void testDeEradicated() throws LiteAssertFailedException
    {
        this.setup();
        int testNum = 1;
        Random rand = new Random();
        DiseaseType[] values = DiseaseType.values();
        boolean[] eradicated = new boolean[values.length];
        for(int i = 0; i < values.length; ++i)
        {
            DiseaseType type = values[i];
            eradicated[i] = true;
            assertEquals(false,this.data.isEradicated(type),
                    "Test DeEradicated " + testNum++ + ": " + type.toString() + " should NOT be cured ");
            this.data.eradicate(type);
            assertEquals(true,this.data.isEradicated(type),
                    "Test DeEradicated " + testNum++ + ": " + type.toString() + " should be cured ");
        }
        boolean continuing = true;
        while(continuing)
        {
            int index = rand.nextInt(values.length);
            if(eradicated[index])
            {
                DiseaseType type = values[index];
                eradicated[index] = false;
                assertEquals(false,this.data.isCured(type),
                        "Test DeEradicated " + testNum++ + ": " + type.toString() + " should NOT be cured ");
                this.data.decure(type);
                assertEquals(this.data.isCured(type),false,
                        "Test DeEradicated " + testNum++ + ": " + type.toString() + " should be cured ");
                continuing = !allFalse(eradicated);
            }
        }
    }
    
    public static void main(String[] args)
    {
        String[] testClasses = new String[] 
        {
                "com.cs428.pandemic.backEnd.test.DiseaseDataTests"
        };
        
        LiteDriver driver = new LiteDriver("src","com.cs428.pandemic.backEnd.test.DiseaseDataTests");
        driver.queueTests();
        driver.executeQueuedTests();
        driver.prettyPrint(true);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.test;

import com.cs428.pandemic.backEnd.model.gamestate.Implementation.StandardDiseaseData;
import static org.junit.Assert.assertEquals;

import com.cs428.pandemic.backEnd.model.gamestate.*;
import java.util.Random;
import org.junit.*;

/**
 *
 * @author James
 */
public class DiseaseDataTests 
{
    private IDiseaseData data;
    
    @Before
    public void setup()
    {
        this.data = new StandardDiseaseData();
        int testNum = 1;
        DiseaseType[] allTypes = DiseaseType.values();
        for(int i = 0; i < allTypes.length; ++i)
        {
            DiseaseType type = allTypes[i];
            assertEquals("Test setup " + testNum++ + ": " + type.toString() + " should NOT be considered cured ",
                    this.data.isCured(type),false);
            assertEquals("Test setup " + testNum++ + ": " + type.toString() + " should NOT be considered eradicated ",
                    this.data.isEradicated(type),false);
        }
        assertEquals("Test setup " + testNum++ + ": not all of the viruses should be considered cured ",
                this.data.allCured(),false);
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
    
    @Test
    public void testCured()
    {
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
                assertEquals("Test Cured " + testNum++ + ": " + type.toString() + " should NOT be cured ",
                        this.data.isCured(type),false);
                this.data.cure(type);
                assertEquals("Test Cured " + testNum++ + ": " + type.toString() + " should be cured ",
                        this.data.isCured(type),true);
                boolean allCure = allTrue(cured);
                assertEquals("Test Cured " + testNum++ + ": all cured is supposed to be " + allCure,
                        this.data.allCured(),allCure);
                continuing = !allCure;
            }
        }
        assertEquals("Test Cured " + testNum++ + ": all cured is supposed to be TRUE",
                this.data.allCured(),true);
    }
    
    @Test
    public void testDeCured()
    {
        int testNum = 1;
        Random rand = new Random();
        DiseaseType[] values = DiseaseType.values();
        boolean[] cured = new boolean[values.length];
        for(int i = 0; i < values.length; ++i)
        {
            DiseaseType type = values[i];
            cured[i] = true;
            assertEquals("Test DeCured " + testNum++ + ": " + type.toString() + " should NOT be cured ",
                    this.data.isCured(type),false);
            this.data.cure(type);
            assertEquals("Test DeCured " + testNum++ + ": " + type.toString() + " should be cured ",
                    this.data.isCured(type),true);
            boolean allCure = allTrue(cured);
            assertEquals("Test Cured " + testNum++ + ": all cured is supposed to be " + allCure,
                    this.data.allCured(),allCure);
        }
        assertEquals("Test DeCured " + testNum++ + ": all cured is supposed to be TRUE",
                this.data.allCured(),true);
        boolean continuing = true;
        while(continuing)
        {
            int index = rand.nextInt(values.length);
            if(cured[index])
            {
                DiseaseType type = values[index];
                cured[index] = false;
                assertEquals("Test DeCured " + testNum++ + ": " + type.toString() + " should be cured ",
                        this.data.isCured(type),true);
                this.data.decure(type);
                assertEquals("Test DeCured " + testNum++ + ": " + type.toString() + " should NOT be cured ",
                        this.data.isCured(type),false);
                boolean allCure = allTrue(cured);
                assertEquals("Test DeCured " + testNum++ + ": all cured is supposed to be " + allCure,
                        this.data.allCured(),allCure);
                continuing = !allFalse(cured);
            }
        }
        assertEquals("Test DeCured " + testNum++ + ": all cured is supposed to be FALSE",
                this.data.allCured(),false);
    }
    
    @Test
    public void testEradicated()
    {
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
                assertEquals("Test Eradicated " + testNum++ + ": " + type.toString() + " should NOT be eradicated ",
                        this.data.isEradicated(type),false);
                this.data.eradicate(type);
                assertEquals("Test Eradicated " + testNum++ + ": " + type.toString() + " should be eradicated ",
                        this.data.isEradicated(type),true);
                continuing = !allTrue(eradicated);
            }
        }
    }
    
    @Test
    public void testDeEradicated()
    {
        int testNum = 1;
        Random rand = new Random();
        DiseaseType[] values = DiseaseType.values();
        boolean[] eradicated = new boolean[values.length];
        for(int i = 0; i < values.length; ++i)
        {
            DiseaseType type = values[i];
            eradicated[i] = true;
            assertEquals("Test DeEradicated " + testNum++ + ": " + type.toString() + " should NOT be cured ",
                    this.data.isEradicated(type),false);
            this.data.eradicate(type);
            assertEquals("Test DeEradicated " + testNum++ + ": " + type.toString() + " should be cured ",
                    this.data.isEradicated(type),true);
        }
        boolean continuing = true;
        while(continuing)
        {
            int index = rand.nextInt(values.length);
            if(eradicated[index])
            {
                DiseaseType type = values[index];
                eradicated[index] = false;
                assertEquals("Test DeEradicated " + testNum++ + ": " + type.toString() + " should NOT be cured ",
                        this.data.isCured(type),false);
                this.data.decure(type);
                assertEquals("Test DeEradicated " + testNum++ + ": " + type.toString() + " should be cured ",
                        this.data.isCured(type),false);
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

        org.junit.runner.JUnitCore.main(testClasses);
    }
}

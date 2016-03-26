package com.cs428.pandemic.backEndTest.model.disease;


import asserts.LiteAssertFailedException;

import com.cs428.pandemic.backEnd.model.disease.DiseaseCubes;
import com.cs428.pandemic.backEnd.model.disease.TooManyDiseaseCubesException;
import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;

import test.annotations.LiteClass;
import test.annotations.LiteTest;
import static asserts.LiteAsserts.*;

@LiteClass
public class DiseaseCubesTests {
	
	private DiseaseCubes dc;
	
	public DiseaseCubesTests(){
		dc = new DiseaseCubes();
	}
	
	@LiteTest
	public void testTakeFromRed() throws LiteAssertFailedException{
		//Test initialization of red
		assertEquals(dc.getRed(), 24);
		//Test taking 1 from red
		assertTrue(dc.takeFromRed(1));
		assertEquals(dc.getRed(), 23);
		//Test taking 2 from red
		assertTrue(dc.takeFromRed(2));
		assertEquals(dc.getRed(), 21);
		//Test taking 3 from red
		assertTrue(dc.takeFromRed(3));
		assertEquals(dc.getRed(), 18);
		//Test taking all remaining cubes from red
		assertTrue(dc.takeFromRed(18));
		assertEquals(dc.getRed(), 0);
		//Test taking a cube from an empty red tray
		assertEquals(dc.takeFromRed(1), false);
		assertEquals(dc.getRed(), 0);
		
	}
	
	@LiteTest
	public void testPutBackRed() throws LiteAssertFailedException{
		//Test initialization of red
		assertEquals(dc.getRed(), 24);
		//Remove all cubes from red
		assertTrue(dc.takeFromRed(24));
		assertEquals(dc.getRed(), 0);
		//Test adding 1 red cubes
		try {
			dc.putBackRed(1);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getRed(), 1);
		//Test adding 2 red cubes
		try {
			dc.putBackRed(2);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getRed(), 3);
		//Test adding 3 red cubes
		try {
			dc.putBackRed(3);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getRed(), 6);
		//Test adding 18 red cubes, resulting in max amount of red cubes
		try {
			dc.putBackRed(18);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getRed(), 24);
		//Test adding too many red cubes
		String message = "FAIL";
		try{
			dc.putBackRed(1);
		}catch(TooManyDiseaseCubesException e){
			message = e.getMessage();
		}
		assertTrue(message.equals("Too many red cubes!"));
	}
	
	@LiteTest
	public void testTakeFromBlue() throws LiteAssertFailedException{
		//Test initialization of blue
		assertEquals(dc.getBlue(), 24);
		//Test taking 1 from blue
		assertTrue(dc.takeFromBlue(1));
		assertEquals(dc.getBlue(), 23);
		//Test taking 2 from blue
		assertTrue(dc.takeFromBlue(2));
		assertEquals(dc.getBlue(), 21);
		//Test taking 3 from blue
		assertTrue(dc.takeFromBlue(3));
		assertEquals(dc.getBlue(), 18);
		//Test taking all remaining cubes from blue
		assertTrue(dc.takeFromBlue(18));
		assertEquals(dc.getBlue(), 0);
		//Test taking a cube from an empty blue tray
		assertEquals(dc.takeFromBlue(1), false);
		assertEquals(dc.getBlue(), 0);
	}
	
	@LiteTest
	public void testPutBackBlue() throws LiteAssertFailedException{
		//Test initialization of blue
		assertEquals(dc.getBlue(), 24);
		//Remove all cubes from blue
		assertTrue(dc.takeFromBlue(24));
		assertEquals(dc.getBlue(), 0);
		//Test adding 1 blue cubes
		try {
			dc.putBackBlue(1);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getBlue(), 1);
		//Test adding 2 blue cubes
		try {
			dc.putBackBlue(2);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getBlue(), 3);
		//Test adding 3 blue cubes
		try {
			dc.putBackBlue(3);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getBlue(), 6);
		//Test adding 18 blue cubes, resulting in max amount of blue cubes
		try {
			dc.putBackBlue(18);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getBlue(), 24);
		//Test adding too many blue cubes
		String message = "FAIL";
		try{
			dc.putBackBlue(1);
		}catch(TooManyDiseaseCubesException e){
			message = e.getMessage();
		}
		assertTrue(message.equals("Too many blue cubes!"));
	}
	
	@LiteTest
	public void testTakeFromBlack() throws LiteAssertFailedException{
		//Test initialization of black
		assertEquals(dc.getBlack(), 24);
		//Test taking 1 from black
		assertTrue(dc.takeFromBlack(1));
		assertEquals(dc.getBlack(), 23);
		//Test taking 2 from black
		assertTrue(dc.takeFromBlack(2));
		assertEquals(dc.getBlack(), 21);
		//Test taking 3 from black
		assertTrue(dc.takeFromBlack(3));
		assertEquals(dc.getBlack(), 18);
		//Test taking all remaining cubes from black
		assertTrue(dc.takeFromBlack(18));
		assertEquals(dc.getBlack(), 0);
		//Test taking a cube from an empty black tray
		assertEquals(dc.takeFromBlack(1), false);
		assertEquals(dc.getBlack(), 0);
	}
	
	@LiteTest
	public void testPutBackBlack() throws LiteAssertFailedException{
		//Test initialization of black
		assertEquals(dc.getBlack(), 24);
		//Remove all cubes from black
		assertTrue(dc.takeFromBlack(24));
		assertEquals(dc.getBlack(), 0);
		//Test adding 1 black cubes
		try {
			dc.putBackBlack(1);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getBlack(), 1);
		//Test adding 2 black cubes
		try {
			dc.putBackBlack(2);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getBlack(), 3);
		//Test adding 3 black cubes
		try {
			dc.putBackBlack(3);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getBlack(), 6);
		//Test adding 18 black cubes, resulting in max amount of black cubes
		try {
			dc.putBackBlack(18);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getBlack(), 24);
		//Test adding too many black cubes
		String message = "FAIL";
		try{
			dc.putBackBlack(1);
		}catch(TooManyDiseaseCubesException e){
			message = e.getMessage();
		}
		assertTrue(message.equals("Too many black cubes!"));
	}
	
	@LiteTest
	public void testTakeFromYellow() throws LiteAssertFailedException{
		//Test initialization of yellow
		assertEquals(dc.getYellow(), 24);
		//Test taking 1 from yellow
		assertTrue(dc.takeFromYellow(1));
		assertEquals(dc.getYellow(), 23);
		//Test taking 2 from yellow
		assertTrue(dc.takeFromYellow(2));
		assertEquals(dc.getYellow(), 21);
		//Test taking 3 from yellow
		assertTrue(dc.takeFromYellow(3));
		assertEquals(dc.getYellow(), 18);
		//Test taking all remaining cubes from yellow
		assertTrue(dc.takeFromYellow(18));
		assertEquals(dc.getYellow(), 0);
		//Test taking a cube from an empty yellow tray
		assertEquals(dc.takeFromYellow(1), false);
		assertEquals(dc.getYellow(), 0);
	}
	
	@LiteTest
	public void testPutBackYellow() throws LiteAssertFailedException{
		//Test initialization of yellow
		assertEquals(dc.getYellow(), 24);
		//Remove all cubes from yellow
		assertTrue(dc.takeFromYellow(24));
		assertEquals(dc.getYellow(), 0);
		//Test adding 1 yellow cubes
		try {
			dc.putBackYellow(1);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getYellow(), 1);
		//Test adding 2 yellow cubes
		try {
			dc.putBackYellow(2);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getYellow(), 3);
		//Test adding 3 yellow cubes
		try {
			dc.putBackYellow(3);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getYellow(), 6);
		//Test adding 18 yellow cubes, resulting in max amount of yellow cubes
		try {
			dc.putBackYellow(18);
		} catch (TooManyDiseaseCubesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(dc.getYellow(), 24);
		//Test adding too many yellow cubes
		String message = "FAIL";
		try{
			dc.putBackYellow(1);
		}catch(TooManyDiseaseCubesException e){
			message = e.getMessage();
		}
		assertTrue(message.equals("Too many yellow cubes!"));
	}
	
	@LiteTest
	public void testGetDiseaseCount() throws LiteAssertFailedException{
		//Test get count of RED
		dc.takeFromRed(10);
		try {
			assertEquals(dc.getDiseaseCount(DiseaseType.RED), 14);
		}  catch (Exception e) {
			assertTrue(false);
		}
		//Test get count of Yellow
		dc.takeFromYellow(9);
		try {
			assertEquals(dc.getDiseaseCount(DiseaseType.YELLOW), 15);
		}  catch (Exception e) {
			assertTrue(false);
		}
		//Test get count of BLUE
		dc.takeFromBlue(21);
		try {
			assertEquals(dc.getDiseaseCount(DiseaseType.BLUE), 3);
		}  catch (Exception e) {
			assertTrue(false);
		}
		//Test get count of BLACK
		dc.takeFromBlack(1);
		try {
			assertEquals(dc.getDiseaseCount(DiseaseType.BLACK), 23);
		}  catch (Exception e) {
			assertTrue(false);
		}
	}
}

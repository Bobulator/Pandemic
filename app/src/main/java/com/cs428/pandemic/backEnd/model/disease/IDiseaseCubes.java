package com.cs428.pandemic.backEnd.model.disease;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;




/**
 * Stores and tracks the number of disease cubes of each color in the game
 * @author Casey
 */
public interface IDiseaseCubes {
	/**
	 * Takes the provided number of disease cubes from the red pile
	 * @param num The number of disease cubes to take from the red pile
	 * @return True if the number of disease cubes in the red pile is greater
	 * than or equal to num, false otherwise
	 */
	public boolean takeFromRed(int num);
	
	/**
	 * Takes the provided number of disease cubes from the blue pile
	 * @param num The number of disease cubes to take from the blue pile
	 * @return True if the number of disease cubes in the blue pile is greater
	 * than or equal to num, false otherwise
	 */
	public boolean takeFromBlue(int num);
	
	/**
	 * Takes the provided number of disease cubes from the black pile
	 * @param num The number of disease cubes to take from the black pile
	 * @return True if the number of disease cubes in the black pile is greater
	 * than or equal to num, false otherwise
	 */
	public boolean takeFromBlack(int num);
	
	/**
	 * Takes the provided number of disease cubes from the yellow pile
	 * @param num The number of disease cubes to take from the yellow pile
	 * @return True if the number of disease cubes in the yellow pile is greater
	 * than or equal to num, false otherwise
	 */
	public boolean takeFromYellow(int num);
	
	/**
	 * Puts the provided number of red disease cubes back in the red pile.
	 * @param num
	 */
	public void putBackRed(int num) throws TooManyDiseaseCubesException;
	
	/**
	 * Puts the provided number of blue disease cubes back in the blue pile.
	 * @param num
	 */
	public void putBackBlue(int num) throws TooManyDiseaseCubesException;
	
	/**
	 * Puts the provided number of black disease cubes back in the black pile.
	 * @param num
	 */
	public void putBackBlack(int num) throws TooManyDiseaseCubesException;
	
	/**
	 * Puts the provided number of yellow disease cubes back in the yellow pile.
	 * @param num
	 */
	public void putBackYellow(int num) throws TooManyDiseaseCubesException;
	
	/**
	 * Returns the amount of disease cubes of the provided disease type
	 * @param dt
	 * @return The number of cubes of dt
	 * @throws Exception
	 */
	public int getDiseaseCount(DiseaseType dt) throws Exception;
	
	public int getRed();
	
	public int getBlack();
	
	public int getBlue();
	
	public int getYellow();
}

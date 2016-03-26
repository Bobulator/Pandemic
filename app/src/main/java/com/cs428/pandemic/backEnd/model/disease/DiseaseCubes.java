package com.cs428.pandemic.backEnd.model.disease;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;



public class DiseaseCubes implements IDiseaseCubes{

	private int red;
	private int blue;
	private int black;
	private int yellow;
	
	public DiseaseCubes(){
		red = 24;
		blue = 24;
		black = 24;
		yellow = 24;
	}
	
	@Override
	public boolean takeFromRed(int num) {
		if(red - num >= 0){
			red -= num;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean takeFromBlue(int num) {
		if(blue - num >= 0){
			blue -= num;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean takeFromBlack(int num) {
		if(black - num >= 0){
			black -= num;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean takeFromYellow(int num) {
		if(yellow - num >= 0){
			yellow -= num;
			return true;
		}
		
		return false;
	}

	@Override
	public void putBackRed(int num) throws TooManyDiseaseCubesException{
		if(red + num > 24){
			throw new TooManyDiseaseCubesException("Too many red cubes!");
		}else{
			red += num;
		}
	}

	@Override
	public void putBackBlue(int num) throws TooManyDiseaseCubesException{
		if(blue + num > 24){
			throw new TooManyDiseaseCubesException("Too many blue cubes!");
		}else{
			blue += num;
		}
	}

	@Override
	public void putBackBlack(int num) throws TooManyDiseaseCubesException{
		if(black + num > 24){
			throw new TooManyDiseaseCubesException("Too many black cubes!");
		}else{
			black += num;
		}
	}

	@Override
	public void putBackYellow(int num) throws TooManyDiseaseCubesException{
		if(yellow + num > 24){
			throw new TooManyDiseaseCubesException("Too many yellow cubes!");
		}else{
			yellow += num;
		}
	}
	
	public int getDiseaseCount(DiseaseType dt) throws Exception{
		switch(dt){
			case BLACK:
				return black;
			case BLUE:
				return blue;
			case YELLOW:
				return yellow;
			case RED:
				return red;
			default:
				throw new Exception("INVALID DISEASE TYPE");
		}
	}
	
	public int getRed(){
		return red;
	}
	
	public int getBlue(){
		return blue;
	}
	
	public int getBlack(){
		return black;
	}

	public int getYellow(){
		return yellow;
	}
	
}

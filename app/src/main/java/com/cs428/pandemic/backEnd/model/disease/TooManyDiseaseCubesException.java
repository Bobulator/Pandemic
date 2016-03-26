package com.cs428.pandemic.backEnd.model.disease;

public class TooManyDiseaseCubesException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	
	public TooManyDiseaseCubesException(){
		
	}
	
	public TooManyDiseaseCubesException(String message){
		super(message);
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
}

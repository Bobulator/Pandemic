package com.cs428.pandemic.backEnd.facade;

import java.util.List;
import java.util.Map;

import com.cs428.pandemic.backEnd.manager.GameManager;
import com.cs428.pandemic.backEnd.manager.IGameManager;
import com.cs428.pandemic.frontEnd.ICommandObject;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Card;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_City;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Disease;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_DrawnCards;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Player;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_SharedKnowledge;

public class BackEndFacade implements IModelInterface{

	IGameManager gm = new GameManager();
	
	@Override
	public boolean canMove() {
		return gm.canMove();
	}

	@Override
	public boolean canFlyCharter() {
		return gm.canFlyCharter();
	}

	@Override
	public boolean canFlyDirect() {
		return gm.canFlyDirect();
	}

	@Override
	public boolean canShareKnowledge() {
		return gm.canShareKnowledge();
	}

	@Override
	public boolean canTreatDisease() {
		return gm.canTreatDisease();
	}

	@Override
	public boolean canCureDisease() {
		return gm.canCureDisease();
	}

	@Override
	public boolean canBuildResearchStation() {
		return gm.canBuildResearchStation();
	}

	@Override
	public boolean canPass() {
		return gm.canPass();
	}

	@Override
	public List<UI_Player> startGame(List<String> players, String difficulty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, UI_City> getCityData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getResearchStationLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> getPawnLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRemainingResearchStations() {
		return gm.getRemainingResearchStations();
	}

	@Override
	public List<UI_Disease> getRemainingDiseaseCubes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOutbreakCounter() {
		return gm.getOutbreakCounter();
	}

	@Override
	public int getInfectionRate() {
		return gm.getInfectionRate();
	}

	@Override
	public UI_Player getCurrentPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UI_Card> getPlayerHand(int playerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UI_Card> getInfectionDiscardedCards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UI_Card> getPlayerDiscardedCards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPlayerCardCount() {
		return gm.getPlayerCardCount();
	}

	@Override
	public int getRemainingActions() {
		return gm.getRemainingActions();
	}

	@Override
	public List<String> getConnectedCities(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCharterFlightCities(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDirectFlightCities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UI_SharedKnowledge getShareableKnowledge(int playerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getRoleActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void movePlayer(int playerID, String city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flyPlayer(int playerID, String cityCard, String destinationCity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void discardCard(int playerID, List<String> cardNames) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shareKnowledge(int givingPlayerID, int receivingPlayerID,
			String cardName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treatDisease(String diseaseColor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cureDisease(String diseaseColor, List<String> cards) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildResearchStation(String city) {
		gm.buildResearchStation(city);
	}

	@Override
	public void playEventCard(int playerID, String card) {
		gm.playEventCard(playerID, card);
	}

	@Override
	public void pass() {
		gm.pass();
	}

	@Override
	public ICommandObject executeActionObject(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void endTurn() {
		gm.endTurn();
	}

	@Override
	public UI_DrawnCards drawCards() {
		// TODO Auto-generated method stub
		return null;
	}

}

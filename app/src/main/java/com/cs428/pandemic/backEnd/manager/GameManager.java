package com.cs428.pandemic.backEnd.manager;

import java.util.List;
import java.util.Map;

import com.cs428.pandemic.frontEnd.ICommandObject;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Card;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_City;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Disease;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_DrawnCards;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Player;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_SharedKnowledge;

public class GameManager implements IGameManager{

	@Override
	public void initializeGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCureIndicators() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInfectionRate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOutbreakIndicator() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shuffleInfectionDeck() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawAndInfect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignPlayerRole() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shuffleCityAndEventCards() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dealCards() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDifficultyLevel(String difficulty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preparePlayerDeck() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawTwoFromPlayerDekc() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infectCities() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void performDiseaseOutbreak(String city, String color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void performEpidemic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canFlyCharter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canFlyDirect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canShareKnowledge() {
		// TODO Auto-generated method stub
		// Always trade from the perspective of the giver; if the current player
		// is requesting a card, call canGiveKnowledge on the player the current
		// player is requesting the card from
		return false;
	}

	@Override
	public boolean canTreatDisease() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canCureDisease() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuildResearchStation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPass() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UI_Disease> getRemainingDiseaseCubes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOutbreakCounter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInfectionRate() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRemainingActions() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playEventCard(int playerID, String card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pass() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICommandObject executeActionObject(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void endTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UI_DrawnCards drawCards() {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.cs428.pandemic.backEnd.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cs428.pandemic.backEnd.command.CommandBFG;
import com.cs428.pandemic.backEnd.command.roles.Scientist;
import com.cs428.pandemic.backEnd.model.Model;
import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;
import com.cs428.pandemic.backEnd.model.map.ICity;
import com.cs428.pandemic.backEnd.model.player.IPlayer;
import com.cs428.pandemic.frontEnd.ICommandObject;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Card;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_City;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Disease;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_DrawnCards;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Player;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_SharedKnowledge;
import com.cs428.pandemic.frontEnd.enums.DiseaseColor;

public class GameManager implements IGameManager{

	private Map<Integer, CommandBFG> playerRoleObjects;

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

	// Populate the playerRoleObjects map
	@Override
	public void assignPlayerRole(int numberOfPlayers) {

		for (int i = 0; i < numberOfPlayers; i++){

			// TODO Figure out a way to randomly assign unique roles
			playerRoleObjects.put(i, new Scientist(Model.getInstance().getPlayers().get(i)));
		}
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
		//Get the current player's role and return canMove from that role
		return this.getCurrentPlayerRole().canDriveFerry();
	}



	@Override
	public boolean canFlyCharter() {
		//Get the current player's role and return canFlyCharter from that role
		return this.getCurrentPlayerRole().canFlyCharter();
	}

	@Override
	public boolean canFlyDirect() {
		//Get the current player's role and return canFlyDirect from that role
		return this.getCurrentPlayerRole().canFlyDirect();
	}

	@Override
	public boolean canShareKnowledge() {
		//Get the current player's role and return canGive or canReceive from that role
		return this.getCurrentPlayerRole().canGiveKnowledgeToAnyPlayer(this.playerRoleObjects) ||
			   this.getCurrentPlayerRole().canReceiveKnowledgeFromAnyPlayer(this.playerRoleObjects);
	}

	@Override
	public boolean canTreatDisease() {
		//Get the current player's role and return canTreatDisease from that role
		return this.getCurrentPlayerRole().canTreatDisease();
	}

	@Override
	public boolean canCureDisease() {
		//Get the current player's role and return canDiscoverCure from that role
		return this.getCurrentPlayerRole().canDiscoverCure();
	}

	@Override
	public boolean canBuildResearchStation() {
		//Get the current player's role and return canBuildStation from that role
		return this.getCurrentPlayerRole().canBuildStation();
	}

	@Override
	public boolean canPass() {
		//Get the current player's role and return canMove from that role
		return this.getCurrentPlayerRole().canPass();
	}

	@Override
	public List<UI_Player> startGame(List<String> players, String difficulty) {

		int numberOfEpidemics = 4;

		// Set numberofEpidemics based on chosen difficulty
		switch(difficulty){

			case "Normal":
				numberOfEpidemics = 4;
				break;
			case "Hard":
				numberOfEpidemics = 5;
				break;
			case "Insane":
				numberOfEpidemics = 6;
				break;
		}

		// Initialize the model
		Model.createInstance(numberOfEpidemics, players);

		// Assign player's a role
		assignPlayerRole(players.size());

		List<UI_Player> uiPlayers = new ArrayList<>();

		// Convert players to UI_Players
		for (int i = 0; i < players.size(); i++){

			uiPlayers.add(new UI_Player(i, players.get(i), playerRoleObjects.get(i).getRoleName()));
		}

		return uiPlayers;
	}

	@Override
	public Map<String, UI_City> getCityData() {
		Map<String, UI_City> cityData = new HashMap<String, UI_City>();

		//
		Map<String, ICity> cities = Model.getInstance().getMap().getAllICities();

		for(Map.Entry<String, ICity> city: cities.entrySet()){
			//Get the city name
			String cityName = city.getKey();

			//Get the city diseaseType and convert it to a diseaseColor
			DiseaseColor cityColor = DiseaseColor.valueOf(city.getValue().getColor().toString());

			//Get the disease count info from the city
			Map<String, Integer> diseaseCounts = city.getValue().getPresentDiseasesCounts();

			//Convert the map of disease names to integers to a map of disease colors to integers
			Map<DiseaseColor, Integer> diseaseCubeInfo = new HashMap<DiseaseColor, Integer>();

			for(Map.Entry<String, Integer> disease : diseaseCounts.entrySet()){
				//Convert disease name to DiseaseColor
				DiseaseColor diseaseColor = DiseaseColor.valueOf(disease.getKey().toString());
				//Get the number of this diseaseColor
				int diseaseCount = disease.getValue();

				diseaseCubeInfo.put(diseaseColor, diseaseCount);
			}

			//Get the names of the adjacent cities
			List<String> adjacentCities = city.getValue().getAdjacentCityNames();

			//Create a new UI_City with the above information and add it to the city data map
			UI_City uiCity = new UI_City(cityName, cityColor, diseaseCubeInfo, adjacentCities);
			cityData.put(cityName, uiCity);
		}

		return cityData;
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

	@Override
	public Map<Integer, CommandBFG> getPlayerRoleObjects(){

			return playerRoleObjects;
	}

	@Override
	public CommandBFG getCurrentPlayerRole() {
		//Get current player's index
		int playerIndex = Model.getInstance().getTurnTracker().getCurrentPlayer();

		//Get and return the role associated with playerIndex
		return this.playerRoleObjects.get(playerIndex);
	}
}

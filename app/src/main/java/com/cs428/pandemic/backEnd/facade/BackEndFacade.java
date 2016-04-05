package com.cs428.pandemic.backEnd.facade;

import java.util.List;
import java.util.Map;

import com.cs428.pandemic.backEnd.manager.GameManager;
import com.cs428.pandemic.backEnd.manager.IGameManager;
import com.cs428.pandemic.frontEnd.ICommandObject;
import com.cs428.pandemic.frontEnd.IUI_Updater;
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
		return false;
	}

	@Override
	public boolean canFlyDirect() {
		return false;
	}

	@Override
	public boolean canFlyShuttle() {
		return false;
	}

	@Override
	public boolean canShareKnowledge() {
		return false;
	}

	@Override
	public boolean canTreatDisease() {
		return false;
	}

	@Override
	public boolean canCureDisease() {
		return false;
	}

	@Override
	public boolean canBuildResearchStation() {
		return false;
	}

	@Override
	public boolean canPass() {
		return false;
	}

	@Override
	public List<UI_Player> startGame(List<String> players, String difficulty, IUI_Updater uiUpdater) {

		return gm.startGame(players, difficulty);
	}

	@Override
	public Map<String, UI_City> getCityData() {
		return null;
	}

	@Override
	public List<String> getResearchStationLocations() {
		return null;
	}

	@Override
	public Map<Integer, String> getPawnLocations() {
		return null;
	}

	@Override
	public int getRemainingResearchStations() {
		return 0;
	}

	@Override
	public List<UI_Disease> getRemainingDiseaseCubes() {
		return null;
	}

	@Override
	public int getOutbreakCounter() {
		return 0;
	}

	@Override
	public int getInfectionRate() {
		return 0;
	}

	@Override
	public UI_Player getCurrentPlayer() {
		return null;
	}

	@Override
	public List<UI_Card> getPlayerHand(int playerID) {
		return null;
	}

	@Override
	public List<UI_Card> getInfectionDiscardedCards() {
		return null;
	}

	@Override
	public List<UI_Card> getPlayerDiscardedCards() {
		return null;
	}

	@Override
	public int getPlayerCardCount() {
		return 0;
	}

	@Override
	public int getRemainingActions() {
		return 0;
	}

	@Override
	public List<String> getConnectedCities(String city) {
		return null;
	}

	@Override
	public List<String> getCharterFlightCities(String city) {
		return null;
	}

	@Override
	public List<String> getDirectFlightCities() {
		return null;
	}

	@Override
	public List<String> getShuttleFlightCities() {
		return null;
	}

	@Override
	public UI_SharedKnowledge getShareableKnowledge(int playerID) {
		return null;
	}

	@Override
	public List<String> getRoleActions() {
		return null;
	}

	@Override
	public void movePlayer(int playerID, String city) {

	}

	@Override
	public void flyPlayer(int playerID, String cityCard, String destinationCity) {

	}

	@Override
	public void discardCard(int playerID, List<String> cardNames) {

	}

	@Override
	public void shareKnowledge(int givingPlayerID, int receivingPlayerID, String cardName) {

	}

	@Override
	public void treatDisease(String diseaseColor) {

	}

	@Override
	public void cureDisease(String diseaseColor, List<String> cards) {

	}

	@Override
	public void buildResearchStation(String city) {

	}

	@Override
	public void playEventCard(int playerID, String card) {

	}

	@Override
	public void pass() {

	}

	@Override
	public ICommandObject executeActionObject(String action) {
		return null;
	}

	@Override
	public void endTurn() {

	}

	@Override
	public UI_DrawnCards drawCards() {
		return null;
	}
}

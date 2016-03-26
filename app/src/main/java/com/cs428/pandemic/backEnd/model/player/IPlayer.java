package com.cs428.pandemic.backEnd.model.player;

import com.cs428.pandemic.backEnd.model.deck.CardCollection;
import com.cs428.pandemic.backEnd.model.deck.IPlayerCard;
import com.cs428.pandemic.backEnd.model.map.ICity;

public interface IPlayer{
	/**
	* @return the name associated with this player
	*/
	String getName();

	/**
	* Sets the name of the given player
	* @param name the name to be given to the player
	*/
	void setName(String name);

	/**
	 * @return the city the player is currently in
	 */
	ICity getLocation();

	/**
	 * Sets the location of the given player
	 * @param city the name of the city the player is now in
	 */
	void setLocation(ICity city);

	/**
	* @return the name of the role of this player
	*/
	String getRole();

	/**
	* Sets the player's role to the new role
	* @param role the name of the new role for the player (Enum?)
	*/
	void setRole(String role);

	/**
	* @return the CardCollection object representing the cards the player is currently holding
	*/
	CardCollection<IPlayerCard> getHand();


	// Should the parameters to these be strings since they're coming from the UI?
	/**
	* Takes the given card and adds it to the player's hand
	* @param card the card to be added to the player's hand
	* @return false if the player is now above the hand-limit and must discard, true otherwise
	*/
	boolean receiveCard(IPlayerCard card);

	// If the param is a string, then return type should be the card (void?)
	/**
	* Removes the given card from the player's hand
	* @param card the card to be taken from the player's hand
	* @return true if card is successfully removed, otherwise false
	*/
	boolean removeCard(IPlayerCard card);
	
	/**
	 * Removes and returns the card associated with the provided city name from the player's hand
	 * @param cityName The name of the city to remove from the player's hand
	 * @return The card associated with the provided city name being removed from the player's hand
	 */
	IPlayerCard removeCityCard(String cityName);
	
	int getPlayerIndex();
	void setPlayerIndex(int index);
}
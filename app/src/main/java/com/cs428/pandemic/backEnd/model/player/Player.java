package com.cs428.pandemic.backEnd.model.player;

import com.cs428.pandemic.backEnd.model.deck.CardCollection;
import com.cs428.pandemic.backEnd.model.deck.IPlayerCard;
import com.cs428.pandemic.backEnd.model.map.ICity;

public class Player implements IPlayer{

	private String name;
	private int player_index;
	private ICity current_location;
	private String role;
	private CardCollection<IPlayerCard> hand;

	public Player(String name, int player_index, ICity current_location){

		this.name = name;
		this.setPlayerIndex(player_index);
		this.current_location = current_location;
		this.role = role;
		this.hand = new CardCollection<IPlayerCard>();
	}

	public String getName(){

		return name;
	}

	public void setName(String name){

		this.name = name;
	}

	public ICity getLocation(){

		return current_location;
	}

	public void setLocation(ICity city){

		this.current_location = city;
	}

	public String getRole(){

		return role;
	}

	public void setRole(String role){

		this.role = role;
	}

	public CardCollection<IPlayerCard> getHand(){

		return hand;
	}

	public boolean receiveCard(IPlayerCard card){

		hand.receiveCard(card);

		return hand.size() > 7;
	}

	public boolean removeCard(IPlayerCard card){

		return hand.removeCard(card);
	}

	public int getPlayerIndex() {
		return player_index;
	}

	public void setPlayerIndex(int player_index) {
		this.player_index = player_index;
	}

	@Override
	public IPlayerCard removeCityCard(String cityName) {
		return this.hand.removeCityCard(cityName);
	}
}
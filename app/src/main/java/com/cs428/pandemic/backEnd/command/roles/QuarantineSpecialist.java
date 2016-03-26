package com.cs428.pandemic.backEnd.command.roles;

import com.cs428.pandemic.backEnd.command.CaseyBFG;
import com.cs428.pandemic.backEnd.command.ICommand;
import com.cs428.pandemic.backEnd.model.player.IPlayer;

public class QuarantineSpecialist extends CaseyBFG{

	public QuarantineSpecialist(IPlayer player){
		super(player);
	}

	@Override
	public ICommand getCanPerformSpecialAction(String specialAction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICommand getPerformSpecialAction(String specialAction) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

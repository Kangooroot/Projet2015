package fr.univavignon.courbes.inter.groupe09.moteur;

import java.util.List;
import java.util.Map;

import fr.univavignon.courbes.common.Board;
import fr.univavignon.courbes.common.Direction;
import fr.univavignon.courbes.common.Profile;
import fr.univavignon.courbes.inter.ClientProfileHandler;
import fr.univavignon.courbes.inter.ErrorHandler;
import fr.univavignon.courbes.network.ClientCommunication;

public class NetworksClient implements ClientCommunication {

	@Override
	public String getIp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIp(String ip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPort(int port) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setErrorHandler(ErrorHandler errorHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProfileHandler(ClientProfileHandler profileHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launchClient() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeClient() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addProfile(Profile profile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeProfile(Profile profile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer retrievePointThreshold() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board retrieveBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendCommands(Map<Integer, Direction> commands) {
		// TODO Auto-generated method stub
		
	}

	
}

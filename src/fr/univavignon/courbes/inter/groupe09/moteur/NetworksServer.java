package fr.univavignon.courbes.inter.groupe09.moteur;

import java.util.List;
import java.util.Map;

import fr.univavignon.courbes.common.Board;
import fr.univavignon.courbes.common.Direction;
import fr.univavignon.courbes.common.Profile;
import fr.univavignon.courbes.inter.ErrorHandler;
import fr.univavignon.courbes.inter.ServerProfileHandler;
import fr.univavignon.courbes.network.ServerCommunication;

public class NetworksServer implements ServerCommunication{

	@Override
	public String getIp() {
		// TODO Auto-generated method stub
		return null;
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
	public void setProfileHandler(ServerProfileHandler profileHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launchServer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeServer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendProfiles(List<Profile> profiles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPointThreshold(int pointThreshold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendBoard(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, Direction> retrieveCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	

}

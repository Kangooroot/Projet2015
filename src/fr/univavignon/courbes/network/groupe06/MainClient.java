package fr.univavignon.courbes.network.groupe06;


import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import fr.univavignon.courbes.common.Profile;



public class MainClient {

	public static void main(String[] args) {
		
	/*	Client C = new Client();
		C.setIp("192.168.0.16");
		C.setPort(2345);
		C.launchClient();
		//C.retrieveSerializable();
		
		List<Profile> profiles = new ArrayList<Profile>();
	
		while(true) {
			System.out.println(C.retrieveText());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			profiles = C.retrieveProfiles();
		//	for(Profile prof:profiles) {
				
				System.out.println("PROUT");
		//	}	
		}
		/* int tab2[]={1,2,3};		// profileIDs
		MyPhysicsEngine b = new MyPhysicsEngine();
		b.ourBoard = b.init(800,600,tab2);
		b.ourBoard.snakes[0].currentAngle = 180;
		b.ourBoard.snakes[1].currentAngle = 0;
		b.ourBoard.snakes[2].currentAngle = 90;
		
		Client C = new Client();
		C.setIp("192.168.0.16");
		C.setPort(2345);
		C.launchClient();
		
		System.out.println("Angle en degré : "+b.ourBoard.snakes[0].currentAngle+
				"\nAngle en degré : "+b.ourBoard.snakes[1].currentAngle+"\nAngle en degré :"
						+ " "+b.ourBoard.snakes[2].currentAngle);

		while(true) {
			
			try {
				Thread.sleep(0050);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			b.ourBoard = C.retrieveBoard();
			if (b.ourBoard != null) {
				System.out.println("Angle en degré : "+b.ourBoard.snakes[0].currentAngle+
				"\nAngle en degré : "+b.ourBoard.snakes[1].currentAngle+"\nAngle en degré :"
						+ " "+b.ourBoard.snakes[2].currentAngle);
				
			}
			
			
		} */
		/*C.sendText("/quit");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		C.closeClient();
		
		/*Client C1 = new Client();
		C1.setIp("10.104.33.108");
		C1.setPort(2345);
		C1.launchClient();
		C1.sendText("/quit");
		
		/*Client C2 = new Client();
		C2.setIp("10.104.33.108");
		C2.setPort(2345);
		C2.launchClient();
		C2.sendText("/quit");*/
		

	}

}
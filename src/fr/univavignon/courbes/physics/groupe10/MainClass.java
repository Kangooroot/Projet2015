package fr.univavignon.courbes.physics.groupe10;
import fr.univavignon.courbes.common.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;

public class MainClass {
	
	public static void main(String[] args) {
		
		int width = 500;
		int height = 500;
		int profileIds[] = {101,102,103};
	  	
		Rnd r = new Rnd();
		
		r.board = r.init(width, height, profileIds);
		r.board.snakes[0].currentX = 20;
		r.board.snakes[0].currentY = 20;
		
		
		/*
		Map<Integer,Direction> commands = new HashMap<Integer, Direction>();
		
		// PROFILE ID OU PLAYER ID ??!
		
		commands.put(profileIds[0], Direction.LEFT);
		commands.put(profileIds[1], Direction.RIGHT);
		commands.put(profileIds[2], Direction.NONE);

		while (true)
			r.update(1, commands);

		*/
		
		r.snakeDrawHead(0);
		r.snakeHeadColision(0);
		
		//System.out.println(r.board.itemsMap.isEmpty());
		
	

}
}

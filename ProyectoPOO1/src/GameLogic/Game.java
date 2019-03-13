/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author David
 */
public class Game {
    private int dice;
    boolean finished;
    ArrayList<Player> players = new ArrayList<>();
    
    private static Map<Integer, Integer> snakeMap = new HashMap<Integer,Integer>();
    private static Map<Integer, Integer> ladderMap = new HashMap<Integer,Integer>();
    
    
    private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
    
    public int rollDice(){
        dice = getRandomNumberInRange(1, 6);
        return dice;
    }
    
    public int movePlayer(Player player){
        int actualPos = player.getPosition();
        dice = rollDice();
        actualPos += dice;
        
        if(actualPos > 100){
            int i = actualPos - 100;
            actualPos = 100 - i;
        }
        
        if(null != snakeMap.get(actualPos)){
            actualPos = snakeMap.get(actualPos);
        }
        
        if(null != ladderMap.get(actualPos)){
            actualPos = ladderMap.get(actualPos);
        }
        player.setPosition(actualPos);
        return dice;
        
    }
    
    public boolean hasWon(Player player){
        return player.getPosition() == 100;
    }
    
    public void addPlayer(int pos, String name, int lvl){
        players.add(new Player(pos, name, lvl));
    }
}

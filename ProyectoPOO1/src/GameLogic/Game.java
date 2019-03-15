/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    
    private boolean playerStart(Player p){
        if (p.getFirstGO() == 5) {
            return true;
        }else{
            p.setFirstGO(rollDice());
            return p.getFirstGO() == 5;
        }
    }
    
    public Player obtainFirstPlayer(ArrayList<Player> pList){
        ArrayList<Player> rePlayers = new ArrayList<>();
        for(Player player : pList){
            player.setFirstGO(rollDice());
        }
        Iterator i = pList.iterator();
        Player p = (Player)i.next();
        rePlayers.add(p);
        while(i.hasNext()){
            Player nextPlayer =(Player)i.next();
            if(p.getFirstGO() < nextPlayer.getFirstGO()){
                rePlayers.clear();
                p = nextPlayer;
                rePlayers.add(p);
            }else if(p.getFirstGO() == nextPlayer.getFirstGO()){
                rePlayers.add(p);
            }
        }
        p = obtainFirstPlayer(rePlayers);
        for(Player player : pList){
            player.setFirstGO(0);
        }
        return p;
    }
    
    public void SortPlayers(Player p){
        int playerPos = players.indexOf(p);
        int pos = playerPos + 1;
        ArrayList<Player> aux = new ArrayList<>();
        int i = playerPos - 1;
        aux.add(players.get(playerPos));
        while( i >= 0){
            aux.add(players.get(i));
            i--;
        }
        while( pos < players.size()){
            aux.add(players.get(pos));
            pos++;
        }
        
        players = aux;
    }
    
    
    public int movePlayer(Player player) throws CantMoveException{
        int actualPos = player.getPosition();
        if(playerStart(player)){
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
        }else{
            throw new  CantMoveException("Player cannot move!");
        }
    }
    
    public boolean hasWon(Player player){
        return player.getPosition() == 100;
    }
    
    public void addPlayer(int pos, String name, int lvl){
        players.add(new Player(pos, name, lvl));
    }
}

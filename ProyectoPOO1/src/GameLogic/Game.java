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
    private ArrayList<Player> players = new ArrayList<>();
    private Player active_player;
    boolean obtainedFirstPlayer = false;
    boolean started = false;
    boolean saved = false;
    private  Map<Integer, Integer> snakeMap = new HashMap<>();
    private  Map<Integer, Integer> ladderMap = new HashMap<>();
    
public Game(){
    snakeMap.put(37, 13);
    snakeMap.put(65, 50);
    snakeMap.put(71, 34);
    snakeMap.put(80, 2);
    snakeMap.put(91, 66);
    snakeMap.put(99, 15);
    snakeMap.put(87, 51);
    
    ladderMap.put(4, 79);
    ladderMap.put(10, 28);
    ladderMap.put(20, 98);
    ladderMap.put(25, 67);
    ladderMap.put(47, 76);
    ladderMap.put(77, 95);
    ladderMap.put(82, 93);
    
}
    public void save(){
        saved = true;
    }
    
    public boolean isSaved(){
        return saved;
    }
    

    
    
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
    
    public boolean playerStart(){
        return active_player.getFirstGO() == 5;
    }
    
    public Player getPlayer(){
        return active_player;
    }
    
    public Player getNextPlayer(){
        if(players.indexOf(active_player) == players.size()-1){
            active_player = players.get(0);
        }else{
            active_player = players.get(players.indexOf(active_player)+1);
        }
        return active_player;
    }
    
    public int playerRollDice_f(){
        int dice_value = rollDice();
        active_player.setFirstGO(dice_value);
        return dice_value;
    }
    
    public int playerRollDice(Player player){
        int dice_value = rollDice();
        player.setFirstGO(dice_value);
        return dice_value;
    }
    
    public void resetPlayers_firstRoll(){
        for(Player p : players){
            p.setFirstGO(0);
        }
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
        
        for(Player player : players){
            player.setidOrder(players.indexOf(player)+1);
        }
    }
    
    
    public int movePlayer(int rolled) throws CantMoveException{
        int actualPos = active_player.getPosition();
        if(playerStart()){
        actualPos += rolled;
        
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
        active_player.setPosition(actualPos);
        if(rolled != 6){
            getNextPlayer();
        }
        return actualPos;
        }else{
            getNextPlayer();
            throw new  CantMoveException("Player cannot move!");
        }
    }
    
    public boolean hasWon(){
        boolean won = false;
        for(Player p : players){
            if(p.getPosition() == 100){
                won = true;
                break;
            }
        }
        return won;
    }
    
    public void addPlayers(ArrayList<String> names){
        for(String player_nameString : names){
            addPlayer(1, player_nameString, 1);
        }
        active_player = players.get(0);
    }
    
    public ArrayList<Player> setPlayersTurn(ArrayList<Player> same_rollPlayers){      
        ArrayList<Player> result = new ArrayList<>();
        Player highestRollPlayer = same_rollPlayers.get(0);
        int i = 1;
        if(started){
            result.add(highestRollPlayer);
            while( i < same_rollPlayers.size()){
                if(same_rollPlayers.get(i).getFirstGO() > highestRollPlayer.getFirstGO()){
                    highestRollPlayer = same_rollPlayers.get(i);
                    result.clear();
                    result.add(highestRollPlayer);
                }else if(same_rollPlayers.get(i).getFirstGO() == highestRollPlayer.getFirstGO()){
                    result.add(same_rollPlayers.get(i));
                }
                i++;
            }
            if(result.size()== 1){
            obtainedFirstPlayer = true;
            active_player = result.get(0);
            }   
        }else{
            started = true;
            return same_rollPlayers;
        }
   
        return result;
    }
    
    public boolean isStarted(){
        return started;
    }
    public void setStart(boolean started){
        this.started = started;
    }
    public boolean isFirstPlayerSet(){
        return obtainedFirstPlayer;
    }
    public  ArrayList<Player> getplayers(){
        
        return players;
    }
    
    public void addPlayer(int pos, String name, int lvl){
        players.add(new Player(pos, name, lvl));
    }
    
    public Player getWinner(){
        while(active_player.getPosition() != 100){
            getNextPlayer();
        }
        return active_player;
    }
    
    public Player getPlayer(int player_number){
        Player player = active_player;
        for(Player p : players){
            if(p.getidOrder() == player_number){
                player = p;
                break;
            }
        }
        return player;
    }
}

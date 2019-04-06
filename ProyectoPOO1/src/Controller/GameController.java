/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GameLogic.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import javax.swing.JFileChooser;
/**
 *
 * @author David
 */
public class GameController {
    MainFrame View;
    Game gameModel;
    String fileName;
    
    public GameController(MainFrame View, Game gameModel) {
        this.View = View;
        this.gameModel = gameModel;
        this.View.addSave_Listener(new saveListener());
        this.View.addLoad_listener(new loadListener());
        this.View.addStart_newGameListener(new startListener());
        this.View.addGameStart_listener(new gameStartListener());
        this.View.addRollDice_listener(new rollDiceLisener());
        this.View.disableRollDiceButton(false);
        
    }

    private  class loadListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Gson gson = new Gson();               
            JFileChooser fileChooser = new JFileChooser(new File("."));
            fileChooser.addChoosableFileFilter(new MyFilter());
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.showOpenDialog(null);
            
            fileName = fileChooser.getSelectedFile().getName();
        try (Reader reader = new FileReader(fileName)) {

			// Convert JSON to Java Object
            gameModel = gson.fromJson(reader, Game.class);

        } catch (IOException ex) {
            View.displayError_message("Invalid file type");
            ex.printStackTrace();
        }
            ArrayList<String> names = new ArrayList<>();
            gameModel.getplayers().forEach((player) -> {
                names.add(player.getName());
            });
            View.setPlayersinBoardGUI(names);
            
            View.setActive_playerLabel(gameModel.getPlayer().getName());
            View.startNewGame();
            if(gameModel.isStarted()){
                View.disableRollDiceButton(true);
                View.disableGamestartbtn();
                 switch(gameModel.getplayers().size()){
                       case 2:
                           View.GUIboard.moverFicha(1, gameModel.getPlayer(1).getPosition(), gameModel.getPlayer(2).getPosition());
                           break;
                       case 3:
                           View.GUIboard.moverFicha(1, gameModel.getPlayer(1).getPosition(), gameModel.getPlayer(2).getPosition(), gameModel.getPlayer(3).getPosition());
                           break;
                       case 4:
                            View.GUIboard.moverFicha(1, gameModel.getPlayer(1).getPosition(), gameModel.getPlayer(2).getPosition(), gameModel.getPlayer(3).getPosition()
                                ,gameModel.getPlayer(4).getPosition());
                            break;
                   }
            }else{
                View.disableRollDiceButton(false);
            }
            
        
        }

    
    }
    
    
    private class saveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Gson gson = new Gson();
            //String jsonStringView = gson.toJson(View);
            
            if(!gameModel.isSaved()){
                fileName = JOptionPane.showInputDialog(View, "Choose a name for the file") + ".json";
                gameModel.save();
            }
            try {
            String jsonStringModel = gson.toJson(gameModel);
            FileWriter fileModel = new FileWriter(fileName);
            fileModel.write(jsonStringModel);
            fileModel.flush();
        } catch (IOException ex) {
            
            //ex.printStackTrace();
        }
            catch (Exception e1){
                View.displayError_message("Invalid file type");
            }
            
        }
        
    }
    private  class gameStartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] option1 = {"Roll Dice"};
            ArrayList<Player> competitors = gameModel.getplayers();
            while(!gameModel.isFirstPlayerSet()){
                //competitors = gameModel.setPlayersTurn(competitors);
                gameModel.setStart(true);
                for(Player p : competitors){
                    JPanel panel = new JPanel();
                    panel.add(new JLabel(p.getName()));
                    panel.add(new JLabel("Last roll: " + p.getFirstGO()));
                    int result = JOptionPane.showOptionDialog(View, panel, "Roll highest number to start first", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, option1, null);
                    if(result == 0){
                       int dicevalue = gameModel.playerRollDice(p);
                       View.displayDice(dicevalue);
                    }
                }
                competitors = gameModel.setPlayersTurn(competitors);
                
            }
            View.disableRollDiceButton(true);
            gameModel.SortPlayers(gameModel.getPlayer());
            ArrayList<String> names = new ArrayList<>();
            gameModel.getplayers().forEach((player) -> {
                names.add(player.getName());
            });
            View.setPlayersinBoardGUI(names);
            View.disableGamestartbtn();
            gameModel.resetPlayers_firstRoll();
            View.setActive_playerLabel(gameModel.getPlayer().getName());

        }
        
    }

    private  class rollDiceLisener implements ActionListener{
        int diceValue = 0;
        @Override
        public void actionPerformed(ActionEvent e) {
                if(!gameModel.hasWon()){
                    View.setActive_playerLabel(gameModel.getPlayer().getName());
                    if(!gameModel.playerStart()){
                        int roll =gameModel.playerRollDice_f();
                        if(roll == 5){    
                           
                            View.displayDice(-1);
                        }else{   
                            gameModel.getNextPlayer();
                            View.displayDice(0);
                        }
                        
                        
                    }else{
                   diceValue = gameModel.rollDice();
                   View.displayDice(diceValue);
                    
                   
                  
                   int pos_bMoving = gameModel.getPlayer().getPosition();
                    View.displayActive_pos(pos_bMoving);
                   try{
                      View.displayActive_pos(gameModel.getPlayer().getPosition());
                      Player moving_player = gameModel.getPlayer();
                       gameModel.movePlayer(diceValue);
                       View.displayActive_pos(moving_player.getPosition());
                   }catch(Exception evtMove){
                       View.displayDice(0);
                   }
                   switch(gameModel.getplayers().size()){
                       case 2:
                           View.GUIboard.moverFicha(pos_bMoving, gameModel.getPlayer(1).getPosition(), gameModel.getPlayer(2).getPosition());
                           break;
                       case 3:
                           View.GUIboard.moverFicha(pos_bMoving, gameModel.getPlayer(1).getPosition(), gameModel.getPlayer(2).getPosition(), gameModel.getPlayer(3).getPosition());
                           break;
                       case 4:
                            View.GUIboard.moverFicha(pos_bMoving, gameModel.getPlayer(1).getPosition(), gameModel.getPlayer(2).getPosition(), gameModel.getPlayer(3).getPosition()
                                ,gameModel.getPlayer(4).getPosition());
                            break;
                   }
                  
                }
                       
                }else{
                    JOptionPane.showMessageDialog(View, "Player " + gameModel.getWinner().getName()+ " has won!");
                }
                
                
                
            
        }
    }
    
    
    
    
    
    public class startListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                ArrayList<String> playerNames = View.getPlayersNames();
                gameModel.addPlayers(playerNames);
                
                View.startNewGame();
                View.setExtendedState(View.getExtendedState() | View.MAXIMIZED_BOTH );
            }catch(IllegalArgumentException exception){
                View.displayError_message(exception.getMessage());
            }
            
            
        }
            
        }
    
    class MyFilter extends javax.swing.filechooser.FileFilter {
  @Override
  public boolean accept(File file) {
    String filename = file.getName();
    return filename.endsWith(".json");
  }
        @Override
        public String getDescription() {
            return "*.json";
        }
        
    
}
}

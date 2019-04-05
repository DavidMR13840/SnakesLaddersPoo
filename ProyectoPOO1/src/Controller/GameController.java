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
import java.util.ArrayList;
/**
 *
 * @author David
 */
public class GameController {
    MainFrame View;
    Game gameModel;

    public GameController(MainFrame View, Game gameModel) {
        this.View = View;
        this.gameModel = gameModel;
        
        this.View.addStart_newGameListener(new startListener());
    }
    
    public class startListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                ArrayList<String> playerNames = View.getPlayersNames();
                gameModel.addPlayers(View.getPlayersNames());
                View.startNewGame();
            }catch(IllegalArgumentException exception){
                View.displayError_message(exception.getMessage());
            }
            
        }
            
        }
        
    
}

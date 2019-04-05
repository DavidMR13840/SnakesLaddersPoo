/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopoo1;

import Controller.GameController;
import GameLogic.Game;
import View.MainFrame;

/**
 *
 * @author David
 */
public class ProyectoPOO1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MainFrame mainFrame = new MainFrame();
        Game game = new Game();
        GameController gameController = new GameController(mainFrame, game);
        mainFrame.setVisible(true);
    }
    
}

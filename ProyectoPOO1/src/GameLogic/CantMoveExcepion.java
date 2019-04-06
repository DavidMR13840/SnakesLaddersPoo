/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic;

/**
 *
 * @author David
 */
 class CantMoveException extends Exception {
    public CantMoveException(String msg) {
        super(msg);
    }
}

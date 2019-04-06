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
public class Player {
    private int position;
    private String name;
    private int level;
    private int firstGO;
    int idOrder;
    
    public Player(int pos, String pname, int lvl){
        setLevel(lvl);
        setName(pname);
        setPosition(pos);
    }

    public int getFirstGO() {
        return firstGO;
    }

    public void setFirstGO(int firstGO) {
        this.firstGO = firstGO;
    }

    public int getPosition() {
        return position;
    }

    public void setidOrder(int idJugador){
        idOrder = idJugador;
    }
    
    public int getidOrder(){
        return idOrder;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    
    
}

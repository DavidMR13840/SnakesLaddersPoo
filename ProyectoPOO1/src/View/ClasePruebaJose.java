/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**jojeofjisejofsefsfes
 * @author Jose Rojas
 */
public class ClasePruebaJose {
    private int position;
    private String name;
    private int level;
    private int firstGO;
    
    public ClasePruebaJose(int pos, String pname, int lvl){
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

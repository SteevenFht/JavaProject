/**
 * @author Steeven
 * A Class who can the display of labyrinthe
 */
package display;

import plateau.*;

public class DisplayGameBoard {

    /**
     * a String representation of the labyrinthe 
     * @param labyrinthe the labyrinthe
     */
    public static void displayGameBoard(Labyrinthe labyrinthe) {
        String save = "";
        String nordWall = "";
        String lateralWall = "";
        String sudWall = "";
        String num ="";
        for (int i = 0 ; i < labyrinthe.getNBLigne() ; i++) {
            for (int j = 0 ; j < labyrinthe.getNBColonne() ; j++) {
                nordWall += displayNorthWall(labyrinthe.getCell(i, j));
                lateralWall += displayWestWall(labyrinthe.getCell(i, j)) + displayEastWall(labyrinthe.getCell(i, j), labyrinthe);
                sudWall += displaySouthWall(labyrinthe.getCell(i, j), labyrinthe);
                if (j<10)num += "  "+j+" ";
                else num += " "+j+" ";
            }
            if (i==0){
            save += num + "\n" + nordWall + "\n" + lateralWall + "\n" + sudWall + "\n";
            }else{
                save +=lateralWall + "\n" + sudWall + "\n";  //pas d'display du mur nord car le sud fait office de nord
            }
            nordWall = "";
            lateralWall = "";
            sudWall = "";
        }
        System.out.println(save);
    }

    /**
     * a String representation of the player
     * @param c the cell
     * @return a String representation of the player
     */
    private static String displayPlayer(Cell c){
        String j="";
        if (c.getIsPlayer()) j="3";
        else j=" ";
        return j;
    }

    /**
     * a String representation of the west wall
     * @param c the cell
     * @return a String representation of the west wall
     */
    private static String displayWestWall(Cell c) {
        String save = "";
        if(c.getAbscisse()==0) save+="|";
        return save;
    }

    /**
     * a String representation of the north wall
     * @param c the cell
     * @return a String representation of the north wall
     */
    private static String displayNorthWall(Cell c) {
        String save = "";
        if (c.getAbscisse()==0 && c.getOrdonnee()==0) save += "+---+";
        else save+="---+";
        return save;
    }

    /**
     * a String representation of the east wall
     * @param c the cell
     * @param labyrinthe the labyrinthe
     * @return a String representation of the east wall
     */
    private static String displayEastWall(Cell c , Labyrinthe labyrinthe) {
        String save = " "+displayPlayer(c)+" ";
        if(c.getAbscisse()==labyrinthe.getNBColonne()-1)save+="|"+c.getOrdonnee();
        else if (c.getEast()) save += "|";
        else save += " ";
        return save;
    }

    /**
     * a String representation of the south wall
     * @param c the cell
     * @param labyrinthe the labyrinthe
     * @return a String representation of the south wall
     */
    private static String displaySouthWall(Cell c, Labyrinthe labyrinthe) {
        String save = "";
            if(c.getOrdonnee()==labyrinthe.getNBLigne()){
                if (c.getAbscisse()==0) {
                    save += "+---+";
                }else{
                    save+="---+";
                }
            }else{
                if (c.getSouth()){
                    if (c.getAbscisse()==0) {
                        save += "+---+";
                    }else{
                        save+="---+";
                    }
                }else{
                    if (c.getAbscisse()==0) {
                        save += "+   +";
                    }else{
                        save+="   +";
                    }
                }
        }
        return save;
    }
}

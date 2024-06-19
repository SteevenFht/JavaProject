/**
 * @Author Massimiliano Righetti
 * A Class Scroll it's a clue for the labyrinthe
 */
package entity.item;


import org.json.JSONArray;

import java.util.*;

import entity.character.*;
import entity.util.*;

public class Scroll extends Item {
    private static int scrollNumber = 0;

    private JsonParser json = new JsonParser("ressources/hint.json");
    private Random r = new Random();
    private String info;
    private int alea;

    private static List<Integer> alreadySeenInfo = new ArrayList<>();

    /**
     * a scroll possessing
     */
    public Scroll(){
        super("parchemin " + Scroll.scrollNumber, "un vieux parchemin contenant du texte", 100);
        Scroll.scrollNumber ++;
        JSONArray hintArray = json.getJSONArrayFromData("hint");
        this.alea = getHintNum(hintArray.length());
        this.info = hintArray.getString(alea);
    }

    /**
     * get an index of a random hint in the json file
     * if there is no more hint available reset the alreadySeenInfo attribute
     * @param size size of the array of hint
     * @return a random number of hint
     */
    public int getHintNum(int size) {
        int alea;
        if(alreadySeenInfo.size() == size)
            resetAlreadySeenInfo();
        do {
            alea = r.nextInt(size);
        }while(alreadySeenInfoContains(alea));
        setAlreadySeenInfo(alea);
        return alea;
    }

    public int getAlea(){
        return this.alea;
    }
    /**
     * add an index to alreadyseeninfo 
     * @param num the index
     */
    public static void setAlreadySeenInfo(int num){Scroll.alreadySeenInfo.add(num);}

    /**
     * return true if the alreadySeenInfo list contains num
     * @param num an interger 
     * @return boolean - if the alreadySeenInfo list contains num
     */
    public static boolean alreadySeenInfoContains(int num){return Scroll.alreadySeenInfo.contains(num);}

    /**
     * remove every elements of alreadyseeninfo
     */
    public static void resetAlreadySeenInfo(){Scroll.alreadySeenInfo.clear();}

    /**
     * It prints out the info of the item.
     * @param p The player who is using the item.
     */
    @Override
    public void effect(Player p){
        System.out.println(this.info);
    }

}

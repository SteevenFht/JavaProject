package entity.character;

import plateau.*;
import entity.Inventory;
import entity.item.Item;
import entity.item.Scroll;
import entity.util.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class MadWise extends NoPlayer {
    private Random r = new Random();
    private JsonParser mono = new JsonParser("ressources/MadWise.json");
    private JsonParser dial = new JsonParser("ressources/MadWise.json");


    public MadWise(String name, Cell cell, String text){
        super(name, cell, text);
        this.canMove = true;
        this.initJSon();
    }

    /**
     * Can initialization the JSon in MadWise
     */
    private void initJSon() {
        JSONArray MadList = mono.getJSONArrayFromData("dialogue");
        // Setting the data of the JSON file to the JSONObject `Mad`.
        int MadIndex = r.nextInt(MadList.length());
        JSONObject MadWise = MadList.getJSONObject(MadIndex);
        this.mono.setData(MadWise);

    }

    /**
     * Make a word
     * @param w, a String
     * @param d, the index of word
     * @return the good word
     */
    private String makeWord(String w, int d) {
        int yWin = Labyrinthe.getWinY();
        int midY = (Labyrinthe.labyrinthe[0].length)/2;
        if (yWin < midY && d == 0 || yWin > midY && d == 2){
            w = "";
        } else {
            int xWin = Labyrinthe.getWinX();
            int midX = (Labyrinthe.labyrinthe.length)/2;
            if (xWin < midX && d == 3 || xWin > midX && d == 1){
                w = "";
            }
        }
        return w;
    }

    /**
     *
     * @param i, an Item
     */
    private void translateWord(Item i) {
        Scroll s = (Scroll)i;
        int d = s.getAlea();
        JSONArray ScrollIndex = dial.getJSONArrayFromData("ScrollIndex");
        String word1 = " ne";
        String word2 = " pas";
        if (d < 4){
            if(d % 2 == 0) {
                word1 = makeWord(word1, d);
                word2 = makeWord(word2, d);
            }
            String answer = String.format(ScrollIndex.getString(d), word1, word2);
            System.out.println(answer);
        }else {
            System.out.println(ScrollIndex.getString(d));
        }
    }

    /**
     *
     * @param liste, the list of scroll
     * @param name, the name of scrolls
     * @param p, the current player
     * Give the choice
     */
    private void choice(ArrayList<Item> liste, String name, Player p) {
        Scanner scanner = new Scanner(System.in);
        if(liste.size() > 0){
            System.out.println("Quel objet voulait vous que je traduise ?");
            System.out.println(name);
            Item i;
            String o;
            do{
                o = scanner.nextLine();
                i = p.inInventory(o, liste);
            } while(i == null);
            this.translateWord(i);
        }else{
            System.out.println("Désolé, je ne peux rien te traduire, tu n'as pas de parchemin");
        }
    }

    /**
     * if the player have a scroll, the MadWise tell him the answer from the scroll 
     * @param p the player
     */
    public void scrollAnswer(Player p){
        String nameScroll = " Vous avez :\n";
        ArrayList <Item> listScroll = new ArrayList<>();
        for (Item scroll : p.getInventory().allItem()) {
            if (scroll instanceof Scroll){
                listScroll.add(scroll);
                nameScroll += " " + scroll.getName() + "\n";
            }
        }
        choice(listScroll, nameScroll, p);
    }

    /** 
    * if the player talk to the MadWise, he tell him the scroll Answer and a long speech
    * @param p the player
    */
    @Override
    public void effect(Player p) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(mono.getData().getString("monologue") + "\n-> ");
        scrollAnswer(p);
    }

}

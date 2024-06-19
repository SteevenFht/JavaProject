package entity.character;
import plateau.*;
import entity.util.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;
import java.util.Random;
import java.util.regex.*;

public class Mad extends NoPlayer {
    private Random r = new Random();
    private JsonParser phrase = new JsonParser("ressources/Mad.json");
    private Pattern pattern;


    public Mad(String name, Cell cell, String text){
        super(name, cell, text);
        this.canMove = true;
        this.initJSon();
    }

    /**
     * Can initialization the JSon in Sphinx
     */
    private void initJSon() {
        JSONArray MadWiseList = phrase.getJSONArrayFromData("Mad");
        // Setting the data of the JSON file to the JSONObject `Mad`.
        int MadIndex = r.nextInt(MadWiseList.length());
        JSONObject Mad = MadWiseList.getJSONObject(MadIndex);
        this.phrase.setData(Mad);
    }

    /**
     * Mad character say something to the Player if he talk to him
     * @param p the player
     */
    @Override
    public void effect(Player p) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(phrase.getData().getString("phrase") + "\n-> ");        
    }

}
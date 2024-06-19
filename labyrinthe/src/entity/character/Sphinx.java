/**
 * @Author Massimiliano Righetti
 * A class who created a Sphinx who focus the player on his cell the weather that the player don't solve the enigma
 */
package entity.character;
import plateau.*;
import entity.util.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;
import java.util.Random;
import java.util.regex.*;
public class Sphinx extends NoPlayer {
    private boolean find ;
    private final int attackDamage;
    private JsonParser json = new JsonParser("ressources/Sphinx.json");
    private Random r = new Random();
    private Pattern pattern;
    
    public Sphinx(String name, Cell cell, String text) {
        super(name, cell, text);
        this.canMove = false;
        this.find = false; 
        this.attackDamage = 5;
        this.initJSon();
    }

    /**
     * Can initialization the JSon in Sphinx
     */
    private void initJSon() {
        JSONArray enigmaList = json.getJSONArrayFromData("enigma");
        // Setting the data of the JSON file to the JSONObject `enigma`.
        int enigmaIndex = r.nextInt(enigmaList.length());
        JSONObject enigma = enigmaList.getJSONObject(enigmaIndex);
        this.json.setData(enigma);
        String correctanswer = json.getData().getString("answer");
        this.pattern = Pattern.compile(correctanswer, Pattern.CASE_INSENSITIVE);
    }

    public boolean getFind(){return this.find;}

    public int getAttackDamage(){return this.attackDamage;}

    /**
     * the action of sphinx if the player find the solve
     * @param p, the current player
     */
    private void freePlayer(Player p) {
        this.find = true;
        p.setCanMove(true);
        System.out.println("C'est impossible! Tu dois avoir triche !");
    }

    /**
     * the action of sphinx if the player don't find the solve
     * @param p, the current player
     */
    private void failurePlayer(Player p) {
        p.setHP(p.getHP() - this.getAttackDamage());
        if (p.isDead()) System.out.println("Devine-moi ca : qu'est-ce qui est mort ? Toi !");
        else System.out.println("Je ne pense pas que tu vas resoudre celle-la.");
    }

    /**
     * If the player has not found the answer, ask the question, if the answer is correct, free the
     * player, otherwise, punish the player
     * 
     * @param p The player who is currently playing
     */
    @Override
    public void effect(Player p) {
        if(! this.getFind()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Devine-moi ca:");
            System.out.print(json.getData().getString("question") + "\n-> ");

            if(this.pattern.matcher(scanner.nextLine()).matches()) this.freePlayer(p);
            else this.failurePlayer(p);

        } else
            System.out.println("Penses-tu vraiment que c'est fini ? Je t'aurais la prochaine fois !");
    }
}
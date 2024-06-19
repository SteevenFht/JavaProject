/**
 * @Author Antoine Mazure, Madeline
 * @Date 07/03
 * A class for create the Player who are in the labyrinth
 */

package entity.character;

import entity.*;
import entity.item.Item;
import plateau.Cell;
import plateau.Labyrinthe;
import plateau.Orientation;
import java.util.regex.*;

import java.util.*;

public class Player extends Charact {
    protected int money ;
    protected Inventory bag;
    protected int hp;

    public Player(String name, Cell cell, int money, Inventory bag, int hp) {
        super(name, cell);
        super.getCell().setPlayer();
        this.money = money;
        this.bag = bag;
        this.hp = hp;
    }

    public Player(String name, Cell cell) {
        super(name, cell);
        super.getCell().setPlayer();
        this.money = 5;
        this.bag = new Inventory();
        this.hp = 10;
    }

    /**
     * @return the Player's amount of money
     */
    public int getMoney() { return this.money; }

    /**
     * @return the Player's inventory
     */
    public Inventory getInventory() { return this.bag; }

    /**
     * @return the player's health point
     */
    public int getHP() { return this.hp; }

    /**
     * Change the value of the player's amount of money
     * @param money the player's amount of money
     */
    public void setMoney(int money) {
        this.money += money;
        if (this.money < 0) this.money = 0;
    }

    /**
     * Set the player's inventory
     * @param bag the player's inventory
     *  C'est vraiment utile ?
     * J'en sais rien honnnetement
     */
    public void setInventory(Inventory bag) { this.bag = bag; }

    /**
     * Change the value of the player's health point
     * @param hp the player's health point'
     */
    public void setHP(int hp) { this.hp = hp; }

    /**
     * Verify if the player is dead of not
     * @return True if the hp of the player are <= 0
     *         False if not
     */
    public boolean isDead(){ return (hp <= 0); }

    //***************************************************L'achat et la vente d'objet***********************************

    /**
     * Verify if the player can buy an object
     * @param price the price of the object
     * @return True if the price of the object is greater or equal of the Player's money
     *         False if not
     */
    public boolean canBuy(int price){ return (this.money >= price); }

    /**
     * Verify if the name is the same that an Item in l
     * @param name the name of the item you want
     * @param l the list of all the item available
     * @return the corresponding item of the name
     */
    public Item inInventory(String name, ArrayList<Item> l){
        for (Item item : l) {
            if (item.getName().toLowerCase().equals(name.toLowerCase())) return item;
        }
        return null;
    }

    /**
     * Choose an Item in a inventory, with a scanner, and verify if Item is in the inventory
     * @param i The inventory of the player
     * @return The item you have choose
     */
    public Item chooseObject(Inventory i){
        Scanner sc =  new Scanner(System.in);
        String o = "";
        System.out.println(i.toString());
        System.out.println("Quel objet voulez-vous ? (Veuillez entrer son nom)");
        o = sc.nextLine();
        return (inInventory(o, i.allItem()));
    }

    /**
     *
     * @param item, the item choice by the player
     * @param action, buy or sell, action choice by player
     * @param keeper, the shopKeeper who help have make the action
     * @return true if the player want to actually make action
     */
    public boolean confirmation(Item item, String action, ShopKeeper keeper) {
        Scanner scanner = new Scanner(System.in);
        if (action.equals("acheter")) System.out.println("Souhaitez-vous bien acheter " + item.getName() + " qui vous couteras " + keeper.incressPrice(item) +" or en sachant que vous avez " + this.getMoney() + " or (ecrivez oui ou non)");
        else System.out.println("Souhaitez-vous bien vendre " + item.getName() + " qui vous rapportera " + item.getPrice() +" or en sachant que vous avez " + this.getMoney() + " or (ecrivez oui ou non)");
        return (scanner.nextLine().toLowerCase().equals("oui"));
    }

    //***************************************************Les actions des pnj presents**********************************
    /**
     *
     * @param personne, the list of NoPlayer with who the player can speak
     * @param choix, the choice make by the player
     * @return the noPlayer select or null (if the choice is wrong)
     */
    private NoPlayer goodChoice(List<NoPlayer> personne, String choix){
        for (NoPlayer noPlayer: personne) {
            if (noPlayer.getName().toLowerCase().equals(choix.toLowerCase())) {
                return noPlayer;
            }
        }
        return null;
    }

    /**
     * Can speak no player
     * @param personne, the list of noPlayer with who the player can speak
     */
    private void speak(List<NoPlayer> personne){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez ecrire le prenom de la personne a qui vous souhaitez-parlez : ");
        NoPlayer noPlayer = this.goodChoice(personne, scanner.nextLine());

        if (noPlayer != null) noPlayer.effect(this);
        else System.out.println("Vous ne parlez a personne");
    }

    /**
     * The player look if on his cell, there is somebody or something
     */
    public void look(){
        List<NoPlayer> areNoPlayer = this.getCell().getAreNoPlayer();
        if  (areNoPlayer.isEmpty()) System.out.println("Il ne semble y avoir personne ici");
        else {
            String personne = "Sur cette case il y a : ";
            for(int i = 0; i < areNoPlayer.size(); i ++){
                personne+= areNoPlayer.get(i).getName() + "   ";
            }
            System.out.println(personne);
            speak(areNoPlayer);
        }
    }

    //***************************************************Le deplacement du joueur**************************************

    /**
     * It takes a string and returns an Orientation if the string matches the input of any Orientation
     * 
     * @param choice The input from the user.
     * @return The Orientation enum that matches the input.
     */
    public Orientation inputToOrientation(String choice)
    {
        for(Orientation orient : Orientation.values()) {
            if(orient.getInput().matcher(choice).matches()) return orient;
        }
        return null;
    }

    @Override
    public void move() {
        move(null);
    }

    /**
     * The function asks the user to enter a direction, then checks if the direction is valid, and if
     * it is, moves the player to the new cell
     * 
     * @param choix the user's input
     */
    public void move(String choix)
    {
        Scanner scanner = new Scanner(System.in);
        if(this.canMove) {
            try {
                if(choix == null) {
                    System.out.println("Veuillez entrer l'une des destinations ci-dessous :");
                    String lsOrient = "";
                    for(Orientation orient : this.availableMovement())
                        lsOrient += orient.getName() + " ";
                    System.out.println(lsOrient);
                    choix = scanner.nextLine();
                }
                Orientation dest = this.inputToOrientation(choix);
                if(dest != null) {
                    if (this.goodDestination(dest)) {
                        this.getCell().setPlayer();
                        this.setCell(this.futurCell(dest));
                        this.getCell().setPlayer();
                    }else System.out.println("Vous avez rentre une mauvaise destination");
                }
                else {
                    System.out.println("Nom de destination incompris");
                }
            } catch (Exception e) {
                System.out.println("On vous a demander de taper une des destinations indiquees");
            }
        } else System.out.println("Quelqu'un vous empeche de bouger ! ");
    }




    //***************************************************Interaction avec son sac**************************************

    /**
     * use an item
     * @param o, the item that the player want to use
     */
    private void effectObject(Item o) {
        if (o != null) {
            o.effect(this);
        }else {
            System.out.println("rater vous n'avez pas cet objet");
        }
    }

    /**
     * Verify if the player want use an item
     */
    private void useObject() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Souhaitez-vous utiliser un objet ? (repondre oui ou non)");

        if (scanner.nextLine().toLowerCase().equals("oui")) {
            Item o = chooseObject(this.getInventory());
            this.effectObject(o);
        }
    }

    /**
     * the player look his bag and we ask if we use an item
     */
    public void useBag() {
        System.out.println("Vous avez " + this.getMoney() + " or");
        
        if (this.getInventory().isEmpty()) {
            System.out.println("Votre sac est vide");
        }else {
            System.out.println(this.getInventory().toString());
            this.useObject();
        }
    }

   /**
    * It takes all the items in the cell and puts them in the player's inventory
    * it also plays the item effect if its onPickUpEffect value is true
    */
    public void pick() {
        List<Item> items = this.getCell().getItemList();
        if  (items.isEmpty()) System.out.println("Il n'y a aucun objet ici");
        else {
            String listeItem = "Sur cette case il y a : \n";
            for(int i = 0; i < items.size(); i ++){
                listeItem+= " - " + items.get(i).getName() + " | " + items.get(i).getText() + "\n";
                this.getInventory().addItem(items.get(i));
                if(items.get(i).getOnPickUpEfect()) items.get(i).effect(this);
            }
            System.out.println(listeItem);
            System.out.println("Vous avez tous pris");
            List<Item> emptyList = new ArrayList<>();
            this.getCell().setItemList(emptyList);
        }
    }

    // **************************************************Permet de l'utiliser en jeux**********************************

    public static final Pattern moveStr = Pattern.compile("m(ove)?|b(ouger)?", Pattern.CASE_INSENSITIVE);
    public static final Pattern lookStr = Pattern.compile("l(ook)?|o(bserver)?", Pattern.CASE_INSENSITIVE);
    public static final Pattern bagStr  = Pattern.compile("bag|s(ac)?", Pattern.CASE_INSENSITIVE);
    public static final Pattern pickStr = Pattern.compile("p(ick)?|p(rendre)?", Pattern.CASE_INSENSITIVE);


    /**
     * Can to applicate the action select for the player
     * @param action the string who representing the action chosing by player
     */
    public void action(String action) {
        String[] actionSplit = action.split(" ");
        if (this.moveStr.matcher(actionSplit[0]).matches()) {
            if(actionSplit.length < 2) this.move();
            else this.move(actionSplit[1]);
        }else if(this.lookStr.matcher(actionSplit[0]).matches()) {
            this.look();
        }else if (this.bagStr.matcher(actionSplit[0]).matches()) {
            this.useBag();
        }else if (this.pickStr.matcher(actionSplit[0]).matches()) {
            this.pick();
        }
    }

}
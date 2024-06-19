/**
 * @Author Steeven
 * @Date 07/03
 */
package entity.character;

import entity.*;
import entity.item.*;
import plateau.*;

import java.util.*;

public class ShopKeeper extends NoPlayer{
    private Inventory shop;

    public ShopKeeper(String name, Cell cell, String text, Inventory i) {
        super(name, cell, text);
        this.shop = i;
    }

    public ShopKeeper(String name, Cell cell, String text) {
        super(name, cell, text);
        this.shop = new Inventory();
    }

    public Inventory getShop(){ return this.shop; }

    /**
     * a good salesperson makes a price margin
     * @param item the item you want incress price
     * @return int , the price of item for shopper
     */
    public int incressPrice(Item item){ return item.getPrice()+1; }

    /**
     * if you have sell one item , this value => -1
     * @param item
     */
    public void haveSell(Item item){ this.shop.dropItem(item); }

    /**
     * to add the item on the shop
     * @param item
     */
    public void addItemForSell(Item item){ this.shop.addItem(item); }

    /**
     * Can make an action (buy or sell)
     * @param p, the current player
     * @param choix, the choice buy or sell make by the player
     */
    private void actionPlayer(Player p, String choix) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Souhaitey-vous " + choix + " ? (repondez par oui ou non)");
        String decision = scanner.nextLine().toLowerCase();
        if (decision.equals("oui") && choix.equals("vendre")) this.sell(p);
        else if (decision.equals("oui") && choix.equals("acheter")) this.buy(p);
        else System.out.println("Oh d'accord, a la prochaine fois");

    }

    /**
     * the action when the player can buy or sell
     * @param p, the player
     */
    private void actionShopOrSell(Player p) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Souhaitez vous acheter ou vendre ? (veuillez taper acheter ou vendre)");
        String choix = scanner.nextLine();
        if (choix.toLowerCase().equals("acheter")) this.actionPlayer(p, "acheter");
        else if (choix.toLowerCase().equals("vendre")) this.actionPlayer(p, "vendre");
    }

    /**
     * Can player to interact with shopkeeper, buy or sell or nothing
     * @param p, the player
     */
    @Override
    public void effect(Player p) {
        System.out.println("Bonjour je suis " + this.getName());

        if (this.getShop().isEmpty() && p.getInventory().isEmpty()) System.out.println("Il semblerait que nos 2 sacs soient vide");
        else if (this.getShop().isEmpty()) this.actionPlayer(p, "vendre");
        else if (p.getInventory().isEmpty()) this.actionPlayer(p, "acheter");
        else this.actionShopOrSell(p);
        System.out.println("A la prochaine fois");

    }

    /**
     * The function who sell an object to a shopkeeper by player
     * @param player, the current player
     */
    public void sell(Player player) {
        Item o = player.chooseObject(player.getInventory());
        if (player.getInventory().isEmpty()) System.out.println("Vous ne pouvez pas vendre quelquechose quand vous n'avez rien a vendre");
        else if (o == null) System.out.println("Il faut me donner le nom de ce que vous souhaitez vendre");
        else if (player.confirmation(o, "vendre", this)) {
            player.setMoney(o.getPrice());
            player.getInventory().dropItem(o);
            this.addItemForSell(o);
            System.out.println("Merci beaucoup pour ces bonnes affaires !");
        }
    }

    /**
     * The function who add an Item to the Player's inventory if he can buy this Item
     * @param player, the current player
     */
    public void buy(Player player) {
        Item o = player.chooseObject(this.getShop());
        if (o == null) System.out.println("Il faut me donner le nom de ce que vous souhautez acheter");
        else if (! player.canBuy(o.getPrice())) System.out.println("Je ne fais pas de rabait");
        else if (player.confirmation(o, "acheter", this)) {
            player.setMoney(-this.incressPrice(o));
            player.bag.addItem(o);
            this.getShop().dropItem(o);
            System.out.println("Merci beaucoup pour ces bonnes affaires ! ");
        }
    }
}
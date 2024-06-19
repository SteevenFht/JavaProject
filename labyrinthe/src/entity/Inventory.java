/**
 * @Author Steeven, Madeline
 * A class Inventory who get the inventory of player or other character
 */
package entity;

import java.util.ArrayList;
import java.util.HashMap;
import entity.item.Item;

public class Inventory{
    private HashMap<Item, Integer> inventory;

    public Inventory(){
        inventory = new HashMap<>();
    }

    public HashMap<Item, Integer> getInventory() {
        return inventory;
    }

    /**
     * for add an item in inventory
     * @param item the item you want add
     */
    public void addItem(Item item){
        if (! this.inventory.containsKey(item)){
            this.inventory.put(item , 0);
        }
        int newValue = this.inventory.get(item)+1;
        this.inventory.put(item,newValue);
    }

    /**
     * for drop an item of the inventory
     * @param item the item you want drop
     */
    public void dropItem(Item item){
        if (this.inventory.get(item)>1){
            int newValue = this.inventory.get(item)-1;
            this.inventory.put(item,newValue);
        }else{
        this.inventory.remove(item);
        }
    }

    /**
     * give the list of all item in the current inventory
     * @return an array list of item
     */
    public ArrayList<Item> allItem(){
        ArrayList<Item> itemavailable =  new ArrayList<>();
        for (Item key : this.inventory.keySet()) {
            itemavailable.add(key);
        }
        return  itemavailable;
    }

    public boolean isEmpty() {
        return this.getInventory().isEmpty();
    }



    /**
     *
     * @return the string of name contains in bag
     */
    public String toString() {
        String save = "[";
        for (Item unItem : this.getInventory().keySet()) {
            save += unItem.getName() + ", ";
        }
        save = save.substring(0, save.length()-2) + "]";
        return save;
    }



}
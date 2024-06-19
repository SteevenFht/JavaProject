package entity.item;
import entity.character.Player;
import java.util.Random;

public class Gold extends Item {
    Random r = new Random();
    private int value = 100 + (r.nextInt(51));
    public Gold()
    {
        super("Or", "tas d'or", 0);
        setText("tas d'or qui vaut " + this.value);
        super.onPickUpEfect = true;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * It adds money to the player.
     * @param p The player who is receiving the money.
     */
    public void effect(Player p)
    {
        p.setMoney(this.value);
        p.getInventory().dropItem(this);
    }
}

package display;

import entity.character.Player;
import plateau.Labyrinthe;

public class Title {
    private final static String LOGO =
            "  _               _               ____                  \n" +
                    " | |       __ _  | |__    _   _  | __ )   _   _    __ _ \n" +
                    " | |      / _` | | '_ \\  | | | | |  _ \\  | | | |  / _` |\n" +
                    " | |___  | (_| | | |_) | | |_| | | |_) | | |_| | | (_| |\n" +
                    " |_____|  \\__,_| |_.__/   \\__, | |____/   \\__,_|  \\__, |\n" +
                    "                          |___/                   |___/ ";

    private final static String DEATH =
            " _______  _______  _______ _________\n" +
                    "(       )(  ___  )(  ____ )\\__   __/\n" +
                    "| () () || (   ) || (    )|   ) (   \n" +
                    "| || || || |   | || (____)|   | |   \n" +
                    "| |(_)| || |   | ||     __)   | |   \n" +
                    "| |   | || |   | || (\\ (      | |   \n" +
                    "| )   ( || (___) || ) \\ \\__   | |   \n" +
                    "|/     \\|(_______)|/   \\__/   )_(   ";

    private final static String WIN =
            " __      __  _          _             _                   _ \n" +
                    " \\ \\    / / (_)        | |           (_)                 | |\n" +
                    "  \\ \\  / /   _    ___  | |_    ___    _   _ __    ___    | |\n" +
                    "   \\ \\/ /   | |  / __| | __|  / _ \\  | | | '__|  / _ \\   | |\n" +
                    "    \\  /    | | | (__  | |_  | (_) | | | | |    |  __/   |_|\n" +
                    "     \\/     |_|  \\___|  \\__|  \\___/  |_| |_|     \\___|   (_)";

    public static String getLogo() {
        return LOGO;
    }

    /**
     * give the condition for the end of game
     * @param laby, the currenet labyrinthe
     * @param player, the current player
     * @return if the win condition is ok
     */
    public static boolean win(Player player, Labyrinthe laby) {
        int choix = (laby.getWinCell().getAbscisse() + laby.getWinCell().getOrdonnee()) % 2;
        if (choix == 0) {
            if ((player.getCell().isWin() && player.getMoney() >= 500)) System.out.println(WIN);
            return (player.getCell().isWin() && player.getMoney() >= 500);
        }else {
            if (player.getCell().isWin() && player.getHP() >= 90) System.out.println(WIN);
            return (player.getCell().isWin() && player.getHP() >= 90);
        }
    }

    /**
     *
     * @param player, the current player
     * @return true il the player is dead and an display
     */
    public static boolean playerDead(Player player) {
        if (player.isDead()) System.out.println(DEATH);
        return player.isDead();
    }
}

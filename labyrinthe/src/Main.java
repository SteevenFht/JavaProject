/**
 * The main class for game
 * @author Madeline Carpentier
 * @Date 24/04/2022
 */

import display.*;
import entity.*;
import entity.character.*;
import entity.item.*;
import entity.util.*;
import org.json.*;
import plateau.*;
import plateau.utilities.*;
import java.util.regex.*;

import java.util.*;

public class Main {
    public static boolean debug = false;
    /**
     * Give the labyrinthe for play
     * @return the labyrinthe for play
     */
    private static Labyrinthe createLabyrinthe(Integer algorithm) {
        Generator generator;

        System.out.println(Title.getLogo()); //Affichage logo
        System.out.println("Bienvenue dans notre jeux, nous allons vous guider pour parametrer votre partie.");
        //On récupére le nombre de ligne et de colonne, pour crée un labyrinthe
        int ligne = Menu.choiceLine();
        int colonne = Menu.choiceColumn();
        Labyrinthe labyrinthe = new Labyrinthe(ligne, colonne);

        //On décide de la manière de crée le labyrinte en fonction du choix
        int selection = Objects.isNull(algorithm)?Menu.choiceLabyrinthe():algorithm;
        if (selection==2) {
            System.out.println("Vous avez choisi une construction de type Sidewinder");
            generator = new Sidewinder(labyrinthe);
        }else {
            System.out.println("Vous avez choisi une construction de type Recursive Bactracker");
            generator = new RecursiveBacktracker(labyrinthe);
        }
        return  labyrinthe;
    }


    /**
     *
     * @param labyrinthe, the current labyrinthe
     * @return, the player who play in labyrinthe
     */
    private static Player createPlayer(Labyrinthe labyrinthe) {
        String name = Menu.choiceNamePlayer();
        return new Player(name, labyrinthe.getRandomCell());
    }

    /**
     *
     * @param labyrinthe, the current labyrinthe
     * @param pnj, the liste of noPlayer in labyrinthe
     * @return, the list pnj add sphinx and minotor if is possible
     */
    private static List<NoPlayer> createUniquePNJ(Labyrinthe labyrinthe, List<NoPlayer> pnj, int nbTotalCase) {
        if (nbTotalCase > 8) {
            pnj.add(new Sphinx("Sphinx", labyrinthe.getRandomCell(), "Un etre mystique adorant les questions"));
            pnj.add(new Minotaur("Minotaure", labyrinthe.getRandomCell(), "Minos !!!"));
        }
        return pnj;
    }

    private static List<NoPlayer> createMad(Labyrinthe labyrinthe, List<NoPlayer> pnj, int nbTotalCase) {
        JsonParser json = new JsonParser("ressources/name.json");
        int nbPnj = (nbTotalCase*20)/100; //faire un pourcentage
        JSONArray nameMad = json.getJSONArrayFromData("mad");
        JSONArray nameWise = json.getJSONArrayFromData("madWise");
        for (int i = 0 ; i < nbPnj ; i++) {
            json.setData(nameWise.getJSONObject(i%21));
            pnj.add(new MadWise(json.getData().getString("name"), labyrinthe.getRandomCell(), "Aime les monologues"));
            json.setData(nameMad.getJSONObject(i%18));
            pnj.add(new Mad(json.getData().getString("name"), labyrinthe.getRandomCell(), "Une etrange personne..."));
        }
        return pnj;
    }

    /**
     * Create ShopKeeper on labyrinthe
     * @param labyrinthe, the current labyrinthe
     * @param pnj, the list of noPlayer
     * @param nbShopKeeper, number of ShopKeeper on Labyrinthe
     * @return The list of No player with the shopKeeper
     */
    private static List<NoPlayer> createShopKeeper(Labyrinthe labyrinthe, List<NoPlayer> pnj, int nbShopKeeper) {
        JsonParser json = new JsonParser("ressources/name.json");
        Random r = new Random();
        JSONArray nameList = json.getJSONArrayFromData("shopkeeper");
        for (int i = 0 ; i < nbShopKeeper ; i++) {
            json.setData(nameList.getJSONObject(i%28));
            int unInventaire = r.nextInt(2);
            if (unInventaire == 0) {
                Inventory inventaire = new Inventory();
                inventaire.addItem(new PotionLife());
                inventaire.addItem(new Scroll());
                pnj.add(new ShopKeeper(json.getData().getString("name"), labyrinthe.getRandomCell(), "un marchand qui vend des objets", inventaire));
            }else {
                pnj.add(new ShopKeeper(json.getData().getString("name"), labyrinthe.getRandomCell(), "un marchand qui vend des objets"));
            }
        }
        return pnj;
    }

    /**
     * Create the no player in labyrinthe
     * @param labyrinthe, the current Labyrinthe
     * @return the List of no Player in labyrinthe
     */
    private static List<NoPlayer> createCharacter(Labyrinthe labyrinthe) {
        List<NoPlayer> pnj = new ArrayList<NoPlayer>();
        int nbTotalCase = labyrinthe.nbTotalCell();
        int nbShopKeeper = (int)(nbTotalCase*0.05);

        pnj = createUniquePNJ(labyrinthe, pnj, nbTotalCase);
        pnj = createShopKeeper(labyrinthe, pnj, nbShopKeeper);
        pnj = createMad(labyrinthe, pnj, nbTotalCase);

        return pnj;
    }

    /**
     * Create Item
     * @param labyrinthe, the current labyrinthe
     * @param nbItem, the number of item we would create
     * @param type, the type of item we will create
     */
    private static void createItems(Labyrinthe labyrinthe, int nbItem, int type) {
        for (int i = 0 ; i < nbItem ; i++) {
            Cell itemCell = labyrinthe.getRandomCell();
            List<Item> newItemList = itemCell.getItemList();
            if (type == 0) {
                newItemList.add(new Scroll());
                itemCell.setItemList(newItemList);
                if(debug) System.out.println("Scroll en x: " + itemCell.getAbscisse() + ", y: " + itemCell.getOrdonnee());
            }else if (type == 1) {
                newItemList.add(new PotionLife());
                itemCell.setItemList(newItemList);
                if(debug) System.out.println("Potion en x: " + itemCell.getAbscisse() + ", y: " + itemCell.getOrdonnee());
            }else {
                newItemList.add(new Gold());
                itemCell.setItemList(newItemList);
                if(debug) System.out.println("sac d'or en x: " + itemCell.getAbscisse() + ", y: " + itemCell.getOrdonnee());
            }

        }
    }

    /**
     * Give the quantity of item and create
     * @param labyrinthe, the labyrinthe
     */
    private static void createAllItems(Labyrinthe labyrinthe) {
        int nbTotalCase = labyrinthe.nbTotalCell();

        int nbScroll = Math.max((int)(nbTotalCase*0.05), 2);
        createItems(labyrinthe, nbScroll, 0);

        int nbPotion = (int)(nbTotalCase*0.01);
        createItems(labyrinthe, nbPotion, 1);

        int nbGoldBag = Math.max((int)(nbTotalCase*0.02), 1);
        createItems(labyrinthe, nbGoldBag, 2);
    }
    
    public static void main(String[] args) {
        Integer algorithm = null;
        for(String arg: args)
        {
            switch (arg){
                case "1":
                    algorithm = 1;
                    break;
                case "2":
                    algorithm = 2;
                    break;
                case "-d":
                case "-debug":
                    Main.debug = true;
                    break;
                default:
                    System.out.println("wrong argument, use:\n" +
                            "- 1: Recursive Backtracker algorithm\n" +
                            "- 2: Sidewinder algorithm\n" +
                            "- debug / -d: to show pnj and objects positions");
                    System.exit(0);
            }
        }

        Labyrinthe labyrinthe = createLabyrinthe(algorithm);
        Player player = createPlayer(labyrinthe);
        List<NoPlayer> pnj = createCharacter(labyrinthe);
        createAllItems(labyrinthe);


        DisplayGameBoard.displayGameBoard(labyrinthe);
        Scanner scanner = new Scanner(System.in);
        String choix = "";
        do {

            player.getCell().pnjEffectCell(player);
            if (player.getHP() <= 0) System.out.println("Ce sont vos dernière volonte");

            for (int i = 0 ; i < pnj.size() ; i++) {
                pnj.get(i).move();
                if(debug) System.out.println(pnj.get(i).getName() + " voici la position en x : " + pnj.get(i).getPositionX() + " voici la position en y : " + pnj.get(i).getPositionY());
            }

            System.out.println("Vous avez : " + player.getHP() + " points de vie \n");
            System.out.println("Voici ce que vous pouvez faire : ");
            System.out.println("arreter  bouger observer sac prendre (veuillez taper votre reponse pour l'executer)");
            choix = scanner.nextLine();
            System.out.println("Voici le choix du joueur " + choix);
            player.action(choix.toLowerCase());

            System.out.println("Voici la position du joueur en x :" + player.getPositionX() + " et la position en y : " + player.getPositionY());

            DisplayGameBoard.displayGameBoard(labyrinthe);

        }while (! choix.toLowerCase().equals("arreter") && !(Title.win(player, labyrinthe)) && !(Title.playerDead(player)));
    }
}

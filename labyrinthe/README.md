# l2s4-projet-2022

# Equipe

- Steeven FLAHAUT
- Antoine MAZURE
- Madeline CARPENTIER
- Massimiliano RIGHETTI

# Sujet

[Le sujet 2022](https://www.fil.univ-lille1.fr/portail/index.php?dipl=L&sem=S4&ue=Projet&label=Documents)

# Livrables

## Livrable 1

### Atteinte des objectifs

Nous avons, au bout des quatres 1ères semaines, réussi à coder le Labyrinthe avec ses différents algorithmes. Il est fonctionnel, nous pouvons même choisir un nombre de lignes et de colonnes ainsi que quels algorithmes utiliser. Nous avons ajouter un Make Main ainsi qu'une branche affichage où se trouvent les fichiers. 

Algo Sidewinder : 

Il s'applique ligne par ligne, dès qu'on est sur une case on l'ajoute dans une liste, puis on lance 1d2 si on tombe sur 1 on voit si on peut enlever le mur est si oui on enlève. Sinon on prend une case sur laquelle on est déjà passée au hasard, on voit si on peut enlever son mur sud si c'est le cas on enlève sinon on passe à la case suivante. Chaque fois qu'un mur sud est supprimé et que la ligne change, la liste est vidée.

Algo RecursiveBacktracker : 

sélectionne une case au hasard et vérifie le chemin disponible, brise un mur au hasard et accéde à la prochaine case
tant que le l'algorithme n'a pas encore visite tout le labyrinthe, continuez comme ça, si une case n'a pas de chemin disponible et que l'algorithme n'est pas terminé, aller à la case d'avant 

### Difficultés restant à résoudre

Les algorithmes à faire et à résoudre, les tests. La génération des NoPlayers

## Livrable 2

### Atteinte des objectifs

Au bout de la semaine 5 et 6, nous avons reussi à coder les personnages, les entités ... Utiliser Make linExec ou winExec selon le système d'exploitation pour lancer le projet. Les no players sont générés sur le plateau mais on ne les voient pas. 

### Difficultés restant à résoudre

Découverte du JSON, difficulté à la création de l'UML. L'interaction avec tout ce qui est présent sur le labyrinthe

## Livrable 3

### Atteinte des objectifs

Au bout des 9 semaines, les actions nécessaires au bon deroulement de notre jeu fonctionnaient. L'ajout d'un certains nombres de NoPlayers suivant la taille du labyrinthe était aussi finalisé permettant ainsi de pouvoir tester toutes les actions implementées.

### Difficultés restant à résoudre

L'ajout d'une case de fin aléatoire. La génération de l'indice du Scroll suivant la case de fin. Le bon fonctionnement de tout le projet, que tout soiut harmonisé pour fonctionner ensemble.

## Livrable 4

### Atteinte des objectifs

La fin du jeu est codé, chaque NoPlayer a une fonction bien particulière,  ils se déplacent et sont générés de manières aléatoires sur le Plateau. Le joueur peut intéragir avec chaque NoPlayer et chaque case sur lesquels quelque chose s'y trouve. Suivant la taille du labyrinthe, le nombre de NoPlayer change. La génération d'une case de fin aléatoire ainsi que des Scroll est parfaitement fonctionnelles. Chaque déplacement se fait maintenant avec une seule lettre (Par exemple pour bouger au nord, il faudra taper b n dans le terminal pour se déplacer).

Pour lancer le projet, en 1er il faut faire un Make doc, puis un Make cls. A partir de ce moment, vous avez 2 choix : 
- Soit faire Make linExec ou winExec (ou lindebug/windebug pour voir où se cachent les PNJs et les objets)
- Soit faire Make jar, puis Make jeu 
Il vous suffira ensuite de suivre les instructions à l'écran pour jouer

### Difficultés restant à résoudre

On aurait bien voulu rajouter quelques petites choses pour améliorer le projet, comme le fait d'avancer de plus d'une case dans une direction, si la génération du labyrinthe le permet. Ajouter d'autres indices, et d'autres enigmes. Ajout de couleurs pour mieux comprendre ce qu'il se passe dans le terminal.

# Journal de bord

## Semaine 1
UML, codage de Orientation, Case, Labyrinthe et les algorithmes d'exploration exaustive et de Sidewinder, ajout librairie des tests JUnit4 et premier test pour voir si ca marche. L'algorithme exploration exaustive fonctionne. Nouvelle branche affichage où tous les fichiers s'y trouvent (Sauf le readme).

## Semaine 2
Finition de l'uml, Sidewinder fonctionnel, renommage et mise dans des package, début et fin affichage, Exploration Exaustive moche

## Semaine 3
Ajout du design pattern strategy, plus debut des tests. Ajout d'un Make Main pour lancer le labyrinthe (commande à taper). Exploration Exaustive jolie

## Semaine 4
Ajout de l'affichage et du scanner plus quelque debug d'affichage. Fin des tests, ajout d'un titre au labyrinthe depuis le terminal.

## Semaine 5
Debut UML personnage. Repartition du travail, début des tests et création des classes/interface associés au personnage. Début enigma(sphynx). 

## Semaine 6
Fin changement UML, JSON Scroll, changement des classes (Entity), coder les classes, Methode dans Player, Inventory ... Modif affichage, changement logo  personnage, Commencement jalon 3. Scanners pour selectionner les objets, pour le deplacement, pour le choix du joueur. Tous les no Players sont sur le plateau.

## Semaine 7
Le personnage se déplace. Fin classe JSON. Effets sur les cases et changement du nom de la classe character. Action player 3/5 faites. Modification déplacemet du Player.

## Semaine 8
Création du JSON Parser, permettant notamment de se servir des fichiers JSON. Les déplacements fonctionnent maintenant avec les 2 algorithmes puis ajout d'une méthode permettant de "jouer" l'effet du NoPlayer se situant sur la même case que le Player. Ajout d'une méthode Look, permettant au Player de regarder ce qu'il se situe sur sa case. 

## Semaine 9
Le player peut désormais vendre ses objets et en acheter auprès d'un ShopKeeper. Creation de la classe Sphinx et des méthodes permettant au Sphinx de poser une énigme au Player tout en l'empechant de bouger. Methode CanMove permettant au Player de bouger si aucun NoPlayer l'en empeche puis début de la génération des NoPlayers sur le Labyrinthe.

## Semaine 10
Discussion de la suite du Projet et de l'organisation de chacun pour la fin. Quelques avancées dans le code notamment au niveau de l'optimisations du code de certaines Classes. L'implémentation de plusieurs NoPlayer de même Classes dans le labyrinthe fonctionne.

## Semaine 11
Renommage de fichier pour faciliter les imports et la lecture. Achat et vente d'objet fini. Pas mal de tris au niveau de Player et d'autres Classes. Ajout de la fonction pick, qui permet au joueur de Prendre un objet sur le sol et de l'ajouter à son sac si un objet est disponible sur la case. Début de fou et du savant fou, ajout de leur JSON et de leur classe puis ajout dans le main. Début de la conception pour la fin du jeu avec un ajout au niveau de l'affichage, desormais les cases sont affichées avec leur indice pour faciliter le déplacement dans le jeu. Ajout de nouveaux textes dans les Scroll.

## Semaine 12
Ajout de Logo pour la victoire et la Mort. Modification des Scrolls pour que leur indice renvoie une réponse par rapport au centre du labyrinthe. Le savant fou traduit désormais les parchemins. Les déplacements se font de manière plus propre (Avec notamment l'appui sur une seule lettre pour chaque action différente). Ajout d'incides pour le Scroll ainsi qu'une modification de la fonction permettant de traduire le Parchemin. Ajout d'une seconde condition de fin

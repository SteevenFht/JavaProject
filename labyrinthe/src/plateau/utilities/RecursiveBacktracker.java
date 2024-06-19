/**
 * @Author
 */
package plateau.utilities;

import java.util.Random;
import java.util.ArrayList;
import java.util.Stack;

import plateau.*;

public class RecursiveBacktracker extends Generator {

    private int mazeSize;

    public RecursiveBacktracker(Labyrinthe laby) {
        super(laby);
    }
    
    /**
     * Allows to initialize the maze with the recursive backtracker method:
     * 
     * select a random cell and check for it's available path
     * break a random wall and get to that next cell
     * while the maze still has some unvisited path keep doing this
     * if a cell has no available path and the algorithm is not over, go to the cell just before
     */
    public void init() {
        mazeSize = super.getLabyrinthe().getNBLigne() * super.getLabyrinthe().getNBColonne();
        boolean[][] isVisited = initIsVisitedCell();
        
        Cell initialCell = getRandomInitialCell(isVisited);

        Stack<Cell> pathOfcell = new Stack<>();
        pathOfcell.push(initialCell);

        generatePath(pathOfcell, isVisited);
    }

    /**
     * return an array of boolean values indicating if the algorithm already went there
     * @return an array of boolean values indicating if the algorithm already went there
     */
    //L'initialisation passe dans Labyrinthe
    public boolean[][] initIsVisitedCell() {
        boolean[][] visited = new boolean[super.getLabyrinthe().getNBLigne()][super.getLabyrinthe().getNBColonne()];
        for (int i = 0; i < super.getLabyrinthe().getNBLigne(); i++) {
            for (int j = 0; j < super.getLabyrinthe().getNBColonne(); j++) {
                visited[i][j] = false;
            }
        }
        return visited;
    }

    /**
     * return a random cell of the board
     * @param visited
     * @return the initial cell selected
     */
    private Cell getRandomInitialCell(boolean[][] visited) {
        Random r = new Random();
        int valueY = r.nextInt(super.getLabyrinthe().getNBLigne()); // generate a random number between [0,this.getNBLigne()[
        int valueX = r.nextInt(super.getLabyrinthe().getNBColonne());
        visited[valueY][valueX] = true;
        Cell initialcell = this.getLabyrinthe().getCell(valueY, valueX);
        return initialcell;
    }

    /**
     * save a stack of the different cell the algorithm went on
     * @param pathOfcell a stack of the different cell the algorithm went on
     * @param visited the array of cell indicating of the algorithm already went there
     */
    public void generatePath(Stack<Cell> pathOfcell, boolean[][] visited) {
        boolean finished = false;
        int nbCellVisited = 1; //the initial cell is already counted
        while (!finished) {
            Cell activeCell = pathOfcell.peek();
            ArrayList<Orientation> availableOrientation = availableOrientation(activeCell, visited);
            if (availableOrientation.size() > 0) {
                Orientation nextOrientation = getRandomOrientation(availableOrientation);

                breakWallForCell(activeCell, nextOrientation);
                activeCell = getNeighborCell(activeCell, nextOrientation);

                nbCellVisited = addNewVisitedCell(pathOfcell, visited, nbCellVisited, activeCell);
            }
            else {
                finished = (nbCellVisited == this.mazeSize);
                if (!finished)
                    pathOfcell.pop();
            }
        }
    }

    /**
     * get the next cell in the list of visited cell by the algorithm
     * @param pathOfcell the path the algorithm used
     * @param visited the array of visited cell
     * @param nbCellVisited number of cells visited by the algorithm
     * @param activeCell the cell curently used by the algorithm
     * @return the new number of visited cell
     */
    public int addNewVisitedCell(Stack<Cell> pathOfcell, boolean[][] visited, int nbCellVisited, Cell activeCell) {
        int newX = activeCell.getAbscisse();
        int newY = activeCell.getOrdonnee();
        visited[newY][newX] = true;
        pathOfcell.push(this.getLabyrinthe().getCell(newY, newX));
        return ++nbCellVisited;
    }

    /**
     * check for a given cell the orientation you can use
     * @param cell the cell you want to check
     * @param visited
     * @return an  Arraylist of orientation where you can break the wall
     */
    public ArrayList<Orientation> availableOrientation(Cell cell, boolean[][] visited) {
        int x = cell.getAbscisse();
        int y = cell.getOrdonnee();
        ArrayList<Orientation> availablePath = new ArrayList<>();

        if (x > 0 && !visited[y][x - 1])
            availablePath.add(Orientation.West);
        if (y > 0 && !visited[y - 1][x])
            availablePath.add(Orientation.North);
        if (x < super.getLabyrinthe().getNBColonne()-1 && !visited[y][x + 1])
            availablePath.add(Orientation.East);
        if (y < super.getLabyrinthe().getNBLigne()-1 && !visited[y + 1][x])
            availablePath.add(Orientation.South);

        return availablePath;
    }

    /**
     * return a random orientation from the list of available orientation
     * @param availableOrientation list of available orientation
     * @return a random orientation from the list of available orientation
     */
    public Orientation getRandomOrientation(ArrayList<Orientation> availableOrientation)
    {
        Random r = new Random();
        return availableOrientation.get(r.nextInt(availableOrientation.size()));
    }

    /**
     * break the wall of the cell given in parameter
     * @param cell cell to break the wall
     * @param orient orientation of that wall
     */
    public void breakWallForCell(Cell cell, Orientation orient)
    {
        cell.setWall(orient, false);
        Cell newCell = getNeighborCell(cell, orient);
        newCell.setWall(orient.getOpposite(), false);
    }

    /**
     * return the cell's neighbor at the correct orientation
     * @param cell the cell you want to find a neighbor
     * @param orient the orientation on where to look
     * @return the cell's neighbor at the correct orientation
     */
    public Cell getNeighborCell(Cell cell, Orientation orient)
    {
        int x = cell.getAbscisse();
        int y = cell.getOrdonnee();
        if(orient == Orientation.West && x > 0)
            x -=1;
        else if(orient == Orientation.North && y > 0)
            y -=1 ;   
        else if(orient == Orientation.East && x < super.getLabyrinthe().getNBColonne()-1)
            x +=1;
        else if(orient == Orientation.South && y < super.getLabyrinthe().getNBLigne()-1)
            y +=1;
        Cell newCell = this.getLabyrinthe().getCell(y, x);
        return newCell;
    }
}
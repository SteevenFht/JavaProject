package plateau.utilities;

import org.junit.Before;
import org.junit.Test;

import plateau.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Stack;

public class RecursiveBacktrackerTest {
    public Labyrinthe l;
    public RecursiveBacktracker g;
    public boolean[][] v, v2;
    public Stack<Cell> s;
    public int nbCellV;
    public Cell aCell, bCell, cell, cell1, cell2, cCell;
    public ArrayList<Orientation> pathA ;


    @Before 
    public void initialisation(){
        l = new Labyrinthe(30, 25);
        g = new RecursiveBacktracker(l);

        v = new boolean[30][25];
        v2 = new boolean[30][25];
        for (int i = 0; i < 30;  i++){
            for (int j = 0; j < 25; j++){
                v[i][j] = false;
                v2[i][j] = false;
            }
        }
        aCell = new Cell(10, 5);
        bCell = new Cell(20, 15);
        cell = new Cell(5, 2);
        cCell = new Cell(0, 2);
        nbCellV = 20;

        s = new Stack<Cell>();

        pathA = new ArrayList<>();

        cell1 =l.getCell(2, 2);
        cell2 =l.getCell(2, 1);
        
    }

    @Test
    public void initIsVisitedCellTest(){
        //pourquoi deprecated ?
        assertEquals(this.v , g.initIsVisitedCell());
        this.v[1][1] = true;
        assertNotEquals(this.v , g.initIsVisitedCell());
    }


    @Test 
    public void addNewVisitedCellTest(){
        assertTrue(this.s.empty());
        s.push(aCell);
        assertEquals(this.s.peek(), this.aCell);
        int b = g.addNewVisitedCell(this.s, this.v, this.nbCellV, this.bCell);
        assertEquals(this.nbCellV+1, b);
        v2[15][20] = true;
        assertEquals(v , v2);
    }

    @Test 
    public void availableOrientationTest(){
        pathA = g.availableOrientation(cell, v);
        assertTrue(pathA.contains(Orientation.West));
        assertTrue(pathA.contains(Orientation.North));
        assertTrue(pathA.contains(Orientation.East));
        assertTrue(pathA.contains(Orientation.South));

        pathA = g.availableOrientation(cCell, v);
        assertFalse(pathA.contains(Orientation.West));
        assertTrue(pathA.contains(Orientation.North));
    }

    @Test 
    public void breakWallForCellTest(){
        g.breakWallForCell(cell1, Orientation.North);
        assertFalse(cell1.getNorth());


    }

    @Test 
    public void getNeighborCellTest(){
        assertEquals(g.getNeighborCell(cell1, Orientation.North),l.getCell(1, 2));
        assertEquals(g.getNeighborCell(cell1, Orientation.West), l.getCell(2, 1));
        assertEquals(g.getNeighborCell(cell1, Orientation.East), l.getCell(2, 3));
        assertEquals(g.getNeighborCell(cell1, Orientation.South), l.getCell(3, 2));

    }
}


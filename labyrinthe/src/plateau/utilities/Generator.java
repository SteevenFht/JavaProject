/**
 * A abstract class Labyrinthe for the creation of labyrinthe
 */
package plateau.utilities;

import plateau.Labyrinthe;

public abstract class Generator {
    protected Labyrinthe labyrinthe;

    public Generator(Labyrinthe laby) {
        this.labyrinthe = laby;
        this.init();
    }

    /**
     * The method that will create a perfect maze, thanks to an algorithm
     */
    abstract public void init();

    public Labyrinthe getLabyrinthe() {
        return this.labyrinthe;
    }

    
}

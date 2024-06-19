package entity;

/**
 * @author Antoine Mazure
 * A class Entity for create an entity
 */
public class Entity {
    protected String name;
    
    public Entity(String name){
        this.name = name;
    }

    /**
    * @return the name of the entity
    */
    public String getName() {
        return this.name;
    }

    /**
    * Change the value of the name Entity
    */
    //Un nom peut se changer en cour de route ?
    public void setName(String name) {
        this.name = name;
    }
}
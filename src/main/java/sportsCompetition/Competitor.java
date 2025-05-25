package sportsCompetition;

public class Competitor {
    private String name;
	private int points;

//	-----constructors-----
    public Competitor(String name){
        this.name = name;
    	this.points=0;
    }

//	-----getters/setters-----
    /**
     * This function returns the value of the competitor's points.
     * 
     * @return The points variable is being returned.
     */
    public int getPoints(){
        return this.points;
    }
    
    public void setPoints(int points) {
		this.points = points;
	}

    /**
     * This function adds the parameter p to the points variable.
     * 
     * @param p The amount of points to add to the competitor's score.
     */
    public void addPoints(int p){
        this.points+=p;
    }

	/**
     * This function returns the name of the competitor.
     * 
     * @return The name of the competitor.
     */
    public String getName() {
		return name;
	}
    
    public void setName(String name) {
    	this.name= name;
    }
    
//	-----methods-----
	/**
     * If the object is a competitor, then compare the points and name fields to check if they are equals
     * 
     * @param o The object to compare to.
     * @return true if o is equal to this competitor
     */
    public boolean equals(Object o) {
		if (o instanceof Competitor) {
			Competitor other = (Competitor) o;
			return ((this.points == other.points) && (this.name.equals(other.name)));
		} else {
			return false;
		}
	}
    
    public String toString() {
        return this.getName();
    }

}

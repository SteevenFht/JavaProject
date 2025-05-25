package sportsCompetition;

public enum Results {
	Victory(1),
	Defeat(0);
	
	private final int POINT;
	
//	-----constructors-----
	private Results(int p) {
		POINT=p;
	}

//	-----getters/setters-----
	/**
	 * This function returns the point value of the card
	 * 
	 * @return The point value of the card.
	 */
	public int getPoint() {
		return POINT;
	}
}

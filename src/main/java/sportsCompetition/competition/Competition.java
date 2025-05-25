package sportsCompetition.competition;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import sportsCompetition.Competitor;
import sportsCompetition.Results;
import sportsCompetition.match.Match;
import sportsCompetition.match.MatchRandom;
import sportsCompetition.observer.Observable;
import sportsCompetition.util.MapUtil;
import sportsCompetition.util.TextFormat;

public abstract class Competition extends Observable{
	final int NAME_MAX_SIZE = 17;
	final private List<Competitor> competitors;
	protected Match matchType;
	protected Map<Competitor, Results> lastResults;
	
	
//	-----constructors-----
	public Competition(List<Competitor> competitor){
		this(competitor, new MatchRandom());
	}
	
	public Competition(List<Competitor> competitor, Match match){
		super();
		this.competitors = competitor;
		this.matchType = match;
	}

//	-----abstracts-----
	protected abstract void play(List<Competitor> competitors);
	
	
//	-----methods-----
/**
 * This function returns a map of competitors and their points, sorted by descending points
 * 
 * @return A map of competitors and their points.
 */
	public Map<Competitor, Integer> ranking() {
		Map<Competitor, Integer> rank= new HashMap<Competitor, Integer>();
		for (Competitor key : this.competitors ) {
			rank.put(key, key.getPoints());	
		}
		return MapUtil.sortByDescendingValue(rank);
	}

/**
 * For each entry in the map, add the points from the results to the competitor
 * 
 * @param scores A map of competitors and their results
 */
	public void applyScoreToCompetitors(Map<Competitor, Results> scores) {
		for(Entry<Competitor, Results> entry : scores.entrySet()) {
		    Competitor key = entry.getKey();
		    Results value = entry.getValue();
		    key.addPoints(value.getPoint());
		}
	}

	/**
	 * This function plays the competition and displays the ranking of the competitors
	 */
	public void play() {
		this.play(this.competitors);
		Map<Competitor, Integer> theRank=this.ranking();
		DisplayRanking(theRank);
	}
	
	/**
	 * This function plays a match between two competitors and updates the score of the competitors
	 * based on the result of the match
	 * 
	 * @param c1 The first competitor
	 * @param c2 The competitor that will be playing against c1
	 */
	public void playMatch(Competitor c1, Competitor c2) {
		this.lastResults = this.matchType.versus(c1, c2);
		String whoVersus = c1.getName()+ " vs "+c2.getName() +"-> ";
		for(Map.Entry<Competitor, Results> entry : this.lastResults.entrySet()) {
			   Competitor key = entry.getKey();
			   Results value = entry.getValue();
			   if (value.equals(Results.Victory)) {
				   whoVersus +=key.getName()+" wins!";
			   }
		}
		System.out.println(whoVersus);
		whoVersus="";
		applyScoreToCompetitors(this.lastResults);
		this.matchOver(lastResults);
	}
	
	/**
	 * Reset the score of all competitors in the competition.
	 */
	public void resetScore() {
		resetScore(this.competitors);
	}
	
	/**
	 * Reset the score of all competitors .
	 * 
	 * @param competitor The list of Competitor
	 */
	public void resetScore(List<Competitor> competitors) {
		for(Competitor c : competitors) {
			c.setPoints(0);
		}
	}
	
	
//	-----getters/setters-----
	public List<Competitor> getCompetitors() {
		return this.competitors;
	}
	
	public Match getMatchType() {
		return this.matchType;
	}
	
	/**
	 * Cut the name to a max size of 17
	 * 
	 * @param a name of Competitor
	 * @return the name with a max_lenth = 17
	 */
	private String cutName(String name) {
		return name.substring(0,Math.min(name.length(),this.NAME_MAX_SIZE));
	}
	
	/**
	 * To display the ranking
	 * 
	 * @param Map<Competitor, Integer> the competitor and this number of points
	 */
	public void DisplayRanking(Map<Competitor, Integer> rank) {
		String background = TextFormat.BACKGROUND_CYAN.getFORMAT();
		String tColor = TextFormat.BLACK.getFORMAT();
		System.out.println("\n"+background+tColor+"|----Rank---|----------Name----------|---Score---|"+TextFormat.RESET.getFORMAT());
		int s = 0;
		int place = 1,i=1;
		for(Map.Entry<Competitor, Integer> entry : rank.entrySet()) {
		   	Competitor key = entry.getKey();
		    Integer value = entry.getValue();
		    if (value!=s) {
		    	place=i;
		    }
		    i++;
		    s=value;
		    String textToPrintPlace = background+tColor+"|Rank:  "+place+" ".repeat(4-(place+"").length());
		    int sizeOfName = this.NAME_MAX_SIZE-(this.cutName(key.getName()).length());
		    String textToPrintName ="|Name: "+this.cutName(key.getName())+" ".repeat(sizeOfName+1);
		    String textToPrintPoint ="|Score: "+value+" ".repeat(4-(value+"").length())+"|"+TextFormat.RESET.getFORMAT() ;
		    System.out.println(textToPrintPlace+textToPrintName+textToPrintPoint);
		}
		System.out.println(background+tColor+"|____Rank___|__________Name__________|___Score___|"+TextFormat.RESET.getFORMAT()+"\n");
	}
}

package sportsCompetition.observer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import sportsCompetition.Competitor;
import sportsCompetition.Results;
import sportsCompetition.util.TextFormat;

public class Bookmaker implements MatchObserver {
	protected Map<Competitor, Double> ratings;
	private String name;
	
	public Bookmaker(String name) {
		this.name = name;
		this.ratings = new HashMap<Competitor, Double>();
	}

	@Override
	/** The method that is called when a match is played. It takes the result of the match as a parameter.
	 * It then finds the winner and the looser of the match. It then checks if the winner and the looser
	 * are already in the ratings map. If not, it adds them with a rating of 1. It then sets the new rates
	 * for the winner and the looser. It then prints the new rates for the winner and the looser.
	 *
	 * @param matchResult the result of the match that will be analyzed
	 */
	 public void matchPlayed(Map<Competitor, Results> matchResult) {
		Competitor winner = null;
		Competitor looser = null;
		double winnerRate;
		double looserRate;
		for(Map.Entry<Competitor, Results> competitor : matchResult.entrySet()) {
			
			if (competitor.getValue().equals(Results.Victory)){winner = competitor.getKey();}
			else{looser = competitor.getKey();}
			}	
		this.checkRate(winner, looser);
		winnerRate = this.ratings.get(winner);
		looserRate = this.ratings.get(looser);
		
		this.setRates(winner, looser);
		
		String resForWinner = winner.getName()+"'s new rating is " + this.ratings.get(winner) +TextFormat.RED.getFORMAT()+ " -" + truncateTo((winnerRate - this.ratings.get(winner)),2) +TextFormat.RESET.getFORMAT(); 
		String resForLooser = looser.getName()+"'s new rating is " + this.ratings.get(looser) +TextFormat.GREEN.getFORMAT()+ " +" + truncateTo((this.ratings.get(looser)-looserRate),2) +TextFormat.RESET.getFORMAT();
		
		
		System.out.println("\n"+this.name+" : "+"\n"+resForWinner+"\n"+resForLooser+"\n");
		
		

	}
	
	/**
	 * The winner's rating is reduced by 25% and the looser's rating is increased by the square root of
	 * the looser's rating
	 * 
	 * @param winner The competitor who won the match
	 * @param looser The competitor who lost the match
	 */
	public void setRates(Competitor winner, Competitor looser) {
		double tmp = 0.75*this.ratings.get(winner);
		tmp= this.truncateTo(tmp,2);
		this.ratings.put(winner,tmp);
		
		tmp = this.ratings.get(looser) + Math.sqrt(this.ratings.get(looser));
		tmp= this.truncateTo(tmp,2);
		this.ratings.put(looser,tmp);

		}
	
	
	
	/** It checks if the competitor is already in the ratings map. If not, it adds it with a rating of 1.
	 *
	 * @param winner The competitor who won the match
	 * @param looser The competitor who lost the match
	 */
	private void checkRate(Competitor winner ,Competitor looser) {
		List<Competitor> competitors = new ArrayList<Competitor>();
		competitors.add(winner);
		competitors.add(looser);
		for(Competitor competitor : competitors) {
			if(!this.ratings.containsKey(competitor)) {this.ratings.put(competitor,(double) 1);}
		}
		
		
	}
	
	/**
	 * This function returns the name of the person.
	 * 
	 * @return The name of the person.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Multiply the number by 10 to the power of the number of decimal places you want to keep, convert
	 * the result to an integer, then divide by 10 to the power of the number of decimal places you want
	 * to keep
	 * 
	 * @param unroundedNumber The number you want to truncate.
	 * @param decimalPlaces The number of decimal places you want to truncate to.
	 * @return The truncated number.
	 */
	private double truncateTo( double unroundedNumber, int decimalPlaces ){
	    int truncatedNumberInt = (int)( unroundedNumber * Math.pow( 10, decimalPlaces ) );
	    double truncatedNumber = (double)( truncatedNumberInt / Math.pow( 10, decimalPlaces ) );
	    return truncatedNumber;
	}

}

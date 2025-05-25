package sportsCompetition.competition;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sportsCompetition.Competitor;
import sportsCompetition.Results;
import sportsCompetition.exception.InvalidInputValueException;
import sportsCompetition.match.Match;
import sportsCompetition.match.MatchRandom;

public class Tournament extends Competition {
	private List<Competitor> remainingCompetitors;
	
//	-----constructors-----
	public Tournament(List<Competitor> competitors) throws InvalidInputValueException
	{
		this(competitors, new MatchRandom());
	}
	
	public Tournament(List<Competitor> competitors, Match match) throws InvalidInputValueException
	{
		super(competitors,match);
		this.remainingCompetitors = new LinkedList<>();
		for(Competitor c: competitors)
			this.remainingCompetitors.add(c);
		if(!(Math.floor(Math.log(competitors.size())/Math.log(2)) == Math.ceil(Math.log(competitors.size())/Math.log(2)))) {
			throw new InvalidInputValueException(competitors.size() + " is not a pow of 2");
		}
	}
	
//	-----getters/setters-----
	public List<Competitor> getRemainingCompetitors() {
		return this.remainingCompetitors;
	}
	
	public void setRemainingCompetitors(List<Competitor> competitors) {
		this.remainingCompetitors = competitors;
	}
	
//	-----methods-----

/**
 * This function removes a competitor from the list of remaining competitors
 * 
 * @param competitor The competitor that lost the match.
 * @return The remaining competitors.
 */
	public List<Competitor> competitorLost(Competitor competitor) {
		this.getRemainingCompetitors().remove(competitor);
		return this.getRemainingCompetitors();
	}
	
	
	@Override
	protected void play(List<Competitor> competitors){
		while(this.getRemainingCompetitors().size() > 1) {
			List<Competitor[]> matchToPlay = generateMatchs(this.getRemainingCompetitors());
			for(Competitor[] match: matchToPlay) {
				playMatch(match[0], match[1]);
			}
		}
	}
	
/**
 * This function plays a match between two competitors and updates the score of the competitors
 * based on the result of the match
 * If a competitor lost, remove them from the tournament
 * 
 * @param c1 The first competitor
 * @param c2 The competitor that will be playing against c1.
 */
	@Override
	public void playMatch(Competitor c1, Competitor c2) {
		super.playMatch(c1, c2);
		for(Map.Entry<Competitor, Results> entry : this.lastResults.entrySet()) {
			if(entry.getValue().equals(Results.Defeat))
				this.competitorLost(entry.getKey());
		}
	}

/**
 * This function generates a list of matches for the next round of the tournament
 * 
 * @param competitors The list of competitors that are participating in the tournament.
 * @return A list of match to play
 */
	public LinkedList<Competitor[]> generateMatchs(List<Competitor> competitors) {
		LinkedList<Competitor[]> newMatch = new LinkedList<>();
		for(int i=0; i < this.getRemainingCompetitors().size() - 1; i+=2){
			Competitor[] newVersus = {this.getRemainingCompetitors().get(i),this.getRemainingCompetitors().get(i+1)};
			newMatch.add(newVersus);
		}
		return newMatch;
	}
}

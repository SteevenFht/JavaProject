package sportsCompetition.competition;

import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

import sportsCompetition.Competitor;
import sportsCompetition.match.Match;


public class League extends Competition {
		
//	-----constructors-----
	public League(List<Competitor> competitors) {
		super(competitors);
	}
	
	public League(List<Competitor> competitors,Match match) {
		super(competitors, match);
	}
	
//	-----methods-----
	/**
	 *For each competitor, generate a match with every other competitor
	 * 
	 * @param competitors a list of competitors
	 * @return A list of matches to play
	 */
	public LinkedList<Competitor[]>  generateMatchs(List<Competitor> competitors) {
		LinkedList<Competitor[]> newMatch = new LinkedList<Competitor[]>();
		for (Competitor p1 : competitors ) {
			for (Competitor p2 : competitors ) {
				if ( ! (p1.equals(p2))) {
					Competitor[] newVersus = {p1,p2};
					newMatch.add(newVersus);
				}
			}
		} 
		Collections.shuffle(newMatch);
		return newMatch;
	}

	/**
	 *The function `play` is a method of the class `Competition` that takes a list of competitors and
	 *play each match
	 * 
	 * @param competitors the list of competitors
	 */
	@Override
	protected void play(List<Competitor> competitors) {
		LinkedList<Competitor[]> match =this.generateMatchs(competitors);
		while(!match.isEmpty()) {
			Competitor[] versus = match.pollFirst();
			this.playMatch(versus[0],versus[1]);
		}
		
	}
	
	
	
}

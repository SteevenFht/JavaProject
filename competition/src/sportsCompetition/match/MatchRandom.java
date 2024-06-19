package sportsCompetition.match;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import sportsCompetition.Competitor;
import sportsCompetition.Results;

public class MatchRandom implements Match {
	Random randomGenerator;
	
//	-----constructors-----
	public MatchRandom() {
		this.randomGenerator = new Random();
	}
	
//	-----methods-----
	/**
	 * This function returns a map of the results of a match between two competitors
	 * 
	 * @param c1 The first competitor
 	 * @param c2 The competitor that will be playing against c1.
	 * @return A map of competitors and their results.
	 */
	@Override
	public Map<Competitor,Results> versus(Competitor c1, Competitor c2) {
		int val = this.randomGenerator.nextInt(2);
		Map<Competitor,Results> res = new HashMap<>();
		res.put(c1, val==1?Results.Victory:Results.Defeat);
		res.put(c2, val==0?Results.Victory:Results.Defeat);
		return res;
	}

}

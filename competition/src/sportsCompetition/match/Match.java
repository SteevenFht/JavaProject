package sportsCompetition.match;

import java.util.Map;

import sportsCompetition.Competitor;
import sportsCompetition.Results;

public interface Match {

	/**
	 * "Given two competitors, return a map of the results of all the matches between them."
	 * 
	 * @param c1 The first competitor
	 * @param c2 The competitor to compare against.
	 * @return A HashMap of Competitor and Results
	 */
	Map<Competitor,Results> versus(Competitor c1, Competitor c2);

}

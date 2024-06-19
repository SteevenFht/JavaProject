package sportsCompetition.observer;

import java.util.Map;

import sportsCompetition.Competitor;
import sportsCompetition.Results;

public interface MatchObserver {
	public void matchPlayed(Map<Competitor, Results> matchResult);
}

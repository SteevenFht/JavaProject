package sportsCompetitionTest.mock;

import java.util.Map;

import sportsCompetition.Competitor;
import sportsCompetition.Results;
import sportsCompetition.observer.MatchObserver;

public class MatchObserverMock implements MatchObserver {
	public int matchPlayedCounter;
	
	public MatchObserverMock(){
		this.matchPlayedCounter = 0;
	}
	@Override
	public void matchPlayed(Map<Competitor, Results> matchResult) {
		this.matchPlayedCounter ++;

	}

}

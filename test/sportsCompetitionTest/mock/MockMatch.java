package sportsCompetitionTest.mock;

import java.util.HashMap;
import java.util.Map;

import sportsCompetition.Competitor;
import sportsCompetition.Results;
import sportsCompetition.match.Match;

public class MockMatch implements Match {
	public int versusCallCounter;
	
	public MockMatch(){
		this.versusCallCounter = 0;
	}

	public Map<Competitor,Results> versus(Competitor c1, Competitor c2)
	{
		this.versusCallCounter ++;
		Map<Competitor,Results> versus = new HashMap<Competitor,Results>();
		versus.put(c1, Results.Victory);
		versus.put(c2, Results.Defeat);
		return versus;
	}
}

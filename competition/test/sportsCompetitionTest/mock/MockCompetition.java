package sportsCompetitionTest.mock;

import java.util.List;
import java.util.Map;

import sportsCompetition.Competitor;
import sportsCompetition.Results;
import sportsCompetition.competition.Competition;
import sportsCompetition.match.Match;

public class MockCompetition extends Competition {
	public int applyScoreToCompetitorsCounter;
	public int playCounter;
	public int rankingCounter;
	
	public MockCompetition(List<Competitor> competitor, Match match){
		super(competitor, match);
		this.applyScoreToCompetitorsCounter = 0;
		this.playCounter = 0;
		this.rankingCounter = 0;
	}
	
	@Override
	public void play(List<Competitor> competitors)
	{
		this.playCounter ++;
	}
	
	@Override
	public Map<Competitor,Integer> ranking()
	{
		this.rankingCounter ++;
		return super.ranking();
	}
	
	@Override
	public void applyScoreToCompetitors(Map<Competitor, Results> scores)
	{
		super.applyScoreToCompetitors(scores);
		this.applyScoreToCompetitorsCounter ++;
	}

}

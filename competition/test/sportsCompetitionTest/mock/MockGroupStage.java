package sportsCompetitionTest.mock;

import java.util.List;
import java.util.Map;

import sportsCompetition.Competitor;
import sportsCompetition.competitorSelection.CompetitorSelection;
import sportsCompetition.exception.InvalidInputValueException;
import sportsCompetition.match.Match;

public class MockGroupStage implements CompetitorSelection {
	private int nbTimeCompetitors;
	
	
	public MockGroupStage(int nbTimeCompetitors) {
		this.nbTimeCompetitors = nbTimeCompetitors;
	}

	@Override
	public int getNumberOfSelectedCompetitors(int nbListCompetitors) {
		return nbTimeCompetitors * nbListCompetitors;
	}

	@Override
	public List<Competitor> selectRemainingPlayers(List<Map<Competitor, Integer>> competitors) {
		return null;
	}
}

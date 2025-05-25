package sportsCompetition.competitorSelection;

import java.util.List;
import java.util.Map;

import sportsCompetition.Competitor;
import sportsCompetition.exception.InvalidInputValueException;

public interface CompetitorSelection {
	public int getNumberOfSelectedCompetitors(int nbListCompetitors);
	public List<Competitor> selectRemainingPlayers(List<Map<Competitor,Integer>> competitors) throws InvalidInputValueException;
}

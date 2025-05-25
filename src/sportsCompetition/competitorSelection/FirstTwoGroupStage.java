package sportsCompetition.competitorSelection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sportsCompetition.Competitor;
import sportsCompetition.exception.InvalidInputValueException;

public class FirstTwoGroupStage implements CompetitorSelection {
	
	public FirstTwoGroupStage() {}
	
	/**
	 * This function selects the two best competitors from each league and returns them in a list
	 * 
	 * @return The best competitors from each league.
	 */
	@Override
	public List<Competitor> selectRemainingPlayers(List<Map<Competitor,Integer>> competitors) throws InvalidInputValueException{
		List<Competitor> res = new ArrayList<>();
		for(Map<Competitor,Integer> rank: competitors) {
			if(rank.size() < 1)
				throw new InvalidInputValueException("not enough player in one of the list");
			Competitor c1 = (Competitor) rank.keySet().toArray()[0];
			Competitor c2 = (Competitor) rank.keySet().toArray()[1];
			res.add(c1);
			res.add(c2);
		}
		
		return res;
	}

	@Override
	public int getNumberOfSelectedCompetitors(int nbListCompetitors) {
		return nbListCompetitors * 2;
	}
}

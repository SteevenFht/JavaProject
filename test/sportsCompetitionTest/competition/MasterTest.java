package sportsCompetitionTest.competition;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import sportsCompetition.Competitor;
import sportsCompetition.competition.Competition;
import sportsCompetition.competition.League;
import sportsCompetition.competition.Master;
import sportsCompetition.competitorSelection.CompetitorSelection;
import sportsCompetition.competitorSelection.FirstTwoGroupStage;
import sportsCompetition.exception.InvalidInputValueException;
import sportsCompetition.match.Match;
import sportsCompetitionTest.mock.MatchObserverMock;
import sportsCompetitionTest.mock.MockGroupStage;

class MasterTest extends CompetitionTest {

	@Override
	protected Competition CreateCompetition(List<Competitor> competitors, Match match) throws InvalidInputValueException {
		CompetitorSelection selection = new FirstTwoGroupStage();
		return new Master(this.competitors, 2, selection,this.match);
	}
	
	@Test
	public void setSelectionWhenOk() {
		Master master = (Master) this.compet;
		
		try {
			CompetitorSelection goodSelection = new MockGroupStage(1);
			master.setSelection(goodSelection);
			new Master(this.competitors, this.competitors.size(), goodSelection ,this.match);
		}
		catch (InvalidInputValueException e) {
			fail();
		}
	}
	
	@Test
	public void setSelectionWhenTooManyLeagueChosen() {
		assertThrows(InvalidInputValueException.class, () -> {
			CompetitorSelection badSelection = new MockGroupStage(2);
			new Master(this.competitors, this.competitors.size(), badSelection ,this.match);
		});
		assertThrows(InvalidInputValueException.class, () -> {
			CompetitorSelection badSelection = new MockGroupStage(2);
			new Master(this.competitors, this.competitors.size(), badSelection ,this.match);
		});
	}

	@Test
	public void observerCalledForEachMatch() {
		int nb_match_groupStages = 4; // 2 leagues of 2 players
		int nb_match_tournament = 3;	// 3 match between the remaining players
		int expectedResult = nb_match_groupStages + nb_match_tournament;
		
		
		this.compet.addObserver(this.matchObserverMock);
		this.compet.play();
		System.out.println(nb_match_tournament);
		assertEquals(expectedResult, ((MatchObserverMock) this.matchObserverMock).matchPlayedCounter);
		
	}
	
	@Test
	void generateLeagueTest() {
		Master master = (Master) this.compet;
		master.generateLeague();
		int nbLeaguesExpected = master.getNBLeagues();
		//Check size 
		League[] allLeagues = master.getLeagues();
		assertEquals(nbLeaguesExpected,allLeagues.length);
		
		//Check if these are the right leagues
		int[] NbCompetitorExpectedPerLeague = {2,2};
		int[] nbCompetitorInLeague = new int[allLeagues.length];
		int index = 0;
		for (League l : allLeagues) {
			 int n = l.getCompetitors().size();
			 nbCompetitorInLeague[index] = n;
			 index ++;
		}
		
		assertArrayEquals(NbCompetitorExpectedPerLeague,nbCompetitorInLeague);
		
	}

}

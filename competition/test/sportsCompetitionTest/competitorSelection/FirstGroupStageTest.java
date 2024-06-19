package sportsCompetitionTest.competitorSelection;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import sportsCompetition.Competitor;
import sportsCompetition.competition.League;
import sportsCompetition.competitorSelection.CompetitorSelection;
import sportsCompetition.competitorSelection.FirstGroupStage;
import sportsCompetition.exception.InvalidInputValueException;

class FirstGroupStageTest extends CompetitorSelectionTest {

	@Override
	protected CompetitorSelection CreateCompetitorSelection() {
		return new FirstGroupStage();
	}
	
	
	@Test
	void selectRemainingPlayersTest() {
		
//		league 1
		List<Competitor> competitorL1 = new ArrayList<>();
		competitorL1.add(this.c1);
		competitorL1.add(this.c2);
		this.c1.setPoints(100);
		this.c2.setPoints(1);
		
//		league 2
		List<Competitor> competitorL2 = new ArrayList<>();
		competitorL2.add(this.c3);
		competitorL2.add(this.c4);
		this.c3.setPoints(50);
		this.c4.setPoints(2);
		
//		league 3
		List<Competitor> competitorL3 = new ArrayList<>();
		competitorL3.add(this.c5);
		competitorL3.add(this.c6);
		this.c5.setPoints(10);
		this.c6.setPoints(10);
		
		League[] ls = new League[3];
		ls[0] = new League(competitorL1, this.match);
		ls[1] = new League(competitorL2, this.match);
		ls[2] = new League(competitorL3, this.match);
		this.master.setLeagues(ls);
		
		List<Map<Competitor, Integer>> groupstageResults = this.master.playGroupStage();
		List<Competitor> actualResult = null;
		try {
			actualResult = competitorSelection.selectRemainingPlayers(groupstageResults);
		} catch (InvalidInputValueException e) {
			fail();
		}
		
		assertTrue(actualResult.contains(this.c1));
		assertTrue(actualResult.contains(this.c3));
		
		int expectedSize = 3;
		assertEquals(expectedSize, actualResult.size());
	}


	@Override
	public void getNumberOfSelectedCompetitorsTest() {
		int expectedResults = 1 * this.master.getNBLeagues();
		assertEquals(expectedResults, this.competitorSelection.getNumberOfSelectedCompetitors(this.master.getNBLeagues()));
		
	}


}

package sportsCompetitionTest.competitorSelection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import sportsCompetition.Competitor;
import sportsCompetition.competition.League;
import sportsCompetition.competitorSelection.CompetitorSelection;
import sportsCompetition.competitorSelection.FirstTwoGroupStage;
import sportsCompetition.exception.InvalidInputValueException;

class FirstTwoGroupStageTest extends CompetitorSelectionTest {
	
	@Override
	protected CompetitorSelection CreateCompetitorSelection() {
		return new FirstTwoGroupStage();
	}

	@Test
	void selectRemainingPlayerTest() {	
		
//		league 1
		List<Competitor> competitorL1 = new ArrayList<>();
		competitorL1.add(this.c1);
		competitorL1.add(this.c2);
		competitorL1.add(this.c3);
		this.c1.setPoints(100);
		this.c2.setPoints(104);
		this.c3.setPoints(99);
		
//		league 2
		List<Competitor> competitorL2 = new ArrayList<>();
		competitorL2.add(this.c4);
		competitorL2.add(this.c5);
		competitorL2.add(this.c6);
		this.c4.setPoints(25);
		this.c5.setPoints(20);
		this.c6.setPoints(30);
		
		League[] ls = new League[2];
		ls[0] = new League(competitorL1, this.match);
		ls[1] = new League(competitorL2, this.match);
		this.master.setLeagues(ls);
		
		List<Map<Competitor, Integer>> groupstageResults = this.master.playGroupStage();
		List<Competitor> actualResult = null;
		try {
			actualResult = competitorSelection.selectRemainingPlayers(groupstageResults);
		} catch (InvalidInputValueException e) {
			fail();
		}
		
		System.out.println("blbfldlkbfkksjfhkfsjhkvbbhfdsjfhjdbkh" + actualResult);
		assertTrue(actualResult.contains(this.c1));
		assertTrue(actualResult.contains(this.c2));
		assertTrue(actualResult.contains(this.c4));
		assertTrue(actualResult.contains(this.c6));
		
		int expectedSize = 4;
		assertEquals(expectedSize, actualResult.size());
	}

	@Override
	public void getNumberOfSelectedCompetitorsTest() {
		int expectedResults = 2 * this.master.getNBLeagues();
		assertEquals(expectedResults, this.competitorSelection.getNumberOfSelectedCompetitors(this.master.getNBLeagues()));
		
	}

}

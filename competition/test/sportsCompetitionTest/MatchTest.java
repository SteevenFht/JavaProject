package sportsCompetitionTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sportsCompetition.Competitor;
import sportsCompetition.Results;
import sportsCompetitionTest.mock.MockMatch;

class MatchTest {
	protected Competitor c1;
	protected Competitor c2;
	protected Competitor c3;
	protected Competitor c4;
	protected List<Competitor> competitors;
	
	MockMatch match;
	
	@BeforeEach
	public void init() {
		this.c1 = new Competitor("team1");
		this.c2 = new Competitor("team2");
		this.c3 = new Competitor("team3");
		this.c4 = new Competitor("team4");
		this.competitors = new ArrayList<>(); 
		competitors.add(c1);
		competitors.add(c2);
		competitors.add(c3);
		competitors.add(c4);		
		this.match = new MockMatch();
	}
	
	@Test
	void matchResultsTest() {
		Map<Competitor,Results> versusResults;
		versusResults=this.match.versus(c1, c2);
		assertEquals(Results.Victory,versusResults.get(c1));
		assertEquals(Results.Defeat,versusResults.get(c2));
		assertEquals(2,versusResults.size());
		
	}
}

package sportsCompetitionTest.competition;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sportsCompetition.Competitor;
import sportsCompetition.Results;
import sportsCompetition.competition.Competition;
import sportsCompetition.exception.InvalidInputValueException;
import sportsCompetition.match.Match;
import sportsCompetition.observer.MatchObserver;
import sportsCompetitionTest.mock.MatchObserverMock;
import sportsCompetitionTest.mock.MockCompetition;
import sportsCompetitionTest.mock.MockMatch;

public abstract class CompetitionTest {
	
	protected Competitor c1;
	protected Competitor c2;
	protected Competitor c3;
	protected Competitor c4;
	protected List<Competitor> competitors;
	protected Competition compet;
	protected MockMatch match;
	protected MatchObserver matchObserverMock;
	
	MockCompetition mockCompet;
	
	protected abstract Competition CreateCompetition(List<Competitor> competitors,Match match) throws InvalidInputValueException;
	
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
		this.mockCompet = new MockCompetition(this.competitors,this.match);
		try {			
			this.compet = CreateCompetition(this.competitors,this.match);
		} catch (InvalidInputValueException e) {
			fail("initialisation failed");
		}
		
		this.matchObserverMock = new MatchObserverMock();
	}

	@Test
	void playTest() {
		assertEquals(0, this.mockCompet.playCounter);
		assertEquals(0, this.mockCompet.rankingCounter);
		this.mockCompet.play();
		assertEquals(1, this.mockCompet.playCounter);
		assertEquals(1, this.mockCompet.rankingCounter);
	}
	
	@Test
	void playMatchTest() {
		System.out.println("\n---PlayMatchTest---START\n");
		assertEquals(0, this.match.versusCallCounter);
		this.compet.playMatch(c1,c2);
		System.out.println("---");
		assertEquals(1,this.match.versusCallCounter);
	
		// checking if calling the right methods
		assertEquals(0, this.mockCompet.applyScoreToCompetitorsCounter);
		this.mockCompet.playMatch(c1, c2);
		assertEquals(1, this.mockCompet.applyScoreToCompetitorsCounter);
		System.out.println("\n---PlayMatchTest---END\n");
	}
	
	@Test
	void rankingTest() {
		int pointC1Before = c1.getPoints();
		int pointC2Before = c2.getPoints();
		c1.addPoints(1);
		c2.addPoints(3);
		Map<Competitor, Integer> score = this.compet.ranking();
		assertEquals(pointC1Before + 1, score.get(this.c1));
		assertEquals(pointC2Before + 3, score.get(this.c2));
		assertEquals(0, score.get(this.c3));
		assertEquals(0, score.get(this.c4));
		
		assertEquals(4, score.size());
		
		//check the order
		LinkedList<Integer> expectedOrder = new LinkedList<>();
		expectedOrder.add(3);
		expectedOrder.add(1);
		expectedOrder.add(0);
		expectedOrder.add(0);
		
		Collection<Integer> actualOrder = score.values();
		LinkedList<Integer> listActualOrder = new LinkedList<>();
		for (Integer rank : actualOrder) { 
		listActualOrder.add(rank);
		}
		assertEquals(expectedOrder, listActualOrder);
	}
	
	@Test
	void applyScoreToCompetitorsTest() {
		c1.addPoints(1);
		c2.addPoints(3);
		c3.addPoints(18);
		c4.addPoints(42);
		
		Map<Competitor, Results> scores = new HashMap<Competitor, Results>();
		scores.put(c1, Results.Victory);
		scores.put(c2, Results.Defeat);
		scores.put(c3, Results.Victory);
		scores.put(c4, Results.Defeat);
		
		assertEquals(1, this.c1.getPoints());
		assertEquals(3, this.c2.getPoints());
		assertEquals(18, this.c3.getPoints());
		assertEquals(42, this.c4.getPoints());
		
		this.compet.applyScoreToCompetitors(scores);
		
		assertEquals(1 + Results.Victory.getPoint()  , this.c1.getPoints());
		assertEquals(3 + Results.Defeat.getPoint()   , this.c2.getPoints());
		assertEquals(18  + Results.Victory.getPoint(), this.c3.getPoints());
		assertEquals(42 + Results.Defeat.getPoint()  , this.c4.getPoints());
	}
	
	@Test
	public void resetScoreTest() {
		this.c2.addPoints(1);
		this.c3.addPoints(2);
		this.c4.addPoints(3);
		assertEquals(0,this.c1.getPoints());
		assertEquals(1,this.c2.getPoints());
		assertEquals(2,this.c3.getPoints());
		assertEquals(3,this.c4.getPoints());
		this.compet.resetScore();
		assertEquals(0,this.c1.getPoints());
		assertEquals(0,this.c2.getPoints());
		assertEquals(0,this.c3.getPoints());
		assertEquals(0,this.c4.getPoints());
	}

}

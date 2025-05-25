package sportsCompetitionTest.competitorSelection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sportsCompetition.Competitor;
import sportsCompetition.competition.Master;
import sportsCompetition.competitorSelection.CompetitorSelection;
import sportsCompetition.exception.InvalidInputValueException;
import sportsCompetitionTest.mock.MockMatch;

public abstract class CompetitorSelectionTest {

	protected Competitor c1;
	protected Competitor c2;
	protected Competitor c3;
	protected Competitor c4;
	protected Competitor c5;
	protected Competitor c6;
	protected Competitor c7;
	protected List<Competitor> competitors;
	protected MockMatch match;
	protected CompetitorSelection competitorSelection;
	protected Master master;
	
//	@Override
	protected abstract CompetitorSelection CreateCompetitorSelection();
	
	@BeforeEach
	public void init() {
		this.c1 = new Competitor("team1");
		this.c2 = new Competitor("team2");
		this.c3 = new Competitor("team3");
		this.c4 = new Competitor("team4");
		this.c5 = new Competitor("team5");
		this.c6 = new Competitor("team6");
		this.c7 = new Competitor("team7");
		this.competitors = new ArrayList<>(); 
		competitors.add(c1);
		competitors.add(c2);
		competitors.add(c3);
		competitors.add(c4);
		competitors.add(c5);
		competitors.add(c6);
		competitors.add(c7);
		this.match = new MockMatch();	
		this.competitorSelection = CreateCompetitorSelection();
		try {			
			this.master = new Master(this.competitors, 2, this.competitorSelection);
		}
		catch(InvalidInputValueException e) {
			fail("failed to initialise tests");
		}
	}
	
	@Test
	public abstract void getNumberOfSelectedCompetitorsTest();	

}

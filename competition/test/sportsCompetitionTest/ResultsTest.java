package sportsCompetitionTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sportsCompetition.Results;

class ResultsTest {


	@Test
	void testVictoryPoint() {
		assertEquals(1,Results.Victory.getPoint());
		assertEquals(0,Results.Defeat.getPoint());
	}

}

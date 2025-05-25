package sportsCompetitionTest.competition;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import sportsCompetition.Competitor;
import sportsCompetition.competition.Competition;
import sportsCompetition.competition.Tournament;
import sportsCompetition.exception.InvalidInputValueException;
import sportsCompetition.match.Match;
import sportsCompetition.util.TextFormat;
import sportsCompetitionTest.mock.MatchObserverMock;

class TournamentTest extends CompetitionTest{

	@Override
	protected Competition CreateCompetition(List<Competitor> competitors, Match match) throws InvalidInputValueException {
		return new Tournament(this.competitors,this.match);
	}
	
	@Test
	public void constructorTestWhenOk() {
		Tournament tournament;
		try {
			tournament = new Tournament(this.competitors);
			assertFalse(tournament.getCompetitors() == tournament.getRemainingCompetitors());
			tournament = new Tournament(this.competitors, this.match);
			assertFalse(tournament.getCompetitors() == tournament.getRemainingCompetitors());
		}
		catch (InvalidInputValueException e) {
			fail();
		}
	}
	
	@Test
	public void constructorTestWhenWrongNumberOfPlayer(){
		this.competitors.remove(0);
		assertThrows(InvalidInputValueException.class, () -> {
			new Tournament(this.competitors);
		});
		assertThrows(InvalidInputValueException.class, () -> {
			new Tournament(this.competitors, this.match);
		});
	}
	
	@Test
	public void playTest(){
		String msg_start = "********* PlayTournamentTest---START *********";
		String msg_end = "********* PlayTournamentTest---END *********";
		System.out.println("\n"+TextFormat.RED.getFORMAT()+msg_start+TextFormat.RESET.getFORMAT()+"\n");
		this.compet.play();
		int[] expectedResults = {0,0,1,2};
		int[] actualResults = new int[this.competitors.size()];
		for(int i=0; i < this.competitors.size(); i++) {
			Competitor competitor = this.competitors.get(i);
			actualResults[i]  = competitor.getPoints();
		}
		Arrays.sort(actualResults);
		assertTrue(Arrays.equals(expectedResults,actualResults));
		System.out.println("\n"+TextFormat.RED.getFORMAT()+msg_end+TextFormat.RESET.getFORMAT()+"\n");
	}
	
    @Test 
    public void matchListGenerationTest(){
    	LinkedList<Competitor[]> matchList;

    	HashMap<Competitor,Integer> numberVersusOfCompetitor = new HashMap<>();
    	for(Competitor c: this.competitors)
    		numberVersusOfCompetitor.put(c,0);
    	
		matchList = ((Tournament) this.compet).generateMatchs(this.competitors);
		assertEquals(this.competitors.size()/2, matchList.size());
		
//    	checking if every remaining competitors has exactly one match
	    for(Competitor[] versusCompetitors: matchList) {
	    	assertEquals(2, versusCompetitors.length);
	    	for(Competitor competitor: versusCompetitors) {
	    		if(numberVersusOfCompetitor.containsKey(competitor)) {
	    			int current = numberVersusOfCompetitor.get(competitor);
	    			numberVersusOfCompetitor.put(competitor, current+1);
	    		}
	    		else
	    			fail();
	    	}    	    	
	    }
	    for (Map.Entry<Competitor, Integer> entry : numberVersusOfCompetitor.entrySet()) {
	    	assertEquals((Integer)1,entry.getValue());
	    }
    }

	@Test
	public void observerCalledForEachMatch() {
		this.compet.addObserver(this.matchObserverMock);
		this.compet.play();
		int expectedResult = competitors.size()-1;
		assertEquals(expectedResult, ((MatchObserverMock) this.matchObserverMock).matchPlayedCounter);
		
	}

}

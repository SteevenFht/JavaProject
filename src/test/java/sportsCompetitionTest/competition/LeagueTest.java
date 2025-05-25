package sportsCompetitionTest.competition;

import java.util.*;

import org.junit.jupiter.api.*;

import sportsCompetition.Competitor;
import sportsCompetition.competition.Competition;
import sportsCompetition.competition.League;
import sportsCompetition.exception.InvalidInputValueException;
import sportsCompetition.match.Match;
import sportsCompetition.util.TextFormat;
import sportsCompetitionTest.mock.MatchObserverMock;

import static org.junit.jupiter.api.Assertions.*;




public class LeagueTest extends CompetitionTest{
	protected List<Competitor[]> allMatch;
	
	@Override
	protected Competition CreateCompetition(List<Competitor> competitors, Match match) throws InvalidInputValueException{
		return new League(competitors,match);
	}
	
	@Override
	@BeforeEach
	public void init() {
		super.init();
		this.allMatch = ((League)this.compet).generateMatchs(competitors);
	}
	
	
	@Test
	public void nbMatchTest(){
		int nbMatchExpected = (competitors.size())*(competitors.size()-1);
		assertEquals(nbMatchExpected,allMatch.size());
	}
	
	@Test
	public void observerCalledForEachMatch() {
		this.compet.addObserver(this.matchObserverMock);
		this.compet.play();
		int expectedResult = (competitors.size())*(competitors.size()-1);
		assertEquals(expectedResult,((MatchObserverMock) this.matchObserverMock).matchPlayedCounter);
	}
	
	
	@Test
	public void goodMatch() {
		for (Competitor p1 : competitors ) {
			for (Competitor p2 : competitors ) {
				if ( ! (p1.equals(p2))) {
					Competitor[] duel = {p1,p2};
					for(int i=0; i<this.allMatch.size(); i++) {
						Competitor[] duo = this.allMatch.get(i);
						if (duo[0].equals(duel[0])&&(duo[1].equals(duel[1]))){
							assertArrayEquals(duo,duel);
						}
					}
				}
			}
		}
	}
		
	
	
	/*	As we use mockMatch we know that the player placed at the head of the match is the winner.
	As each player is placed in match lead 3 times here (nbcompetitors - 1)
	here each player must therefore obtain 3 points in total
	*/
	@Test
	public void playTest() {
		String msg_end = "********* PlayLeagueTest---END *********";
		String msg_start = "********* PlayLeagueTest---START *********";
		System.out.println("\n"+TextFormat.GREEN.getFORMAT()+msg_start+TextFormat.RESET.getFORMAT()+"\n");
		this.compet.play();
		for (Competitor c : competitors ) {
			assertEquals(3,c.getPoints());
		}
		System.out.println("\n"+TextFormat.GREEN.getFORMAT()+msg_end+TextFormat.RESET.getFORMAT()+"\n");
		
	}	
	
	
}

package sportsCompetitionTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import sportsCompetition.Competitor;



public class CompetitorTest {
	public Competitor c1;
	public Competitor c2;
	
	@BeforeEach
    public void init() {
		this.c1 = new Competitor("team1");
		this.c2 = new Competitor("team2");
    }
	
	
    @Test 
    public void addPointTest(){
        assertEquals(0,this.c1.getPoints());
        this.c1.addPoints(1);
        assertEquals(1,this.c1.getPoints());

    }
	
	@Test
	public void equalsTest() {
		String notAPlayer = "not a player";
		assertTrue(this.c1.equals(this.c1));
		assertFalse(this.c1.equals(this.c2));
		assertFalse(this.c1.equals(notAPlayer));
		this.c1.addPoints(2);
		assertTrue(this.c1.equals(this.c1));
	}
    
    
}
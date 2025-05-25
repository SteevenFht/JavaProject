package sportsCompetition.observer;

import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sportsCompetition.Competitor;
import sportsCompetition.Results;

public class Journalist implements MatchObserver {
	private String name;
	private List<String> comments;
	private Random random;
	
//	-----constructors-----
	public Journalist(String name) {
		this.name = name;
		
		this.random = new Random();
		this.comments = new ArrayList<>();
		Collections.addAll(comments,
				"%s won the match over %s",
				"What an incredible math for %s, %s had no chance of winning this one",
				"This was close, but %s won it in the end against %s");
	}


//	-----methods-----	
	@Override
	public void matchPlayed(Map<Competitor, Results> matchResult) {
		String winners = null;
		String loosers = null;
		for(Map.Entry<Competitor, Results> competitor : matchResult.entrySet()) {
			switch (competitor.getValue()){
				case Victory: {
					winners = winners == null ? competitor.getKey().getName(): winners + ", " + competitor.getKey().getName();
					break;
				}
				case Defeat: {
					loosers = loosers == null ? competitor.getKey().getName(): loosers + ", " + competitor.getKey().getName();
					break;
				}
			}
		}
		
		int randomComment = random.nextInt(this.comments.size());
		String res = String.format("%s: "+ this.comments.get(randomComment), this.getName(), winners, loosers);
		
		System.out.println(res);
	}

	
//	-----getters/setters-----
	public String getName() {return this.name;}
	public void setName(String name) {this.name=name;}
	
}

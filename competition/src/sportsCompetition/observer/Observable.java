package sportsCompetition.observer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sportsCompetition.Competitor;
import sportsCompetition.Results;

public abstract class Observable {
	private List<MatchObserver> observers;
	
	public Observable() {
		this.observers = new LinkedList<>();
	}
	
	public void setObservers(List<MatchObserver> observers) {
		this.observers = observers;
	}
	
	public List<MatchObserver> getObservers() {return this.observers;}
	
	public void addObserver(MatchObserver observer) {
		this.observers.add(observer);
	}
	
	public void removeObserver(MatchObserver observer) {
		this.observers.remove(observer);
	}
	
	public void matchOver(Map<Competitor, Results> matchResult) {
		for(MatchObserver observer: this.observers) {
			observer.matchPlayed(matchResult);
		}
	}
}
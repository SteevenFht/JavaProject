package sportsCompetition.competition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sportsCompetition.Competitor;
import sportsCompetition.Results;
import sportsCompetition.competitorSelection.CompetitorSelection;
import sportsCompetition.exception.InvalidInputValueException;
import sportsCompetition.match.Match;
import sportsCompetition.match.MatchRandom;
import sportsCompetition.util.TextFormat;

public class Master extends Competition {
	private CompetitorSelection selection;
	private League[] allLeagues;
	private int nbLeague;
	
//	-----constructors-----
	public Master(List<Competitor> competitor, int nbLeague, CompetitorSelection selection, Match match) throws InvalidInputValueException {
		super(competitor, match);
		this.nbLeague = nbLeague;
		this.allLeagues = new League[nbLeague];
		this.setSelection(selection);
	}
	
	public Master(List<Competitor> competitor, int nbLeague, CompetitorSelection selection) throws InvalidInputValueException{
		this(competitor, nbLeague, selection, new MatchRandom());
	}
	
//	-----methods-----
	/**
	 * This function play a playSelector that return a list of remaining competitors.
	 * With this list of remaining player it plays a tournament
	 * 
	 * @param competitors the list of competitors
	 */
	@Override
	protected void play(List<Competitor> competitors) {
		try {
			generateLeague();
			List<Map<Competitor, Integer>> groupStageResults = this.playGroupStage();
			List<Competitor> remainingCompetitor =  this.selection.selectRemainingPlayers(groupStageResults);
			this.resetScore(remainingCompetitor);
			
			for (Competitor c : remainingCompetitor) {
				System.out.println(TextFormat.BLINK.getFORMAT()+TextFormat.YELLOW.getFORMAT()+c.getName()+" is qualified"+TextFormat.RESET.getFORMAT());
			}
			System.out.println();
			Tournament mainStage = new Tournament(remainingCompetitor, this.matchType);
			mainStage.setObservers(getObservers());
			mainStage.play();
		}
		catch(InvalidInputValueException e) {
			System.out.println(e);
		}
	}
	
	public List<Map<Competitor, Integer>> playGroupStage() {
		List<Map<Competitor, Integer>> res = new ArrayList<>();
		for (League l : this.getLeagues()) {
    		l.play();
    		res.add(l.ranking());
    	}
		return res;
	}
	
	/**
	 *The function generates leagues
	 * 
	 * @param competitors the list of competitors to add to the different leagues
	 */
	public void generateLeague() {
		List<Competitor> remainingCompetitors= new LinkedList<>();
		for(Competitor c: this.getCompetitors())
			remainingCompetitors.add(c);
		
    	int playerPerLeague = (remainingCompetitors.size()/this.nbLeague);
    	int LeagueWithAdditionalPlayers = (remainingCompetitors.size()%this.nbLeague);
    	for (int i=0; i<this.getNBLeagues() ;i++) {
    		//initialize new Competitor List
    		List<Competitor> competitorToAdd = new ArrayList<Competitor>();
			for (int j=0; j<(playerPerLeague + (LeagueWithAdditionalPlayers > 0 ? 1 : 0)); j++){
				//add player in a list before adding in league
				Competitor currentcompetitor = remainingCompetitors.remove(0);
				competitorToAdd.add(currentcompetitor);
			}
			LeagueWithAdditionalPlayers-=1;

    		this.allLeagues[i] = new League(competitorToAdd,this.getMatchType());
    		this.allLeagues[i].setObservers(this.getObservers());
    	}
    }
	
//	-----getters/setters-----
	public CompetitorSelection getSelection() {
		return this.selection;
	}
	
	/**
	 * set the selection in parameter as the selection that will be used to choose which player will compete in the tournament
	 * @param selection: a type of selection
	 * @throws InvalidInputValueException the number of selected competitors is greater than the number of competitors in total OR the number of selected competitors is not a power of 2
	 */
	public void setSelection(CompetitorSelection selection) throws InvalidInputValueException {
		int nbSelected = selection.getNumberOfSelectedCompetitors(this.getNBLeagues());
		if(nbSelected > this.getCompetitors().size() || !(Math.floor(Math.log(nbSelected)/Math.log(2)) == Math.ceil(Math.log(nbSelected)/Math.log(2)))) {
			throw new InvalidInputValueException("the CompetitorSelection output an invalid number of competitor to play the tournament");
		}
		this.selection = selection;
	}
	
	public void DisplayRanking(Map<Competitor, Integer> rank) {
		System.out.println();
	}
	
	public League[] getLeagues() {
		return this.allLeagues;
	}
	
	public void setLeagues(League[] leagues) {
		this.allLeagues = leagues;
		this.nbLeague = leagues.length;
	}
	
	public int getNBLeagues() {
		return this.nbLeague;
	}

}

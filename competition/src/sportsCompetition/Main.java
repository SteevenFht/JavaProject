package sportsCompetition;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

import sportsCompetition.competition.Competition;
import sportsCompetition.competition.League;
import sportsCompetition.competition.Master;
import sportsCompetition.competition.Tournament;
import sportsCompetition.competitorSelection.CompetitorSelection;
import sportsCompetition.competitorSelection.FirstGroupStage;
import sportsCompetition.competitorSelection.FirstTwoGroupStage;
import sportsCompetition.observer.Bookmaker;
import sportsCompetition.observer.Journalist;
import sportsCompetition.observer.MatchObserver;


public class Main {
	public static final Pattern nTypeStr = Pattern.compile("[1-3]|3[1-2]");
	public static final Pattern masterPattern = Pattern.compile("3[1-2]");
	public static final Pattern numStr = Pattern.compile("[1-9][0-9]*");
	
	public static void main(String[] args) {
		List<Competitor> competitors = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		String competitionType = "";
		String nb_competitor = "";
		int nbi_competitor =-1;
		String nb_leagues = "";
		int nbi_leagues = -1;
		Competition compet = null;
		CompetitorSelection selector = null;
		int remainingAfterGroupSTage = -1;
//		Competition type seleciton
		do {
			System.out.print("Choose the competition type:\n"+
						"   1) Tournament\n"+
						"   2) League\n"+
						"   3) Master\n"+
						"   -> 31) select the first competitor of each league during the group stage phase\n"+
						"   -> 32) select the first and the second competitor of each league during the group stage phase\n-> ");	
			competitionType = scan.next();
		} while(!((nTypeStr).matcher(competitionType).matches()));
		
//		group stage selector
		if(competitionType.equals("3")) {
			do {
				System.out.print("Choose a group stage:\n"+
								 "   1) select the first competitor of each league during the group stage phase\n"+
								 "   2) select the first and the second competitor of each league during the group stage phase\n-> ");
				competitionType = "3" + scan.next();
			}while(!masterPattern.matcher(competitionType).matches());
		}
		
//		nb of League selection
		if(masterPattern.matcher(competitionType).matches()) {
			boolean valid = false;
			while (!valid) {
				do {
					System.out.print("Choose a number of league to play: (the number of player selected must be a power of 2)\n-> ");
					nb_leagues = scan.next();
				} while(!numStr.matcher(nb_leagues).matches());
				nbi_leagues = Integer.valueOf(nb_leagues);
				
				try  {
					switch (competitionType) {
					case "31": {
						selector = new FirstGroupStage();
						break;
					}
					case "32": {
						selector = new FirstTwoGroupStage();
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + competitionType);
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				
				valid = true;
				CompetitorSelection g1 = (CompetitorSelection) selector;
				remainingAfterGroupSTage = selector.getNumberOfSelectedCompetitors(nbi_leagues*g1.getNumberOfSelectedCompetitors(nbi_leagues));
				valid = (Math.floor(Math.log(remainingAfterGroupSTage)/Math.log(2)) == Math.ceil(Math.log(remainingAfterGroupSTage)/Math.log(2)));
			}
			
		}
		
//		number of competitors selection
		boolean validNumberOfPlayer = false;
		while(!validNumberOfPlayer) {
			String competString = "";
			switch (competitionType) {
			case "1": {
				competString = "the number of competitors must be a power of 2 and greater than 0";
				break;
			}
			case "2": {	
				competString = "the number of competitors must be greater than 0";
				break;
						}
			case "31": {
			}
			case "32": {
				competString = "the number of competitors must be greater than " + selector.getNumberOfSelectedCompetitors(nbi_leagues);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected competitionType value: " + competitionType);
			}
			System.out.println("Number of competitors (" + competString + ")");	
			do {
				System.out.print("-> ");
				nb_competitor = scan.next();
			} while(!numStr.matcher(nb_competitor).matches());
			nbi_competitor = Integer.parseInt(nb_competitor);
			validNumberOfPlayer = true;
			if (competitionType.equals("1") && !(Math.floor(Math.log(nbi_competitor)/Math.log(2)) == Math.ceil(Math.log(nbi_competitor)/Math.log(2)))) {
				validNumberOfPlayer = false;
			}
			if((competitionType.equals("31") || competitionType.equals("32")) && nbi_competitor < selector.getNumberOfSelectedCompetitors(nbi_leagues)) {
				validNumberOfPlayer = false;
			}
		}

//		input of competitors names
		System.out.println("Input names:");
		for(int i = 0; i < nbi_competitor; i++)
		{
			System.out.print((i+1) + ") ");
            competitors.add(new Competitor(scan.next()));
		}
		scan.close();
		
//		Competition generation
		try {
			switch (competitionType) {
			case "1": {
				compet = new Tournament(competitors);
				break;
			}
			case "2": {
				compet = new League(competitors);		
				break;
						}
			case "31": {}
			case "32": {
				compet = new Master(competitors, nbi_leagues, selector);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected competitionType value: " + competitionType);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		MatchObserver ob1 = new Journalist("OdPixel");
		MatchObserver ob2 = new Journalist("YouYou");
		MatchObserver bm1 = new Bookmaker("RMC");
		compet.addObserver(ob1);
		compet.addObserver(ob2);
		compet.addObserver(bm1);
		compet.play();
		
		
	}
}

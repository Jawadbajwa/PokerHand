import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Functionality {

	static String order = "234567890JQKA";
	
	private static boolean isHighCard(Rank rank, List<String> acards) {
		HashMap<Character, Integer> rep = getRep(acards);
		
		for (Map.Entry<Character, Integer> e : rep.entrySet()) {
	    	  rank.rank = 1;
	    	  if (rank.cardNum < order.indexOf(e.getKey()))
	    		  rank.cardNum = order.indexOf(e.getKey());
		}
		
		char ch = order.charAt(rank.cardNum);
		for (int i = 0; i < acards.size(); i++) {
			if (acards.get(i).charAt(0) == ch) {
				acards.remove(i);
				i--;
			}
		}

  	  	return true;
	}
	
	static boolean isStraitFlush(Rank rank, List<String> acards) {
		char suit = acards.get(0).charAt(1);
		
		for (String card : acards) {
			if (card.charAt(1) != suit)
				return false;
		}
		
		int id = order.indexOf(acards.get(0).charAt(0));
		for (int i = 1; i < 5; i++) {
			if (id + 1 != order.indexOf(acards.get(i).charAt(0)))
				return false;
			id++;
		}
			
		rank.rank = id == 12 ? 10 : 9;
		rank.cardNum = id;
		
		acards.clear();
		
		return true;
	}
	private static boolean isFourKind(Rank rank, List<String> acards) {
		HashMap<Character, Integer> rep = getRep(acards);
		
		if (rep.size() <= 2) {
			for (Map.Entry<Character, Integer> e : rep.entrySet()) {
			      if (e.getValue() == 4) {
			    	  rank.rank = 8;
			    	  rank.cardNum = order.indexOf(e.getKey());
			    	  
			    	  for (int i = 0; i < acards.size(); i++) {
			    		  if (acards.get(i).charAt(0) == e.getKey()) {
			    			  acards.remove(i);
			    			  i--;
			    		  }
			    	  }
			    	  
			    	  return true;
			      }
			}
		}
		
		return false;
	}
	

	static Rank rank(List<String> aCards, int curRank) {
		Rank rank = new Rank();
		
		// check strait flush
		if (curRank > 9 && isStraitFlush(rank, aCards))
			return rank;
		
		// check four cards are the same value
		if (curRank > 8 && isFourKind(rank, aCards))
			return rank;
		
		// check three of a kind and a pair
		if (curRank > 7 && isFullHouse(rank, aCards))
			return rank;
		
		// check three of a kind and a pair
		if (curRank > 6 && isFlush(rank, aCards))
			return rank;
		
		if (curRank > 5 && isStrait(rank, aCards))
			return rank;
		
		if (curRank > 4 && isThreeKind(rank, aCards))
			return rank;
		
		if (curRank > 3 && isTwoPairs(rank, aCards))
			return rank;
		
		if (curRank > 2 && isPair(rank, aCards))
			return rank;
		
		if (curRank >= 1 && isHighCard(rank, aCards))
			return rank;
				
		return rank;
	}
	
	
	
	private static boolean isPair(Rank rank, List<String> acards) {
		HashMap<Character, Integer> rep = getRep(acards);
		
		if (rep.size() <= 4) {
			for (Map.Entry<Character, Integer> e : rep.entrySet()) {
			      if (e.getValue() == 2) {
			    	  rank.rank = 2;
		    		  rank.cardNum = order.indexOf(e.getKey());
			    	  for (int i = 0; i < acards.size(); i++) {
			    		  if (acards.get(i).charAt(0) == e.getKey()) {
			    			  acards.remove(i);
			    			  i--;
			    		  }
			    	  }

			    	  return true;
			      }
			}
		}
		return false;
	}
	
	private static boolean isTwoPairs(Rank rank, List<String> acards) {
		HashMap<Character, Integer> rep = getRep(acards);
		
		if (rep.size() <= 3) {
			for (Map.Entry<Character, Integer> e : rep.entrySet()) {
			      if (e.getValue() == 2) {
			    	  rank.rank = 3;
			    	  if (rank.cardNum < order.indexOf(e.getKey()))
			    		  rank.cardNum = order.indexOf(e.getKey());
			      }
			}
			
			char ch = order.charAt(rank.cardNum);
			for (int i = 0; i < acards.size(); i++) {
				if (acards.get(i).charAt(0) == ch) {
					acards.remove(i);
					i--;
				}
			}

	    	return true;
		}
		return false;
	}
	
	private static boolean isThreeKind(Rank rank, List<String> acards) {
		HashMap<Character, Integer> rep = getRep(acards);
		
		if (rep.size() == 3) {
			for (Map.Entry<Character, Integer> e : rep.entrySet()) {
			      if (e.getValue() == 3) {
			    	  rank.rank = 4;
			    	  rank.cardNum = order.indexOf(e.getKey());
			    	  for (int i = 0; i < acards.size(); i++) {
			    		  if (acards.get(i).charAt(0) == e.getKey()) {
			    			  acards.remove(i);
			    			  i--;
			    		  }
			    	  }

			    	  return true;
			      }
			}
		}
		return false;
	}
	
	private static boolean isStrait(Rank rank, List<String> acards) {
		int id = order.indexOf(acards.get(0).charAt(0));
		for (int i = 1; i < 5; i++) {
			if (id + 1 != order.indexOf(acards.get(i).charAt(0)))
				return false;
			id++;
		}
			
		rank.rank = 5;
		rank.cardNum = id;
		acards.clear();
		
		return true;
	}
	
	private static boolean isFlush(Rank rank, List<String> acards) {
		char suit = acards.get(0).charAt(1);
		
		for (String card : acards) {
			if (card.charAt(1) != suit)
				return false;
		}
		
		rank.rank = 6;
		acards.clear();
		
		return true;
	}
	
	private static boolean isFullHouse(Rank rank, List<String> acards) {
		HashMap<Character, Integer> rep = getRep(acards);
		
		if (rep.size() == 2) {
			for (Map.Entry<Character, Integer> e : rep.entrySet()) {
			      if (e.getValue() == 3) {
			    	  rank.rank = 7;
			    	  rank.cardNum = order.indexOf(e.getKey());

			    	  for (int i = 0; i < acards.size(); i++) {
			    		  if (acards.get(i).charAt(0) == e.getKey()) {
			    			  acards.remove(i);
			    			  i--;
			    		  }
			    	  }

			    	  return true;
			      }
			}
		}
		
		return false;
	}

	
	static HashMap<Character, Integer> getRep(List<String> acards) {
		HashMap<Character, Integer> repetitions = new HashMap<Character, Integer>();

		for (String card : acards) {
		    if (repetitions.containsKey(card.charAt(0)))
		        repetitions.put(card.charAt(0), repetitions.get(card.charAt(0)) + 1);
		    else
		        repetitions.put(card.charAt(0), 1);
		}
		
		return repetitions;
	}
	
	static int check(String input) {
		String acard = input.substring(0, 14);
		String bcard = input.substring(15);
		
		String[] cards = acard.split(" ");
		
		for (int i = 0; i < cards.length - 1; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				if (cards[i].charAt(0) > cards[j].charAt(0)) {
					String temp = cards[i];
					cards[i] = cards[j];
					cards[j] = temp;
				}
			}
		}
		
		List<String> aCards = new ArrayList(Arrays.asList(cards));

		cards = bcard.split(" ");
		
		for (int i = 0; i < cards.length - 1; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				if (cards[i].charAt(0) > cards[j].charAt(0)) {
					String temp = cards[i];
					cards[i] = cards[j];
					cards[j] = temp;
				}
			}
		}
		List<String> bCards = new ArrayList(Arrays.asList(cards));

		int prank = 11;
		
		while (true) {
			Rank rankA = rank(aCards, prank);
			Rank rankB = rank(bCards, prank);
			
			if (rankA.rank > rankB.rank)
				return 1;
			if (rankA.rank < rankB.rank)
				return -1;
			
			if (rankA.cardNum > rankB.cardNum)
				return 1;
			if (rankA.cardNum < rankB.cardNum)
				return -1;
			
			if (aCards.isEmpty() || bCards.isEmpty()) 
				break;
			
			prank = rankA.rank;
		}
		
		return 0;
	}
	
}

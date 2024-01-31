
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {

		String tail = str.substring(1);
		return tail ;
	}

	public static int levenshtein(String word1, String word2) {
	
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		if ( word2.length() == 0 ){

			return word1.length();

		} else if (word1.length() == 0) {

			return  word2.length();
			
		} else if (word1.charAt(0) == word2.charAt(0) ) {
			
			 return levenshtein(tail(word1), tail(word2)) ;
		} else{
			  int minLevenshtein = Math.min(levenshtein(tail(word1), word2) , levenshtein( word1, tail(word2))) ; 
			 return 1 + Math.min(minLevenshtein, levenshtein(tail(word1), tail(word2))) ;
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		int numberOfWord = 0 ; 

		while (!in.isEmpty() && numberOfWord < 3000 ){
			
			String word = in.readString() ;
			dictionary[numberOfWord] = word ; 
			numberOfWord++ ; 

		}
		in.close(); 

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		
	int minChanges = levenshtein(word, dictionary[0]) ;
	int correntNumberOfChanges = 0 ; 
	String valueOfMinChanges = "" ;

	for (int i = 0; i < dictionary.length; i++) {
		correntNumberOfChanges = levenshtein(word, dictionary[i]);

		if (minChanges > correntNumberOfChanges) {
			minChanges = correntNumberOfChanges;
			valueOfMinChanges = dictionary[i];
		 }
	  }
	if (minChanges > threshold) {
		return word;
	} else {
		return valueOfMinChanges;
	  }
	}

}

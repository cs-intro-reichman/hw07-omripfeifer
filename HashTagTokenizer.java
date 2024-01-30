

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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

	public static boolean existInDictionary(String word, String []dictionary) {
		
		boolean isInDictionary = false ;
		for (int i = 0 ; i < 3000 ; i++ ){

		if ( word.equals(dictionary[i]) ){
			isInDictionary = true ; 
			break ; // ending loop 
		}
	  }	
	  return isInDictionary ; 
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		String lowerCaseHashTag = hashtag.toLowerCase();
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {

			if ( existInDictionary(lowerCaseHashTag.substring(0,i), dictionary)) {

				System.out.println(lowerCaseHashTag.substring(0,i) ) ; 
				breakHashTag(lowerCaseHashTag.substring(i), dictionary);
				return ; 
			} 
    }

  }
}
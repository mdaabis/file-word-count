import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WordCounter {
	
	public static final String FILE_PATH = "C:\\Users\\mosta\\OneDrive\\Documents\\words.txt";
	
//	TODO check if methods should be static
	
	public static void main(String[] args) {
	    String text = convertFileToString();
	}
	
	public static int getTotalWords(String text) {	
		if(text.equals(""))
			return 0;
	    String[] words = text.split(" ");
	    return words.length;
	}
	
	public static int getTotalCharactersExcludingSpaces(String text) {
	    String textWithoutSpaces = removeSpaces(text);
	    return textWithoutSpaces.length();
	}
	
	public static double getAverageWordLength(String text) {
		if(getTotalWords(text) == 0)
			return 0.0;
		return getTotalCharactersExcludingSpaces(text) / getTotalWords(text);
	}
	
	public static int getMostFrequentlyOccuringWordLength(String text) {
	    String[] words = text.split(" ");
		Map<Integer, Integer> frequencyMap = new TreeMap<>();
		
		for(String word : words) {
			if(!frequencyMap.containsKey(word.length()))
				frequencyMap.put(word.length(), 0);
				
			frequencyMap.put(word.length(), frequencyMap.get(word.length()) + 1);
		}
		
		List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());

		Collections.sort(entryList, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));

		return entryList.get(0).getValue();

	}
	
	public static String removeSpaces(String text) {
		return text.replaceAll("\\s+","");
	}
	
	public static String convertFileToString() {
		String text = null;
		
		try {
			text = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return text;
	}
	

		

}

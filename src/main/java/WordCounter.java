import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WordCounter {
	
	public static final String FILE_PATH = "C:\\Users\\mosta\\OneDrive\\Documents\\words.txt";
	
//	TODO check if methods should be static
	
	public static void main(String[] args) {
	    String text = convertFileToString();
//	    String text = "Hello world & good morning. The date is 18/05/2016";
	    text = cleanText(text);
	    int wordCount = getTotalWords(text);
	    double averageWordLength = getAverageWordLength(text);
	    List<Map.Entry<Integer, Integer>> lengthFrequencyMap = getWordLengthFrequency(text, true);
	    List<Map.Entry<Integer, List<Integer>>> mostFrequentLengths = new ArrayList<>(getMostFrequentlyOccuringWordLengths(text).entrySet());
	    
	    System.out.println("Word count = " + wordCount);
	    System.out.println("Average word length = " + averageWordLength);
	    for(Map.Entry<Integer, Integer> entry : lengthFrequencyMap) {
		    System.out.println("Number of words of length " + entry.getKey() + " is " + entry.getValue());
	    }
	    System.out.println("The most frequently occurring word length is " + mostFrequentLengths.get(0).getKey() + ", for word lengths " + mostFrequentLengths.get(0).getValue());
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
		
		DecimalFormat df = new DecimalFormat("#.###"); // Round to 3 d.p.
		df.setRoundingMode(RoundingMode.HALF_UP);
		double average = Double.parseDouble(df.format((double) getTotalCharactersExcludingSpaces(text) / getTotalWords(text))); 
		return average;
	}
	
	public static List<Map.Entry<Integer, Integer>> getWordLengthFrequency(String text, boolean sortByKey) {
	    String[] words = text.split(" ");
		Map<Integer, Integer> frequencyMap = new TreeMap<>();
		
		for(String word : words) {
			if(!frequencyMap.containsKey(word.length()))
				frequencyMap.put(word.length(), 0);
				
			frequencyMap.put(word.length(), frequencyMap.get(word.length()) + 1);
		}
		
		List<Map.Entry<Integer, Integer>> frequencyList = new ArrayList<>(frequencyMap.entrySet());

		if(sortByKey) 
			Collections.sort(frequencyList, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
		else
			Collections.sort(frequencyList, (e1, e2) -> e1.getValue().compareTo(e2.getValue()));

		return frequencyList;

	}
	
	public static Map<Integer, List<Integer>> getMostFrequentlyOccuringWordLengths(String text) {
		List<Map.Entry<Integer, Integer>> frequencyList = getWordLengthFrequency(text, false);
		int mostFrequentLengthCount = frequencyList.get(frequencyList.size() - 1).getValue();
		
		Map<Integer, List<Integer>> maxFrequencies = new HashMap<>();
		maxFrequencies.put(mostFrequentLengthCount, new ArrayList<>());
		
		for(Map.Entry<Integer, Integer> entry : frequencyList) {
			if(entry.getValue() == mostFrequentLengthCount) {
				List<Integer> list = new ArrayList<>(maxFrequencies.get(mostFrequentLengthCount));
				list.add(entry.getKey());
				maxFrequencies.put(mostFrequentLengthCount, list);
			}
		}
		return maxFrequencies;
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
	
	public static String cleanText(String text) {
		text = text.replaceAll("\\.", "");
		text = text.replaceAll("\\,", "");
		return text;
	}		

}

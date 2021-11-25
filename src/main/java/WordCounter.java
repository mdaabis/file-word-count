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
import java.util.stream.Collectors;

public class WordCounter {
	
	public static final String FILE_PATH = "C:\\Users\\mosta\\OneDrive\\Documents\\words.txt";
		
	public static void main(String[] args) {
//	    String text = convertFileToString();
	    String text = "Hello world & good morning. The date is 18/05/2016";
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
	    System.out.println("The most frequently occurring word length is " + mostFrequentLengths.get(0).getKey() + ", for word lengths " + 
	    		mostFrequentLengths
    				.get(0)
    				.getValue()
    				.stream()
    				.map(String::valueOf)
    				.collect(Collectors.joining(" & ")));
	}
	
	/**
	 * Returns the total word count of the string passed. Returns a length of 0 if the 
	 * argument is an empty string.
	 * 
	 * @param text string to be counted
	 * @return word count
	 */
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
	
	/**
	 * Counts number of occurrences for each word length.
	 * <p>
	 * Each word's length is counted and added to frequencyMap if it's not there already. If it is, then 
	 * the count is incremented. Then, the map is sorted in ascending order either using the key or the value.
	 * <p>
	 * 
	 * @param text string being tested
	 * @param sortByKey determines whether map is sorted by key (used for printing output) or value (used for
	 * getting most frequently occurring length)
	 * @return a map of entries containing word lengths and their frequency
	 */
	
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
	
	/**
	 * Finds the most frequently occurring word length (or lengths if more than one) by iterating through frequencyList
	 * 
	 * @param text string being tested
	 * @return map with one entry containing maximum occurring word length (key) and a list of all the word lengths with that
	 * frequency (value)
	 */
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
	
	/**
	 * Remove spaces in a string.
	 * 
	 * @param text string with spaces
	 * @return string with spaces removed
	 */
	public static String removeSpaces(String text) {
		return text.replaceAll("\\s+","");
	}
	
	/**
	 * Converts input file to string
	 * 
	 * @return string version of input file
	 */
	public static String convertFileToString() {
		String text = null;
		try {
			text = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return text;
	}
	
	/**
	 * Removes characters we do not want as part of the character/word count.
	 * 
	 * @param text string to be cleaned
	 * @return cleaned text
	 */
	public static String cleanText(String text) {
		text = text.replaceAll("\\.", "");
		text = text.replaceAll("\\,", "");
		text = text.replaceAll("\\-", "");
		text = text.replaceAll("\\;", "");
		text = text.replaceAll("\\:", "");

		return text;
	}
}

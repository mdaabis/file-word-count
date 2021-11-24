import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class WordCounterTest {
	
	private WordCounter wordCounter;
	private String data;
	
	
	@Before
	public void setup() {
		wordCounter = new WordCounter();
	    data = wordCounter.convertFileToString();
	}
	

	@Test
	public void removeSpacesTest() {
		assertEquals("TextWithoutSpaces", wordCounter.removeSpaces("Text Without Spaces"));
	}
	
	@Test
	public void getTotalWordsTest() {
		assertEquals(21, wordCounter.getTotalWords(data));
		assertEquals(0, wordCounter.getTotalWords(emptyString()));
		assertEquals(5, wordCounter.getTotalWords(stringWithNumbersAndSpaces()));

	}
	
	@Test
	public void getTotalCharactersExcludingSpacesTest() {
		assertEquals(69, wordCounter.getTotalCharactersExcludingSpaces(data));
		assertEquals(0, wordCounter.getTotalCharactersExcludingSpaces(emptyString()));
		assertEquals(11, wordCounter.getTotalCharactersExcludingSpaces(stringWithNumbersAndSpaces()));

	}
	
	@Test
	public void getAverageWordLengthTest() {		
		assertEquals(3.286, wordCounter.getAverageWordLength(data), 0.001);
		assertEquals(0, wordCounter.getAverageWordLength(emptyString()), 0.001);
		assertEquals((double) 11 / 5, wordCounter.getAverageWordLength(stringWithNumbersAndSpaces()), 0.001);
	}
	
	
	// This test also tests getWordLengthFrequency() so an additional test for that method is not needed.
	@Test
	public void getMostFrequentlyOccuringWordLengthsTest() {
		Map<Integer, List<Integer>> answerList = new HashMap<>();
		answerList.put(12, Arrays.asList(3));
		
		Map<Integer, List<Integer>> answerList1 = new HashMap<>();
		answerList1.put(2, Arrays.asList(4,5));
		
		assertEquals(answerList, wordCounter.getMostFrequentlyOccuringWordLengths(data));
		assertEquals(answerList1, wordCounter.getMostFrequentlyOccuringWordLengths("Hello world & good morning. The date is 18/05/2016"));


	}
	
	public String emptyString() {
		return "";
	}
	
	public String stringWithNumbersAndSpaces() {
		return "12abc 45d 2 4 d"; 
	}
	
	public String stringWithNumbersAndNoSpaces() {
		return "12abc45d24d"; 
	}

}

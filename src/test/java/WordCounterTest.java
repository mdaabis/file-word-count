import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class WordCounterTest {
	
	private String text;
	
	
	@Before
	public void setup() {
	    text = WordCounter.convertFileToString();
	    text = WordCounter.cleanText(text);
	}
	

	@Test
	public void removeSpacesTest() {
		assertEquals("TextWithoutSpaces", WordCounter.removeSpaces("Text Without Spaces"));
		assertEquals("", WordCounter.removeSpaces(" "));
		assertEquals("TextWithoutSpaces", WordCounter.removeSpaces("TextWithoutSpaces"));
	}
	
	@Test
	public void getTotalWordsTest() {
		assertEquals(21, WordCounter.getTotalWords(text));
		assertEquals(0, WordCounter.getTotalWords(emptyString()));
		assertEquals(5, WordCounter.getTotalWords(stringWithNumbersAndSpaces()));
	}
	
	@Test
	public void getTotalCharactersExcludingSpacesTest() {
		assertEquals(69, WordCounter.getTotalCharactersExcludingSpaces(text));
		assertEquals(0, WordCounter.getTotalCharactersExcludingSpaces(emptyString()));
		assertEquals(11, WordCounter.getTotalCharactersExcludingSpaces(stringWithNumbersAndSpaces()));
	}
	
	@Test
	public void getAverageWordLengthTest() {		
		assertEquals(3.286, WordCounter.getAverageWordLength(text), 0.001);
		assertEquals(0, WordCounter.getAverageWordLength(emptyString()), 0.001);
		assertEquals((double) 11 / 5, WordCounter.getAverageWordLength(stringWithNumbersAndSpaces()), 0.001);
	}
	
	
	// This test also tests getWordLengthFrequency() so an additional test for that method is not needed.
	@Test
	public void getMostFrequentlyOccuringWordLengthsTest() {
		Map<Integer, List<Integer>> answerList = new HashMap<>();
		answerList.put(12, Arrays.asList(3));
		
		Map<Integer, List<Integer>> answerList1 = new HashMap<>();
		answerList1.put(2, Arrays.asList(4,5));
		
		assertEquals(answerList, WordCounter.getMostFrequentlyOccuringWordLengths(text));
		assertEquals(answerList1, WordCounter.getMostFrequentlyOccuringWordLengths("Hello world & good morning. The date is 18/05/2016"));
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
	
	public String onlyCharacters() {
		return ",,..:.;";
	}

}

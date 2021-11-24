import static org.junit.Assert.assertEquals;

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
		assertEquals(3, wordCounter.getAverageWordLength(data), 0.001);
		assertEquals(0, wordCounter.getAverageWordLength(emptyString()), 0.001);
		assertEquals(11 / 5, wordCounter.getAverageWordLength(stringWithNumbersAndSpaces()), 0.001);
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

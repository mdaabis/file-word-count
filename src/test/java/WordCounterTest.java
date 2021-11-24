import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class WordCounterTest {
	
	private WordCounter wc;
	
	@Before
	public void setup() {
		wc = new WordCounter();
	}

	@Test
	public void getTotalWordsTest() {
		assertEquals(12, wc.getTotalWords());
	}
	
	@Test
	public void getTotalCharactersNotSpacesTest() {
		assertEquals(36, wc.getTotalCharactersNotSpaces());
	}

}

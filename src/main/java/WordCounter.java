import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WordCounter {
	
	public static final String FILE_PATH = "C:\\Users\\mosta\\OneDrive\\Documents\\words.txt";
	
	public static void main(String[] args) {
		
	}
	
	public int getTotalWords() {	
	    String data = convertFileToString();
	    String[] words = data.split(" ");
	    return words.length;
	}
	
	public int getTotalCharactersNotSpaces() {
	    String text = convertFileToString();
	    text = removeSpaces(text);
	    String[] words = text.split("");

	    return words.length;
	}
	
	public String removeSpaces(String text) {
		return text.replaceAll("\\s+","");
	}
	
	public String convertFileToString() {
		String text = null;
		
		try {
			text = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return text;
	}
		

}

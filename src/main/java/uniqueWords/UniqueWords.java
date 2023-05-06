package uniqueWords;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UniqueWords {

	public static void getUniqueWords(String promoText) {
		File file = new File(promoText);
		Logger logger = LogManager.getLogger(UniqueWords.class);
		String text = "";

		try {
			text = FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			logger.error("Error reading file: " + e.getMessage());
		}

		String[] words = StringUtils.normalizeSpace(text)
						.replaceAll("[^a-zA-Z ]", "")
						.toLowerCase()
						.split("\\s+");

		HashSet<String> uniqueWords = new HashSet<>(Arrays.asList(words));
		String wordsNumber = "\n There are " + uniqueWords.size() + " unique words in the text.";

		try { FileUtils.writeStringToFile(file, wordsNumber, "UTF-8",
				true);
		} catch (IOException e) {
			logger.error("Error writing to file: " + e.getMessage());
		}
	}
}

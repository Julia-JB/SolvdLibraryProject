import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class BookCheckOutStatus {
	public static int totalBooksCheckout;
	public static Map<String, Integer> bookCounts = new HashMap<>();

	/**
	 * This method is invoked on each book checkout and counts the number of times
	 * the book was checked out. It allows to keep track of most popular books in the library
	 *
	 * @param book
	 */
	public static void countBookCheckouts(Book book) {
		totalBooksCheckout++;
		String isbn = book.getIsbn();
		int count = bookCounts.getOrDefault(isbn, 0);
		bookCounts.put(isbn, count + 1);
	}

	/**
	 * This method prints the total number of times each book was checked out.
	 */
	public static void printBookCheckouts() {
		// instantiating logger as the method is static
		Logger logger = LogManager.getLogger(LibrarySystem.class);
		logger.info("Number of book's checkouts by ISBN");
		bookCounts.forEach((key, value) -> logger.info("ISBN " + key + ": " + value));
	}

}

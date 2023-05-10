import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class SearchService implements Searchable{
	Logger logger = LogManager.getLogger(LibrarySystem.class);

	/**
	 * This method searches for an item by its author
	 */
	public void findItemByAuthor(String author) {
		List<LibraryItem> itemsFound = SearchLambda.searchItems(LibrarySystem.items, SearchLambda.searchByAuthor(author));

		String message = SearchLambda.messageFunction.apply(itemsFound);
		logger.info(message);
	}

	/**
	 * This method searches for an item by its title
	 */
	public void findItemByTitle(String title) {
		List<LibraryItem> itemsFound = SearchLambda.searchItems(LibrarySystem.items,
				SearchLambda.searchByTitle(title));

		String message = SearchLambda.messageFunction.apply(itemsFound);
		logger.info(message);
	}

	/**
	 * This method searches for an item by its title and author.
	 * It combines predicates.
	 */
	public void findItemByTitleAndAuthor(String title, String author) {

		List<LibraryItem> itemsFound = SearchLambda.searchItems(LibrarySystem.items,
				SearchLambda.searchByTitleAndAuthor(title, author));

		String message = SearchLambda.messageFunction.apply(itemsFound);
		logger.info(message);
	}

	public void findUserByName(String name) {
		List<User> usersFound = SearchLambda.searchItems(UserSystem.users, SearchLambda.searchByName(name));
		if (usersFound.size() > 0) {
			logger.info(usersFound);
		} else {
			logger.warn("No matching users found");
		}
	}
}

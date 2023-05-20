package lambdaFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import user.User;
import libraryItem.LibraryItem;
import org.apache.commons.lang3.StringUtils;

public class SearchLambda {
	public static <T> List<T> searchItems(List<T> items, Predicate<T> searchPredicate) {
		List<T> itemsFound = new ArrayList<>();

		for (T item : items) {
			if(searchPredicate.test(item)) {
				itemsFound.add(item);
			}
		}
		return itemsFound;
	}

	public static Predicate<LibraryItem> searchByAuthor(String author) {
		return (LibraryItem item) -> StringUtils.containsIgnoreCase(item.getAuthor(), author);
	}

	public static Predicate<LibraryItem> searchByTitle(String title) {
		return (LibraryItem item) -> StringUtils.containsIgnoreCase(item.getTitle(), title);
	}

	public static Predicate<LibraryItem> searchByTitleAndAuthor(String title, String author) {
		return searchByTitle(title).and(searchByAuthor(author));
	}

	public static Function <List<LibraryItem>, String> messageFunction = (itemsFound) -> {
		if (itemsFound.size() > 0) {
			return "Item(s) found are " + itemsFound;
		} else {
			return "No matching item(s) found. Please check your request.";
		}
	};
	public static Predicate<User> searchByName(String name) {
		return (User user) -> StringUtils.containsIgnoreCase(user.getName(), name);
	}
}

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Filter {
	public static <T> List<T> filter(List<T> items, Predicate<T> predicate) {
		List<T> filteredItems = new ArrayList<>();
		for (T item : items) {
			if (predicate.test(item)) {
				filteredItems.add(item);
			}
		}
		return filteredItems;
	}
	public static Predicate<User> studentUser = user -> user.isStudent();

	public static Predicate<User> hasBorrowedItems = user -> user.getBorrowedItems().size() > 0;

	public static Predicate<LibraryItem> itemNotAvailable = item -> !item.isAvailable();
}






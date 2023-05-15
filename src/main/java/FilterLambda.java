import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterLambda {
	public static <T> List<T> filter(List<T> items, Predicate<T> predicate) {
		List<T> filteredItems = items.stream()
				.filter(predicate)
				.collect(Collectors.toList());
		return filteredItems;
	}
	public static Predicate<User> studentUser = user -> user.isStudent();

	public static Predicate<User> hasBorrowedItems = user -> user.getBorrowedItems().size() > 0;

	public static Predicate<LibraryItem> itemNotAvailable = item -> !item.isAvailable();

	public static Predicate<LibraryItem> isOverdue =
			item -> LocalDate.now().isAfter(item.getReturnDate());
}






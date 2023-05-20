package lambdaFunctions;

import libraryItem.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MapStringLambda {
	public static <T> List<String> mapItemsToStrings(List<T> items,
	                                                Function<T, String> mapFunction) {
		List<String> mappedStrings = new ArrayList<>();
		for (T item : items) {
			String mappedString = mapFunction.apply(item);
			mappedStrings.add(mappedString);
		}

		return mappedStrings;
	}

	public static Function<Book, String> bookTitleAuthor =
			item -> item.getTitle() + " by " + item.getAuthor();
}


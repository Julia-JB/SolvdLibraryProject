import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;
import java.util.stream.Collectors;

public class LibrarySystem {
    // Fields
    public static String libraryName;
    public static String libraryPhoneNumber;
    public static String libraryWebsite;
    public static int totalNumberOfLibraryItems;
    public static int totalItemsCheckout;

    public static List<LibraryItem> items = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();
    public static Queue<HoldItem> holdQueue = new LinkedList<>();
    public static Map<Integer, List<LibraryItem>> usersBorrowedItemsMap = new HashMap<>();

    Logger logger = LogManager.getLogger(LibrarySystem.class);

    // Constructor
    public LibrarySystem(String libraryName, String libraryPhoneNumber, String libraryWebsite) {
        LibrarySystem.libraryName = libraryName;
        LibrarySystem.libraryPhoneNumber = libraryPhoneNumber;
        LibrarySystem.libraryWebsite = libraryWebsite;
        LibrarySystem.holdQueue = holdQueue;
    }


    // Methods
    /**
     * This method adds a library item to the list of library items and increments the total number of library
     * items counter
     */
    public static void addLibraryItem(LibraryItem item) {
        totalNumberOfLibraryItems++;
        items.add(item);
    }

    /**
     * This method updates the map of users and items that they have borrowed
     * @param user
     */
    public void updateBorrowedItemsMap(User user) {
        usersBorrowedItemsMap.put(user.getId(), user.getBorrowedItems());
    }

    /**
     * This method displays items that are overdue
     */
    public List<LibraryItem> getItemsOverdue() {
        List<LibraryItem> itemsOverdue = FilterLambda.filter(items, FilterLambda.isOverdue);
        logger.info(itemsOverdue);

        if(itemsOverdue.size() == 0) {
            logger.info("There are no overdue items");
        }

        return itemsOverdue;
    }

    /**
     * This method prints the items in the hold queue
     */
    public static void printQueueItems() {
        Logger logger = LogManager.getLogger(LibrarySystem.class);
        holdQueue.stream()
                .forEach(holdItem -> logger.info(holdItem.toString()));
    }

    public static void processReturnedItem(LibraryItem returnedItem){
        HoldItem matchedHoldItem = null;
        for (HoldItem holdItem : holdQueue) {
            if (holdItem.getItem().equals(returnedItem)) {
                matchedHoldItem = holdItem;
            }
        }

        if (matchedHoldItem != null) {
            notifyUserItemAvailable(matchedHoldItem);
        }
    }

    private static void notifyUserItemAvailable(HoldItem holdItem) {
        Logger logger = LogManager.getLogger();
        logger.info("Item \"" + holdItem.getItem().getTitle() + "\" by " + holdItem.getItem().getAuthor()
                + " is available for user " + holdItem.getUser().getName());
    }

    /**
     * This method displays items that are currently unavailable in the library
     * @return List<LibraryItem>
     */
    public List<LibraryItem> getItemsNotAvailable() {
        List<LibraryItem> itemsNotAvailable = FilterLambda.filter(items, FilterLambda.itemNotAvailable);
        logger.info(itemsNotAvailable);

        return itemsNotAvailable;
    }

    /**
     * This method displays basic book details. Practicing lambda expressions.
     * @return List<String>
     */
    public List<String> getBooksBasicInfo() {
        List<String> bookStrings = MapStringLambda.mapItemsToStrings(books,
                MapStringLambda.bookTitleAuthor);

        return bookStrings;
    }

    /**
     * This method retrieves only children chapter books. Enums and streams practice
     * @return List<Book>
     */
    public List<Book> getChildrenChapterBooks() {
        List<Book> chapterBooks = books.stream()
                .filter(item -> item.getGenre() == BookGenre.CHILDRENS_CHAPTER_BOOK)
                .collect(Collectors.toList());

        return chapterBooks;
    }

    /**
     * This method sorts books by title. Streams practice
     * @return List</Book>
     */
    public List<Book> sortBooksByTitle() {
        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());

        return sortedBooks;
    }
}




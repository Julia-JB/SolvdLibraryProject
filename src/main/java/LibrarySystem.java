import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;

public class LibrarySystem implements Searchable, Printable {
    // Fields
    public static String libraryName;
    public static String libraryPhoneNumber;
    public static String libraryWebsite;
    public static List<User> users = new ArrayList<>();
    protected static List<LibraryItem> items = new ArrayList<>();
    public static Map<String, Integer> bookCounts = new HashMap<>();
    public static int totalNumberOfUsers;
    public static int totalNumberOfLibraryItems;
    public static int totalBooksCheckout;
    public static int totalItemsCheckout;
    private static Queue<HoldItem> holdQueue = new LinkedList<>();

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
     * This method adds a new user to the list of users and increments the total number of users counter
     */
    public static void addUser(User user) {
        totalNumberOfUsers++;
        users.add(user);
    }

    /**
     * This method adds a library item to the list of library items and increments the total number of library
     * items counter
     */
    public static void addLibraryItem(LibraryItem item) {
        totalNumberOfLibraryItems++;
        items.add(item);
    }

    /**
     * This method searches for an item by its author
     */
    public void findItemByAuthor(String author) {;
        boolean found = false;

        for (LibraryItem item : items) {
            if (item.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                logger.info(item.getItemType() + ": " + item.getTitle() + " - " + item.getAuthor());
                found = true;
            }
        } if (!found) {
            logger.warn("Item not found. Please check your request and try again");
        }
    }

    /**
     * This method searches for an item by its title
     */
    public void findItemByTitle(String title) {
        boolean found = false;

        for (LibraryItem item : items) {
            if (item.getTitle().toLowerCase().contains(title.toLowerCase())) {
                logger.info(item.getItemType() + ": " + item.getTitle() + " - " + item.getAuthor());
                found = true;
            }
        }
        if (!found) {
            logger.warn("Item not found. Please check your request and try again");
        }
    }

    /**
     * This method is invoked on each book checkout and counts the number of times
     * the book was checked out. It allows to keep track of most popular books in the library
     * @param book
     */
    public static void countBookCheckouts(Book book) {
        totalBooksCheckout++;
        String isbn = book.getIsbn();
        int count= bookCounts.getOrDefault(isbn, 0);
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

    /**
     * This method prints the item's info.
     */
    public static void printItemInfo(LibraryItem item) {
        Logger logger = LogManager.getLogger(LibrarySystem.class);
        logger.info(item.toString());
    }

    /**
     * This method prints names of all users
     */
    public void printAllUsersNames() {
        List<String> userNames = new ArrayList<>();
        users.forEach(user -> userNames.add(user.getName()));
        logger.info("The users of the library are: " + userNames);
    }

    /**
     * This method prints total number of library users
     */
    public void printTotalNumberOfUsers() {
        logger.info("Total number of users: " + LibrarySystem.totalNumberOfUsers);
    }

    /** This method adds items to the hold queue
     *
     * @param holdItem
     */
    public static void addToHoldQueue(HoldItem holdItem) {
        holdQueue.add(holdItem);
    }

    /** This method removes item from the hold queue
     * @param holdItem
     */
    public void removeFromHoldQueue(HoldItem holdItem) {
        holdQueue.remove(holdItem);
    }

    /**
     * This method checks if a user has placed a hold on the item - not implemented yet
     * @param
     * @return boolean
     */

    /**
     * This method prints the items in the hold queue
     */
    public void printQueueItems() {
        for (HoldItem holdItem : holdQueue) {
            logger.info(holdItem.toString());
        }
    }
}




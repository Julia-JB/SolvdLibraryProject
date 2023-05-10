import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;

public class LibrarySystem {
    // Fields
    public static String libraryName;
    public static String libraryPhoneNumber;
    public static String libraryWebsite;
    public static int totalNumberOfLibraryItems;
    public static int totalItemsCheckout;

    protected static List<LibraryItem> items = new ArrayList<>();
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
     * This method displays items that are currently unavailable in the library
     * @return
     */
    public List<LibraryItem> getItemsNotAvailable() {
        List<LibraryItem> itemsNotAvailable = FilterLambda.filter(items, FilterLambda.itemNotAvailable);
        logger.info(itemsNotAvailable);

        return itemsNotAvailable;
    }


}




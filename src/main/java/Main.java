import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import static uniqueWords.UniqueWords.uniqueWords;

public class Main {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(Main.class);

        // Instantiating LibrarySystem
        LibrarySystem librarySystem = new LibrarySystem("Uptown Library",
                "425-778-21-48", "www.uptown-library.org");

        // Creating users
        UserFileReader reader = new UserFileReader(UserFileReader.userdata);
        UserFileReader.createUsers(5);
        User user1 = LibrarySystem.users.get(0);
        User user2 = LibrarySystem.users.get(1);
        User user3 = LibrarySystem.users.get(2);
        User user4 = LibrarySystem.users.get(3);
        User user5 = LibrarySystem.users.get(4);

        // Creating library items
        Book book1 = new Book(1, "book","To the Lighthouse",
                "Virginia Woolf", 14.50, true, 3,
                BookGenre.ROMANCE, "8365781899458", 209);

        Book book2 = new Book(2, "book",  "The Call of the Wild",
                "Jack London", 12.80, true, 2,
                BookGenre.FICTION, "9788838439018", 46);

        ChildrenBook book3 = new ChildrenBook(1, "children's book",
                "The Lion, the Witch and the Wardrobe", "C.S. Lewis", 17.10,
                true, 2, BookGenre.CHILDRENS_CHAPTER_BOOK,
                "9780064404990", 208, "grades: 5-8");

        CD cd1 = new CD(1, "cd", "Origins", "Imagine Dragons", 12.99, true, 2, "40:02");

        // ISBN validation is implemented on setting an ISBN
        book1.setISBN("9780306406157"); // ISBN is valid
        book1.setISBN("9780306406158");  // "Please check the ISBN: ISBN is not valid" message is printed
        book1.setISBN("asyhfkfkfkfkf");  // "Please check the ISBN: ISBN should contain 13 digits" message is printed

        // Printing items info in JSON format
        book1.printItemJSON();
        cd1.printItemJSON();

        // Printing user's info in different formats
        user1.printUserInfo();
        user1.printUserJson();

        // Checking out items
        book1.checkoutBook(user1);
        book2.checkoutBook(user2);
        book2.checkoutBook(user4);
        book3.checkoutBook(user1);
        cd1.checkoutItem(user3);

        // Printing return date for the item based on 21-day period starting today
        user1.printReturnDateForItem(cd1); // Output: Please return the cd "Origins" by May 16, 2023

        // Printing a receipt upon the checkout
        user1.printReceipt();

        // Returning the item
        book1.returnItem(user1);
        cd1.returnItem(user1);

        // Printing user's updated info after the items were returned
        user1.printUserInfo();

        // Printing out all library users
        librarySystem.printAllUsers();
        librarySystem.printTotalNumberOfUsers();

        // Searching for an item by the author and title (case-insensitive)
        librarySystem.findItemByAuthor("London"); // Output: The Call of the Wild - Jack London
        librarySystem.findItemByTitle("origins"); //

        // Printing the number of times each book was checked out
        LibrarySystem.printBookCheckouts();

        // Instantiating MediaCenter
        MediaCenter mediaCenter = new MediaCenter();

        // Printing formatted cost of using media center resources with and without student discount
        mediaCenter.printUsageCost(user1, 12, 10);
        mediaCenter.printUsageCost(user2, 12, 10);

        // Creating a library event
        Event event1 = new Event("Spring Book Fair", LocalDate.of(2023, 5,
                1), EventType.BOOK_SALE, true);
        Event event2 = new Event("Uptown kids Adventures", LocalDate.of(2023,
                6, 12 ), EventType.CHILDREN_STORY_TIME, true);

        // Adding users to library event - Custom LinkedList methods are used in this part
        event1.addUserToEvent(user2);
        event1.addUserToEvent(user1);
        event1.addUserToEvent(user3);
        event1.printEventAttendees();
        event1.removeEventAttendee(user3);
        event1.removeEventAttendee(user5); // 'Element is not in the list' message output
        event1.printEventAttendees(); // Event attendees list after the removal

        // Instantiating the Librarian class
        Librarian librarian = new Librarian(1, "Dorothy Hudson", 25);

        // Printing formatted librarian wages
        librarian.printWages(38);

        // Testing placeHold() functionality
        book2.checkoutBook(user5);
        book2.placeHold(user5);
        book1.placeHold(user4);
        user1.printItemsBorrowedByUser();
        user5.printItemsOnHold();

        // Printing total number of books checked out from the library
        logger.info("Total books checkouts: " + LibrarySystem.totalBooksCheckout);

        // Printing total number of items checked out from the library
        logger.info("Total items checkouts: " + LibrarySystem.totalItemsCheckout);

        // Printing items in the hold queue
        librarySystem.printQueueItems();

        // Printing the number of unique words to file
        // Output: "There are 21 unique words in the text"
        uniqueWords("my-app/src/main/resources/libraryPromo.txt");

    }
}
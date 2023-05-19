import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static uniqueWords.UniqueWords.getUniqueWords;

public class Main {
    public static void main(String[] args) throws Exception {
        Logger logger = LogManager.getLogger(Main.class);

        // Instantiating LibrarySystem
        LibrarySystem librarySystem = new LibrarySystem("Uptown Library",
                "425-778-21-48", "www.uptown-library.org");
        UserSystem usersSystem = new UserSystem();

        // Creating users
        UserFileReader reader = new UserFileReader(UserFileReader.userdata);
        UserFileReader.createUsers(5);
        User user1 = usersSystem.users.get(0);
        User user2 = usersSystem.users.get(1);
        User user3 = usersSystem.users.get(2);
        User user4 = usersSystem.users.get(3);
        User user5 = usersSystem.users.get(4);

        // Creating library items
        Book book1 = new Book(1, ItemType.BOOK,"To the Lighthouse",
                "Virginia Woolf", 14.50, true, 3,
                BookGenre.ROMANCE, "8365781899458", 209);

        Book book2 = new Book(2, ItemType.BOOK,  "The Call of the Wild",
                "Jack London", 12.80, true, 2,
                BookGenre.FICTION, "9788838439018", 46);

        ChildrensBook childrensBook3 = new ChildrensBook(1, ItemType.BOOK,
                "The Lion, the Witch and the Wardrobe", "C.S. Lewis", 17.10,
                true, 2, BookGenre.CHILDRENS_CHAPTER_BOOK,
                "9780064404990", 208, AgeGroup.ELEMENTARY_TO_MIDDLE);

        CD cd1 = new CD(1, ItemType.CD, "Origins", "Imagine Dragons", 12.99,
                true, 2, "40:02");
        Periodic magazine1 = new Periodic(1, ItemType.MAGAZINE, "The New-Yorker", 5, Frequency.WEEKLY,
                2022, 12);


        // ISBN validation is implemented on setting an ISBN
        book1.setISBN("9780306406157"); // ISBN is valid
        book1.setISBN("9780306406158");  // "Please check the ISBN: ISBN is not valid" message is printed
        book1.setISBN("asyhfkfkfkfkf");  // "Please check the ISBN: ISBN should contain 13 digits" message is printed

        // Printing items info in JSON format
        book1.printItemJSON();
        cd1.printItemJSON();

        // Printing user's info in different formats
        user1.printUserInfo();
        user1.printUserJSON();

        // Checking out items
        logger.info("Here " + book2.getNumberOfItemsAvailable());
        logger.info(book2.isAvailable());
        book1.checkoutBook(user1);
        book2.checkoutBook(user2);
        book2.checkoutBook(user4);
        cd1.checkoutItem(user3);

        logger.info("Here " + book2.getNumberOfItemsAvailable());
        logger.info(book2.isAvailable());

        // Printing a receipt upon the checkout
        ReceiptFormatter formatter = new ReceiptFormatter();
        logger.info(formatter.formatReceipt(user1));

        // Returning the item
        book1.returnItem(user1);

        // Printing user's updated info after the items were returned
        user1.printUserInfo();

        // Printing out all library users
        usersSystem.printAllUsersNames();
        usersSystem.printTotalNumberOfUsers();

        // Searching for an item by the author and title (case-insensitive)
        SearchService searchService = new SearchService();
        searchService.findItemByAuthor("London"); // Output: The Call of the Wild - Jack London
        searchService.findItemByTitle("snow"); //

        // Printing the number of times each book was checked out
        BookCheckOutStatus.printBookCheckouts();

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
        event1.addEventAttendee(user2);
        event1.addEventAttendee(user1);
        event1.addEventAttendee(user3);
        event1.printEventAttendees();
        event1.removeEventAttendee(user3);
        event1.removeEventAttendee(user5); // 'Element is not in the list' message output
        event1.printEventAttendees(); // Event attendees list after the removal

        // Instantiating the Librarian class
        Employee librarian = new Employee(1, "Dorothy Hudson", 25);

        // Printing formatted librarian wages with and without hours worked overtime
        librarian.printWages(38);
        librarian.printWages(40, 6);

        // Testing placeHold() functionality. It can only be invoked if the item is currently
        // unavailable based on initial number of the items and item's checkouts
        book2.checkoutBook(user5); // This item is not available
        logger.info("Here " + book2.getNumberOfItemsAvailable());
        logger.info(book2.isAvailable());
        book2.placeHold(user5);
        book1.placeHold(user4); //This item is available
        user1.printItemsBorrowedByUser();
        user5.printItemsOnHold();

        // Printing total number of books checked out from the library
        logger.info("Total books checkouts: " + BookCheckOutStatus.totalBooksCheckout);

        // Printing total number of items checked out from the library
        logger.info("Total items checkouts: " + LibrarySystem.totalItemsCheckout);

        // Printing items in the hold queue
        HoldItem.printQueueItems();

        // Working with HashMap
        librarySystem.updateBorrowedItemsMap(user1);
        librarySystem.updateBorrowedItemsMap(user2);
        librarySystem.updateBorrowedItemsMap(user3);
        logger.info(LibrarySystem.usersBorrowedItemsMap);

        // Printing the number of unique words to file
        // Output: "There are 21 unique words in the text"
        getUniqueWords("my-app/src/main/resources/libraryPromo.txt");

        // Testing methods that use custom lambda functions
        usersSystem.getStudentUsers();
        usersSystem.getUsersWithBorrowedItems();
        librarySystem.getItemsNotAvailable();
        logger.info(librarySystem.getBooksBasicInfo());
        searchService.findItemByTitleAndAuthor("wild", "London");
        logger.info(usersSystem.getUsersWithOverdueItems());

        // Testing enum methods
        childrensBook3.checkAgeGroup(14); // This book is suitable for the user's age.
        logger.info(magazine1.getFrequency().getIssuesPerYear());
        logger.info(librarySystem.getChildrenChapterBooks());

        // Testing methods with streams
        logger.info(librarySystem.sortBooksByTitle());
        logger.info(usersSystem.getUsersWithBorrowedItems());

        // Reflection practice
        ReflectionPractice.invokeMethod();
        ReflectionPractice.getClassDetails();

        // Connection pool and multithreading practice

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        ConnectionPool connectionPool = new ConnectionPool(5);
        Connection connection = connectionPool.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        List<User> users = new ArrayList<>(UserSystem.users);

        CompletableFuture future = CompletableFuture.runAsync(() -> {
                    for (User user : users) {
                        UserInsertTask task = new UserInsertTask(userDAO, user);
                        executorService.submit(task);
                    }
                }).thenRun(() -> {
                    UserInsertTask task1 = new UserInsertTask(userDAO, new User(7,
                    "James Davis", "jamesdave@gmail.com", "3605221314", false));
                    UserInsertTask task2 = new UserInsertTask(userDAO, new User(8, "Savanna " +
                            "Styles", "savanna24@)radio.net", "425820141", true));
                    new Thread(task1).start();
                    new Thread(task2).start();
                    });

            future.join();

        connectionPool.releaseConnection(connection);
        executorService.shutdown();
    }
}
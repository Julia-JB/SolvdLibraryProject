import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;


abstract class LibraryItem {
    private static final Logger logger = LogManager.getLogger(LibraryItem.class);
    // Fields
    private int id;
    private String itemType;
    private String title;
    private String author;
    private double price;
    private boolean available;
    private int numberOfItemsAvailable;
    public static final int MAX_DAYS_LOAN = 21;
    private LocalDate returnDate;
    private boolean onHold;
    private LocalDate dateOnHold;

    // Constructor
    public LibraryItem() {
    }

    public LibraryItem(int id, String itemType, String title, String author, double price, boolean available,
                       int numberOfItemsAvailable) {
        this.id = id;
        this.itemType = itemType;
        this.title = title;
        this.author = author;
        this.price  = price;
        this.available = available;
        this.numberOfItemsAvailable = numberOfItemsAvailable;
        LibrarySystem.addLibraryItem(this);
    }

    public LibraryItem(int id, String itemType, String title, double price) {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getItemType() {
        return itemType;
    }

    public void setItemType() {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getNumberOfItemsAvailable() {
        return numberOfItemsAvailable;
    }

    public void setNumberOfItemsAvailable(int numberOfItemsAvailable) {
        this.numberOfItemsAvailable = numberOfItemsAvailable;
    }

    public void setReturnDate() {
        LocalDate today = LocalDate.now();
        this.returnDate = today.plusDays(MAX_DAYS_LOAN);
    }

    public LocalDate getReturnDate() {
            if (returnDate == null) {
                logger.info("User returned this item");
            }
        return returnDate;
    }

    public void setOnHold() {
        this.onHold = true;
    }

    public boolean isOnHold() {
        return onHold;
    }
    public LocalDate getDateOnHold() {
        return dateOnHold;
    }

    public void setDateOnHold(LocalDate dateOnHold) {
        this.dateOnHold = LocalDate.now();
    }

    // Methods
    /**
     * This method provides item checkout functionality
     */
    public void checkoutItem(User user) {
        try {
            if (this.available) {
                numberOfItemsAvailable -= 1;
                setReturnDate();
                addToCheckedOutItems(user);
                addToBorrowedItems(user);
                LibrarySystem.totalItemsCheckout += 1;

            } else {
                throw new NoAvailableItemsException("Sorry, this item is not available. You can place a hold on it.");
            }

            if (numberOfItemsAvailable == 0) {
                available = false;
            }
        } catch (NoAvailableItemsException e) {
           logger.warn(e.getMessage(), e);
        }
    }


    public void addToCheckedOutItems(User user) {
        user.getCheckedOutItems().add(this);
    }

    /**
     * This method provides book return functionality
     */
    public void returnItem(User user) {
        setNumberOfItemsAvailable(numberOfItemsAvailable += 1);
        if (!available) {
            setAvailable(true);
        }
        removeFromBorrowedItems(user);
        this.returnDate = null;
    }

    /**
     * This method provides functionality for placing a hold on an item
     * @param user
     */
    public void placeHold(User user) {
        Logger logger = LogManager.getLogger(LibraryItem.class);
        if (!this.available) {
            this.setOnHold();
            addToHoldItems(user);
            logger.info("You successfully placed  a hold on the item " + this);
        } else {
            logger.info("This item is available");
        }
    }

    public void addToHoldItems(User user) {
        user.getItemsOnHold().add(this);
    }

    /**
     * This item adds an item to the list of items borrowed by the user
     * @param user
     */
    public void addToBorrowedItems(User user) {
        user.getBorrowedItems().add(this);
    }

    /**
     * This item removes an item from the list of items borrowed by the user
     * @param user
     */
    public void removeFromBorrowedItems(User user) {
        user.getBorrowedItems().remove(this);
    }

    abstract String formatJSON();

    public void printItemJSON() {
        Logger logger = LogManager.getLogger(LibraryItem.class);
        logger.info(formatJSON());
    }
}

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.time.LocalDateTime;

abstract class LibraryItem {
    // Fields
    private int id;
    public ItemType itemType;
    private String title;
    private String author;
    private double price;
    private boolean available;
    private int numberOfItemsAvailable;
    public static final int MAX_DAYS_LOAN = 21;
    private LocalDate returnDate;
    private boolean onHold;
    private LocalDate dateOnHold;
    Logger logger = LogManager.getLogger(LibraryItem.class);

    // Constructor
    public LibraryItem(int id, ItemType itemType, String title, String author, double price,
                       boolean available,
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

    public LibraryItem(int id, ItemType itemType, String title, double price) {
        this.id = id;
        this.itemType = itemType;
        this.title = title;
        this.price = price;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemType getItemType() {
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
//            if (returnDate == null) {
//                logger.info("User returned this item");
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
                numberOfItemsAvailable -=1;
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

    /**
     * This method adds the item to the list of the items checked out by the user
     * @param user
     */
    public void addToCheckedOutItems(User user) {
        user.getCheckedOutItems().add(this);
    }

    /**
     * This method provides item return functionality
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
        if (!this.available) {
            this.setOnHold();
            addToHoldItems(user);
            LocalDateTime currentDate = LocalDateTime.now();
            HoldItem holdItem = new HoldItem<>(this, user, currentDate);
            HoldItem.addToHoldQueue(holdItem);
            logger.info("You successfully placed  a hold on the item " + this);
        } else {
            logger.warn("This item is available");
        }
    }

    /**
     * This method adds an items to the list of items the user has put on hold
     * @param user
     */
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

    /**
     * Abstract method to be implemented by child classes
     * @return
     */
    abstract String formatJSON();

    /**
     * This method prints the item info in JSON format
     */
    public void printItemJSON() {
        logger.info(formatJSON());
    }

    /**
     * This method prints the item's info.
     */
    public static void printItemInfo(LibraryItem item) {
        Logger logger = LogManager.getLogger(LibrarySystem.class);
        logger.info(item.toString());
    }
}

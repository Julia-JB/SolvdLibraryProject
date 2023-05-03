import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class User implements Formattable {
    //Fields
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private boolean isStudent;
    private List<LibraryItem> borrowedItems;
    private List<LibraryItem> checkedOutItems;
    private List<LibraryItem> itemsOnHold;
    private double totalPriceCheckedItems = 0;
    Logger logger = LogManager.getLogger(LibrarySystem.class);

    //Constructor
    public User(int id, String name, String email, String phoneNumber, boolean isStudent) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isStudent = isStudent;
        borrowedItems = new ArrayList<>();
        itemsOnHold = new ArrayList<>();
        checkedOutItems = new ArrayList<>();
        LibrarySystem.addUser(this);
    }

    // Getter and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isStudent() {
        return isStudent;
    }
    public void setStudent() {
        this.isStudent = isStudent;
    }

    public List<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }

    public List<LibraryItem> getItemsOnHold() {
        return itemsOnHold;
    }

    public List<LibraryItem> getCheckedOutItems() {
        return checkedOutItems;
    }

    // Methods
    /**
     * This method prints the due date for returning the item.
     * The date is calculated based on the 21-day return period starting today (given that book is checked out today)
     */
    public void printReturnDateForItem(LibraryItem item) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        String formattedDate = item.getReturnDate().format(formatter);
        logger.info("Please return the " + item.getItemType() + " \"" + item.getTitle() + "\" by " + formattedDate);
    }
    public void printItemsBorrowedByUser() {
        logger.info("User " + this.getName() + "has borrowed the items: " + borrowedItems.toString());
    }
    /**
     * This method prints out the items that the user placed a hold on
     */
    public void printItemsOnHold() {
        if (this.itemsOnHold.size() == 0) {
            logger.info("User " + this.name + "has not placed a hold on any items");
        } else {
            logger.info("User " + this.getName() + "placed a hold on the item: " + itemsOnHold.toString());
        }
    }

    /**
     * This method overrides toString() method and formats user info including borrowed items
     */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n----------------------------");
            sb.append("\nName: ").append(name).append(", \n");
            sb.append("Email: ").append(email).append(", \n");
            if (borrowedItems.size() > 0) {
                sb.append("Borrowed items: ");
                for (LibraryItem item : borrowedItems) {
                    sb.append(item.getTitle()).append(" - ").append(item.getAuthor());
                }
            } else sb.append("No borrowed items");

            return sb.toString();
        }

    /**
     * This method prints out user info in regular string format
     */
    public void printUserInfo() {
            logger.info(this.toString());
        }

    /**
     * This method formats user basic info as a JSON object literal
     * @return
     */
    public String formatJSON() {
        String jsonString = String.format("{\n \"id\": %d, \n \"name\": \"%s\", \n \"email\": \"%s\", \n \"isStudent\": %b \n}",
                id, name, email, isStudent);
        return jsonString;
    }

    /**
     * This method prints out user info in the JSON format
     */
    public void printUserJson() {
        logger.info(formatJSON());
    }

    public double calculateCheckedOutItemsCost() {
        for (LibraryItem item : checkedOutItems) {
            totalPriceCheckedItems += item.getPrice();
        }
        return totalPriceCheckedItems;
    }

    /**
     * This method formats a receipt upon the user's checkout
     */
    public String formatReceipt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        calculateCheckedOutItemsCost();
        StringBuilder sb = new StringBuilder();
        sb.append("\n----------------------------");
        sb.append("\nCheck Out Receipt");
        sb.append("\n" + LibrarySystem.libraryName);
        sb.append("\n" + LibrarySystem.libraryPhoneNumber);
        sb.append("\n" + LibrarySystem.libraryWebsite);
        sb.append("\n" + LocalDate.now().format(formatter));
        for (LibraryItem item : checkedOutItems) {
            sb.append("\nTitle: ").append(item.getTitle());
        }
        sb.append("\nDue: ").append(checkedOutItems.get(0).getReturnDate().format(formatter));
        sb.append("\nTotal items: ").append(checkedOutItems.size());

        sb.append("\nYou just saved " +
                NumberFormat.getCurrencyInstance(Locale.US).format(totalPriceCheckedItems)+
                " by using your library. " +
                "\nThis is suggested retail price of the item(s) checked out.");
        return sb.toString();
    }

    /**
     * This method prints the receipt upon the user's checkout
     */
    public void printReceipt() {
        logger.info(formatReceipt());
    }

    /**
     * This method overrides equals() method to compare User objects based on their names
     * and phone numbers properties
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof User) {
            User testObj = (User) obj;
            return testObj.name == this.name && testObj.phoneNumber == this.phoneNumber;
        }
        return false;
    }

    /**
     * This method overrides hashCode() method based on User object name and phone number properties
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.name) + 31 * hash + Objects.hashCode(this.phoneNumber);
        return hash;
    }
}

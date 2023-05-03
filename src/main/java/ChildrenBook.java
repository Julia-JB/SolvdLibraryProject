public class ChildrenBook extends Book {

    // Fields
    private String ageGroup;

    // Constructor
    public ChildrenBook(int id, String itemType, String title, String author, double price, boolean available,
                        int numberOfItemsAvailable, BookGenre genre, String ISBN, int pageCount, String ageGroup) {
        super(id, itemType, title, author, price, available, numberOfItemsAvailable, genre, ISBN, pageCount);
        this.ageGroup = ageGroup;
    }

    // Getters and Setters
    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup() {
        this.ageGroup = ageGroup;
    }

}

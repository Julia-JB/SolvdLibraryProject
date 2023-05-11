public class ChildrensBook extends Book {

    // Fields
    private AgeGroup ageGroup;

    // Constructor
    public ChildrensBook(int id, ItemType itemType, String title, String author, double price,
                         boolean available,
                        int numberOfItemsAvailable, BookGenre genre, String ISBN, int pageCount,
                         AgeGroup ageGroup) {
        super(id, itemType, title, author, price, available, numberOfItemsAvailable, genre, ISBN, pageCount);
        this.ageGroup = ageGroup;
    }

    // Getters and Setters
    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

   public void checkAgeGroup(int userAge) {
        if (this.getAgeGroup().isAgeRange(userAge)) {
            logger.info("This book is suitable for the user's age.");
        } else {
            logger.info("This book may not suitable for the user's age.");
        }
   }
}

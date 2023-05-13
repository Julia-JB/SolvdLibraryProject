    import java.util.Map;
    import java.util.Objects;
    import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;

    public class Book extends LibraryItem {
    // Fields
        private BookGenre genre;
        private String isbn;
        private int pageCount;
        Logger logger = LogManager.getLogger(LibrarySystem.class);

    // Constructor
        public Book(int id, ItemType itemType, String title, String author, double price, boolean
                available, int numberOfItemsAvailable, BookGenre genre, String isbn,
                    int pageCount) {

            super(id, itemType,title, author, price, available, numberOfItemsAvailable);
            this.isbn = isbn;
            this.pageCount = pageCount;
            this.genre = genre;

            LibrarySystem.books.add(this);
        }

        public Book(){}

    // Getters and Setters
        public BookGenre getGenre() {
            return genre;
        }

        public void setGenre(BookGenre genre) {
            this.genre = genre;
        }

        public String getIsbn() {
            return isbn;
        }

        /**
         * This setter method sets ISBN if ISBN passes validation successfully
         * @param
         */
        public void setISBN(String isbn) {
            try {
                checkISBN(isbn);
                this.isbn = isbn;
            } catch (InvalidISBNException e) {
                logger.warn("Please check the ISBN: " + e.getMessage());
            } catch (InvalidISBNFormatException e) {
                logger.warn("An issue occurred", e);
            }
        }

        /**
         * This setter method provides validation on setting ISBN
         * @param
         */
        final void checkISBN(String isbn) throws InvalidISBNException {
            try {
                if (isbn.length() != 13) {
                    throw new InvalidISBNException("ISBN should contain 13 digits");
                }
                int sum = 0;
                for (int i = 0; i < isbn.length() - 1; i += 2) {
                    sum += Integer.parseInt(String.valueOf(isbn.charAt(i))) +
                            Integer.parseInt(String.valueOf(isbn.charAt(i + 1))) * 3;
                }

                int checkDigit = (sum % 10 == 0) ? 0 : 10 - (sum % 10);

                if (Integer.parseInt(String.valueOf(isbn.charAt(12))) != checkDigit) {
                    throw new InvalidISBNException("ISBN is not valid");
                }

            } catch (NumberFormatException e) {
                throw new InvalidISBNFormatException("ISBN should contain only digits");
            }
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        /**
         * This method calls book checkout counting method from LibrarySystem Class
         */
        public void updateCheckoutCounter() {
            BookCheckOutStatus.countBookCheckouts(this);
        }

        /**
         * This method provides checkout functionality for a book
         * @param user
         */
        public void checkoutBook(User user) {
            if (isAvailable()) {
                updateCheckoutCounter();
                checkoutItem(user);
            } else {
                logger.warn("This item is not available");
            }
        }

        /**
         * This method implements abstract method from LibraryItem class and
         * formats book info as JSON literal
         * @return
         */
        public String formatJSON() {
            String jsonString = String.format("{\n \"id\": %d, \n \"type\": \"%s\", " +
                            "\n \"title\": \"%s\", \n \"author\": \"%s\" \n \"genre\": \"%s\"," +
                            "\n \"isbn\": \"%s\", \n \"available\": %b, \n \"itemsAvailable\": %d \n}",
                    getId(), getItemType().getDisplayName(), getTitle(), getAuthor(), getGenre(), isbn,
                    isAvailable(),
                    getNumberOfItemsAvailable());
            return jsonString;
        }

        /**
         * This method overrides toString() method and formats book info
         * @return
         */
        @Override
        public String toString() {
           return String.format("\nTitle: %s\n" +
                   "Author: %s\n" +
                   "ISBN: %s",
                   getTitle(), getAuthor(), isbn);
        }

        /**
         * This method overrides equals() method to compare Book objects based on their ISBNs
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
            if (obj instanceof Book) {
                Book testObj = (Book) obj;
                return testObj.isbn == this.isbn;
            }
            return false;
        }

        /**
         * This method overrides hashCode() method based on Book object ISBN property
         * @return
         */
        @Override
        public int hashCode() {
            int hash = 7;
            hash = 31 * hash + Objects.hashCode(this.isbn);
            return hash;
        }
    }




public class Magazine extends LibraryItem {
    // Fields
    private int year;
    private int month;

    // Constructor
    public Magazine(int id, String itemType, String title, double price) {
        super(id, itemType, title, price);
        this.year = year;
        this.month = month;
    }

    // Getters and Setters
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * This method overrides toString() method and formats magazine info
     * @return
     */
    @Override
    public String toString() {
        return String.format("\n\u001B[32mTitle:\u001B[0m %s\n" +
                        "\u001B[32mYear:\u001B[0m%d" +
                  "\u001B[32mMonth:\u001B[0m%d" +
                getTitle(), year, month);
    }
    /**
     * This method implements abstract method from LibraryItem class and
     * formats book info as JSON literal
     * @return
     */
    public String formatJSON() {
        String jsonString = String.format("{\n \"id\": %d, \n \"type\": \"%s\", " +
                        "\n \"title\": \"%s\", \n \"year\": \"%d\", \n \"month\": %d \n}",
                getId(), getItemType(), getTitle(), year, month);
        return jsonString;
    }
}


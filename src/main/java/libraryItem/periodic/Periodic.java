package libraryItem.periodic;

import libraryItem.ItemType;
import libraryItem.LibraryItem;

public class Periodic extends LibraryItem {
    // Fields
    private int year;
    private int number;
    private Frequency frequency;


    // Constructor
    public Periodic(int id, ItemType itemType, String title, double price, Frequency frequency,
                    int year, int number) {
        super(id, itemType, title, price);
        this.year = year;
        this.number = number;
        this.frequency = frequency;
    }

    // Getters and Setters
    public int getYear() {
        return year;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    /**
     * This method overrides toString() method and formats magazine info
     * @return
     */
    @Override
    public String toString() {
        return String.format("\nTitle: %s\n" +
                        "Year: %d\n" +
                        "Number: %d\n" +
                        "libraryItem.periodic.Frequency: %s\n",
                getTitle(), year, number, frequency.getDisplayName());
    }

    /**
     * This method implements abstract method from libraryItem.LibraryItem class and
     * formats book info as JSON literal
     * @return
     */
    public String formatJSON() {
        String jsonString = String.format("{\n \"id\": %d, \n \"type\": \"%s\", " +
                        "\n \"title\": \"%s\", \n \"year\": \"%d\", \n \"number\": %d \n}",
                getId(), getItemType().getDisplayName(), getTitle(), year, number);
        
        return jsonString;
    }
}


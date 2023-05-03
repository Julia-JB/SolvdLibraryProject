import java.text.NumberFormat;
import java.util.Locale;

public class Librarian implements WagesCalculator {
    // Fields
    private int id;
    private String name;
    private int hourlyRate;
    private int hoursWorked;

    // Constructor
    public Librarian(int id, String name, int hourlyRate) {
        this.id = id;
        this.name = name;
        this.hourlyRate = hourlyRate;
    }

    // Getters and Setters

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

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    /**
     * This method calculates librarian wages based on hourly rate and hours worked
     */
    public int calculateWages(int hoursWorked) {
            if (hoursWorked > 0) {
                return this.hourlyRate * hoursWorked;
            } throw new InvalidHoursWorkedException("Hours of work should be greater than zero");

    }

    /**
     * This method prints out librarian wages
     */
    public void printWages(int hoursWorked) {
        String formattedWage = NumberFormat.getCurrencyInstance(Locale.US).format(calculateWages(hoursWorked));
        System.out.println("The wage is " + formattedWage);
    }
}

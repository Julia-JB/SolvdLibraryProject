package mediaCenter;

import user.User;
import librarySystem.LibrarySystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.NumberFormat;
import java.util.Locale;

public final class MediaCenter implements UsageCostCalculator {
    // Fields
    static float copyPrice;
    static float scanPrice;
    static byte studentDiscountPercent;

    // Static block assigns values to the fields
    static {
        copyPrice = 0.2f;
        scanPrice = 0.4f;
        studentDiscountPercent = 20;
    }

    Logger logger = LogManager.getLogger(LibrarySystem.class);

    // Constructor
    public MediaCenter() {
    }

    // Getters and setters
    public float getCopyPrice() {
        return copyPrice;
    }

    public float getScanPrice() {
        return scanPrice;
    }

    public void setCopyPrice(float copyPrice) {
        MediaCenter.copyPrice = copyPrice;
    }

    public void setScanPrice(float scanPrice) {
        MediaCenter.scanPrice = scanPrice;
    }

    public byte getStudentDiscount() {
        return studentDiscountPercent;
    }

    public void setStudentDiscount() {
        studentDiscountPercent = studentDiscountPercent;
    }

    // Method for cost calculation is provided in mediaCenter.UsageCostCalculator interface as a default method
    /**
     * This method prints out the formatted cost of media center usage (copier and scanner).
     * @param user
     * @param numberOfCopies
     * @param numberOfScans
     */
    public void printUsageCost(User user, int numberOfCopies, int numberOfScans) {
        double totalCost = calculateUsageCost(user, numberOfCopies, numberOfScans);
        String formattedCost = NumberFormat.getCurrencyInstance(Locale.US).format(totalCost);
        logger.info("Please pay " + formattedCost + " for using media center resources");
    }
}

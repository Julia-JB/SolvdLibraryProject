package mediaCenter;
import user.User;

public interface UsageCostCalculator {
    /**
     * This method calculates the cost of media center usage (copier and scanner).
     * A discount is provided for student users.
     * @param user
     * @param numberOfCopies
     * @param numberOfScans
     * @return double
     */
        default double calculateUsageCost(User user, int numberOfCopies, int numberOfScans) {
        float totalCopierCost = MediaCenter.copyPrice * numberOfCopies;
        float totalScannerCost = MediaCenter.scanPrice * numberOfScans;
        float totalCost = totalCopierCost + totalScannerCost;

        if (user.isStudent()) {
            totalCost = totalCost/ 100 * (100 - MediaCenter.studentDiscountPercent);
        }
        return totalCost;
    }
}

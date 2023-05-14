import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public class ReceiptFormatter {
	private double totalPriceCheckedItems = 0;
		public double calculateCheckedOutItemsCost(User user) {

			return totalPriceCheckedItems = user.getCheckedOutItems().stream()
					.mapToDouble(LibraryItem::getPrice)
					.sum();
		}

	public String formatReceipt(User user) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

				StringBuilder sb = new StringBuilder();
				sb.append("\n----------------------------");
				sb.append("\nCheck Out Receipt");
				sb.append("\n" + LibrarySystem.libraryName);
				sb.append("\n" + LibrarySystem.libraryPhoneNumber);
				sb.append("\n" + LibrarySystem.libraryWebsite);
				sb.append("\n" + LocalDate.now().format(formatter));

				if(!user.getCheckedOutItems().isEmpty()) {
					calculateCheckedOutItemsCost(user);
					for (LibraryItem item : user.getCheckedOutItems()) {
						sb.append("\nTitle: ").append(item.getTitle());
					}
					sb.append("\nDue: ").append(user.getCheckedOutItems().get(0).getReturnDate().format(formatter));
					sb.append("\nTotal items: ").append(user.getCheckedOutItems().size());

					sb.append("\nYou just saved " + NumberFormat.getCurrencyInstance(Locale.US).format(totalPriceCheckedItems) + " by using your library. " + "\nThis is suggested retail price of the item(s) checked out.");
				} else {
					sb.append("\nNo items checked out.");
				}

				return sb.toString();
		}
}

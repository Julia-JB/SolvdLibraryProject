import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ReceiptFormatter {
	private double totalPriceCheckedItems = 0;
		public double calculateCheckedOutItemsCost(User user) {
			for (LibraryItem item : user.getCheckedOutItems()){
				totalPriceCheckedItems += item.getPrice();
			}
			return totalPriceCheckedItems;
		}

	public String formatReceipt(User user) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
		calculateCheckedOutItemsCost(user);
		StringBuilder sb = new StringBuilder();
		sb.append("\n----------------------------");
		sb.append("\nCheck Out Receipt");
		sb.append("\n" + LibrarySystem.libraryName);
		sb.append("\n" + LibrarySystem.libraryPhoneNumber);
		sb.append("\n" + LibrarySystem.libraryWebsite);
		sb.append("\n" + LocalDate.now().format(formatter));
		for (LibraryItem item : user.getCheckedOutItems()) {
			sb.append("\nTitle: ").append(item.getTitle());
		}
		sb.append("\nDue: ").append(user.getCheckedOutItems().get(0).getReturnDate().format(formatter));
		sb.append("\nTotal items: ").append(user.getCheckedOutItems().size());

		sb.append("\nYou just saved " +
				NumberFormat.getCurrencyInstance(Locale.US).format(totalPriceCheckedItems)+
				" by using your library. " +
				"\nThis is suggested retail price of the item(s) checked out.");
		return sb.toString();
	}
}

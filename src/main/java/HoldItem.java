import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HoldItem <T> {
	private LibraryItem item;
	private User user;
	private LocalDateTime date;

	public HoldItem(LibraryItem item, User user, LocalDateTime date) {
		this.item = item;
		this.user = user;
		this.date = date;
	}

	public LibraryItem getItem() {
		return item;
	}

	public User getUser() {
		return user;
	}
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("\n----------------------------");
		sb.append("\nName: ").append(user.getName()).append(", \n");
		sb.append("Email: ").append(user.getEmail()).append(", \n");
		sb.append("Type: ").append(item.getItemType()).append(", \n");
		sb.append("Title: ").append(item.getTitle()).append(", \n");
		sb.append("Author: ").append(item.getAuthor()).append("\n");
		sb.append("Date: ").append(date.format(formatter));

		return sb.toString();
	}
}

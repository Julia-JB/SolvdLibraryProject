import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

	/**
	 * This method adds items to the hold queue
	 * @param holdItem
	 */
	public static void addToHoldQueue(HoldItem holdItem) {
		LibrarySystem.holdQueue.add(holdItem);
	}

	/** This method removes item from the hold queue
	 * @param holdItem
	 */
	public static void removeFromHoldQueue(HoldItem holdItem) {
		LibrarySystem.holdQueue.remove(holdItem);
	}

	/**
	 * This method prints the items in the hold queue
	 */
	public static void printQueueItems() {
		Logger logger = LogManager.getLogger(LibrarySystem.class);
		for (HoldItem holdItem : LibrarySystem.holdQueue) {
			logger.info(holdItem.toString());
		}
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

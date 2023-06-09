package libraryItem;

import user.User;

import librarySystem.LibrarySystem;

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
	 * This method overrides toString() method and displays the info of an item on hold,
	 * including user's and item's details as well as exact time of placing a hold.
	 * @return
	 */
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("\n----------------------------");
		sb.append("\nName: ").append(user.getName()).append(", \n");
		sb.append("Email: ").append(user.getEmail()).append(", \n");
		sb.append("Type: ").append(item.getItemType().getDisplayName()).append(", \n");
		sb.append("Title: ").append(item.getTitle()).append(", \n");
		sb.append("Author: ").append(item.getAuthor()).append("\n");
		sb.append("Date: ").append(date.format(formatter));

		return sb.toString();
	}
}

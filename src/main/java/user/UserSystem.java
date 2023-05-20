package user;

import lambdaFunctions.FilterLambda;
import user.User;
import libraryItem.LibraryItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserSystem implements Printable {
	public static int totalNumberOfUsers;
	public static List<User> users = new ArrayList<>();
	Logger logger = LogManager.getLogger();

	public UserSystem() {
	}

	/**
	 * This method adds a new user to the list of users and increments the total number of users counter
	 */
	public static void addUser(User user) {
		totalNumberOfUsers++;
		users.add(user);
	}

	/**
	 * This method displays the number of items borrowed by each user
	 * @return
	 */
	public Map<Integer, Integer> getBorrowedItemsNumber() {
		Function<User, Integer> countBorrowedItems = user -> user.getBorrowedItems().size();
		Map<Integer, Integer> userItemsNumber = new HashMap<>();

		users.forEach(user ->
				userItemsNumber.put(user.getId(), countBorrowedItems.apply(user)));

		logger.info(userItemsNumber);
		return userItemsNumber;
	}

	/**
	 * This method displays all users with items borrowed
	 * @return
	 */
	public List<String> getUsersWithBorrowedItems() {
		List<String> usersAndItems = users.stream()
				.filter(user -> user.getBorrowedItems().size() > 0)
				.map(user -> user.getName() + user.getBorrowedItems())
				.collect(Collectors.toList());

		return usersAndItems;
	}

	/**
	 * This method gets users with overdue items
	 * @return
	 */
	public void getUsersWithOverdueItems() {
		HashMap<User, List<LibraryItem>> usersWithOverdueItems = new HashMap<>();
		List<LibraryItem> overdueItems = new ArrayList<>();
		for (User user : users) {
			if (user.getBorrowedItems().size() != 0) {
				 overdueItems = user.getBorrowedItems().stream()
						.filter(item -> item.getReturnDate() != null && LocalDate.now().isAfter(item.getReturnDate()))
						.collect(Collectors.toList());
					}
			if (!overdueItems.isEmpty()) {
				usersWithOverdueItems.put(user, overdueItems);
			}
		}

		if (usersWithOverdueItems.size() == 0) {
			logger.info("No users with overdue items");
		} else {
			logger.info(usersWithOverdueItems);
		}
	}

	/**
	 * This method displays a list of student users.
	 * @return
	 */
	public List<User> getStudentUsers() {
		List<User> studentUsers = FilterLambda.filter(users, FilterLambda.studentUser);
		logger.info(studentUsers);
		return studentUsers;
	}

	/**
	 * This method prints names of all users.
	 */
	public void printAllUsersNames() {
		List<String> userNames = new ArrayList<>();
		users.forEach(user -> userNames.add(user.getName()));
		logger.info("The users of the library are: " + userNames);
	}

	/**
	 * This method prints total number of library users
	 */
	public void printTotalNumberOfUsers() {
		logger.info("Total number of users: " + totalNumberOfUsers);
	}
}

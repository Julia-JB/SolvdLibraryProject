package sqlService;

import user.User;

import java.sql.SQLException;

public class UserInsertTask implements Runnable {
	private UserDAO userDAO;
	private User user;

	public UserInsertTask(UserDAO userDAO, User user) {
		this.userDAO = userDAO;
		this.user = user;
	}

	@Override
	public void run() {
		try {
			userDAO.insertUser(user);
			System.out.println(user.getName() + " was inserted. Hello from thread " + Thread.currentThread().getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

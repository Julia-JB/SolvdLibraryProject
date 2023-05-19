import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	private final String url = Keys.SQL_URL.getKey();
	private final String USERNAME = Keys.SQL_USERNAME.getKey();
	private final String PASSWORD = Keys.SQL_PASSWORD.getKey();

	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);

			if (!connection.isClosed()) {
				System.out.println("Connected to the database!");
			} else {
				System.out.println("Failed to make connection!");
			}

			if (connection.isClosed()) {
				System.out.println("Disconnected");
			} else {
				System.out.println("Still connected");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}

// in Main
//        MySQLConnection mySQLConnection = new MySQLConnection();
//        Connection connection = mySQLConnection.getConnection();

//        try {
//            UserDAO userDAO = new UserDAO(connection);
//            userDAO.insertUser(user1);
//            connection.close();
//            if (connection.isClosed()) {
//                System.out.println("Disconnected");
//            } else {
//                System.out.println("Still connected");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

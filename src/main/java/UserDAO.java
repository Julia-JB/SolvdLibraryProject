import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
	private Connection connection;

	public UserDAO(Connection connection) {
			this.connection = connection;
		}

		public void insertUser(User user) throws SQLException {
		Statement statement = connection.createStatement();
		String sql = String.format("insert into users (name, email, student) " +
						"values('%s', '%s', %s)",
						user.getName(), user.getEmail(), user.isStudent() ? "1" : "0");
		statement.execute(sql);
		}
}


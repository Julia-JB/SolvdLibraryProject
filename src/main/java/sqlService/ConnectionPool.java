package sqlService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import extra.Keys;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionPool {
	private final String url = Keys.SQL_URL.getKey();
	private final String USERNAME = Keys.SQL_USERNAME.getKey();
	private final String PASSWORD = Keys.SQL_PASSWORD.getKey();
	private int poolSize;
	private BlockingQueue<Connection> connectionBlockingQueue;

	public ConnectionPool(int poolSize) {
		connectionBlockingQueue = new ArrayBlockingQueue<>(poolSize);

		for (int i = 0; i < poolSize; i++) {
			Connection connection = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			this.connectionBlockingQueue.offer(connection);
		}
	}

	public Connection getConnection() throws InterruptedException {
		return this.connectionBlockingQueue.take();
	}

	public void releaseConnection(Connection connection) {
		this.connectionBlockingQueue.offer(connection);
	}
}

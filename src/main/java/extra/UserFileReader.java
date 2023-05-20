package extra;

import user.User;
import librarySystem.LibrarySystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserFileReader {
    public static String userdata = "my-app/src/main/resources/userdata.csv";
    public UserFileReader(String userdata) {
    }

    /**
     * This method creates users of the library by reading data from data/userdata.csv file.
     * It demonstrates using try-catch with resources
     * @param numUsers
     * @return
     */
    public static void createUsers(int numUsers) {
        Logger logger = LogManager.getLogger(LibrarySystem.class);
        try (BufferedReader reader = new BufferedReader(new FileReader(userdata))) {
            for (int i = 1; i <= numUsers; i++) {
                String line = reader.readLine();
                if (line == null) {
                    throw new IOException("Not enough lines in the line");
                }
                String[] tokens = line.split(",");
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                String email = tokens[2];
                String phoneNumber = tokens[3];
                boolean isStudent = Boolean.parseBoolean(tokens[4]);

                User user = new User(id, name, email, phoneNumber, isStudent);
            }
        } catch (IOException e) {
           logger.error(e.getMessage());
        }
    }

}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    private String username;
    private String password;
    private String email;

    public User(String u, String p, String e) {
        this.username = u;
        this.password = p;
        this.email = e;
    }

    public int addToDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            System.out.println(456);
            String sql = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.username);
            statement.setString(2, this.password);
            statement.setString(3, this.email);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
                return 0;
            }

        } catch (SQLException exception) {
            System.out.println(exception);;
        }
        return 1;
    }
}

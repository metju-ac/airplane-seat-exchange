import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private final String username;
    private String password;
    private String email;

    public User(String u, String p, String e) {
        this.username = u;
        this.password = p;
        this.email = e;
    }

    private Boolean checkUsername() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "SELECT * FROM Users WHERE username = (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean checkEmail() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "SELECT * FROM Users WHERE email = (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean checkEmailFormat() {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.email);
        return matcher.matches();
    }

    private Boolean checkPassword() {
        return (password.length() >= 8);
    }

    public int addToDatabase() {
        if (! checkUsername()) {
            return 1;
        }
        if (! checkEmail()) {
            return 2;
        }
        if (! checkEmailFormat()) {
            return 3;
        }
        if (! checkPassword()) {
            return 4;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
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

import java.sql.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private final String username;
    private char[] password;
    private String email;
    private int score;

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public int getScore() {
        return this.score;
    }

    public User(String u, char[] p, String e) {
        this.username = u;
        this.password = p;
        this.email = e;
        this.score = 0;
    }

    public User(String username) {
        this.username = username;
    }

    private Boolean checkUsername() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "SELECT * FROM user WHERE username = (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.username);
            ResultSet rs = statement.executeQuery();
            return !rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean checkEmail(String email) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "SELECT * FROM user WHERE email = (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            return !rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean checkEmailFormat(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private Boolean checkPassword(String password) {
        return (password.length() >= 8);
    }

    public int addToDatabase() {
        if (! checkUsername()) {
            return 1;
        }
        if (! checkEmail(this.email)) {
            return 2;
        }
        if (! checkEmailFormat(this.email)) {
            return 3;
        }
        if (! checkPassword(new String(this.password))) {
            return 4;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "INSERT INTO user (username, password, email, score) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.username);
            statement.setString(2, new String(this.password));
            statement.setString(3, this.email);
            statement.setInt(4, this.score);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return 0;
            }

        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return -1;
    }

    public int Login(char[] password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "SELECT * FROM user WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.username);
            ResultSet rs = statement.executeQuery();

            if (! rs.next()) {
                return 1;
            }
            if (!Objects.equals(rs.getString("password"), new String(password))) {
                return 2;
            }

            this.password = password;
            this.email = rs.getString("email");
            this.score = rs.getInt("score");
            return 0;

        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return -1;
    }

    public int changeEmail(String oldEmail, String newEmail, char[] password) {
        if (!Objects.equals(oldEmail, this.email)) {
            return 1;
        }
        if (!Arrays.equals(password, this.password)) {
            return 2;
        }
        if (!checkEmailFormat(newEmail)) {
            return 3;
        }
        if (!checkEmail(newEmail)) {
            return 4;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "UPDATE user SET email = ? WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newEmail);
            statement.setString(2, this.username);

            int rowsChanged = statement.executeUpdate();
            if (rowsChanged > 0) {
                this.email = newEmail;
                return 0;
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return -1;
    }

    public int changePassword(char[] oldPassword, char[] newPassword) {
        if (!Arrays.equals(oldPassword, this.password)) {
            return 1;
        }
        if (!checkPassword(new String(newPassword))) {
            return 2;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "UPDATE user SET password = ? WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, new String(newPassword));
            statement.setString(2, this.username);

            int rowsChanged = statement.executeUpdate();
            if (rowsChanged > 0) {
                this.password = newPassword;
                return 0;
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return -1;
    }

    public int deleteAccount(char[] password) {
        if (!Arrays.equals(password, this.password)) {
            return 1;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "DELETE FROM user WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.username);

            int rowsChanged = statement.executeUpdate();
            if (rowsChanged > 0) {
                return 0;
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return -1;
    }
}

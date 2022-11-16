import java.sql.*;
import java.util.ArrayList;

public class CountryManager {
    public CountryManager(){}

    public ArrayList<Country> getCountries() {
        ArrayList<Country> countries = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "SELECT  * FROM country";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            int countryId;
            String countryName;
            String countryCode;
            while (rs.next()) {
                countryId = rs.getInt("id");
                countryName = rs.getString("country_name");
                countryCode = rs.getString("country_code");
                countries.add(new Country(countryId, countryName, countryCode));
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }

        return countries;
    }
}

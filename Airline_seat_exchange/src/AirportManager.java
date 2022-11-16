import java.sql.*;
import java.util.ArrayList;

public class AirportManager {
    public AirportManager() {}

    public ArrayList<Airport> getAirports() {
        ArrayList<Airport> airports = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "SELECT  * FROM airport";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            int airportId;
            int countryId;
            String airportName;

            while (rs.next()) {
                airportId = rs.getInt("id");
                countryId = rs.getInt("airport_country_id");
                airportName = rs.getString("airport_name");
                airports.add(new Airport(airportId, countryId, airportName));
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }

        return airports;
    }

    public ArrayList<Airport> getAirports(Country country) {
        ArrayList<Airport> airports = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "SELECT  * FROM airport WHERE airport_country_id = (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, country.getId());

            ResultSet rs = statement.executeQuery();
            int airportId;
            int countryId;
            String airportName;

            while (rs.next()) {
                airportId = rs.getInt("id");
                countryId = rs.getInt("airport_country_id");
                airportName = rs.getString("airport_name");
                airports.add(new Airport(airportId, countryId, airportName));
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }

        return airports;
    }
}

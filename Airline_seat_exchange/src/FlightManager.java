import java.sql.*;
import java.util.ArrayList;

public class FlightManager {
    public FlightManager() {}

    public ArrayList<Flight> getFlights() {
        ArrayList<Flight> flights = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "SELECT  * FROM flight";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            int flightId;
            String flightCode;
            int airportFromId;
            int airportToId;
            Timestamp departure;
            Timestamp arrival;

            while (rs.next()) {
                flightId = rs.getInt("id");
                flightCode = rs.getString("flight_code");
                airportFromId = rs.getInt("from_airport_id");
                airportToId = rs.getInt("to_airport_id");
                departure = rs.getTimestamp("flight_departure_time");
                arrival = rs.getTimestamp("flight_arrival_time");
                flights.add(new Flight(flightId, flightCode, airportFromId, airportToId, departure, arrival));
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }

        return flights;
    }

    public ArrayList<Flight> getFlights(Airport airportFrom, Airport airportTo) {
        ArrayList<Flight> flights = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airplane_seat_exchange",
                "root", "Rootroot")) {
            String sql = "SELECT  * FROM flight WHERE from_airport_id = (?) AND to_airport_id = (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, airportFrom.getId());
            statement.setInt(2, airportTo.getId());

            ResultSet rs = statement.executeQuery();
            int flightId;
            String flightCode;
            int airportFromId;
            int airportToId;
            Timestamp departure;
            Timestamp arrival;

            while (rs.next()) {
                flightId = rs.getInt("id");
                flightCode = rs.getString("flight_code");
                airportFromId = rs.getInt("from_airport_id");
                airportToId = rs.getInt("to_airport_id");
                departure = rs.getTimestamp("flight_departure_time");
                arrival = rs.getTimestamp("flight_arrival_time");
                flights.add(new Flight(flightId, flightCode, airportFromId, airportToId, departure, arrival));
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }

        return flights;
    }
}

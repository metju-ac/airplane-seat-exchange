import java.sql.Timestamp;

public class Flight {
    private final int id;
    private final String flightCode;
    private final int fromAirportId;
    private final int toAirportId;
    private final Timestamp departure;
    private final Timestamp arrival;

    public int getId() {
        return this.id;
    }

    public Flight(int id, String code, int fromId, int toId, Timestamp dep, Timestamp arr) {
        this.id = id;
        this.flightCode = code;
        this.fromAirportId = fromId;
        this.toAirportId = toId;
        this.departure = dep;
        this.arrival = arr;
    }

    public String toString() {
        return this.flightCode;
    }
}
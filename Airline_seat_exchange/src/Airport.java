public class Airport {
    private final int id;
    private final int countryId;
    private final String name;

    public int getId() {
        return this.id;
    }
    public int getCountryId() {
        return this.countryId;
    }
    public String getName() {
        return this.name;
    }

    public Airport(int i, int c, String n) {
        this.id = i;
        this.countryId = c;
        this.name = n;
    }

    public String toString() {
        return this.name;
    }
}

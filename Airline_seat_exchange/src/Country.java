public class Country {
    private final int id;
    private final String name;
    private final String code;

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getCode() {
        return this.code;
    }

    public Country(int i, String n, String c) {
        this.id = i;
        this.name = n;
        this.code = c;
    }

    public String toString() {
        return this.name;
    }
}

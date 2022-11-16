public class Country {
    private final int id;
    private final String name;
    private final String code;

    public int getId() {
        return this.id;
    }
    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }

    public Country(int i, String n, String c) {
        this.id = i;
        this.name = n;
        this.code = c;
    }
}

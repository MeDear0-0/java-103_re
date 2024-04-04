package exam.base;

// change this class to be an enum
public class ToBeEnum {
    private final int year;
    private final String name;

    public ToBeEnum(int year, String name) { this.year = year; this.name = name; }
    public int getYear() { return year; }
    public String getName() { return name; }
    @Override public String toString() { return "ToBeEnum(" + year + ":" + name + ")"; }
}

package Store;

public class listItem {
    private String name;
    private Integer count;

    public listItem(String name1, Integer stock1) {
            name = name1;
            count = stock1;
    }

    public String get_name () {
        return name;
    }
    public Integer get_count () {
        return count;
    }
}

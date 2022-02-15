package Staff;

//example of abstraction
abstract class Staff {

    private String name;

    public Staff(String name1) {
        name = name1;
    }

    public String get_name() {
        return name;
    }

}

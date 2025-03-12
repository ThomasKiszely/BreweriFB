import java.util.List;

public class Soda {
    private String name;
    List<Soda> sodaList;

    public Soda(String name) {
        this.name = name;
    }
    public List<Soda> getSodaList() {
        return sodaList;
    }
    public void setSodaList(List<Soda> sodaList) {
        this.sodaList = sodaList;
    }
    @Override
    public String toString() {
        return name;
    }
}

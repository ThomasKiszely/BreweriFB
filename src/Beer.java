import java.util.List;

public class Beer {
    private String name;
    List<Beer> beerList;

    public Beer(String name) {
        this.name = name;
    }
    public List<Beer> getBeerList() {
        return beerList;
    }
    public void setBeerList(List<Beer> beerList) {
        this.beerList = beerList;
    }
    @Override
    public String toString() {
        return name;
    }
}

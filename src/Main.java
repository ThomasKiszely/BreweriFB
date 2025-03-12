import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Buffer counter = new Buffer();
        List<Beer> beerList = new ArrayList<Beer>();
        List<Soda> sodaList = new ArrayList<Soda>();

        BeerProducer beerProducer = new BeerProducer(beerList);
        BeerConsumer beerConsumer = new BeerConsumer(beerList);
        SodaProducer sodaProducer = new SodaProducer(sodaList);
        SodaConsumer sodaConsumer = new SodaConsumer(sodaList);

        Thread duffProducer = new Thread(beerProducer);
        Thread duffConsumer = new Thread(beerConsumer);
        Thread buzzProducer = new Thread(sodaProducer);
        Thread buzzConsumer = new Thread(sodaConsumer);
        duffProducer.start();
        duffConsumer.start();
        buzzProducer.start();
        buzzConsumer.start();

    }
}
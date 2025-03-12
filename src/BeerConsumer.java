import java.util.ArrayList;
import java.util.List;

public class Consumer implements Runnable {
    Buffer beerCounter;
    private Boolean switcher = true;

    List<Beer> beerList;
    List<Soda> sodaList;
//    Producer beerProducer = new Producer(beerList);

    public Consumer(List<Beer> beerList, List<Soda> sodaList) {
        this.beerList = beerList;
        this.sodaList = sodaList;
    }
    @Override
    public void run() {
        consume();
    }

    private void consume(){
        consumeBeer();
    }
    private void consumeBeer() {
        while (true){
            synchronized (beerList) {
                for (int i = 9; i >= 0; i--) {
                    System.out.println("Now consuming " + beerList.get(i));
                    beerList.remove(beerList.get(i));
                    if (beerList.isEmpty()) {
                        beerList.notify();
                        try {
                            beerList.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

        }
    }
    private void consumeSoda() {
        while (true){
            synchronized (sodaList) {
                for (int i = 0; i < sodaList.size(); i++) {
                    System.out.println("Now consuming " + sodaList.get(i));
                    sodaList.remove(sodaList.get(i));
                    if (sodaList.isEmpty()) {
                        sodaList.notify();
                        try {
                            sodaList.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

        }
    }
}

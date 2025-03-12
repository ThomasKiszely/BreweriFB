import java.util.List;

public class BeerConsumer implements Runnable {
    Buffer beerCounter;

    List<Beer> beerList;
    List<Soda> sodaList;


    public BeerConsumer(List<Beer> beerList) {
        this.beerList = beerList;
    }
    @Override
    public void run() {
        consume();
    }

    private void consume(){
        consumeBeer();
    }
    private synchronized void consumeBeer() {
        while (true){
            synchronized (beerList) {
                for (int i = 9; i >= 0; i--) {
                    System.out.println("Now consuming " + beerList.get(i));
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    beerList.remove(beerList.get(i));
                    if (beerList.isEmpty()) {
                        beerList.notify();
                        try {
                            beerList.wait();
                            System.out.println("Now waiting for beerconsumer");
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

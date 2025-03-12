import java.util.List;

public class SodaConsumer implements Runnable {
    Buffer beerCounter;

    List<Beer> beerList;
    List<Soda> sodaList;


    public SodaConsumer(List<Soda> sodaList) {
        this.sodaList = sodaList;
    }
    @Override
    public void run() {
        consume();
    }

    private void consume(){
        consumeSoda();
    }

    private synchronized void consumeSoda() {
        while (true){
            synchronized (sodaList) {
                for (int i = 9; i >= 0; i--) {
                    System.out.println("Now consuming " + sodaList.get(i));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    sodaList.remove(sodaList.get(i));
                    if (sodaList.isEmpty()) {
                        sodaList.notify();
                        try {
                            sodaList.wait();
                            System.out.println("Now waiting for sodaconsumer");
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}


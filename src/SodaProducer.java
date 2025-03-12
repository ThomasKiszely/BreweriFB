import java.util.List;

public class SodaProducer implements Runnable {
    Buffer beerCounter;

    List<Soda> sodaList;

    public SodaProducer(List<Soda> sodaList) {

        this.sodaList = sodaList;
    }


    @Override
    public void run() {
        produce();
    }

    private void produce() {
        produceSoda();
    }

    private synchronized void produceSoda() {
        while(true){
        synchronized (sodaList) {
            for (int i = 0; i < 10; i++) {
                sodaList.add(new Soda("Buzz Cola " + i));
                System.out.println("Now producing " + sodaList.get(i));
                if (sodaList.size() == 10) {
                    sodaList.notify();
                    try {
                        sodaList.wait();
                        System.out.println("Now waiting for sodaproducer");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    }
                }
            }
        }
    }
}

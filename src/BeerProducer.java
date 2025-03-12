import java.util.List;

public class BeerProducer implements Runnable {
    Buffer beerCounter;


    List<Beer> beerList;
    List<Soda> sodaList;

    public BeerProducer(List<Beer> beerList) {
        this.beerList = beerList;
    }


    @Override
    public void run() {
        produce();
    }

    private void produce() {
        produceBeer();
    }

    private synchronized void produceBeer() {
        while (true) {
            synchronized (beerList) {
                for (int i = 0; i < 10; i++) {
                    beerList.add(new Beer("Duff " + i));
                    System.out.println("Now producing " + beerList.get(i));
                    if (beerList.size() == 10) {
                        beerList.notify();
                        try {
                            beerList.wait();
                            System.out.println("Now waiting for beerproducer");
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }



    private void produceSoda() {
            synchronized (sodaList) {
                for (int i = 0; i < 10; i++) {
                    sodaList.add(new Soda("Buzz Cola " + i));
                    System.out.println("Now producing " + sodaList.get(i));
                    if (sodaList.size() == 10) {
                        beerList.notify();
                        try {
                            produceBeer();
                            sodaList.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }





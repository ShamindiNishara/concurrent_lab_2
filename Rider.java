public class Rider implements Runnable {
    int riderIndex;

    public Rider(int riderIndex) {
        this.riderIndex = riderIndex;
    }

    @Override
    public void run() {
        try {
            SharedResources.inWaiting.acquire();
            System.out.println("Rider Number: " + this.riderIndex + " entered the bus stop");

            SharedResources.mutex.acquire();
            SharedResources.riders++;
            SharedResources.mutex.release();

            SharedResources.busArrived.acquire();
            SharedResources.inWaiting.release();
            boardBus();

            SharedResources.riders--;

            if (SharedResources.riders == 0) {
                System.out.println("All riders got in. Bus departing...");
                SharedResources.fullyBoarded.release();
            } else {
                SharedResources.busArrived.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void boardBus() {
        System.out.println("Rider Number: " + this.riderIndex + " boarded");
    }
}

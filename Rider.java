public class Rider implements Runnable {
    int riderIndex;

    public Rider(int riderIndex) {
        this.riderIndex = riderIndex;
    }

    @Override
    public void run() {
        try {
            SharedResources.waitingRiders.acquire();
            System.out.println("Rider " + this.riderIndex + " arrived at the bus stop");

            SharedResources.mutex.acquire();
            SharedResources.riders++;
            SharedResources.mutex.release();

            SharedResources.busArrived.acquire(); //wait until bus  come to board
            SharedResources.waitingRiders.release(); // release for another rider can wait at the bus stop under the maximum capacity of 50
            boardBus();

            SharedResources.riders--; //protected by busArrived semaphore 

            if (SharedResources.riders == 0) { //last rider boarded to the bus
                System.out.println("All riders got in. Bus departing");
                SharedResources.fullyBoarded.release();
            } else { //let all waiting riders to boarded into the bus sequentially
                SharedResources.busArrived.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void boardBus() {
        System.out.println("Rider " + this.riderIndex + " boarded");
    }
}

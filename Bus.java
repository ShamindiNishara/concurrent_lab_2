public class Bus implements Runnable {
    int busIndex;

    public Bus(int busIndex) {
        this.busIndex = busIndex;
    }

    @Override
    public void run() {
        try {
            SharedResources.mutex.acquire();
            System.out.println("Bus arrived at the stop. " + SharedResources.riders + " riders waiting to board");

            if (SharedResources.riders > 0) {
                SharedResources.busArrived.release();
                SharedResources.fullyBoarded.acquire();
            } else {
                System.out.println("Bus leaving because no riders in the bus stop"); 
            }

            SharedResources.mutex.release(); //riders arrive while the bus is boarding has to wait until mutex release-->next bus comes
            depart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void depart() {
        System.out.println("Bus " + this.busIndex + " departed");
    }
}
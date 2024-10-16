import java.util.Random;

public class RiderScheduler implements Runnable {
    static Random random;
    float riderArrivalMean;

    public RiderScheduler(float riderArrivalMean) {
        this.riderArrivalMean = riderArrivalMean;
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            //a rider arrives at the bus stop
            Rider rider = new Rider(SharedResources.riderIndex);
            Thread riderThread = new Thread(rider);
            riderThread.start();

            try {
                Thread.sleep(this.riderSleepTime(riderArrivalMean, random));//rider sheduler sleeps 
                SharedResources.riderIndexIncrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private long riderSleepTime(float riderArrivalMean, Random random) {
        float lambda = 1 / riderArrivalMean;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
}

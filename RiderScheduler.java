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
            Rider passenger = new Rider(SharedResources.riderIndex);
            Thread passengerThread = new Thread(passenger);
            passengerThread.start();

            try {
                Thread.sleep(this.calcRiderSleepTime(riderArrivalMean, random));
                SharedResources.riderIndexIncrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private long calcRiderSleepTime(float riderArrivalMean, Random random) {
        float lambda = 1 / riderArrivalMean;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
}

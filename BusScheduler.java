import java.util.Random;

public class BusScheduler implements Runnable {
    static Random random;
    float busArrivalMean;

    public BusScheduler(float busArrivalMean) {
        this.busArrivalMean = busArrivalMean;
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            Bus driver = new Bus(SharedResources.busIndex);
            Thread driverThread = new Thread(driver);
            driverThread.start();

            try {
                Thread.sleep(this.calcBusSleepTime(busArrivalMean, random));
                SharedResources.busIndexIncrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private long calcBusSleepTime(float busArrivalMean, Random random) {
        float lambda = 1 / busArrivalMean;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
}

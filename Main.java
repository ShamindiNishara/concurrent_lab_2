public class Main {
    //  rider arrival mean time 
    static float riderArrivalMean = 30f * 1000;
    // bus arrival mean time
    static float busArrivalMean = 20 * 60f * 1000;

    public static void main(String[] ariderSchedulers) {
        // Create rider scheduler to schedule riders
        RiderScheduler riderScheduler = new RiderScheduler(riderArrivalMean);
        // Create bus scheduler to schedule buses
        BusScheduler busScheduler = new BusScheduler(busArrivalMean);

      
        Thread riderSchedulerThread = new Thread(riderScheduler);
        Thread busSchedulerThread = new Thread(busScheduler);

        
        riderSchedulerThread.start();
        busSchedulerThread.start();
    }
}

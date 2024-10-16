import java.util.concurrent.Semaphore;

public class SharedResources {

    public static int riderIndex = 1;

    public static int busIndex = 1;

    //  to hold the number of riders waiting to board the bus
    public static int riders = 0;

    //  to protect riders variable
    public static Semaphore mutex = new Semaphore(1);

    //to allow riders to enter the bus stop. maximum 50 threads can enter at a time
    public static Semaphore waitingRiders = new Semaphore(50);

    // to signal whether a person can board the bus(only after a bus has arrived)
    public static Semaphore busArrived = new Semaphore(0); 

    // to signal whether all waiting riders has boarded the bus
    public static Semaphore fullyBoarded = new Semaphore(0);

   
    public static void riderIndexIncrement() {
        SharedResources.riderIndex++;
    }


    public static void busIndexIncrement() {
        SharedResources.busIndex++;
    }
    
}

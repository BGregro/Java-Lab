import static java.lang.Thread.sleep;

public class Consumer { //implements Runnable {
    String str;
    Fifo storage;
    int sleep;

    public Consumer(Fifo f, String s, int n) {
        storage = f;
        str = s;
        sleep = n;
    }

    public void run() {
        System.out.println("consumed" + str + " " + storage.get() + System.currentTimeMillis());
        /*
        System.out.println("consumed" + str + " " + storage.get() + System.currentTimeMillis());

        while (true) {
            System.out.println("consumed" + str + " " + storage.get() + System.currentTimeMillis());
            try {
                sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("XD");
            }
        }
        */
    }
}

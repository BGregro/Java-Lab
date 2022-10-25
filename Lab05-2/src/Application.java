import static java.lang.Thread.sleep;

import java.util.concurrent.CountDownLatch;

public class Application {
    public static void main(String[] args) throws Exception {
        Fifo storage = new Fifo();
        
        Consumer cm = new Consumer(storage, "consumer", 100);
        cm.run();
    }
}

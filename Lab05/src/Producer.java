import static java.lang.Thread.sleep;

public class Producer implements Runnable {
    public Producer() {

    }

    public void go() throws InterruptedException {
        while (true) {
            sleep(1000);
            
        }
    }

    public void run() {

    }
}

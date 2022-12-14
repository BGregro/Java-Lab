import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable {
	private Fifo storage;
	private String message;
	private int sleepTime;

	public Consumer(Fifo f, String s, int n) {
		storage = f;
		message = s;
		sleepTime = n;
	}
	
	public void run() {
		while(true) {
			try {
				 System.out.println("consumed " + message + " " + storage.get() + " " + System.currentTimeMillis());
				 sleep(sleepTime);
			} catch (InterruptedException ex) {
				Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}

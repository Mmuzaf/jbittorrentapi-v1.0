package examples;

import java.util.concurrent.TimeUnit;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage examples
 * By @author Mmuzafarov
 * Created at 03.07.14
 */
public class LiftOff implements Runnable {
    protected int countDown = 10; // Значение по умолчанию
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff() {}
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }
    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }
    public void run() {
        while(countDown-- > 0) {
            try {
            System.out.print(status());
            TimeUnit.MILLISECONDS.sleep(Math.round(Math.random()*10));

        } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
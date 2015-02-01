package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage concurrency
 * By @author Mmuzaf
 * Created at 13.08.14
 */
public class Operations2 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        final Account a = new Account(1000);
        final Account b = new Account(2000);

        for(int i = 0; i < 10; i++) {
            service.submit(new Transfer(i, a, b, new Random().nextInt(300)));
        }

        service.shutdown();
    }
}

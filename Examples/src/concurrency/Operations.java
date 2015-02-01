package concurrency;

import javax.naming.InsufficientResourcesException;
import java.util.concurrent.TimeUnit;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage concurrency
 * By @author Mmuzaf
 * Created at 12.08.14
 */
public class Operations {

    public static long WAIT_SEC = 5;

    public static void main(String[] args) {
        final Account a = new Account(1000);
        final Account b = new Account(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    transfer(a, b, 100);
                } catch (InsufficientFundsException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            transfer(b, a, 200);
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        }
    }

    static void transfer(Account acc1, Account acc2, int amount) throws InsufficientFundsException {
        try {
            if (acc1.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
                try {
                    if (acc2.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
                        try {
                            if (acc1.getBalance() < amount) {
                                throw new InsufficientFundsException();
                            }

                            acc1.withDraw(amount);
                            acc2.deposit(amount);
                            System.out.println("Transfer amount " + amount);
                        } finally {
                            acc2.getLock().unlock();
                        }
                    } else {
                        System.out.println("Unable to lock acc2");
                    }
                } finally {
                    acc1.getLock().unlock();
                }
            } else {
                System.out.println("Unable to lock acc1");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

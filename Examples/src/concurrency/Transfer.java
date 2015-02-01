package concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage concurrency
 * By @author Mmuzaf
 * Created at 13.08.14
 */
public class Transfer implements Callable<Boolean> {

    public static long WAIT_SEC = 3;
    private int id;

    private Account fromAcc;
    private Account toAcc;
    private int amount;

    public Transfer(int id, Account fromAcc, Account toAcc, int amount) {
        this.id = id;
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        this.amount = amount;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            if (fromAcc.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
                try {
                    if (toAcc.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
                        try {
                            if (fromAcc.getBalance() < amount) {
                                throw new InsufficientFundsException();
                            }

                            fromAcc.withDraw(amount);
                            toAcc.deposit(amount);
                            System.out.println("id=" + id + " and transfer amount " + amount);

                            Thread.sleep(new Random().nextInt(5000));
                        } finally {
                            toAcc.getLock().unlock();
                        }
                    } else {
                        toAcc.incrementFailCounter();
                        System.out.println("Unable to lock acc2");
                        return Boolean.FALSE;
                    }
                } finally {
                    fromAcc.getLock().unlock();
                }
            } else {
                fromAcc.incrementFailCounter();
                System.out.println("Unable to lock acc1");
                return Boolean.FALSE;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }
}

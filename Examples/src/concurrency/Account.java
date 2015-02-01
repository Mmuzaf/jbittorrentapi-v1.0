package concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage concurrency
 * By @author Mmuzaf
 * Created at 12.08.14
 */
public class Account {

    private int balance;
    Lock lock;
    private AtomicInteger failCounter;

    public Account(int initialBalance) {
        this.balance = initialBalance;
        this.lock = new ReentrantLock();
        this.failCounter = new AtomicInteger(0);
    }

    public void withDraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void incrementFailCounter() {
        this.failCounter.addAndGet(1);
    }

    public int getBalance() {
        return balance;
    }

    public Lock getLock() {
        return lock;
    }

    public int getFailCounter() {
        return failCounter.get();
    }

}

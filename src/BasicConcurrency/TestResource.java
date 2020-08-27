package BasicConcurrency;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestResource  {

    /**
     * you have 2 methods
     * and access to them should be within separate locks
     *
     * //A lock impl must have lock.lock() and lock.unlock() (unlock in finally)
     *
     */
    public final Lock ReadLock;
    public final Lock ShowLock;

    public TestResource() {
        ReadLock = new ReentrantLock();
        ShowLock = new ReentrantLock();
    }

    //use ShowLock
    public void displayContent(int mySeq) {
        final Lock showLock = this.ShowLock;
        try {
            showLock.lock();
            long duration = 100 * (long)(1+ 100 * Math.random()) ;
            //long duration = 10000 * (long)Math.random();
            System.out.println("Showing document: " + mySeq + " " + new Date()
                    + " for duration : "  +duration);
            Thread.sleep(duration);
        }
        catch(InterruptedException e ) {
            System.out.println("display interrupted");
        }
        finally {
            System.out.println("Displayed document: " + mySeq + " " + new Date());
            showLock.unlock();
        }


    }

    //use ReadLock
    public void readContent(int mySeq) {
        final Lock readLock = this.ReadLock;
        readLock.lock();
        try {
            long duration = 100 * (long)(1+ 100 * Math.random()) ;
            System.out.println("Reading document: " + mySeq + " " + new Date()
                               + " for duration : "  +duration);
            Thread.sleep(duration);
        }
        catch(InterruptedException e ) {
            System.out.println("read interrupted");
        }
        finally {
            System.out.println("Read document: " + mySeq + " " + new Date());
            readLock.unlock();
        }
    }


}

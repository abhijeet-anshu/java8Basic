package WinnerThread;

import java.util.concurrent.Semaphore;

/**
 * A semaphore will have t main methods
 * semaphore constructor
 * semaphore.acquire() - the thread if it calls this will sleep until semaphore is acquired
 * semaphore.release() -  release on being called will release the semaphore, thus making the above sleeping thread
 * active
 */

public class SemaphoreWinner {
    Semaphore semaphore = new Semaphore(0);
    String winnerName = null;

    synchronized void finished(String threadName) {

        if(winnerName==null) {
            winnerName = threadName;
        }
        semaphore.release();
    }

    /*@FunctionalInterface
    public interface myRunnable {
        void run();
    }*/

    void createThread(String threadName) {
        long duration =  100 * (long)(1+ 100 * Math.random()) ;
        try {
            System.out.println(threadName +  " Thread active for duration = " + duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("!!!!!!! " + threadName + " !!!!!!!");
            this.finished(threadName);
        }
    }

    private void runThreads() {
        Thread t1 = new Thread( () -> {createThread("thread 1~");});
        Thread t2 = new Thread( () -> {createThread("thread @2");});

        t1.start();
        t2.start();

        try {
            semaphore.acquire();
            System.out.println("The winner is " + winnerName);
        } catch (InterruptedException e) {
            System.out.println("Interrupted!! no winner");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SemaphoreWinner().runThreads();
    }




}

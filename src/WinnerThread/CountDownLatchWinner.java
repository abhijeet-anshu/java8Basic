package WinnerThread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchWinner {
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private String WinnerName = null;

    private  synchronized  void finished(String threadName) {
        if(WinnerName==null) {
            WinnerName = threadName;
        }
        countDownLatch.countDown(); //decrease to 0
    }

    private void createThread(String threadName) {
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
        Thread t1 = new Thread(() -> createThread("Thread-ABC")  );
        Thread t2 = new Thread(() -> createThread("Thread-TUV")  );

        t1.start();
        t2.start();

        try {
            countDownLatch.await();
            System.out.println("The Winner thread is: " + WinnerName);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted!!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CountDownLatchWinner().runThreads();
    }

}

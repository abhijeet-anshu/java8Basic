package BasicConcurrency;

import java.util.concurrent.locks.Lock;

public class MultipleLocksExample{
    public static void main(String[] args) {
        TestResource tr = new TestResource();
        Thread myThreads[] = new Thread[10];
        //use the tr and run display job and read job in multiple threads

        for (int i = 0; i < 5; i++) {
            //new Thread( () -> tr.displayContent() ).start();
            final int threadIdentifier = i ;//* 2010;
            myThreads[i] = new Thread( () -> tr.displayContent(threadIdentifier) );
        }

        for (int i = 5; i < 10; i++) {
            //new Thread( () -> tr.readContent() ).start();
            final int threadIdentifier = i;// * 102;
            myThreads[i] = new Thread( () -> tr.readContent( threadIdentifier) );
        }

        for (int i = 0; i < 10; i++) {
            myThreads[i].start();
        }

    }

}

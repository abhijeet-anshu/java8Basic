package helloLambda;

public class HellowWorld {
    Object hello = new Object();
    Object world = new Object();


    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i<100;i++){
            Runnable helloTask = new Runnable(){
                @Override
                public void run(){
                    new HellowWorld().printHello();
                }
            };

            Runnable worldTask = new Runnable(){
                @Override
                public void run(){
                    new HellowWorld().printWorld();
                }
            };

            Thread t1 = new Thread(helloTask);
            Thread t2 = new Thread(worldTask);
            t1.start();
            t1.join();
            t2.start();
            t2.join();
        }

    }

    public void printHello(){
       // synchronized (hello) {
            System.out.print("Hello ");
        //}
    }

    public void printWorld(){
        //synchronized (world) {
            System.out.println("World");
        //}
    }
}
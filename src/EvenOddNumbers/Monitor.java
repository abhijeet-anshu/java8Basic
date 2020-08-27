package EvenOddNumbers;

public class Monitor {
    final int maxPrint = 10000;
    boolean isEvenTurn = false;
    boolean isOddTurn =  true;
    boolean turn  = isOddTurn; //(begin with odd turn)

    private synchronized void isValidTurn(boolean askedTurn) {
        while(askedTurn!=turn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //else exit
    }

    private synchronized void switchTurn() {
        turn = !turn;
        notify();
    }

    private void myNumberThread(boolean isOddTurn) {
        int startNum = isOddTurn?1:2;
        for (int i = startNum; i <= maxPrint; i+=2) {
            isValidTurn(isOddTurn);
            //skipped
            System.out.println(i);
            switchTurn();
        }
    }

    private void runThreads() {
        Thread evenNumber = new Thread(() -> myNumberThread(isEvenTurn));
        Thread oddNumber = new Thread(() -> myNumberThread(isOddTurn));

        evenNumber.start();
        oddNumber.start();
        try {
            evenNumber.join();
        }catch (InterruptedException e) {
            //ignore
        }
        try {
            oddNumber.join();
        }catch (InterruptedException e) {
            //ignore
        }
    }

    public static void main(String[] args) {
        new Monitor().runThreads();
    }


}

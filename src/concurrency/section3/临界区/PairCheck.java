package concurrency.section3.临界区;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-01-20
 * @ Modified:
 **/
public class PairCheck implements Runnable {

    private PairManager pm;

    public PairCheck(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true){
            pm.checkCount.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}

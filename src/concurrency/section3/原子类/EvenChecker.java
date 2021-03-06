package concurrency.section3.原子类;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ Author: liuqianshun
 * @ Description:
 *
 * 书685案例
 *
 * 用原子类对 demo_21_3_2/use_lock中的MutexEvenGenerator进行重写
 *
 *
 * @ Date: Created in 2018-01-20
 * @ Modified:
 **/
public class EvenChecker implements Runnable {
    private IntGenerator generator;

    public EvenChecker(IntGenerator generator) {
        this.generator = generator;
    }


    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int value = generator.next();
            System.out.println(Thread.currentThread().getName()+", value: " + value);
            if (value % 7 != 0) {
                System.out.println(Thread.currentThread().getName() +"," + value + " 不是7的倍数,关闭7的倍数产生!");
                generator.cancel();
            }
        }
    }

    /**
     * 测试各种类型的IntGenerator
     *
     * @param generator
     * @param ident
     */
    public static void test(IntGenerator generator, int ident) {
        ExecutorService exec = null;
        for (int i = 0; i < ident; i++) {
            exec = Executors.newCachedThreadPool();
            exec.execute(new EvenChecker(generator));
        }
        exec.shutdown();
    }

    /**
     * 指定线程数量
     *
     * @param generator
     */
    public static void test(IntGenerator generator) {
        test(generator, 100);
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGenerator());
    }


}

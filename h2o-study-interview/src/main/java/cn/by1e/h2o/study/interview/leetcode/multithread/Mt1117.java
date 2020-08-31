package cn.by1e.h2o.study.interview.leetcode.multithread;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.InvokeUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * 题目：H2O 生成
 * <p>
 * 描述：现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。
 * <p>
 * 存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。
 * <p>
 * 氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。
 * <p>
 * 这些线程应该三三成组突破屏障并能立即组合产生一个水分子。
 * <p>
 * 你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。
 * <p>
 * 换句话说:
 * <p>
 * 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
 * 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
 * 书写满足这些限制条件的氢、氧线程同步代码。
 *
 * @author bangquan.qian
 * @date 2020-08-31 10:45
 */
@LevelMiddle
public class Mt1117 {

    public static class Solution1 {

        static class H2O {

            private final Semaphore h = new Semaphore(2);
            private final Semaphore o = new Semaphore(1);
            private final CyclicBarrier cb = new CyclicBarrier(3);

            public H2O() {

            }

            public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
                // releaseHydrogen.run() outputs "H". Do not change or remove this line.
                h.acquire();
                try {
                    cb.await();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                releaseHydrogen.run();
            }

            public void oxygen(Runnable releaseOxygen) throws InterruptedException {
                // releaseOxygen.run() outputs "O". Do not change or remove this line.
                o.acquire();
                try {
                    cb.await();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                releaseOxygen.run();
                cb.reset();
                h.release();
                h.release();
                o.release();
            }
        }

        public static void main(String[] args) {
            H2O h2o = new H2O();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> h2o.hydrogen(() -> ConsoleUtils.sout("H")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> h2o.hydrogen(() -> ConsoleUtils.sout("H")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> h2o.oxygen(() -> ConsoleUtils.sout("O")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> h2o.hydrogen(() -> ConsoleUtils.sout("H")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> h2o.hydrogen(() -> ConsoleUtils.sout("H")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> h2o.oxygen(() -> ConsoleUtils.sout("O")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> h2o.hydrogen(() -> ConsoleUtils.sout("H")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> h2o.hydrogen(() -> ConsoleUtils.sout("H")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> h2o.oxygen(() -> ConsoleUtils.sout("O")))).start();
        }

    }

}

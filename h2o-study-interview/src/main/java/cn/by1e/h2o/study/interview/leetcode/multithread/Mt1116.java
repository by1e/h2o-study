package cn.by1e.h2o.study.interview.leetcode.multithread;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.InvokeUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 题目：打印零与奇偶数
 * <p>
 * 描述：假设有这么一个类：
 * <p>
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 * public void zero(printNumber) { ... }  // 仅打印出 0
 * public void even(printNumber) { ... }  // 仅打印出 偶数
 * public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 * <p>
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *
 * @author bangquan.qian
 * @date 2020-08-31 10:45
 */
@LevelMiddle
public class Mt1116 {

    /**
     * 执行用时：25ms,在所有Java提交中击败了27.40%的用户
     * 内存消耗：39.7MB,在所有Java提交中击败了45.93%的用户
     */
    public static class Solution1 {

        static class ZeroEvenOdd {
            private int n;

            private volatile int x;

            private final Semaphore semaphore1 = new Semaphore(1);
            private final Semaphore semaphore2 = new Semaphore(0);
            private final Semaphore semaphore3 = new Semaphore(0);

            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void zero(IntConsumer printNumber) throws InterruptedException {
                for (; x < n; ) {
                    semaphore1.acquire();
                    printNumber.accept(0);
                    x++;
                    if (x % 2 == 0) {
                        semaphore2.release();
                    } else {
                        semaphore3.release();
                    }
                }
            }

            public void even(IntConsumer printNumber) throws InterruptedException {
                int z = n % 2 == 0 ? n : n - 1;
                for (; x < z; ) {
                    semaphore2.acquire();
                    printNumber.accept(x);
                    semaphore1.release();
                }
            }

            public void odd(IntConsumer printNumber) throws InterruptedException {
                int z = n % 2 == 1 ? n : n - 1;
                for (; x < z; ) {
                    semaphore3.acquire();
                    printNumber.accept(x);
                    semaphore1.release();
                }
            }
        }

        public static void main(String[] args) {
            ZeroEvenOdd zeo = new ZeroEvenOdd(14);
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> zeo.zero(ConsoleUtils::sout))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> zeo.even(ConsoleUtils::sout))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> zeo.odd(ConsoleUtils::sout))).start();
        }
    }

    /**
     * 执行用时：9ms,在所有Java提交中击败了59.36%的用户
     * 内存消耗：38.6MB,在所有Java提交中击败了92.49%的用户
     */
    public static class Solution2 {

        static class ZeroEvenOdd {
            private int n;

            private volatile int x;

            private final Lock lock = new ReentrantLock();
            private final Condition cond1 = lock.newCondition();
            private final Condition cond2 = lock.newCondition();
            private final Condition cond3 = lock.newCondition();

            private final CountDownLatch latch = new CountDownLatch(2);

            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void zero(IntConsumer printNumber) throws InterruptedException {
                latch.await();
                lock.lock();
                try {
                    for (; x < n; ) {
                        printNumber.accept(0);
                        x++;
                        if (x % 2 == 0) {
                            cond2.signal();
                        } else {
                            cond3.signal();
                        }
                        cond1.await();
                    }
                } finally {
                    lock.unlock();
                }
            }

            public void even(IntConsumer printNumber) throws InterruptedException {
                lock.lock();
                try {
                    latch.countDown();
                    int z = n % 2 == 0 ? n : n - 1;
                    for (; x < z; ) {
                        cond2.await();
                        printNumber.accept(x);
                        cond1.signal();
                    }
                } finally {
                    lock.unlock();
                }
            }

            public void odd(IntConsumer printNumber) throws InterruptedException {
                lock.lock();
                try {
                    latch.countDown();
                    int z = n % 2 == 1 ? n : n - 1;
                    for (; x < z; ) {
                        cond3.await();
                        printNumber.accept(x);
                        cond1.signal();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

        public static void main(String[] args) {
            ZeroEvenOdd zeo = new ZeroEvenOdd(15);
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> zeo.zero(ConsoleUtils::sout))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> zeo.even(ConsoleUtils::sout))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> zeo.odd(ConsoleUtils::sout))).start();
        }
    }
}

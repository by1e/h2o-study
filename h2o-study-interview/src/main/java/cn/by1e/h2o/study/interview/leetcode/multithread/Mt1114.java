package cn.by1e.h2o.study.interview.leetcode.multithread;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelSimple;
import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.InvokeUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：按序打印
 * <p>
 * 描述：我们提供了一个类：
 * <p>
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 * <p>
 * 线程 A 将会调用 first() 方法
 * 线程 B 将会调用 second() 方法
 * 线程 C 将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 *
 * @author bangquan.qian
 * @date 2020-08-30 22:48
 */
@LevelSimple
public class Mt1114 {

    /**
     * 执行用时：11ms,在所有Java提交中击败了94.83%的用户
     * 内存消耗：39.4MB,在所有Java提交中击败了41.79%的用户
     */
    public static class Solution1 {

        /**
         * 无锁自旋法
         */
        static class Foo {

            private volatile int flag = 0;

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();
                flag = 1;
            }

            public void second(Runnable printSecond) throws InterruptedException {
                while (flag != 1) ;
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                flag = 2;
            }

            public void third(Runnable printThird) throws InterruptedException {
                while (flag != 2) ;
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
                flag = 3;
            }
        }

        public static void main(String[] args) {
            Foo foo = new Foo();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.second(() -> ConsoleUtils.sout("second")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.third(() -> ConsoleUtils.sout("third")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.first(() -> ConsoleUtils.sout("first")))).start();
        }
    }

    /**
     * 执行用时：11ms,在所有Java提交中击败了94.83%的用户
     * 内存消耗：39.6MB,在所有Java提交中击败了19.23%的用户
     */
    public static class Solution2 {

        /**
         * CountDownLatch法
         */
        static class Foo {

            private CountDownLatch latch1 = new CountDownLatch(1);
            private CountDownLatch latch2 = new CountDownLatch(1);

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();
                latch1.countDown();
            }

            public void second(Runnable printSecond) throws InterruptedException {
                latch1.await();
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                latch2.countDown();
            }

            public void third(Runnable printThird) throws InterruptedException {
                latch2.await();
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
            }
        }

        public static void main(String[] args) {
            Foo foo = new Foo();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.second(() -> ConsoleUtils.sout("second1")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.third(() -> ConsoleUtils.sout("third1")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.first(() -> ConsoleUtils.sout("first1")))).start();
        }
    }

    /**
     * 执行用时：11ms,在所有Java提交中击败了94.83%的用户
     * 内存消耗：39.4MB,在所有Java提交中击败了52.96%的用户
     */
    public static class Solution3 {

        /**
         * semaphore法
         */
        static class Foo {

            private Semaphore semaphore1 = new Semaphore(0);
            private Semaphore semaphore2 = new Semaphore(0);

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();
                semaphore1.release();
            }

            public void second(Runnable printSecond) throws InterruptedException {
                semaphore1.acquire();
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                semaphore2.release();
            }

            public void third(Runnable printThird) throws InterruptedException {
                semaphore2.acquire();
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
            }
        }

        public static void main(String[] args) {
            Foo foo = new Foo();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.second(() -> ConsoleUtils.sout("second2")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.third(() -> ConsoleUtils.sout("third2")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.first(() -> ConsoleUtils.sout("first2")))).start();
        }
    }

    /**
     * 执行用时：11ms,在所有Java提交中击败了94.83%的用户
     * 内存消耗：39.4MB,在所有Java提交中击败了52.96%的用户
     */
    public static class Solution4 {

        /**
         * synchronized加JVM锁法
         */
        static class Foo {

            private final Object lock1 = new Object();
            private final Object lock2 = new Object();

            private /*volatile*/ boolean flag1;
            private /*volatile*/ boolean flag2;

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                // printFirst.run() outputs "first". Do not change or remove this line.
                synchronized (lock1) {
                    printFirst.run();
                    flag1 = true;
                    lock1.notifyAll();
                    //ConsoleUtils.sout("1111");
                }
            }

            public void second(Runnable printSecond) throws InterruptedException {
                synchronized (lock1) {
                    //ConsoleUtils.sout("2222");
                    while (!flag1) {
                        lock1.wait();
                    }
                    // printSecond.run() outputs "second". Do not change or remove this line.
                    printSecond.run();
                    synchronized (lock2) {
                        flag2 = true;
                        lock2.notifyAll();
                        //ConsoleUtils.sout("3333");
                    }
                }
            }

            public void third(Runnable printThird) throws InterruptedException {
                synchronized (lock2) {
                    //ConsoleUtils.sout("4444");
                    while (!flag2) {
                        lock2.wait();
                    }
                    // printThird.run() outputs "third". Do not change or remove this line.
                    printThird.run();
                }
            }
        }

        public static void main(String[] args) {
            Foo foo = new Foo();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.second(() -> ConsoleUtils.sout("second3")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.third(() -> ConsoleUtils.sout("third3")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.first(() -> ConsoleUtils.sout("first3")))).start();
        }
    }

    /**
     * 执行用时：12ms,在所有Java提交中击败了76.39%的用户
     * 内存消耗：39.2MB,在所有Java提交中击败了69.75%的用户
     */
    public static class Solution5 {

        /**
         * 加JUC锁
         */
        static class Foo {

            private final Lock lock1 = new ReentrantLock();
            private final Lock lock2 = new ReentrantLock();

            private final Condition cond1 = lock1.newCondition();
            private final Condition cond2 = lock2.newCondition();

            private volatile boolean flag1;
            private volatile boolean flag2;

            public Foo() {

            }

            public void first(Runnable printFirst) throws InterruptedException {
                // printFirst.run() outputs "first". Do not change or remove this line.
                lock1.lock();
                try {
                    printFirst.run();
                    flag1 = true;
                    cond1.signalAll();
                    //ConsoleUtils.sout("1111");
                } finally {
                    lock1.unlock();
                }
            }

            public void second(Runnable printSecond) throws InterruptedException {
                lock1.lock();
                try {
                    //ConsoleUtils.sout("2222");
                    while (!flag1) {
                        cond1.await();
                    }
                    // printSecond.run() outputs "second". Do not change or remove this line.
                    printSecond.run();
                    lock2.lock();
                    try {
                        flag2 = true;
                        cond2.signalAll();
                        //ConsoleUtils.sout("3333");
                    } finally {
                        lock2.unlock();
                    }
                } finally {
                    lock1.unlock();
                }
            }

            public void third(Runnable printThird) throws InterruptedException {
                lock2.lock();
                try {
                    //ConsoleUtils.sout("4444");
                    while (!flag2) {
                        cond2.await();
                    }
                    // printThird.run() outputs "third". Do not change or remove this line.
                    printThird.run();
                } finally {
                    lock2.unlock();
                }
            }
        }

        public static void main(String[] args) {
            Foo foo = new Foo();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.second(() -> ConsoleUtils.sout("second4")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.third(() -> ConsoleUtils.sout("third4")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> foo.first(() -> ConsoleUtils.sout("first4")))).start();
        }
    }


}

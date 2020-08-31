package cn.by1e.h2o.study.interview.leetcode.multithread;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.InvokeUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 题目：交替打印字符串
 * <p>
 * 描述：编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * <p>
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 * <p>
 * 假设有这么一个类：
 * <p>
 * class FizzBuzz {
 *   public FizzBuzz(int n) { ... }               // constructor
 * public void fizz(printFizz) { ... }          // only output "fizz"
 * public void buzz(printBuzz) { ... }          // only output "buzz"
 * public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 * public void number(printNumber) { ... }      // only output the numbers
 * }
 * 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
 * <p>
 * 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
 * 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
 * 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
 * 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 *
 * @author bangquan.qian
 * @date 2020-08-31 10:45
 */
@LevelMiddle
public class Mt1195 {

    /**
     * 执行用时：8ms,在所有Java提交中击败了99.30%的用户
     * 内存消耗：39.5MB,在所有Java提交中击败了48.78%的用户
     */
    public static class Solution1 {
        static class FizzBuzz {
            private int n;

            private volatile int x = 1;

            private final Lock lock = new ReentrantLock();

            private final Condition cond = lock.newCondition();

            public FizzBuzz(int n) {
                this.n = n;
            }

            // printFizz.run() outputs "fizz".
            public void fizz(Runnable printFizz) throws InterruptedException {
                lock.lock();
                try {
                    for (; ; ) {
                        while (x <= n && (x % 3 != 0 || x % 5 == 0)) {
                            cond.await();
                        }
                        if (x > n) {
                            break;
                        }
                        printFizz.run();
                        x++;
                        cond.signalAll();
                    }
                } finally {
                    lock.unlock();
                }
            }

            // printBuzz.run() outputs "buzz".
            public void buzz(Runnable printBuzz) throws InterruptedException {
                lock.lock();
                try {
                    for (; ; ) {
                        while (x <= n && (x % 5 != 0 || x % 3 == 0)) {
                            cond.await();
                        }
                        if (x > n) {
                            break;
                        }
                        printBuzz.run();
                        x++;
                        cond.signalAll();
                    }
                } finally {
                    lock.unlock();
                }
            }

            // printFizzBuzz.run() outputs "fizzbuzz".
            public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
                lock.lock();
                try {
                    for (; ; ) {
                        while (x <= n && (x % 3 != 0 || x % 5 != 0)) {
                            cond.await();
                        }
                        if (x > n) {
                            break;
                        }
                        printFizzBuzz.run();
                        x++;
                        cond.signalAll();
                    }
                } finally {
                    lock.unlock();
                }
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void number(IntConsumer printNumber) throws InterruptedException {
                lock.lock();
                try {
                    for (; ; ) {
                        while (x <= n && (x % 3 == 0 || x % 5 == 0)) {
                            cond.await();
                        }
                        if (x > n) {
                            break;
                        }
                        printNumber.accept(x);
                        x++;
                        cond.signalAll();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

        public static void main(String[] args) {
            FizzBuzz fb = new FizzBuzz(15);
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> fb.fizz(() -> ConsoleUtils.sout("fizz")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> fb.buzz(() -> ConsoleUtils.sout("buzz")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> fb.fizzbuzz(() -> ConsoleUtils.sout("fizzbuzz")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> fb.number(ConsoleUtils::sout))).start();
        }

    }
}

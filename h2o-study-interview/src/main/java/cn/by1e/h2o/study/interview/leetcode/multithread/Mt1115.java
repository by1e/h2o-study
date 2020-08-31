package cn.by1e.h2o.study.interview.leetcode.multithread;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.InvokeUtils;

import java.util.concurrent.Semaphore;

/**
 * 题目：交替打印FooBar
 * <p>
 * 描述：我们提供一个类：
 * <p>
 * class FooBar {
 * public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 * }
 * <p>
 * public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 * }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * <p>
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 *
 * @author bangquan.qian
 * @date 2020-08-31 10:45
 */
@LevelMiddle
public class Mt1115 {

    /**
     * 执行用时：25ms,在所有Java提交中击败了27.40%的用户
     * 内存消耗：39.7MB,在所有Java提交中击败了45.93%的用户
     */
    public static class Solution1 {

        static class FooBar {
            private int n;

            private Semaphore semaphore1 = new Semaphore(0);
            private Semaphore semaphore2 = new Semaphore(1);

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    semaphore2.acquire();
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    semaphore1.release();
                }
            }

            public void bar(Runnable printBar) throws InterruptedException {

                for (int i = 0; i < n; i++) {
                    semaphore1.acquire();
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    semaphore2.release();
                }
            }
        }

        public static void main(String[] args) {
            FooBar fb = new FooBar(15);
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> fb.foo(() -> ConsoleUtils.sout("foo")))).start();
            new Thread(() -> InvokeUtils.voidInvokeRe(() -> fb.bar(() -> ConsoleUtils.sout("bar")))).start();
        }

    }

}

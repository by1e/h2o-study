package cn.by1e.h2o.study.interview.leetcode.multithread;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.ox.core.util.ConsoleUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：哲学家进餐
 * <p>
 * 描述：5 个沉默寡言的哲学家围坐在圆桌前，每人面前一盘意面。叉子放在哲学家之间的桌面上。（5 个哲学家，5 根叉子）
 * <p>
 * 所有的哲学家都只会在思考和进餐两种行为间交替。哲学家只有同时拿到左边和右边的叉子才能吃到面，而同一根叉子在同一时间只能被一个哲学家使用。每个哲学家吃完面后都需要把叉子放回桌面以供其他哲学家吃面。只要条件允许，哲学家可以拿起左边或者右边的叉子，但在没有同时拿到左右叉子时不能进食。
 * <p>
 * 假设面的数量没有限制，哲学家也能随便吃，不需要考虑吃不吃得下。
 * <p>
 * 设计一个进餐规则（并行算法）使得每个哲学家都不会挨饿；也就是说，在没有人知道别人什么时候想吃东西或思考的情况下，每个哲学家都可以在吃饭和思考之间一直交替下去。
 * <p>
 * 问题描述和图片来自维基百科 wikipedia.org
 * <p>
 * 哲学家从 0 到 4 按 顺时针 编号。请实现函数 void wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork)：
 * <p>
 * philosopher 哲学家的编号。
 * pickLeftFork 和 pickRightFork 表示拿起左边或右边的叉子。
 * eat 表示吃面。
 * putLeftFork 和 putRightFork 表示放下左边或右边的叉子。
 * 由于哲学家不是在吃面就是在想着啥时候吃面，所以思考这个方法没有对应的回调。
 * 给你 5 个线程，每个都代表一个哲学家，请你使用类的同一个对象来模拟这个过程。在最后一次调用结束之前，可能会为同一个哲学家多次调用该函数。
 *
 * @author bangquan.qian
 * @date 2020-08-31 10:45
 */
@LevelMiddle
public class Mt1226 {

    // 超时
    public static class Solution1 {

        static class DiningPhilosophers {

            private static final int N = 5;

            private final Lock[] locks = new ReentrantLock[N];

            public DiningPhilosophers() {
                for (int idx = 0; idx < N; idx++) {
                    locks[idx] = new ReentrantLock();
                }
            }

            // call the run() method of any runnable to execute its code
            public void wantsToEat(int philosopher,
                                   Runnable pickLeftFork,
                                   Runnable pickRightFork,
                                   Runnable eat,
                                   Runnable putLeftFork,
                                   Runnable putRightFork) throws InterruptedException {

                Lock lockLeft = locks[philosopher];
                Lock lockRight = locks[(philosopher + 1) % 5];

                boolean eatOk = false;
                while (!eatOk) {
                    boolean left = lockLeft.tryLock();

                    if (!left) {
                        continue;
                    }

                    boolean right = false;

                    try {

                        try {
                            right = lockRight.tryLock();
                        } finally {
                            if (!right) {
                                lockLeft.unlock();
                            }
                        }

                        if (!right) {
                            continue;
                        }

                        try {
                            pickLeftFork.run();
                            pickRightFork.run();

                            eat.run();

                            putLeftFork.run();
                            putRightFork.run();

                        } finally {
                            lockRight.unlock();
                        }

                    } finally {
                        if (right) {
                            lockLeft.unlock();
                        }
                    }

                    eatOk = true;
                }
            }

        }

        public static void main(String[] args) {
            DiningPhilosophers dps = new DiningPhilosophers();
            int times = 50;
            new Thread(() -> dps(dps, 0, times)).start();
            new Thread(() -> dps(dps, 1, times)).start();
            new Thread(() -> dps(dps, 2, times)).start();
            new Thread(() -> dps(dps, 3, times)).start();
            new Thread(() -> dps(dps, 4, times)).start();
        }

        private static void dps(DiningPhilosophers dps, int philosopher, int times) {
            for (int idx = 0; idx < times; idx++) {
                try {
                    dps.wantsToEat(philosopher,
                            () -> ConsoleUtils.sout("PickLeftFork"),
                            () -> ConsoleUtils.sout("PickRightFork"),
                            () -> ConsoleUtils.sout("Eat"),
                            () -> ConsoleUtils.sout("PutLeftFork"),
                            () -> ConsoleUtils.sout("PutRightFork")
                    );
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    /**
     * 执行用时：15ms,在所有Java提交中击败了70.76%的用户
     * 内存消耗：40.8MB,在所有Java提交中击败了46.72%的用户
     */
    public static class Solution2 {

        static class DiningPhilosophers {

            private final ReentrantLock table = new ReentrantLock();

            private static final int N = 5;

            private final Lock[] locks = new ReentrantLock[N];

            public DiningPhilosophers() {
                for (int idx = 0; idx < N; idx++) {
                    locks[idx] = new ReentrantLock();
                }
            }

            // call the run() method of any runnable to execute its code
            public void wantsToEat(int philosopher,
                                   Runnable pickLeftFork,
                                   Runnable pickRightFork,
                                   Runnable eat,
                                   Runnable putLeftFork,
                                   Runnable putRightFork) throws InterruptedException {

                Lock lockLeft = locks[philosopher];
                Lock lockRight = locks[(philosopher + 1) % 5];

                table.lock();
                try {
                    lockLeft.lock();
                    try {
                        lockRight.lock();
                        try {

                            pickLeftFork.run();
                            pickRightFork.run();

                            eat.run();

                            putLeftFork.run();
                            putRightFork.run();


                        } finally {
                            lockRight.unlock();
                        }
                    } finally {
                        lockLeft.unlock();
                    }
                } finally {
                    table.unlock();
                }
            }
        }

        public static void main(String[] args) {
            DiningPhilosophers dps = new DiningPhilosophers();
            int times = 50;
            new Thread(() -> dps(dps, 0, times)).start();
            new Thread(() -> dps(dps, 1, times)).start();
            new Thread(() -> dps(dps, 2, times)).start();
            new Thread(() -> dps(dps, 3, times)).start();
            new Thread(() -> dps(dps, 4, times)).start();
        }

        private static void dps(DiningPhilosophers dps, int philosopher, int times) {
            for (int idx = 0; idx < times; idx++) {
                try {
                    dps.wantsToEat(philosopher,
                            () -> ConsoleUtils.sout("PickLeftFork"),
                            () -> ConsoleUtils.sout("PickRightFork"),
                            () -> ConsoleUtils.sout("Eat"),
                            () -> ConsoleUtils.sout("PutLeftFork"),
                            () -> ConsoleUtils.sout("PutRightFork")
                    );
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}

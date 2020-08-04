package cn.by1e.h2o.study.java.basic;

import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.SleepUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @author bangquan.qian
 * @date 2020-08-02 14:39
 */
public class MyConcurrent {

    public void test() {
        /*Thread thread = new Thread();
        thread.setDaemon(true);
        thread.start();*/

        new Thread(() -> {
            for (; ; ) {
                SleepUtils.sleepSeconds(1);
                ConsoleUtils.sout("111");
            }
        }).start();
    }

    public void test2() throws Throwable {
        RunnableFuture<Integer> rf = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                SleepUtils.sleepSeconds(5);
            }
        }, 123);

        new Thread(rf).start();

        RunnableFuture<Integer> rf2 = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() {
                SleepUtils.sleepSeconds(5);
                return 455;
            }
        });

        new Thread(rf2).start();

        new Thread(() -> {
            Integer integer = null;
            try {
                integer = rf.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            ConsoleUtils.sout(integer);
        }).start();

        new Thread(() -> {
            Integer integer = null;
            try {
                integer = rf2.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            ConsoleUtils.sout(integer);
        }).start();
    }
}

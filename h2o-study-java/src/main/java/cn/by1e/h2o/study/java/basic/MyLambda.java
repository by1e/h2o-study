package cn.by1e.h2o.study.java.basic;

import cn.by1e.ox.core.util.SleepUtils;

/**
 * @author bangquan.qian
 * @date 2020-08-01 16:59
 */
public class MyLambda {

    public void test() {
        System.out.println(this);
        Runnable runnable = () -> {
            System.out.println(this);
        };
        runnable.run();
        new Thread(() -> {
            System.out.println(this);
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(this);
            }
        }).start();
        SleepUtils.sleepSeconds(3);
    }
}

package cn.by1e.h2o.study.java.collection;

import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.SleepUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author bangquan.qian
 * @date 2020-08-01 18:34
 */
public class MyWeakHashMap {

    private static class Student {
        private String name;

        private InputStream is;

        public Student(String name) {
            this.name = name;
            this.is = new ByteArrayInputStream(new byte[0]) {
                @Override
                public void close() throws IOException {
                    super.close();
                    ConsoleUtils.sout("close");
                }
            };
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * @throws Throwable
         * @see java.lang.ref.Finalizer.FinalizerThread
         * @see java.lang.ref.Finalizer
         * @see java.lang.ref.FinalReference
         */
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            ConsoleUtils.sout(name + " finalize");
            is.close();
        }
    }

    private static class Score {
        private String name;

        public Score(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            ConsoleUtils.sout(name + " score finalize");
        }
    }

    public void test() {
        Map<Student, Score> map1 = new HashMap<>();
        map1.put(new Student("jack"), new Score("jack"));

        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 10_0000; i++) {
                    Map<Student, Score> map2 = new WeakHashMap<>();
                    map2.put(new Student("tom" + i), new Score("tom" + i));
                    SleepUtils.sleepSeconds(1);
                }
                ConsoleUtils.sout("put down");
            }
        }.start();

        SleepUtils.sleepSeconds(3);

        new Thread(() -> {
            while (true) {
                SleepUtils.sleepSeconds(3);
                System.gc();
            }
        }).start();

        SleepUtils.sleepSeconds(10000);
    }
}

package cn.by1e.h2o.study.java.basic;

import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.SleepUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author bangquan.qian
 * @date 2020-08-01 11:01
 */
public class MyThreadLocal {

    public void test() {
        int val = (int) (Integer.MAX_VALUE * 0.618);
        ConsoleUtils.sout(val);
        ConsoleUtils.sout(Integer.toBinaryString(val));
        ConsoleUtils.sout(Integer.toHexString(val));
    }

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

    private static class XxThreadLocal {

        private static volatile ThreadLocal<Student> threadLocal = new ThreadLocal<Student>() {
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
                ConsoleUtils.sout("ThreadLocal finalize");
            }
        };

        private static final ReferenceQueue<ThreadLocal<Student>> queue = new ReferenceQueue<>();

        private static volatile WeakReference<ThreadLocal<Student>> threadLocalRefer = new WeakReference<>(threadLocal, queue);

    }

    public void test2() throws Throwable {

        new Thread() {
            @Override
            public void run() {
                ConsoleUtils.sout(this);
                XxThreadLocal.threadLocalRefer.get().set(new Student("jack"));
                while (true) {
                    SleepUtils.sleepSeconds(1);
                    try {
                        if (XxThreadLocal.threadLocalRefer.get() != null) {
                            Student student = XxThreadLocal.threadLocalRefer.get().get();
                            if (student == null) {
                                ConsoleUtils.sout("lose student jack");
                            }
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                    Field threadLocals = FieldUtils.getField(this.getClass(), "threadLocals", true);
                    try {
                        Object o = threadLocals.get(this);
                        if (o != null) {
                            Field size = FieldUtils.getField(o.getClass(), "size", true);
                            Object sizeVal = size.get(o);
                            ConsoleUtils.prettyJson(sizeVal);
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                    try {
                        Object o = threadLocals.get(this);
                        if (o != null) {
                            // 触发该线程的Thread.ThreadLocalMap.expungeStaleEntries，移除ThreadLocalMap失效的Entry
                            Method expungeStaleEntries = MethodUtils.getMatchingMethod(o.getClass(), "expungeStaleEntries");
                            expungeStaleEntries.setAccessible(true);
                            expungeStaleEntries.invoke(o);
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                ConsoleUtils.sout(this);
                XxThreadLocal.threadLocalRefer.get().set(new Student("tom"));
                while (true) {
                    SleepUtils.sleepSeconds(1);
                    try {
                        if (XxThreadLocal.threadLocalRefer.get() != null) {
                            Student student = XxThreadLocal.threadLocalRefer.get().get();
                            if (student == null) {
                                ConsoleUtils.sout("lose student tom");
                            }
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                    Field threadLocals = FieldUtils.getField(this.getClass(), "threadLocals", true);
                    try {
                        Object o = threadLocals.get(this);
                        if (o != null) {
                            Field size = FieldUtils.getField(o.getClass(), "size", true);
                            Object sizeVal = size.get(o);
                            ConsoleUtils.prettyJson(sizeVal);
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        SleepUtils.sleepSeconds(10);

        // 会产生内存泄漏
        XxThreadLocal.threadLocal = null;

        SleepUtils.sleepSeconds(10);

        new Thread(() -> {
            try {
                while (true) {
                    XxThreadLocal.queue.remove();
                    ConsoleUtils.sout("remove");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        while (true) {
            byte[] bytes = new byte[1 * 1024 * 1024];
            SleepUtils.sleep(TimeUnit.MICROSECONDS, 100);
        }

    }

    public void test3() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    ThreadLocal<Student> tl = null;

                    tl = new ThreadLocal<Student>() {
                        @Override
                        protected void finalize() throws Throwable {
                            super.finalize();
                            ConsoleUtils.sout(this + " finalize");
                        }

                        @Override
                        protected Student initialValue() {
                            return new Student(this + " jack");
                        }
                    };

                    tl.get();

                    //SleepUtils.sleep(TimeUnit.MICROSECONDS, 100);
                }
            }
        }.start();

        SleepUtils.sleep(TimeUnit.MINUTES, 10);
    }

}

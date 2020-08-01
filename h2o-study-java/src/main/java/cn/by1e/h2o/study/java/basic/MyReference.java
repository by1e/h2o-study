package cn.by1e.h2o.study.java.basic;

import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.SleepUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author bangquan.qian
 * @date 2020-08-01 10:31
 * @see SoftReference
 * @see WeakReference
 * @see PhantomReference
 * @see java.lang.ref.FinalReference
 * <p>
 * -ea -XX:+PrintGCDetails -Xmx4m -Xmn2m
 */
public class MyReference {

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
            ConsoleUtils.sout("finalize");
            is.close();
        }
    }

    public void test1() {
        Student student1 = new Student("jack");

        ReferenceQueue<Student> queue = new ReferenceQueue<>();
        // 虚引用主要用来跟踪对象被垃圾回收器回收的活动
        // 可用于NIO堆外内存回收
        PhantomReference<Student> reference = new PhantomReference<>(student1, queue);

        student1 = null;

        Student student2 = reference.get();
        ConsoleUtils.prettyJson(student2);
        student2 = null;

        System.gc();

        SleepUtils.sleepSeconds(3);

        Student student3 = reference.get();
        ConsoleUtils.prettyJson(student3);
        student3 = null;
    }

    public void test2() {
        Student student1 = new Student("jack");

        ReferenceQueue<Student> queue = new ReferenceQueue<>();
        // 弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。
        // 在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。
        // 不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
        WeakReference<Student> reference = new WeakReference<>(student1, queue);

        student1 = null;

        Student student2 = reference.get();
        ConsoleUtils.prettyJson(student2);
        student2 = null;

        // 发生GC时回收
        System.gc();

        SleepUtils.sleepSeconds(3);

        Student student3 = reference.get();
        ConsoleUtils.prettyJson(student3);
        student3 = null;

        System.gc();

        SleepUtils.sleepSeconds(30);

        Student student4 = reference.get();
        ConsoleUtils.prettyJson(student4);
        student4 = null;
    }

    public void test3() {
        Student student1 = new Student("jack");

        ReferenceQueue<Student> queue = new ReferenceQueue<>();
        // 如果一个对象只具有软引用，则内存空间充足时，垃圾回收器就不会回收它；
        // 如果内存空间不足了，就会回收这些对象的内存。只要垃圾回收器没有回收它，该对象就可以被程序使用。
        SoftReference<Student> reference = new SoftReference<>(student1, queue);

        student1 = null;

        Student student2 = reference.get();
        ConsoleUtils.prettyJson(student2);
        student2 = null;

        //All soft references to softly-reachable objects are guaranteed to have been cleared before the virtual machine throws an <code>OutOfMemoryError</code>.
        // 使得堆内存临近不足
        while (true) {
            try {
                //1M=1024*1024
                byte[] bytes = new byte[1 * 1024 * 1024 + (int) (0.5 * 1024 * 1024)];
            } catch (Throwable e) {
                e.printStackTrace();
                continue;
            }
            break;
        }

        //System.gc();

        SleepUtils.sleepSeconds(3);

        Student student3 = reference.get();
        ConsoleUtils.prettyJson(student3);
        student3 = null;
    }

}

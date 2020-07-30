package cn.by1e.h2o.study.java.test;

import cn.by1e.h2o.study.java.collection.MyHashMap;
import cn.by1e.ox.core.util.ConsoleUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bangquan.qian
 * @date 2020-07-28 11:21
 */
public class MyTest01 {

    @Test
    public void test4() {
        int len = 100_0000;

        Map<Integer, Integer> map1 = new HashMap<>(1, len);
        Map<Integer, Integer> map2 = new HashMap<>();

        StopWatch sw1 = StopWatch.createStarted();

        for (int i = 0; i < len; i++) {
            map1.put(i, i);
        }
        for (int i = 0; i < len; i++) {
            map1.get(i);
        }

        sw1.stop();

        StopWatch sw2 = StopWatch.createStarted();

        for (int i = 0; i < len; i++) {
            map2.put(i, i);
        }
        for (int i = 0; i < len; i++) {
            map2.get(i);
        }

        sw2.stop();

        ConsoleUtils.sout(sw1.toString());
        ConsoleUtils.sout(sw2.toString());
    }

    @Test
    public void test2() {
        //    static final int MIN_TREEIFY_CAPACITY = 64;
        //    static final int TREEIFY_THRESHOLD = 8;
        Map<Integer, Integer> map = new MyHashMap<>(65, 9);
        for (int i = 0; i < 10_0000; i++) {
            map.put(i, i);
        }
    }

    @Test
    public void test3() {
        int time = 128;
        int len = 64;
        int[] num1 = new int[len];
        int[] num2 = new int[len];
        for (int i = 0; i < time; i++) {
            int hash1 = hash1(i);
            num1[hash1 & len - 1]++;

            int hash2 = hash2(i);
            num2[hash2 & len - 1]++;
        }
        long sum1 = sum(num1);
        long sum2 = sum(num1);
        ConsoleUtils.sout(sum1);
        ConsoleUtils.sout(sum2);
        ConsoleUtils.sout("done");
    }

    private long sum(int[] nums) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    private static int hash2(Object key) {
        int h;
        return (key == null) ? 0 : key.hashCode();
    }

    private static int hash1(Object key) {
        int h;
        // null为0，扰动函数
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Test
    public void test1() {
        int size1 = tableSizeFor(63);
        ConsoleUtils.sout(size1);

        int size2 = tableSizeFor(64);
        ConsoleUtils.sout(size2);

        ConsoleUtils.sout("done");
    }

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 每一位都置为1，再加1，实现扩大到离cap最近的2^n次
     */
    private static int tableSizeFor(final int cap) {
        String s = Integer.toBinaryString(cap);
        // 减1是为了64-1=63能最终结果为64，65-1=64最终结果为128
        int n = cap - 1;
        s = Integer.toBinaryString(n);
        n |= n >>> 1;
        s = Integer.toBinaryString(n);
        n |= n >>> 2;
        s = Integer.toBinaryString(n);
        n |= n >>> 4;
        s = Integer.toBinaryString(n);
        n |= n >>> 8;
        s = Integer.toBinaryString(n);
        n |= n >>> 16;
        s = Integer.toBinaryString(n);
        n = (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
        s = Integer.toBinaryString(n);
        return n;
    }

}

package cn.by1e.h2o.study.java.test;

import cn.by1e.ox.core.util.ConsoleUtils;
import org.junit.Test;

/**
 * @author bangquan.qian
 * @date 2020-07-28 11:21
 */
public class MyTest01 {

    @Test
    public void test1() {
        int size1 = tableSizeFor(63);
        ConsoleUtils.sout(size1);

        int size2 = tableSizeFor(64);
        ConsoleUtils.sout(size2);
    }

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 每一位都置为1，再加1，实现扩大到2^n次
     */
    private static int tableSizeFor(final int cap) {
        String s = Integer.toBinaryString(cap);
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

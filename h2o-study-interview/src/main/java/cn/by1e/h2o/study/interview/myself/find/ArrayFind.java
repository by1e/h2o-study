package cn.by1e.h2o.study.interview.myself.find;

import cn.by1e.ox.core.util.AssertUtils;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author bangquan.qian
 * @date 2020-08-11 22:31
 */
public class ArrayFind {

    public static <T extends Comparable<T>> T min(T[] arr) {
        AssertUtils.notNull(arr);

        T min = null;
        for (T val : arr) {
            int cmp = min == null ? -1 : ObjectUtils.compare(val, min);
            if (cmp < 0) {
                min = val;
            }
        }

        return min;
    }

    public static <T extends Comparable<T>> T max(T[] arr) {
        AssertUtils.notNull(arr);

        T max = null;
        for (T val : arr) {
            int cmp = max == null ? 1 : ObjectUtils.compare(val, max);
            if (cmp > 0) {
                max = val;
            }
        }

        return max;
    }

    private static final int NOT_FOUND = -1;

    /**
     * 线性查找
     */
    public static <T> int line(T[] arr, T tgt) {
        check(arr, tgt);

        int len = arr.length;
        for (int idx = 0; idx < len; idx++) {
            if (ObjectUtils.equals(arr[idx], tgt)) {
                return idx;
            }
        }

        return NOT_FOUND;
    }

    /**
     * 折半查找(二分查找)
     * <p>
     * 必须为有序数组
     */
    public static <T extends Comparable<T>> int half(T[] arr, T tgt) {
        check(arr, tgt);

        int lft = 0;
        int rgt = arr.length - 1;

        do {
            int mid = (lft + rgt) >>> 1;
            int cmp = ObjectUtils.compare(arr[mid], tgt);
            if (cmp == 0) {
                return mid;
            } else if (cmp > 0) {
                rgt = mid - 1;
            } else {
                lft = mid + 1;
            }
        } while (lft <= rgt);

        return NOT_FOUND;
    }

    public static <T> Entry[] buildHash(T[] arr) {
        AssertUtils.notNull(arr);

        int len = arr.length;
        int box = 16;
        int box0 = box - 1;

        // 构建hash表
        Entry[] table = new Entry[box];
        for (int idx = 0; idx < len; idx++) {
            T val = arr[idx];
            int hash = hash0(val);
            int pos = hash & box0;
            Entry<T> newEntry = new Entry<T>(hash, val, idx);
            Entry entry = table[pos];
            if (entry == null) {
                table[pos] = newEntry;
                continue;
            }
            while (true) {
                if (entry.hash == hash && ObjectUtils.equals(entry.value, val)) {
                    entry.value = val;
                    break;
                }
                if (entry.next == null) {
                    entry.next = newEntry;
                    break;
                }
                entry = entry.next;
            }
        }

        return table;
    }

    public static <T> int findHash(Entry[] tab, T tgt) {
        AssertUtils.notNull(tab);

        int box = tab.length;
        int box0 = box - 1;

        int hash = hash0(tgt);
        Entry entry = tab[hash & box0];
        while (entry != null) {
            if (entry.hash == hash && ObjectUtils.equals(entry.value, tgt)) {
                break;
            }
            entry = entry.next;
        }

        return entry == null ? NOT_FOUND : entry.index;
    }

    /**
     * hash查找
     * <p>
     * 可以是无序数组，模拟hashmap，但不支持重复的元素（重复的元素会被覆盖）
     */
    public static <T> int hash(T[] arr, T tgt) {
        return findHash(buildHash(arr), tgt);
    }

    private static class Entry<T> {
        private int hash;
        private T value;
        // 在原数组的位置
        private int index;

        private Entry<T> next;

        public Entry() {
        }

        public Entry(int hash, T value, int index) {
            this.hash = hash;
            this.value = value;
            this.index = index;
        }

    }

    /**
     * 抖动函数算hash，高低位均加入hash运算，减少冲突
     *
     * @see java.util.HashMap#hash(Object)
     */
    private static int hash0(Object obj) {
        int h;
        return (obj == null) ? 0 : (h = obj.hashCode()) ^ (h >>> 16);
    }

    private static <T> void check(T[] arr, T tgt) {
        AssertUtils.notNull(arr);
        AssertUtils.notNull(tgt);
    }

}

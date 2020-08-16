package cn.by1e.h2o.study.interview.myself.sort;

import cn.by1e.ox.core.util.AssertUtils;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 再练习一下常见的排序算法：
 * 冒泡排序、插入排序、选择排序、快速排序、归并排序
 *
 * @author bangquan.qian
 * @date 2020-08-15 16:46
 */
public class ArraySortAgain {

    public static void bubble(int[] a) {
        AssertUtils.notNull(a);

        int len = a.length;
        // i: 重复次数
        for (int i = 0; i < len - 1; i++) {
            boolean sorted = true;
            // j: 每次向上浮动比较的次数
            for (int j = 0; j < len - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    // 每次将底部最大的元素慢慢浮到顶部，同时尽可能帮助其他元素向上浮动
                    sorted = false;
                    swap(a, j, j + 1);
                }
            }
            if (sorted) {
                break;
            }
        }
    }

    public static void select(int[] a) {
        AssertUtils.notNull(a);

        int len = a.length;
        for (int i = 0; i < len; i++) {
            // 每次从后段找出最小的元素接在已排序好的部分后面
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (a[min] > a[j]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(a, i, min);
            }
        }
    }

    public static void insert(int[] a) {
        AssertUtils.notNull(a);

        int len = a.length;
        // i: 未排序的元素
        for (int i = 1; i < len; i++) {
            // 每次取尚未排序的序列的第一个元素插入到前面序列的正确位置
            int t = a[i];
            int j = i;
            // j: 插入位置
            for (; j > 0 && t < a[j - 1]; j--) {
                // shift，腾出插入位
                a[j] = a[j - 1];
            }
            a[j] = t;
        }
    }

    public static void quick(int[] a) {
        if (ArrayUtils.isEmpty(a)) {
            return;
        }

        quick0(a, 0, a.length - 1);
    }

    private static void quick0(int[] a, int l, int r) {
        int m = l;
        int n = r;
        //标杆
        int t = a[m];
        while (m < n) {
            while (a[n] > t && m < n) {
                n--;
            }
            if (m < n) {
                //把比标杆矮的挪到左侧
                a[m] = a[n];
                m++;
            }
            while (a[m] < t && m < n) {
                m++;
            }
            if (m < n) {
                //把比标杆高的挪到右侧
                a[n] = a[m];
                n--;
            }
        }
        //此时m==n，讲标杆插入中间
        a[m] = t;

        if (l < m - 1) {
            //继续递归左侧
            quick0(a, l, m - 1);
        }
        if (m + 1 < r) {
            //继续递归右侧
            quick0(a, m + 1, r);
        }
    }

    public static void merge(int[] a) {
        //一分为二
        int l = 0;
        int r = a.length - 1;
        merge0(a, l, r);
    }

    private static void merge0(int[] a, int l, int r) {
        int m = (l + r) / 2;
        if (l < m) {
            //左侧[l,m]
            merge0(a, l, m);
        }
        if (r > m + 1) {
            //右侧[m+1,r]
            merge0(a, m + 1, r);
        }

        //最小处理任务只有1个元素，此时只需要合并即可

        //合并左右两侧
        _merge(a, l, m, r);
    }

    private static void _merge(int[] a, int l, int m, int r) {
        int len = r - l + 1;
        //备份原数组
        int[] b = new int[len];
        for (int i = 0; i < len; i++) {
            b[i] = a[i + l];
        }
        //双指针
        int i = l;
        int j = m + 1;
        for (int k = l; k <= r; k++) {
            if (i > m) {
                a[k] = b[j++ - l];
            } else if (j > r) {
                a[k] = b[i++ - l];
            } else if (b[i - l] > b[j - l]) {
                a[k] = b[j++ - l];
            } else {
                a[k] = b[i++ - l];
            }
        }
    }

    public static void merge2(int[] a) {
        AssertUtils.notNull(a);

        int r = a.length - 1;
        for (int d = 1; ; d <<= 1) {
            for (int i = 0; i <= r; i += d) {
                int j = i + d - 1;
                _merge(a, i, (i + j) / 2, Math.min(r, j));
            }
            if (d >= r) {
                break;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

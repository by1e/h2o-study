package cn.by1e.h2o.study.interview.myself.sort;

import cn.by1e.ox.core.util.AssertUtils;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 冒泡排序、鸡尾酒排序；插入排序、二分插入排序，希尔排序；选择排序；快速排序；堆排序；归并排序；桶排序；计数排序；基数排序
 *
 * @author bangquan.qian
 * @date 2020-08-12 14:18
 */
public class ArraySort {

    private static final int DEFAULT_GAP = 1;

    /**
     * 冒泡排序
     */
    // 分类 -------------- 内部比较排序
    // 数据结构 ---------- 数组
    // 最差时间复杂度 ---- O(n^2)
    // 最优时间复杂度 ---- 如果能在内部循环第一次运行时,使用一个旗标来表示有无需要交换的可能,可以把最优时间复杂度降低到O(n)
    // 平均时间复杂度 ---- O(n^2)
    // 所需辅助空间 ------ O(1)
    // 稳定性 ------------ 稳定
    public static <T extends Comparable<T>> void bubble(T[] arr) {
        bubble(arr, DEFAULT_GAP);
    }

    public static <T extends Comparable<T>> void bubble(T[] arr, int gap) {
        check(arr);

        int len = arr.length;
        // 每次循环将数组左侧剩下的元素中最小的元素逐步挪到右侧已经拍好序的数组元素的最左侧
        for (int idx = 0; idx < len - gap; idx += gap) {
            boolean sorted = true;
            for (int jdx = 0; jdx < len - gap - idx; jdx += gap) {
                if (ObjectUtils.compare(arr[jdx], arr[jdx + gap]) > 0) {
                    sorted = false;
                    swap(arr, jdx, jdx + gap);
                }
            }
            if (sorted) {
                break;
            }
        }
    }

    /**
     * 鸡尾酒排序，又名定向冒泡排序
     */
    // 分类 -------------- 内部比较排序
    // 数据结构 ---------- 数组
    // 最差时间复杂度 ---- O(n^2)
    // 最优时间复杂度 ---- 如果序列在一开始已经大部分排序过的话,会接近O(n)
    // 平均时间复杂度 ---- O(n^2)
    // 所需辅助空间 ------ O(1)
    // 稳定性 ------------ 稳定
    public static <T extends Comparable<T>> void cocktail(T[] arr) {
        check(arr);

        int lft = 0;
        int rgt = arr.length - 1;

        // 双向
        while (lft < rgt) {
            boolean sorted = true;
            for (int idx = lft; idx < rgt; idx++) {
                if (ObjectUtils.compare(arr[idx], arr[idx + 1]) > 0) {
                    sorted = false;
                    swap(arr, idx, idx + 1);
                }
            }
            if (sorted) {
                break;
            }
            rgt--;
            for (int idx = rgt; idx > lft; idx--) {
                if (ObjectUtils.compare(arr[idx - 1], arr[idx]) > 0) {
                    sorted = false;
                    swap(arr, idx - 1, idx);
                }
            }
            if (sorted) {
                break;
            }
            lft++;
        }
    }

    /**
     * 选择排序
     */
    // 分类 -------------- 内部比较排序
    // 数据结构 ---------- 数组
    // 最差时间复杂度 ---- O(n^2)
    // 最优时间复杂度 ---- O(n^2)
    // 平均时间复杂度 ---- O(n^2)
    // 所需辅助空间 ------ O(1)
    // 稳定性 ------------ 不稳定
    public static <T extends Comparable<T>> void select(T[] arr) {
        select(arr, DEFAULT_GAP);
    }

    public static <T extends Comparable<T>> void select(T[] arr, int gap) {
        check(arr);

        int len = arr.length;
        //在每次排序中，在尚未排序的序列中选择一个最小的然后换到已经排序好的元素后面
        for (int idx = 0; idx < len - gap; idx += gap) {
            int min = idx;
            for (int jdx = idx + gap; jdx < len; jdx += gap) {
                if (ObjectUtils.compare(arr[min], arr[jdx]) > 0) {
                    min = jdx;
                }
            }
            if (min != idx) {
                swap(arr, min, idx);
            }
        }
    }

    /**
     * 插入排序
     */
    // 分类 ------------- 内部比较排序
    // 数据结构 ---------- 数组
    // 最差时间复杂度 ---- 最坏情况为输入序列是降序排列的,此时时间复杂度O(n^2)
    // 最优时间复杂度 ---- 最好情况为输入序列是升序排列的,此时时间复杂度O(n)
    // 平均时间复杂度 ---- O(n^2)
    // 所需辅助空间 ------ O(1)
    // 稳定性 ------------ 稳定
    public static <T extends Comparable<T>> void insert(T[] arr) {
        insert(arr, DEFAULT_GAP);
    }

    public static <T extends Comparable<T>> void insert(T[] arr, int gap) {
        check(arr);

        int len = arr.length;
        //每次取尚未排序的序列的第一个元素插入到前面序列的正确位置
        for (int idx = gap; idx < len; idx += gap) {
            //前段数组的排序，这里采用顺序查找法
            T src = arr[idx];
            int jdx = idx;
            for (; jdx > 0; jdx -= gap) {
                if (ObjectUtils.compare(src, arr[jdx - gap]) >= 0) {
                    break;
                }
                arr[jdx] = arr[jdx - gap];
            }
            if (jdx != idx) {
                arr[jdx] = src;
            }
        }
    }

    /**
     * 插入排序2
     */
    // 分类 -------------- 内部比较排序
    // 数据结构 ---------- 数组
    // 最差时间复杂度 ---- O(n^2)
    // 最优时间复杂度 ---- O(nlogn)
    // 平均时间复杂度 ---- O(n^2)
    // 所需辅助空间 ------ O(1)
    // 稳定性 ------------ 稳定
    public static <T extends Comparable<T>> void insert2(T[] arr) {
        check(arr);

        int len = arr.length;
        //每次取尚未排序的序列的第一个元素插入到前面序列的正确位置
        for (int idx = 1; idx < len; idx++) {
            //前段数组的排序，这里采用二分查找法缩小范围
            T src = arr[idx];
            int lft = 0;
            int rgt = idx - 1;
            while (lft <= rgt) {
                int mid = (lft + rgt) / 2;
                if (ObjectUtils.compare(arr[mid], src) > 0) {
                    rgt = mid - 1;
                } else {
                    lft = mid + 1;
                }
            }
            for (int jdx = idx; jdx > lft; jdx--) {
                arr[jdx] = arr[jdx - 1];
            }
            arr[lft] = src;
        }
    }

    /**
     * 希尔排序
     */
    // 分类 -------------- 内部比较排序
    // 数据结构 ---------- 数组
    // 最差时间复杂度 ---- 根据步长序列的不同而不同。已知最好的为O(n(logn)^2)
    // 最优时间复杂度 ---- O(n)
    // 平均时间复杂度 ---- 根据步长序列的不同而不同。
    // 所需辅助空间 ------ O(1)
    // 稳定性 ------------ 不稳定
    public static <T extends Comparable<T>> void shell(T[] arr) {
        check(arr);

        int len = arr.length;
        for (int gap = len; gap > 0; gap >>>= 1) {
            //bubble(arr, gap);

            //select(arr, gap);

            insert(arr, gap);
        }
    }

    /**
     * 计数排序
     */
    // 分类 ------------ 内部非比较排序
    // 数据结构 --------- 数组
    // 最差时间复杂度 ---- O(n + k)
    // 最优时间复杂度 ---- O(n + k)
    // 平均时间复杂度 ---- O(n + k)
    // 所需辅助空间 ------ O(n + k)
    // 稳定性 ----------- 稳定
    public static <T extends Comparable<T>> void count(T[] arr) {

    }

    private static <T> void swap(T[] arr, int idx, int jdx) {
        T tmp = arr[idx];
        arr[idx] = arr[jdx];
        arr[jdx] = tmp;
    }

    private static <T> void check(T[] arr) {
        AssertUtils.notNull(arr);
    }

}

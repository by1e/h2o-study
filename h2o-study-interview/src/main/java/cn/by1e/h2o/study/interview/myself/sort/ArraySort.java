package cn.by1e.h2o.study.interview.myself.sort;

import cn.by1e.ox.core.util.AssertUtils;
import org.apache.commons.lang3.ArrayUtils;
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
    public static void count(int[] arr) {
        if (ArrayUtils.isEmpty(arr)) {
            return;
        }

        int min = arr[0];
        int max = min;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }

        int len = arr.length;
        int box = max - min + 1;
        int[] frq = new int[box];
        //适用于数值范围较小的数组，否则frq数组会比较大
        for (int idx = 0; idx < len; idx++) {
            frq[arr[idx] - min]++;
        }
        for (int idx = 0, kdx = 0; idx < box; idx++) {
            for (int jdx = 0, val = idx + min; jdx < frq[idx]; jdx++) {
                arr[kdx++] = val;
            }
        }
    }

    /**
     * 快速排序：选择一个数作为基准(一般是第一个数),然后将之后的序列中比这个基准数小的数放在左边，比它大的数放在右边。接着将基准数放在“中间”，通过递归分别再处理左边和右边。
     *
     * @param a 要排序的数组
     */
    // 分类 ------------ 内部比较排序
    // 数据结构 --------- 数组
    // 最差时间复杂度 ---- 每次选取的基准都是最大（或最小）的元素，导致每次只划分出了一个分区，需要进行n-1次划分才能结束递归，时间复杂度为O(n^2)
    // 最优时间复杂度 ---- 每次选取的基准都是中位数，这样每次都均匀的划分出两个分区，只需要logn次划分就能结束递归，时间复杂度为O(nlogn)
    // 平均时间复杂度 ---- O(nlogn)
    // 所需辅助空间 ------
    // 主要是递归造成的栈空间的使用(用来保存left和right等局部变量)，取决于递归树的深度，一般为O(logn)，最差为O(n)
    // 稳定性 ---------- 不稳定
    public static void quick(int[] a) {

    }

    /**
     * 归并排序：自顶而下，递归做法
     *
     * @param a 要排序的数组
     */
    // 分类 -------------- 内部比较排序
    // 数据结构 ---------- 数组
    // 最差时间复杂度 ---- O(nlogn)
    // 最优时间复杂度 ---- O(nlogn)
    // 平均时间复杂度 ---- O(nlogn)
    // 所需辅助空间 ------ O(n)
    // 稳定性 ------------ 稳定
    public static void merge(int[] a) {
        if (ArrayUtils.isEmpty(a)) {
            return;
        }
        int n = a.length;
        int low = 0;
        int high = n - 1;
        mergeSortPartSort(a, low, high);
    }

    private static void mergeSortPartMerge(int[] a, int low, int mid, int high) {
        // 两个指针
        int p = low;
        int q = mid + 1;
        int len = high - low + 1;
        // 临时数组，用于排序的备份
        int[] t = new int[len];
        for (int i = low; i <= high; i++)
            t[i - low] = a[i];
        for (int i = low; i <= high; i++) {
            // 左半边已无剩余
            if (p > mid) {
                a[i] = t[q++ - low];
            }
            // 右半边已无剩余
            else if (q > high) {
                a[i] = t[p++ - low];
            }
            // 右半边当前元素小于左半边当前元素，取右半边的元素
            else if (t[q - low] < t[p - low]) {
                a[i] = t[q++ - low];
            }
            // 右半边当前元素大于或等于左半边当前元素，取左半边的元素
            else {
                a[i] = t[p++ - low];
            }
        }
    }

    // 自顶而下，递归做法
    private static void mergeSortPartSort(int[] a, int low, int high) {
        // 分到每组一个元素为止
        if (low >= high)
            return;

        int mid = (high - low) / 2 + low;
        // 左右两边分治，分别进行排序
        mergeSortPartSort(a, low, mid);
        mergeSortPartSort(a, mid + 1, high);
        // 左右两边归并
        mergeSortPartMerge(a, low, mid, high);
    }

    /**
     * 归并排序：自底而上，循环做法
     *
     * @param a 要排序的数组
     */
    public static void merge2(int[] a) {
        if (ArrayUtils.isEmpty(a)) {
            return;
        }
        int n = a.length;
        int high = n - 1;
        // 进行logN次两两归并
        // p代表size
        int delta = 0;
        for (int p = 1; p <= high; p = delta) {
            delta = p << 1;
            // 每个size下对每一个划分的小组进行归并
            for (int low = 0; low <= high - p; low += delta) {
                mergeSortPartMerge(a, low, low + p - 1, Math.min(low + delta - 1, high));
            }
        }
    }

    /**
     * 基数排序：非比较排序算法，这里是最低位优先基数排序LSD(Least significant digit)
     *
     * @param a 要排序的数组
     */
    // 分类 ------------- 内部非比较排序
    // 数据结构 ---------- 数组
    // 最差时间复杂度 ---- O(n * dn)
    // 最优时间复杂度 ---- O(n * dn)
    // 平均时间复杂度 ---- O(n * dn)
    // 所需辅助空间 ------ O(n * dn)
    // 稳定性 ----------- 稳定
    public static void radix(int[] a) {
        if (ArrayUtils.isEmpty(a)) {
            return;
        }
        radix(a, DECIMAL);
    }

    private static void radix(int[] a, int radix) {
        int max = a[0];
        int min = max;
        for (int i : a) {
            if (i > max)
                max = i;
            if (i < min)
                min = i;
        }
        // 最大和最小的差
        int delta = max - min;
        // 指数
        int exponent = 1;
        // 从低位开始往高位依次进行按位计数排序
        while (delta / exponent >= 1) {
            countSortByDigit(a, radix, exponent, delta, min);
            exponent *= radix;
        }
    }

    private static void countSortByDigit(int[] a, int radix, int exponent, int delta, int min) {
        int n = a.length;
        // 用于统计
        int len = delta + 1;
        int[] f = new int[len];
        // 统计出现的次数（频率）
        int findex = 0;
        for (int i = 0; i < n; i++) {
            // 取数组元素的第exponent位。
            // 比如exponent等于1，则取最后一位（个位）；exponent等于10，则取十位。
            // 如果超过位数，则计算下来也正好是0，起到补0的效果。
            findex = ((a[i] - min) / exponent) % radix;
            f[findex]++;
        }
        // 计算相对位移
        for (int i = 1; i < len; i++)
            f[i] += f[i - 1];
        // 反序输出到中间数组（这样确保稳定）
        int[] r = new int[n];
        for (int i = n - 1; i > -1; i--) {
            findex = ((a[i] - min) / exponent) % radix;
            r[--f[findex]] = a[i];
        }
        // 再拷贝回去
        for (int i = 0; i < n; i++)
            a[i] = r[i];
    }

    private static final int DECIMAL = 10;

    /**
     * 桶排序：先使用计数排序进行分桶操作，然后对每个非空桶进行排序。
     * <p>
     * 非比较排序，又叫箱排序。
     */
    // 分类 ------------- 内部非比较排序
    // 数据结构 --------- 数组
    // 最差时间复杂度 ---- O(nlogn)或O(n^2)，只有一个桶，取决于桶内排序方式
    // 最优时间复杂度 ---- O(n)，每个元素占一个桶
    // 平均时间复杂度 ---- O(n)，保证各个桶内元素个数均匀即可
    // 所需辅助空间 ------ O(n + bn)
    // 稳定性 ----------- 稳定
    public static void bucket(int[] a) {
        if (ArrayUtils.isEmpty(a)) {
            return;
        }

        int n = a.length;
        int max = a[0];
        int min = max;
        for (int i : a) {
            if (i > max)
                max = i;
            if (i < min)
                min = i;
        }
        // 桶的容量
        int size = 1;
        int delta = max - min;
        int t = delta;
        while (t > 0) {
            size *= DECIMAL;
            t /= 10;
        }
        // 桶的数量
        int bs = n % size == 0 ? n / size : n / size + 1;
        // 记录桶的边缘
        int[] b = new int[bs];
        // 计数排序进行分桶
        countSortForBucket(a, b, size, min);
        // 每个桶进行单独排序
        for (int i = 0; i < bs; i++) {
            int start = b[i];
            int end = i == bs - 1 ? n - 1 : b[i + 1] - 1;
            // 这里使用选择排序，其他排序方法也是可以的
            selectSortForBucket(a, start, end);
        }
    }

    private static void countSortForBucket(int[] a, int[] bucket, int size, int min) {
        int n = a.length;
        int bn = bucket.length;
        int[] r = new int[n];
        // 统计出现的次数
        int findex = 0;
        for (int i = 0; i < n; i++) {
            findex = (a[i] - min) / size;
            bucket[findex]++;
        }
        // 计算相对位移
        for (int i = 1; i < bn; i++)
            bucket[i] += bucket[i - 1];
        // 倒序输出数组（稳定）
        for (int i = n - 1; i > -1; i--) {
            findex = (a[i] - min) / size;
            // 桶的边缘被更新：bucket[i]为第i号桶中第一个元素所在r中的位置
            r[--bucket[findex]] = a[i];
        }
        // 再拷贝回去
        for (int i = 0; i < n; i++)
            a[i] = r[i];
    }

    private static void selectSortForBucket(int[] a, int start, int end) {
        // 都是闭合区间
        for (int i = start; i < end; i++) {
            int min = i;
            for (int j = i + 1; j <= end; j++) {
                if (a[j] < a[min])
                    min = j;
            }
            if (min != i) {
                swap(a, min, i);
            }
        }
    }

    /**
     * 堆排序：利用数组来实现堆，利用堆的性质来排序。
     * <p>
     * 因为这个堆是使用数组实现的，所以元素节点不需要复杂操作的左孩子和右孩子，只是有数学上的关系而已。
     * <p>
     * 堆的含义是：完全二叉树中所有非叶子结点的值均不大于（或不小于）其左、右孩子结点的值。
     */
    // 分类 -------------- 内部比较排序
    // 数据结构 ---------- 数组
    // 最差时间复杂度 ---- O(nlogn)
    // 最优时间复杂度 ---- O(nlogn)
    // 平均时间复杂度 ---- O(nlogn)
    // 所需辅助空间 ------ O(1)
    // 稳定性 ------------ 不稳定
    public static void heap(int[] a) {
        if (ArrayUtils.isEmpty(a)) {
            return;
        }

        int n = a.length;
        // 构造初始大顶堆
        heapSortBuild(a);
        // 无序区的元素个数大于1，则代表未完成排序
        // n==1时即排序完成，最后一个元素无需调整
        while (n > 1) {
            // 每次将第一个元素（堆顶元素）和最后一个元素（叶子节点）交换
            // 交换不仅会破环原来的堆结构，也会改变相对位置，所以堆排序是非稳定的
            swap(a, 0, --n);
            // 从新的堆顶元素开始向下重新进行堆调整，使其成为一个新堆，时间复杂度O(logn)
            heapify(a, 0, n);
        }
    }

    // 构建大顶堆其实也是堆调整的过程
    private static void heapSortBuild(int[] a) {
        int n = a.length;
        // 从最后一个非叶子结点开始向上进行调整
        for (int i = n / 2 - 1; i > -1; i--) {
            heapify(a, i, n);
        }
    }

    // 堆调整，对大小为0-size的堆中第index个元素进行调整，使其满足大顶堆
    private static void heapify(int[] a, int index, int size) {
        // 该节点，节点的左孩子，节点的右孩子哪个最大
        int max = index;
        // 左孩子节点是该节点的2*index+1
        int left = 2 * index + 1;
        if (left < size && a[left] > a[max])
            max = left;
        // 右孩子节点是该节点的2*index+2
        int right = 2 * index + 2;
        if (right < size && a[right] > a[max])
            max = right;
        if (max != index) {
            // 将最大的节点调整为父节点
            swap(a, max, index);
            // 递归调用，继续将交换到max节点的被破坏部分进行调整
            heapify(a, max, size);
        }
    }

    private static void swap(int[] arr, int idx, int jdx) {
        int tmp = arr[idx];
        arr[idx] = arr[jdx];
        arr[jdx] = tmp;
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

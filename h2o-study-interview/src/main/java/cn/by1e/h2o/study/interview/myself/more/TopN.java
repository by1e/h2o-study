package cn.by1e.h2o.study.interview.myself.more;

/**
 * 找出最大的数
 *
 * @author bangquan.qian
 * @date 2020-08-25 14:08
 */
public class TopN {

    public int[] topN(int n, int[] data) {
        int dataLen = data.length;
        int heapLen = n > dataLen ? dataLen : n;
        int[] arr = new int[heapLen];
        System.arraycopy(data, 0, arr, 0, heapLen);
        buildHeapTree(arr, heapLen);
        for (int idx = heapLen; idx < dataLen; idx++) {
            adjustHeapTree(arr, heapLen, data[idx]);
        }
        return arr;
    }

    private void adjustHeapTree(int[] arr, int len, int num) {
        if (num < arr[0]) {
            return;
        }

        //换掉堆顶
        arr[0] = num;

        int top = 0;
        while (true) {
            //调整堆顶，继续保持小顶堆
            int min = top;
            int left = left(top);
            if (left < len && arr[left] < arr[min]) {
                min = left;
            }
            int right = right(top);
            if (right < len && arr[right] < arr[min]) {
                min = right;
            }
            if (min == top) {
                break;
            }
            swap(arr, min, top);
            top = min;
        }
    }

    private void buildHeapTree(int[] arr, int len) {
        //构建初始的小顶堆
        for (int idx = 1; idx < len; idx++) {
            int top = idx;
            int pap;
            while (top != 0 && arr[pap = parent(top)] > arr[top]) {
                swap(arr, top, pap);
                top = pap;
            }
        }
    }

    private int left(int n) {
        return 2 * n + 1;
    }

    private int right(int n) {
        return 2 * n + 2;
    }

    private int parent(int n) {
        return (n - 1) / 2;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}

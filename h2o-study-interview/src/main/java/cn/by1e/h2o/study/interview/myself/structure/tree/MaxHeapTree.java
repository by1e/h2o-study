package cn.by1e.h2o.study.interview.myself.structure.tree;

import java.util.Arrays;

/**
 * @author bangquan.qian
 * @date 2020-08-25 13:12
 */
public class MaxHeapTree extends HeapTree {

    public MaxHeapTree() {
        super();
    }

    public MaxHeapTree(int size) {
        super(size);
    }

    public MaxHeapTree(Entry[] entry) {
        super(entry);
    }

    public MaxHeapTree(int[] kvs) {
        super(kvs);
    }

    @Override
    public void put(int key, int value) {
        Entry entry = new Entry(key, value);
        putVal(entry);
    }

    private void putVal(Entry entry) {
        ensureCapacity();
        int oldCount = count;
        table[count++] = entry;
        if (oldCount == 0) {
            return;
        }
        int len = count;
        for (int idx = len / 2 - 1; idx > -1; idx--) {
            adjust(table, idx, len);
        }
    }

    @Override
    void buildHeapTree(Entry[] entry) {
        Entry[] table = super.table = Arrays.copyOf(entry, entry.length);
        int len = table.length;
        for (int idx = len / 2 - 1; idx > -1; idx--) {
            adjust(table, idx, len);
        }
        count = len;
    }

    private void adjust(Entry[] entry, int idx, int len) {
        int max = idx;
        int left = left(idx);
        if (left < len && entry[left].key > entry[max].key) {
            max = left;
        }
        int right = right(idx);
        if (right < len && entry[right].key > entry[max].key) {
            max = right;
        }
        if (max != idx) {
            swap(entry, idx, max);
            adjust(entry, max, len);
        }
    }
}

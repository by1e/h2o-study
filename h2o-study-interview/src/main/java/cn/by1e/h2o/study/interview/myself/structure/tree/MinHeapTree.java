package cn.by1e.h2o.study.interview.myself.structure.tree;

import java.util.Arrays;

/**
 * @author bangquan.qian
 * @date 2020-08-25 13:12
 */
public class MinHeapTree extends HeapTree {

    public MinHeapTree() {

    }

    public MinHeapTree(int size) {
        super(size);
    }

    public MinHeapTree(Entry[] entry) {
        super(entry);
    }

    public MinHeapTree(int[] kvs) {
        super(kvs);
    }

    @Override
    public void put(int key, int value) {
        Entry entry = new Entry(key, value);
        putVal(entry);
    }

    private void putVal(Entry entry) {
        ensureCapacity();
        table[count++] = entry;
        int top = count - 1;
        if (top == 0) {
            return;
        }
        int pap;
        while (top != 0 && table[pap = parent(top)].key > table[top].key) {
            swap(table, top, pap);
            top = pap;
        }
    }

    @Override
    void buildHeapTree(Entry[] entry) {
        Entry[] table = super.table = Arrays.copyOf(entry, entry.length);
        int len = table.length;
        for (int idx = 1; idx < len; idx++) {
            int top = idx;
            int pap;
            while (top != 0 && table[pap = parent(top)].key > table[top].key) {
                swap(table, top, pap);
                top = pap;
            }
        }
        super.count = len;
    }
}

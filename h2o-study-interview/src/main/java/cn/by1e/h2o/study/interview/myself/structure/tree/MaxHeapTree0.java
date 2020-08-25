package cn.by1e.h2o.study.interview.myself.structure.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author bangquan.qian
 * @date 2020-08-25 11:57
 */
public class MaxHeapTree0 {

    private Entry[] table;
    private float THRESHOLD = 0.75f;
    private int count;

    private static final int DEFAULT_CAPACITY = 10;

    public MaxHeapTree0() {
        this(DEFAULT_CAPACITY);
    }

    public MaxHeapTree0(int size) {
        this.table = new Entry[size];
    }

    public MaxHeapTree0(Entry[] entry) {
        buildHeapTree(entry);
    }

    private void buildHeapTree(Entry[] originEntry) {
        this.table = Arrays.copyOfRange(originEntry, 0, originEntry.length);
        int len = table.length;
        for (int idx = len / 2 - 1; idx > -1; idx--) {
            adjust(table, idx, len);
        }
        count = table.length;
    }

    private void adjust(Entry[] table, int idx, int len) {
        int max = idx;
        int left = left(idx);
        if (left < len && table[left].key > table[max].key) {
            max = left;
        }
        int right = right(idx);
        if (right < len && table[right].key > table[max].key) {
            max = right;
        }
        if (max != idx) {
            swap(table, max, idx);
            adjust(table, max, len);
        }
    }

    private void swap(Entry[] entry, int parent, int node) {
        Entry tmp = entry[parent];
        entry[parent] = entry[node];
        entry[node] = tmp;
    }

    public void put(int key, int value) {
        Entry entry = new Entry(key, value);
        putVal(entry);
    }

    private void putVal(Entry entry) {
        ensureCapacity();
        table[count++] = entry;
        for (int idx = count / 2 - 1; idx > -1; idx--) {
            adjust(table, idx, count);
        }
    }

    private void ensureCapacity() {
        Entry[] oldEntry = table;
        int oldCount = count;
        int oldLen = table.length;
        if (oldLen * THRESHOLD >= oldCount) {
            return;
        }
        int newLen = oldLen + oldLen << 1;
        Entry[] newEntry = new Entry[newLen];
        System.arraycopy(oldEntry, 0, newEntry, 0, count);
        this.table = newEntry;
    }

    public int size() {
        return count;
    }

    public List<Entry> getEntry() {
        Entry[] oldEntry = table;
        int oldCount = count;
        List<Entry> list = new ArrayList<>();
        for (int idx = 0; idx < oldCount; idx++) {
            list.add(oldEntry[idx]);
        }
        return list;
    }

    private int parent(int idx) {
        return (idx - 1) / 2;
    }

    private int left(int idx) {
        return 2 * idx + 1;
    }

    private int right(int idx) {
        return 2 * idx + 2;
    }

    private class Entry {
        private int key;
        private int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Entry() {
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }
}

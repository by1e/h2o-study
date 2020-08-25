package cn.by1e.h2o.study.interview.myself.structure.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 小顶堆
 *
 * @author bangquan.qian
 * @date 2020-08-24 11:35
 */
public class MinHeapTree0 {

    private Entry[] entry;
    private float THRESHOLD = 0.75f;
    private int count;

    private static final int DEFAULT_CAPACITY = 10;

    public MinHeapTree0() {
        this(DEFAULT_CAPACITY);
    }

    public MinHeapTree0(int size) {
        this.entry = new Entry[size];
    }

    public MinHeapTree0(Entry[] entry) {
        buildHeapTree(entry);
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

    private void buildHeapTree(Entry[] originEntry) {
        this.entry = Arrays.copyOfRange(originEntry, 0, originEntry.length);
        int len = entry.length;
        for (int idx = 1; idx < len; idx++) {
            int top = idx;
            int pap;
            //小顶堆调整
            while (top != 0 && entry[pap = parent(top)].key > entry[top].key) {
                swap(entry, pap, top);
                top = pap;
            }
        }
        count = entry.length;
    }

    private void swap(Entry[] entry, int parent, int node) {
        Entry tmp = entry[parent];
        entry[parent] = entry[node];
        entry[node] = tmp;
    }

    public class Entry {
        private int key;
        private int value;

        public Entry() {
        }

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

    }

    public void put(int key, int value) {
        Entry entry = new Entry(key, value);
        adjust(entry);
    }

    private void adjust(Entry insertEntry) {
        ensureCapacity();
        if (count == 0) {
            entry[count++] = insertEntry;
            return;
        }
        int t = count;
        entry[t] = insertEntry;
        while (t != 0 && entry[parent(t)].key > entry[t].key) {
            swap(entry, parent(t), t);
            t = parent(t);
        }
        count++;
    }

    private void ensureCapacity() {
        Entry[] oldEntry = entry;
        int oldCount = count;
        int oldLen = entry.length;
        if (oldLen * THRESHOLD >= oldCount) {
            return;
        }
        int newLen = oldLen + oldLen << 1;
        Entry[] newEntry = new Entry[newLen];
        System.arraycopy(oldEntry, 0, newEntry, 0, count);
        this.entry = newEntry;
    }

    public int size() {
        return count;
    }

    public List<Entry> getEntry() {
        Entry[] oldEntry = entry;
        int oldCount = count;
        List<Entry> list = new ArrayList<>();
        for (int idx = 0; idx < oldCount; idx++) {
            list.add(oldEntry[idx]);
        }
        return list;
    }
}

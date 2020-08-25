package cn.by1e.h2o.study.interview.myself.structure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bangquan.qian
 * @date 2020-08-25 13:06
 */
public abstract class HeapTree {

    Entry[] table;
    int count;

    private static final int DEFAULT_CAPACITY = 10;

    private static final float THRESHOLD = 0.75f;

    public HeapTree() {
        this(DEFAULT_CAPACITY);
    }

    public HeapTree(int size) {
        this.table = new Entry[size];
    }

    public HeapTree(Entry[] entry) {
        buildHeapTree(entry);
    }

    public HeapTree(int[] kvs) {
        int len = kvs.length;
        Entry[] table = new Entry[len];
        for (int idx = 0; idx < len; idx++) {
            int kv = kvs[idx];
            table[idx] = new Entry(kv, kv);
        }
        buildHeapTree(table);
    }

    public abstract void put(int key, int value);

    abstract void buildHeapTree(Entry[] entry);

    void ensureCapacity() {
        Entry[] oldEntry = table;
        int oldCount = count;
        int oldLen = table.length;
        if (oldLen * THRESHOLD >= oldCount) {
            return;
        }
        int newLen;
        if (oldLen < 1) {
            newLen = DEFAULT_CAPACITY;
        } else {
            newLen = oldLen + oldLen << 1;
        }
        Entry[] newEntry = new Entry[newLen];
        System.arraycopy(oldEntry, 0, newEntry, 0, count);
        this.table = newEntry;
    }

    void swap(Entry[] entry, int parent, int node) {
        Entry tmp = entry[parent];
        entry[parent] = entry[node];
        entry[node] = tmp;
    }

    public int size() {
        return count;
    }

    public int[] getKey() {
        Entry[] oldEntry = table;
        int oldCount = count;
        int[] key = new int[oldCount];
        for (int idx = 0; idx < oldCount; idx++) {
            key[idx] = oldEntry[idx].key;
        }
        return key;
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

    int parent(int idx) {
        return (idx - 1) / 2;
    }

    int left(int idx) {
        return 2 * idx + 1;
    }

    int right(int idx) {
        return 2 * idx + 2;
    }

    public class Entry {
        int key;
        int value;

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

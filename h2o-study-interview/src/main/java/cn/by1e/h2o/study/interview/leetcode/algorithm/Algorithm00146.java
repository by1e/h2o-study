package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.*;
import cn.by1e.ox.core.util.ConsoleUtils;

/**
 * 标题：LRU缓存机制
 * <p>
 * 描述：运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 标签：设计
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelMiddle
@TagDesign
public class Algorithm00146 {

    public static void main(String[] args) {
        new Algorithm00146().test();
    }

    private void test() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        print(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        print(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        print(cache.get(1));       // 返回 -1 (未找到)
        print(cache.get(3));       // 返回  3
        print(cache.get(4));       // 返回  4
    }

    private void print(int i) {
        ConsoleUtils.sout(i);
    }

    /**
     * 执行用时：44ms,在所有Java提交中击败了16.39%的用户
     * 内存消耗：47.8MB,在所有Java提交中击败了72.36%的用户
     */
    class LRUCache {

        //hash，便于查找
        private MyHashMap hash;
        //空头
        private MyLinkNode head;
        //空尾
        private MyLinkNode tail;

        private int capacity;

        private static final int NOT_FOUND = -1;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.hash = new MyHashMap();
            this.head = new MyLinkNode();
            this.tail = new MyLinkNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            //先操作hash，再操作链表
            MyLinkNode node = hash.get(key);
            if (node == null) {
                return NOT_FOUND;
            }
            moveFirst(node);
            return node.value;
        }

        private MyLinkNode removeNode(MyLinkNode node) {
            MyLinkNode prev = node.prev;
            MyLinkNode next = node.next;
            prev.next = next;
            next.prev = prev;
            return node;
        }

        private void moveFirst(MyLinkNode node) {
            /*MyLinkNode next = head.next;
            head.next = node;
            node.next = next;
            node.prev = head;
            next.prev = node;*/
            removeNode(node);
            addFirst(node);
        }

        public void put(int key, int value) {
            //先操作hash，再操作链表
            MyLinkNode old = hash.put(key, value);
            if (old != null) {
                moveFirst(old);
                return;
            }
            addFirst(hash.get(key));
            if (hash.size() > capacity) {
                MyLinkNode remove = removeLast();
                hash.remove(remove.key);
            }
        }

        private void addFirst(MyLinkNode myLinkNode) {
            MyLinkNode next = head.next;
            head.next = myLinkNode;
            myLinkNode.next = next;
            myLinkNode.prev = head;
            next.prev = myLinkNode;
        }

        private void addLast(MyLinkNode myLinkNode) {
            MyLinkNode prev = tail.prev;
            prev.next = myLinkNode;
            myLinkNode.next = tail;
            myLinkNode.prev = prev;
            tail.prev = myLinkNode;
        }

        private MyLinkNode removeLast() {
            /*MyLinkNode node = tail.prev;
            MyLinkNode prev = node.prev;
            prev.next = tail;
            tail.prev = prev;
            return node;*/
            return removeNode(tail.prev);
        }

        class MyLinkNode {
            private int key;
            private int value;
            private MyLinkNode prev;
            private MyLinkNode next;
        }

        class Entry {
            int hash;
            MyLinkNode node;
            Entry next;
        }

        class MyHashMap {

            private int MOD = 16;
            private int mod = MOD - 1;

            private int size;

            private Entry[] table = new Entry[16];

            public MyLinkNode get(int key) {
                int hash = key & mod;
                Entry entry = table[hash];
                if (entry == null) {
                    return null;
                }
                while (entry != null) {
                    if (entry.hash == hash && entry.node.key == key) {
                        return entry.node;
                    }
                    entry = entry.next;
                }
                return null;
            }

            public MyLinkNode put(int key, int value) {
                int hash = key & mod;
                Entry newEntry = new Entry();
                newEntry.hash = hash;
                newEntry.node = new MyLinkNode();
                newEntry.node.key = key;
                newEntry.node.value = value;
                Entry entry = table[hash];
                if (entry == null) {
                    table[hash] = newEntry;
                    size++;
                    return null;
                } else {
                    while (true) {
                        if (entry.hash == hash && entry.node.key == key) {
                            entry.node.value = value;
                            return entry.node;
                        }
                        if (entry.next == null) {
                            entry.next = newEntry;
                            size++;
                            return null;
                        }
                        entry = entry.next;
                    }
                }
            }

            public int size() {
                return size;
            }

            public MyLinkNode remove(int key) {
                int hash = key & mod;
                Entry entry = table[hash];
                if (entry == null) {
                    return null;
                }
                Entry prev = null;
                while (entry != null) {
                    if (entry.hash == hash && entry.node.key == key) {
                        Entry next = entry.next;
                        if (prev == null) {
                            table[hash] = next;
                        } else {
                            prev.next = next;
                        }
                        return entry.node;
                    }
                    prev = entry;
                    entry = entry.next;
                }
                return null;
            }
        }
    }

}

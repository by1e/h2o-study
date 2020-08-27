package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelSimple;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDesign;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagQueue;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagStack;
import cn.by1e.ox.core.util.ConsoleUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 标题：用队列实现栈
 * <p>
 * 描述：使用队列实现栈的下列操作：
 * <p>
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 * <p>
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 * <p>
 * 标签：栈、队列、设计
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelSimple
@TagQueue
@TagStack
@TagDesign
public class Algorithm00225 {

    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<>();
        MyStack stack = new MyStack();
        stack.push(1);
        lst.add(stack.pop());
        stack.push(2);
        stack.push(3);
        lst.add(stack.pop());
        stack.push(4);
        stack.push(5);
        stack.push(6);
        lst.add(stack.pop());
        lst.add(stack.pop());
        lst.add(stack.pop());
        lst.add(stack.pop());
        lst.add(stack.pop());
        stack.push(7);
        lst.add(stack.pop());
        lst.add(stack.pop());
        ConsoleUtils.json(lst);
    }

    public static class MyStack {

        private static final int NOT_EXIST = -1;

        private class Queue {
            private int[] table;
            //指向头元素
            private int head;
            //指向尾元素，是下一个可以插入的元素
            private int tail;

            //元素的个数
            private int count;

            private int capacity;

            private boolean autoResize = false;

            private static final int DEFAULT_CAPACITY = 16;

            private static final float THRESHOLD = 0.75f;

            private Queue() {
                this.table = new int[DEFAULT_CAPACITY];
                this.capacity = table.length;
            }

            public Queue(boolean autoResize) {
                this();
                this.autoResize = autoResize;
            }

            private void ensureCapacityIfAutoResize() {
                if (!autoResize) {
                    return;
                }
                int oldCapacity = capacity;
                int[] oldTable = table;
                int size = size();
                int oldThr = size + size >> 1;
                if (oldThr <= oldCapacity) {
                    return;
                }
                int newCapacity = oldCapacity << 1;
                int[] newTable = new int[newCapacity];
                transfer(newTable);
                table = newTable;
                capacity = newTable.length;
                head = 0;
                tail = 0;
            }

            private void transfer(int[] newTable) {
                int idx = 0;
                while (!isEmpty()) {
                    newTable[idx++] = poll();
                }
            }

            public void offer(int x) {
                ensureCapacityIfAutoResize();
                //容量不足时默认覆盖
                table[tail] = x;
                tail = (tail + 1) & (capacity - 1);
            }

            public int poll() {
                if (isEmpty()) {
                    return NOT_EXIST;
                }
                int x = table[head];
                head = (head + 1) & (capacity - 1);
                return x;
            }

            public int first() {
                if (isEmpty()) {
                    return NOT_EXIST;
                }
                return table[head];
            }

            public int tail() {
                if (isEmpty()) {
                    return NOT_EXIST;
                }
                return table[(tail + capacity - 1) & (capacity - 1)];
            }

            public int size() {
                return (capacity - head + tail) & (capacity - 1);
            }

            public int left() {
                return capacity - size();
            }

            public boolean isFull() {
                return ((tail + 1) & (capacity - 1)) == head;
            }

            public boolean isEmpty() {
                //return size()==0;
                //return !isFull();
                return tail == head;
            }
        }

        private Queue queue1;
        private Queue queue2;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            this.queue1 = new Queue(true);
            this.queue2 = new Queue(true);
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            queue1.offer(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            while (queue1.size() > 1) {
                queue2.offer(queue1.poll());
            }
            //此时queue1只有一个元素，poll出之后为空队列
            int poll = queue1.poll();
            //直接交换
            Queue queue = queue1;
            queue1 = queue2;
            queue2 = queue;
            return poll;
        }

        /**
         * Get the top element.
         */
        public int top() {
            while (queue1.size() > 1) {
                queue2.offer(queue1.poll());
            }
            //此时queue1只有一个元素，poll出之后为空队列
            return queue1.first();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }

}

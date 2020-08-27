package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelSimple;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDesign;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagQueue;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagStack;
import cn.by1e.ox.core.util.ConsoleUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 标题：用栈实现队列
 * <p>
 * 描述：使用栈实现队列的下列操作：
 * <p>
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 *  
 * <p>
 * 示例:
 * <p>
 * MyQueue queue = new MyQueue();
 * <p>
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 *  
 * <p>
 * 说明:
 * <p>
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 * <p>
 * 标签：栈、队列、设计
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelSimple
@TagStack
@TagQueue
@TagDesign
public class Algorithm00232 {

    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<>();
        MyQueue queue = new MyQueue();
        queue.push(1);
        lst.add(queue.pop());
        queue.push(2);
        queue.push(3);
        lst.add(queue.pop());
        queue.push(4);
        queue.push(5);
        queue.push(6);
        lst.add(queue.pop());
        queue.push(7);
        queue.push(8);
        lst.add(queue.pop());
        lst.add(queue.pop());
        lst.add(queue.pop());
        lst.add(queue.pop());
        lst.add(queue.pop());
        queue.push(9);
        lst.add(queue.pop());
        lst.add(queue.pop());
        lst.add(queue.pop());
        lst.add(queue.pop());
        ConsoleUtils.json(lst);
    }

    /**
     * 执行用时：0ms,在所有Java提交中击败了100.00%的用户
     * 内存消耗：37.7MB,在所有Java提交中击败了5.29%的用户
     */
    public static class MyQueue {

        private static class Stack {
            private int[] table;
            private int top;

            private static final int DEFAULT_CAPACITY = 10;

            public Stack() {
                this.table = new int[DEFAULT_CAPACITY];
            }

            private void ensureCapacity() {
                int oldTop = top;
                int[] oldTable = table;
                int oldTableLen = oldTable.length;
                int newTableLen = oldTop + oldTop << 1;
                if (newTableLen < oldTableLen) {
                    return;
                }
                int[] newTable = new int[newTableLen];
                System.arraycopy(oldTable, 0, newTable, 0, oldTop);
                this.table = newTable;
            }

            public void push(int x) {
                ensureCapacity();
                table[top++] = x;
            }

            public Integer pop() {
                return top < 1 ? null : table[top-- - 1];
            }

            public Integer peek() {
                return top < 1 ? null : table[top - 1];
            }

            public int size() {
                return top;
            }

            public boolean isEmpty() {
                return size() == 0;
            }
        }

        //主栈
        private Stack stack1;
        //辅助栈
        private Stack stack2;

        private static final int NOT_EXIST = -1;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            stack1 = new Stack();
            stack2 = new Stack();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            stack1.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            if (stack2.isEmpty()) {
                return NOT_EXIST;
            }
            return stack2.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (!stack2.isEmpty()) {
                return stack2.peek();
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            if (stack2.isEmpty()) {
                return NOT_EXIST;
            }
            return stack2.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return peek() == NOT_EXIST;
        }
    }
}

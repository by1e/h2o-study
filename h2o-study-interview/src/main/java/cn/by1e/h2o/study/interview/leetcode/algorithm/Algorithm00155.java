package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelSimple;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDesign;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagStack;
import cn.by1e.ox.core.util.ConsoleUtils;

/**
 * 标题：最小栈
 * <p>
 * 描述：设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * <p>
 * 标签：栈、设计
 * <p>
 * 难度：简单
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelSimple
@TagStack
@TagDesign
public class Algorithm00155 {

    /**
     * 执行用时：6ms,在所有Java提交中击败了97.65%的用户
     * 内存消耗：41.9MB,在所有Java提交中击败了7.64%的用户
     */
    static class MinStack2 {

        class Stack {

            private int[] tab;

            private int top;

            private static final int DEFAULT_CAPACITY = 10;

            private static final int NOT_EXIST = -1;

            /**
             * initialize your data structure here.
             */
            public Stack() {
                this.tab = new int[DEFAULT_CAPACITY];
            }

            public void push(int x) {
                ensureCapacity();
                tab[top++] = x;
            }

            public int pop() {
                return top < 1 ? NOT_EXIST : tab[--top];
            }

            public int top() {
                return top < 1 ? NOT_EXIST : tab[top - 1];
            }

            public int size() {
                return top;
            }

            private void ensureCapacity() {
                int[] oldTab = tab;
                int oldLen = top;
                int oldSize = oldTab.length;
                if ((oldLen + (oldLen >> 1)) <= oldSize) {
                    return;
                }
                int newSize = oldSize << 1;
                int[] newTab = new int[newSize];
                System.arraycopy(oldTab, 0, newTab, 0, oldLen);
                tab = newTab;
            }

        }

        private Stack tab;
        private Stack min;

        /**
         * initialize your data structure here.
         */
        public MinStack2() {
            this.tab = new Stack();
            this.min = new Stack();
        }

        public void push(int x) {
            tab.push(x);
            min.push(min.top() >= x || min.size() < 1 ? x : min.top());
        }

        public int pop() {
            int pop = tab.pop();
            min.pop();
            return pop;
        }

        public int top() {
            return tab.top();
        }

        public int getMin() {
            return min.top();
        }

    }

    /**
     * 执行用时：7ms,在所有Java提交中击败了80.55%的用户
     * 内存消耗：41.4MB,在所有Java提交中击败了76.42%的用户
     */
    static class MinStack {

        class Stack {

            private int[] tab;

            private int top;

            private static final int DEFAULT_CAPACITY = 10;

            private static final int NOT_EXIST = -1;

            /**
             * initialize your data structure here.
             */
            public Stack() {
                this.tab = new int[DEFAULT_CAPACITY];
            }

            public void push(int x) {
                ensureCapacity();
                tab[top++] = x;
            }

            public int pop() {
                return top < 1 ? NOT_EXIST : tab[--top];
            }

            public int top() {
                return top < 1 ? NOT_EXIST : tab[top - 1];
            }

            public int size() {
                return top;
            }

            private void ensureCapacity() {
                int[] oldTab = tab;
                int oldLen = top;
                int oldSize = oldTab.length;
                if ((oldLen + (oldLen >> 1)) <= oldSize) {
                    return;
                }
                int newSize = oldSize << 1;
                int[] newTab = new int[newSize];
                System.arraycopy(oldTab, 0, newTab, 0, oldLen);
                tab = newTab;
            }
        }


        private Stack tab;
        private Stack min;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            this.tab = new Stack();
            this.min = new Stack();
        }

        public void push(int x) {
            tab.push(x);
            if (min.size() < 1 || min.top() >= x) {
                min.push(x);
            }
        }

        public int pop() {
            int pop = tab.pop();
            if (min.top() == pop) {
                min.pop();
            }
            return pop;
        }

        public int top() {
            return tab.top();
        }

        public int getMin() {
            return min.top();
        }

    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        ConsoleUtils.sout(minStack.getMin());
        minStack.pop();
        //ConsoleUtils.sout(minStack.top());
        ConsoleUtils.sout(minStack.getMin());
    }

}

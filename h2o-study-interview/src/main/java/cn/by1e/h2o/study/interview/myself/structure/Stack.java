package cn.by1e.h2o.study.interview.myself.structure;

public class Stack {

    private Object[] value;

    //指向下一个push的位置
    private int top;

    private int threshold;

    public Stack(int len) {
        this.threshold = len;
        this.value = new Object[len];
    }

    public void push(Object val) {
        ensureCapacity();
        value[top++] = val;
    }

    private void ensureCapacity() {
        //1.5
        int oldTop = top;
        int oldThr = threshold;
        int newThr = oldTop + oldTop >> 1;
        if (newThr < oldThr) {
            return;
        }
        Object[] old = value;
        Object[] newNode = new Object[newThr];
        this.value = newNode;
        threshold = newThr;
        for (int idx = 0; idx < old.length; idx++) {
            newNode[idx] = old[idx];
        }
    }

    public Object peek() {
        return top > 0 ? value[top - 1] : null;
    }

    public Object pop() {
        Object val = peek();
        if (top > 0) {
            value[--top] = null;
        }
        return val;
    }

    public int size() {
        return top;
    }

}
package cn.by1e.h2o.study.interview.myself.structure.tree;

/**
 * @author bangquan.qian
 * @date 2020-08-17 18:20
 */
public class BinNode<T> {

    private T value;

    private BinNode<T> left;

    private BinNode<T> right;

    public BinNode() {
    }

    public BinNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinNode<T> left) {
        this.left = left;
    }

    public BinNode<T> getRight() {
        return right;
    }

    public void setRight(BinNode<T> right) {
        this.right = right;
    }
}

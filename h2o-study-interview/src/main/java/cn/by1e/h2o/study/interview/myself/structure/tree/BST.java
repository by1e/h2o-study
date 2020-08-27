package cn.by1e.h2o.study.interview.myself.structure.tree;

import cn.by1e.ox.core.util.AssertUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉查找树  (Binary Search Tree)
 *
 * @author bangquan.qian
 * @date 2020-08-17 18:13
 */
public class BST<T extends Comparable<T>> implements BinTree<T> {

    private BinNode<T> root;

    private int size;

    public T add(T t) {
        AssertUtils.notNull(t);
        return doAdd(t);
    }

    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    private T doAdd(T t) {
        BinNode<T> newNode = new BinNode<>(t);
        if (root == null) {
            root = newNode;
            return null;
        }

        BinNode<T> curr = root;
        BinNode<T> prev = null;
        int flag = LEFT;
        while (curr != null) {
            prev = curr;
            int compare = ObjectUtils.compare(curr.value, t);
            if (compare == 0) {
                break;
            }
            if (compare > 0) {
                curr = curr.left;
                flag = LEFT;
            } else {
                curr = curr.right;
                flag = RIGHT;
            }
        }

        T old = null;
        if (curr != null) {
            //替换当前节点
            old = curr.value;
            curr.value = t;
        } else {
            if (flag == LEFT) {
                prev.left = newNode;
            } else {
                prev.right = newNode;
            }
        }

        return old;
    }

    public boolean contains(T t) {
        AssertUtils.notNull(t);
        return doContains(t);
    }

    private boolean doContains(T t) {
        BinNode<T> curr = root;
        while (curr != null) {
            int compare = ObjectUtils.compare(curr.value, t);
            if (compare == 0) {
                return true;
            }
            if (compare > 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return false;
    }

    /**
     * TODO BST移除
     */
    public boolean remove(T t) {
//        return false;
        throw new UnsupportedOperationException();
    }

    public int size() {
        return size;
    }

    @Override
    public List<T> dlr() {
        return null;
    }

    //手动栈做法
    public List<T> ldr2() {
        //BST的中序遍历是有序的
        List<T> lst = new ArrayList<>(size);

        LinkedList<BinNode<T>> stack = new LinkedList<>();
        BinNode<T> curr = null;

        curr = root;
        while (curr != null) {
            curr = curr.left;
            if (curr != null) {
                stack.addLast(curr);
            }
        }

        while (stack.size() > 0) {
            BinNode<T> node = stack.pollLast();
            lst.add(node.value);
            if (node.right != null) {

            }
        }

        return lst;
    }

    @Override
    public List<T> ldr() {
        //BST的中序遍历是有序的
        List<T> lst = new ArrayList<>(size);
        //递归做法
        ldr(lst, root);
        return lst;
    }

    private void ldr(List<T> lst, BinNode<T> node) {
        if (node == null) {
            return;
        }

        ldr(lst, node.left);
        lst.add(node.value);
        ldr(lst, node.right);
    }

    @Override
    public List<T> lrd() {
        return null;
    }

    @Override
    public List<T> dfs() {
        return null;
    }

    @Override
    public List<T> bfs() {
        return null;
    }

}

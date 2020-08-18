package cn.by1e.h2o.study.interview.myself.structure.tree;

import cn.by1e.ox.core.util.AssertUtils;

import java.util.List;

/**
 * 平衡二叉查找树 ( Balanced Binary Search Tree )
 *
 * @author bangquan.qian
 * @date 2020-08-17 18:14
 */
public class AVL<T extends Comparable<T>> implements BinTree<T> {

    private BinNode<T> root;

    public T add(T t) {
        AssertUtils.notNull(t);

        BinNode<T> node = new BinNode<>();

        return null;
    }

    public void delete(T t) {

    }

    @Override
    public List<T> dlr() {
        return null;
    }

    @Override
    public List<T> ldr() {
        return null;
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

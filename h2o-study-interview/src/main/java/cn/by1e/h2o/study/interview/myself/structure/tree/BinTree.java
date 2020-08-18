package cn.by1e.h2o.study.interview.myself.structure.tree;

import java.util.List;

/**
 * @author bangquan.qian
 * @date 2020-08-17 18:07
 */
public interface BinTree<T> extends Tree<T> {

    /**
     * 前序遍历
     */
    List<T> dlr();

    /**
     * 中序遍历
     */
    List<T> ldr();

    /**
     * 后序遍历
     */
    List<T> lrd();

}

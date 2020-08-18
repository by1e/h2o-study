package cn.by1e.h2o.study.interview.myself.structure.tree;

import java.util.List;

/**
 * @author bangquan.qian
 * @date 2020-08-17 18:04
 */
public interface Tree<T> {

    /**
     * 深度优先遍历
     */
    List<T> dfs();

    /**
     * 广度优先遍历
     */
    List<T> bfs();

}

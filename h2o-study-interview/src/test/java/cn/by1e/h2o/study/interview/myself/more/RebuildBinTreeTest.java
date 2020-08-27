package cn.by1e.h2o.study.interview.myself.more;

import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.JsonUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bangquan.qian
 * @date 2020-08-26 23:26
 */
public class RebuildBinTreeTest {

    @Test
    public void test() {
        // 前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
        int[] a = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] b = {4, 7, 2, 1, 5, 3, 8, 6};
        RebuildBinTree tree = new RebuildBinTree();
        RebuildBinTree.BinNode root = tree.rebuild(a, b);
        List<Integer> lst = dlr(root);
        ConsoleUtils.json(lst);
        ConsoleUtils.json(ObjectUtils.equals(JsonUtils.toJsonString(a), JsonUtils.toJsonString(lst)));
    }

    private List<Integer> dlr(RebuildBinTree.BinNode root) {
        List<Integer> lst = new ArrayList<>();
        _dlr(lst, root);
        return lst;
    }

    private void _dlr(List<Integer> lst, RebuildBinTree.BinNode node) {
        lst.add(node.value);
        if (node.left != null) {
            _dlr(lst, node.left);
        }
        if (node.right != null) {
            _dlr(lst, node.right);
        }
    }

}

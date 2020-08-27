package cn.by1e.h2o.study.interview.myself.more;

import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.JsonUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import java.util.*;

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
        List<Integer> lst2 = dlr2(root);
        ConsoleUtils.json(lst2);
        ConsoleUtils.json(ObjectUtils.equals(JsonUtils.toJsonString(a), JsonUtils.toJsonString(lst2)));

        List<Integer> lst3 = ldr(root);
        ConsoleUtils.json(lst3);
        List<Integer> lst4 = ldr2(root);
        ConsoleUtils.json(lst4);
        ConsoleUtils.json(ObjectUtils.equals(JsonUtils.toJsonString(lst3), JsonUtils.toJsonString(lst4)));

        List<Integer> lst5 = lrd(root);
        ConsoleUtils.json(lst5);
        List<Integer> lst6 = lrd2(root);
        ConsoleUtils.json(lst6);
        ConsoleUtils.json(ObjectUtils.equals(JsonUtils.toJsonString(lst5), JsonUtils.toJsonString(lst6)));
    }

    private static final Integer LEFT_VISITED = 1;
    private static final Integer RIGHT_VISITED = 2;

    private List<Integer> lrd2(RebuildBinTree.BinNode root) {
        List<Integer> lst = new ArrayList<>();
        Deque<RebuildBinTree.BinNode> stack = new LinkedList<>();
        Map<RebuildBinTree.BinNode, Integer> visit = new HashMap<>();
        RebuildBinTree.BinNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.addFirst(curr);
                visit.put(curr, LEFT_VISITED);
                curr = curr.left;
            }
            while (!stack.isEmpty() && RIGHT_VISITED.equals(visit.get(stack.peekFirst()))) {
                RebuildBinTree.BinNode node = stack.pollFirst();
                lst.add(node.value);
            }
            if (!stack.isEmpty()) {
                curr = stack.peekFirst();
                visit.put(curr, RIGHT_VISITED);
                curr = curr.right;
            }
        }
        return lst;
    }

    private List<Integer> lrd(RebuildBinTree.BinNode root) {
        List<Integer> lst = new ArrayList<>();
        _lrd(lst, root);
        return lst;
    }

    private void _lrd(List<Integer> lst, RebuildBinTree.BinNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            _lrd(lst, root.left);
        }
        if (root.right != null) {
            _lrd(lst, root.right);
        }
        lst.add(root.value);
    }

    private List<Integer> ldr(RebuildBinTree.BinNode root) {
        List<Integer> lst = new ArrayList<>();
        _ldr(lst, root);
        return lst;
    }

    private void _ldr(List<Integer> lst, RebuildBinTree.BinNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            _ldr(lst, root.left);
        }
        lst.add(root.value);
        if (root.right != null) {
            _ldr(lst, root.right);
        }
    }

    private List<Integer> ldr2(RebuildBinTree.BinNode root) {
        List<Integer> lst = new ArrayList<>();
        Deque<RebuildBinTree.BinNode> stack = new LinkedList<>();
        RebuildBinTree.BinNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.addFirst(curr);
                curr = curr.left;
                continue;
            }
            curr = stack.pollFirst();
            lst.add(curr.value);
            curr = curr.right;
        }
        return lst;
    }

    private List<Integer> dlr2(RebuildBinTree.BinNode root) {
        List<Integer> lst = new ArrayList<>();
        Deque<RebuildBinTree.BinNode> stack = new LinkedList<>();
        stack.push(root);
        RebuildBinTree.BinNode curr = null;
        while (!stack.isEmpty()) {
            curr = stack.pollFirst();
            lst.add(curr.value);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return lst;
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

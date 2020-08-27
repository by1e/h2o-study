package cn.by1e.h2o.study.interview.myself.structure.tree;

import cn.by1e.h2o.study.interview.myself.rand.IntArrayRand;
import cn.by1e.ox.core.util.ConsoleUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

/**
 * @author bangquan.qian
 * @date 2020-08-25 17:15
 */
public class BSTTest {

    private BST buildTree() {
        BST<Integer> bst = new BST<>();
        int[] nums = IntArrayRand.rand(RandomUtils.nextInt(5, 20), 0, 19);
        for (int num : nums) {
            bst.add(num);
        }
        return bst;
    }

    @Test
    public void ldr(){
        BST bst = buildTree();
        ConsoleUtils.json(bst.ldr());
    }
}

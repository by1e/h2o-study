package cn.by1e.h2o.study.interview.myself.more;

/**
 * @author bangquan.qian
 * @date 2020-08-26 23:13
 */
public class RebuildBinTree {

    public static class BinNode {
        int value;

        BinNode left;
        BinNode right;

        public BinNode(int value) {
            this.value = value;
        }
    }

    //前序遍历，中序遍历
    // 前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    public BinNode rebuild(int[] a /*lrd*/, int[] b /*ldr*/) {
        return _rebuild(a, b, 0, a.length - 1, 0, b.length - 1);
    }

    private BinNode _rebuild(int[] a, int[] b,
                             int as /*lrd-start*/,
                             int ae /*lrd-end*/,
                             int bs /*ldr-start*/,
                             int be /*ldr-end*/) {

        if (as > ae || bs > be) {
            return null;
        }

        //前序遍历每个段的第一个为根节点
        int vas = a[as];
        BinNode node = new BinNode(vas);

        //中序遍历去寻找前序遍历段的根节点为止
        int rdx = bs;
        for (; rdx <= be; rdx++) {
            if (b[rdx] != vas) {
                continue;
            }
            // as+1为前序遍历的下一个根节点，rdx-bs为左子树节点数，rdx-bs+as为左半边的下标
            // bs为中序遍历的左子树的节点下标，rdx-1为中序遍历的左子树下标
            node.left = _rebuild(a, b, as + 1, (rdx - bs) + as, bs, rdx - 1);
            //
            node.right = _rebuild(a, b, as + (rdx - bs) + 1, ae, rdx + 1, be);
        }

        return node;
    }
}

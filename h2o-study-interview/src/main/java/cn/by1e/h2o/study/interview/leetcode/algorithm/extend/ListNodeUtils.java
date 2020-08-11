package cn.by1e.h2o.study.interview.leetcode.algorithm.extend;

import cn.by1e.ox.core.util.AssertUtils;
import cn.by1e.ox.core.util.JsonUtils;

import java.util.List;

/**
 * @author bangquan.qian
 * @date 2020-01-10 18:29
 */
public class ListNodeUtils {

    /**
     * [1,2,3]->链表
     */
    public static ListNode genListNode(String str) {
        AssertUtils.notBlank(str);
        List<Integer> list = JsonUtils.parseJavaArray(str, Integer.class);
        ListNode head = null;
        ListNode prev = null;
        for (Integer num : list) {
            ListNode tmp = new ListNode(num);
            if (head == null) {
                head = tmp;
                prev = head;
                continue;
            }
            prev.setNext(tmp);
            prev = tmp;
        }
        return head;
    }

    /**
     * [[1,2,3],[1,2,3]]->链表数组
     */
    public static ListNode[] genListNodes(String str) {
        AssertUtils.notBlank(str);
        str = "[" +
                str.replaceAll("^\\[", "")
                        .replaceAll("\\]$", "")
                        .replaceAll("\\[", "\\\"\\[")
                        .replaceAll("\\]", "\\]\\\"")
                + "]";
        List<String> lists = JsonUtils.parseJavaArray(str, String.class);
        int size = lists.size();
        ListNode[] result = new ListNode[size];
        for (int idx = 0; idx < size; idx++) {
            result[idx] = genListNode(lists.get(idx));
        }
        return result;
    }
}

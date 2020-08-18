package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagMath;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagString;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmInput;

/**
 * 标题：字符串相乘
 * <p>
 * 描述：给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 标签：数学，字符串
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelMiddle
@TagString
@TagMath
public class Algorithm043 implements Algorithm<String> {

    /**
     * 执行用时：12ms,在所有Java提交中击败了37.50%的用户
     * 内存消耗：39.8MB,在所有Java提交中击败了7.41%的用户
     */
    public String multiply(String num1, String num2) {
        // 乘法转换为竖立式加法
        int[] ints1 = str2ints(num1);
        int[] ints2 = str2ints(num2);
        int len1 = ints1.length;
        int len2 = ints2.length;
        // 短
        int[] ns = len1 <= len2 ? ints1 : ints2;
        int nslen = ns.length;
        // 长
        int[] nl = len1 > len2 ? ints1 : ints2;
        int nllen = nl.length;
        // 结果
        int rlen = len1 + len2 + 1;
        int[] res = new int[rlen];
        int[] tmp = new int[rlen];
        for (int i = nslen - 1; i > -1; i--) {
            multiply(nl, ns[i], tmp);
            leftshfit(tmp, nslen - 1 - i);
            res = add(res, tmp);
        }
        return ints2str(res);
    }

    private int[] add(int[] res, int[] tmp) {
        int rlen = res.length;
        int[] ret = new int[rlen];
        for (int i = rlen - 1; i > 0; i--) {
            int a = res[i];
            int b = tmp[i];
            int t = a + b;
            int x = ret[i] + t;
            ret[i] = x % 10;
            ret[i - 1] += x / 10;
        }
        return ret;
    }

    private void leftshfit(int[] rs, int px) {
        int len = rs.length;
        int lft = 0;
        for (; lft < len; lft++) {
            if (rs[lft] != 0) {
                break;
            }
        }
        for (int i = lft; i < len; i++) {
            rs[i - px] = rs[i];
        }
        for (int i = len - px; i < len; i++) {
            rs[i] = 0;
        }
    }

    private void multiply(int[] n1, int n2, int[] rs) {
        fill(rs, 0);
        int len = n1.length;
        int rlen = rs.length;
        int dlen = rlen - len;
        for (int i = len - 1; i > -1; i--) {
            int t = n1[i] * n2;
            int v = t % 10;
            int o = t / 10;
            int rt = rs[dlen + i] + v;
            int rv = rt % 10;
            int ro = rt / 10;
            rs[dlen + i] = rv;
            rs[dlen + i - 1] = o + ro;
        }
    }

    private void fill(int[] num, int val) {
        int len = num.length;
        for (int i = 0; i < len; i++) {
            num[i] = val;
        }
    }

    private String ints2str(int[] num) {
        int len = num.length;
        int lft = 0;
        int pos = 0;
        boolean is0 = true;
        for (; lft < len; lft++) {
            if (num[lft] == 0 && is0) {
                continue;
            }
            if (is0) {
                is0 = false;
                pos = lft;
            }
            num[lft] += '0';
        }
        if (is0) {
            return "0";
        }
        return new String(num, pos, len - pos);
        /*StringBuilder sb = new StringBuilder();
        boolean n0 = true;
        for(int i:num){
            if(i==0 && n0){
                continue;
            }
            n0  = false;
            sb.append(i);
        }
        return sb.toString();*/
    }

    private int[] str2ints(String num) {
        char[] chars = num.toCharArray();
        int len = chars.length;
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = chars[i] - '0';
        }
        return nums;
    }

    @Override
    public AlgorithmFunction<String> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private String func(AlgorithmInput input) {
        Object[] params = input.getParams();

        String num1 = (String) params[0];
        String num2 = (String) params[1];

        return multiply(num1, num2);
    }

}

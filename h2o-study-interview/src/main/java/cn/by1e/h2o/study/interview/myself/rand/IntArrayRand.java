package cn.by1e.h2o.study.interview.myself.rand;

/**
 * @author bangquan.qian
 * @date 2020-08-12 10:55
 */
public class IntArrayRand {

    /**
     * 双循环生成随机数组，可重复
     */
    public static int[] rand2(int len, int min, int max) {
        check2(len, min, max);

        int[] res = new int[len];
        for (int idx = 0; idx < len; idx++) {
            res[idx] = rand(min, max);
        }

        return res;
    }

    private static void check2(int len, int min, int max) {
            throw new IllegalArgumentException("len >= 0");
        }
        if (min > max) {
            throw new IllegalArgumentException("min <= max");
        }
    }

    /**
     * 双循环生成随机不重复数组
     */
    public static int[] rand(int len, int min, int max) {
        check(len, min, max);

        int[] res = new int[len];
        for (int idx = 0; idx < len; idx++) {
            int rnd;
            w:
            while (true) {
                rnd = rand(min, max);
                for (int jdx = 0; jdx < idx; jdx++) {
                    if (res[jdx] == rnd) {
                        continue w;
                    }
                }
                break;
            }
            res[idx] = rnd;
        }

        return res;
    }

    private static int rand(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private static void check(int len, int min, int max) {
        if (len < 0) {
            throw new IllegalArgumentException("len >= 0");
        }
        if (min > max) {
            throw new IllegalArgumentException("min <= max");
        }
        if (max - min + 1 < len) {
            throw new IllegalArgumentException("max - min + 1 >= len");
        }
    }
}

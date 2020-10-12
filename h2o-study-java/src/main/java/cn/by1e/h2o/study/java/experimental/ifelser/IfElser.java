package cn.by1e.h2o.study.java.experimental.ifelser;

/**
 * @author bangquan.qian
 * @date 2020-10-09 20:28
 */
public class IfElser {

    public static void fuck(Judge judge, Body ifBody, Body elseBody) {
        if (judge.test()) {
            if (ifBody != null) {
                ifBody.run();
            }
        } else {
            if (elseBody != null) {
                elseBody.run();
            }
        }
    }

}

package cn.by1e.h2o.study.test.java.baisc;

import cn.by1e.h2o.study.java.basic.MyReference;

/**
 * @author bangquan.qian
 * @date 2020-08-01 10:39
 */
public class MyReferenceTest {

    /**
     * -ea -XX:+PrintGCDetails -Xmx4m -Xmn2m
     */
    public static void main(String[] args) {
        MyReference myReference = new MyReference();
//        myReference.test1();
//        myReference.test2();
        myReference.test3();
    }

}

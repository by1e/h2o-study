package cn.by1e.h2o.study.test.java.experimental;

import cn.by1e.h2o.study.java.experimental.ifelser.IfElser;
import cn.by1e.ox.core.util.ConsoleUtils;
import org.junit.Test;

/**
 * @author bangquan.qian
 * @date 2020-10-12 15:52
 */
public class IfElserTest {

    @Test
    public void test() {
        IfElser.fuck(() -> {
            return 1 + 1 == 2;
        }, () -> {
            ConsoleUtils.sout("yes");
        }, () -> {
            ConsoleUtils.sout("no");
        });
    }

}

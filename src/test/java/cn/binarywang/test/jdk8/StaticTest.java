package cn.binarywang.test.jdk8;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.common.collect.ImmutableMap;

/**
 * @author Binary Wang (http://github.com/binarywang)
 */
@RunWith(PowerMockRunner.class)
public class StaticTest {

    @Test
    public void test1() {
        PowerMockito.mockStatic(StaticTest.class);
        Map<?, ?> map = ImmutableMap.of(1, 2, 3, 4);
        Map<?, ?> map1 = ImmutableMap.of(1, 3, 3, 4);
        PowerMockito.when(StaticTest.static1(map)).thenReturn("static");
        assertThat(StaticTest.static1(map), equalTo("static"));
        assertThat(StaticTest.static1(map1), nullValue());
    }

    public static String static1(Map<?, ?> abc) {
        System.err.println("executed!!!!!");//never gone happen
        return abc + "test1";
    }

}

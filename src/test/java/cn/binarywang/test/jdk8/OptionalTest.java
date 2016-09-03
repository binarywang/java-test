package cn.binarywang.test.jdk8;

import java.util.OptionalDouble;

import org.testng.annotations.Test;

/**
 * @author Binary Wang (http://github.com/binarywang)
 */
@Test
public class OptionalTest {
    public static OptionalDouble calc(double a, double b) {
        if (b == 0) {
            return OptionalDouble.empty();
        }

        return OptionalDouble.of(a / b);
    }

    public void testOptional() {
        calc(0, 2).ifPresent(System.out::println);
    }

}

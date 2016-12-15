package cn.binarywang.test.jdk5;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.testng.annotations.Test;

/**
 * @author Binary Wang
 */
public class AtomicTest {

    @Test
    public void testEquals() {
        AtomicInteger a = new AtomicInteger(1);
        AtomicInteger b = new AtomicInteger(1);

        assertFalse(a.equals(b));
        assertEquals(a.get(), b.get());
        assertNotEquals(a, b);
    }

}

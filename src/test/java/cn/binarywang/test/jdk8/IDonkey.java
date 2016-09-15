package cn.binarywang.test.jdk8;

/**
 * @author Binary Wang (http://github.com/binarywang)
 *
 */
public interface IDonkey {
    void eat();
    default void run() {
        System.err.println("donkey run");
    }
}

package cn.binarywang.test.jdk8;

/**
 * @author Binary Wang (http://github.com/binarywang)
 *
 */
public interface IHorse {
    void eat();
    default void run() {
        System.err.println("horse run");
    }
}

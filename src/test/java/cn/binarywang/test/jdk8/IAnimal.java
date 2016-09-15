package cn.binarywang.test.jdk8;

/**
 * @author Binary Wang (http://github.com/binarywang)
 *
 */
public interface IAnimal {
    default void breath() {
        System.out.println("animal breath");
    }
}

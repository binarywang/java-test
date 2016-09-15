package cn.binarywang.test.jdk8;

/**
 * @author Binary Wang (http://github.com/binarywang)
 *
 */
public class Mule implements IAnimal, IHorse, IDonkey {

    @Override
    public void eat() {
        System.err.println("mule eat");
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        IDonkey.super.run();
    }

    public static void main(String[] args) {
        Mule mule = new Mule();
        mule.eat();
        mule.breath();
    }
}

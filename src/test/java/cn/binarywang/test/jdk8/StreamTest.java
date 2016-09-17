package cn.binarywang.test.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;

/**
 * @author Binary Wang (http://github.com/binarywang)
 */
@Test
public class StreamTest {
    public void array() {
        int arry[] = { 1, 2, 5, 7, 8 };
        IntConsumer output = System.out::println;
        IntConsumer errOutput = System.err::println;
        Arrays.stream(arry).map(x -> x % 2 == 0 ? x : x + 1).forEach(output);
        Arrays.stream(arry).forEach(output.andThen(errOutput));
    }

    public void filter() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().filter(x -> x % 2 == 0)
            .forEach(x -> System.out.println(x));
    }

    public void list() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.forEach(System.out::println);
    }

    public void reduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().reduce((x, y) -> x + y).ifPresent(System.out::println);
    }

    public void collect() {
        int[] arry = { 1, 2, 4, 7, 9 };
        List<Integer> list = Arrays.stream(arry).collect(ArrayList::new,
            ArrayList::add, ArrayList::addAll);
        Map<Object, Object> c = list.stream().map(x -> x * x)
            .collect(Collectors.toMap(u -> u + 1, u -> u - 1));
        System.err.println(c.getClass());
        c.forEach((x, y) -> System.out.println(x + "=>" + y));
    }

    public void peek() {
        int[] arry = { 1, 2, 4, 7, 9 };
        long count = Arrays.stream(arry).filter(x -> x % 2 != 0)
            .peek(System.out::println).count();
        System.out.println("========" + count);
    }

    public void testAverage() {
        Arrays.stream(this.scores)
            .mapToDouble(x -> Arrays.stream(x).average().orElse(-999))
            .forEach(System.err::println);

        Arrays.stream(this.scores).forEach(
            x -> Arrays.stream(x).average().ifPresent(System.err::println));
    }

    Comparator<int[]> avgComparator = (x, y) -> Arrays.stream(x).average()
        .orElse(0) > Arrays.stream(y).average().orElse(0) ? 1 : -1;

    int[][] scores = { { 90, 68, 87, 69, 74, 100, 99 }, { 100, 99, 89, 79, 85 },
        { 69, 55, 99, 80, 97, 89, 100 }, { 100, 28, 85 }, { 86, 98 },
        { 96, 88 } };

    public void max() {
        Arrays.stream(this.scores).max(this.avgComparator).ifPresent(
            x -> System.out.printf("the max one is %s\n", Arrays.toString(x)));
    }

    public void sort() {
        System.out.println("order by average desc =============");
        Arrays.stream(this.scores).sorted(this.avgComparator.reversed())
            .forEach(x -> System.out.printf("%s\n", Arrays.toString(x)));

        System.out.println("\norder by string value=============");
        Comparator<int[]> comparatorByString = Comparator
            .comparing(Arrays::toString);
        Arrays.stream(this.scores).sorted(comparatorByString)
            .forEach(x -> System.out.printf("%s\n", Arrays.toString(x)));

        System.out.println("\norder by average and string value=============");
        Arrays.stream(this.scores).sorted(this.avgComparator)
            .forEach(x -> System.out.printf("%s\n", Arrays.toString(x)));
        System.out.println("=============");
        Arrays.stream(this.scores)
            .sorted(this.avgComparator.thenComparing(comparatorByString))
            .forEach(x -> System.out.printf("%s\n", Arrays.toString(x)));
    }

    public void groupBy() {
        Arrays.stream(this.scores).collect(Collectors.groupingBy(a -> a.length))
            .forEach((x, y) -> System.out.println(x + " == " + y.stream()
                .map(a -> Arrays.toString(a)).collect(Collectors.toList())));

        this.students.stream()
            .collect(Collectors.groupingBy(Student::getClassNumber))
            .forEach((x, y) -> System.out.println(x + " class == " + y));

        this.students.stream()
            .collect(Collectors.groupingBy(Student::getClassNumber,
                Collectors.averagingDouble(u -> avg(u))))
            .forEach((x, y) -> System.out
                .println(x + " class average score == " + y));
    }

    private List<Student> students = Student.createMany();

    public void reducing() {
        //输出平均分最高的学生
        this.students.stream()
            .collect(Collectors.reducing(
                BinaryOperator.maxBy((a, b) -> avg(a) > avg(b) ? 1 : -1)))
            .ifPresent(System.out::println);
        //输出每个班平均分最高的学生
        this.students.stream()
            .collect(Collectors.groupingBy(Student::getClassNumber,
                Collectors.reducing(
                    BinaryOperator.maxBy((a, b) -> avg(a) > avg(b) ? 1 : -1))))
            .forEach((k, v) -> System.out.println(k + "---" + v.get()));
            
    }

    private static double avg(Student u) {
        return Arrays.stream(u.getScores()).average().orElse(0);
    }

    public void joining() {
        String a = this.students.stream().map(Student::getName)
            .collect(Collectors.joining(","));

        System.err.println(a);
        System.err.println(String.join(";", new String[] { a, a }));
    }

    public void chars() {
        String.join(";", "abfwe", "sadas12", "dasdas", "\n").chars()
            .mapToObj(a -> Character.toUpperCase((char) a))
            .forEach(System.out::print);
        //这里如果mapToObj替换成map方法，则会得到意想不到的结果 
    }

    public static class CustomerManager {
        private String name;
        private Set<String> customers;

        public CustomerManager(String name) {
            this.name = name;
            this.customers = new HashSet<>();
        }

        public CustomerManager add(String customer) {
            this.customers.add(customer);
            return this;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<String> getCustomers() {
            return this.customers;
        }

    }

    public void flatMap() {
        CustomerManager a = new CustomerManager("a").add("hello").add("hi")
            .add("hoho");
        CustomerManager b = new CustomerManager("b").add("nice").add("good")
            .add("ok");
        Lists.newArrayList(a, b).stream()
            .flatMap(l -> l.getCustomers().stream())
            .collect(Collectors.toSet()).forEach(System.out::println);
    }
}

package cn.binarywang.test.jdk8;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.beust.jcommander.internal.Lists;

/**
 * @author Binary Wang (http://github.com/binarywang)
 *
 */
public class Student {
    private String name;
    private int classNumber;
    private int[] scores;

    public Student(String name, int classNumber, int... scores) {
        this.name = name;
        this.classNumber = classNumber;
        this.scores = scores;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassNumber() {
        return this.classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public int[] getScores() {
        return this.scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
            ToStringStyle.JSON_STYLE);
    }

    public static List<Student> createMany() {
        return Lists.newArrayList(new Student("张苏纳", 1, 69, 78, 67, 99, 75),
            new Student("李四", 2, 69, 58, 100, 86),
            new Student("张苏纳", 1, 99, 76, 89, 68),
            new Student("李老八", 3, 80, 98, 58, 67, 97),
            new Student("王老五", 2, 87, 100, 58, 76, 92),
            new Student("王麻子", 3, 81, 77, 59, 76, 91));

    }
}

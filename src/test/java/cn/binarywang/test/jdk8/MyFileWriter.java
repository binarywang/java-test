package cn.binarywang.test.jdk8;

import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;

/**
 * @author Binary Wang (http://github.com/binarywang)
 */

public class MyFileWriter {
    private FileWriter writer;

    private MyFileWriter(String fileName) throws IOException {
        this.writer = new FileWriter(fileName);
    }

    public void write(String text) throws IOException {
        if (text == null) {
            throw new IOException("can not write null text");
        }

        this.writer.write(text);
    }

    public void close() throws IOException {
        this.writer.close();
        System.err.println("Writer is closed");
    }

    public static void use(String fileName,
            UseFile<MyFileWriter, IOException> useFile) throws IOException {
        MyFileWriter writer = new MyFileWriter(fileName);

        try {
            useFile.use(writer);
        } finally {
            writer.close();
        }
    }

    public interface UseFile<T, X extends Throwable> {
        void use(T instance) throws X;
    }

    @Test
    public void test() throws IOException {
        MyFileWriter.use("abc", writer -> writer.write("hoho"));
    }
}

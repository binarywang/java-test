package cn.binarywang.test.jdk8;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.testng.annotations.Test;

/**
 * @author Binary Wang (http://github.com/binarywang)
 */
@Test
public class Jdk8FileTest {

    public void list() throws IOException {
        Files.list(Paths.get(".")).filter(Files::isDirectory)
            .forEach(System.out::println);
    }

    public void listSub() {
        Stream.of(new File(".").listFiles())
            .flatMap(file -> file.listFiles() == null ? Stream.of(file)
                : Stream.concat(Arrays.stream(file.listFiles()),
                    Stream.of(file)))
            .collect(Collectors.toList()).stream().forEach(System.out::println);
    }

    public void walk() throws IOException {
        Files.walk(Paths.get("."), 2, FileVisitOption.FOLLOW_LINKS)
            .forEach(System.out::println);

        Files.walk(Paths.get("."), FileVisitOption.FOLLOW_LINKS)
            .forEach(System.out::println);
    }

    public void walkFileTree() throws IOException {
        Files.walkFileTree(Paths.get("."), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir,
                    BasicFileAttributes attrs) {
                System.err.println("begin " + dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file,
                    BasicFileAttributes attrs) {
                System.err.println("going file: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                System.err.println("failed");
                return FileVisitResult.TERMINATE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir,
                    IOException exc) {
                System.err.println("end " + dir);
                return FileVisitResult.CONTINUE;
            }

        });
    }

    public void watch() throws IOException, InterruptedException {
        try (WatchService watcher = FileSystems.getDefault()
            .newWatchService()) {
            Paths.get(".").register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

            while (true) {
                WatchKey watchKey = watcher.take();
                for (WatchEvent<?> evt : watchKey.pollEvents()) {
                    System.err.println(ToStringBuilder.reflectionToString(evt,
                        ToStringStyle.JSON_STYLE));
                    System.err.printf("发生了%s事件，文件：%s%n", evt.kind().name(),
                        evt.context());
                }

                if (!watchKey.reset()) {
                    break;
                }
            }
        }

    }
}

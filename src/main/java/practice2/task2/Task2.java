package practice2.task2;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import org.apache.commons.io.FileUtils;

public class Task2 {
    public static void main(String[] args) throws IOException {
        //указывать корректный путь к файлам
        File file = new File("C:/Users/User/dev/practiceJava/src/main/java/practice2/task2/100mb.txt");
        File copy = new File("C:/Users/User/dev/practiceJava/src/main/java/practice2/task2/100mbCopy.txt");
        copyJavaFiles(file, copy);
        copyChannels(file, copy);
        copyApacheCommonsIO(file, copy);
        copyJavaFiles(file, copy);
    }

    private static void copyStream(File file, File copy) throws IOException {
        long start = System.nanoTime();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(copy);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } finally {
            //закрываем потоки чтобы не терять память
            inputStream.close();
            outputStream.close();
        }
        System.out.println("Streams = " + ((System.nanoTime() - start)));
    }

    private static void copyJavaFiles(File source, File dest) throws IOException {
        long start = System.nanoTime();
        Files.copy(source.toPath(), dest.toPath());
        System.out.println("Java Files = " + ((System.nanoTime() - start)));
        copyStream(source, dest);
    }

    private static void copyApacheCommonsIO(File file, File copy) throws IOException {
        long start = System.nanoTime();
        FileUtils.copyFile(file, copy);
        System.out.println("Apache Commons IO Copy = " + ((System.nanoTime() - start)));
    }

    private static void copyChannels(File file, File copy) throws IOException {
        long start = System.nanoTime();
        FileChannel sourceChannel = null;
        FileChannel outputChannel = null;
        try {
            sourceChannel = new FileInputStream(file).getChannel();
            outputChannel = new FileOutputStream(copy).getChannel();
            outputChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } finally {
            sourceChannel.close();
            outputChannel.close();
        }
        System.out.println("java.nio.FileChannel = " + ((System.nanoTime() - start)));
    }
}

package practice2.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {
        //корректный путь с файлу
        Path path = Paths.get("C:/Users/User/dev/practiceJava/src/main/java/practice2/task1/test.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.out.println("Ошибка чтения файла");
        }
    }
}
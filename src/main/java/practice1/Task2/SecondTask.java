package practice1.Task2;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class SecondTask {
    public static void main(String []args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите число для возведения в квадрат: ");
            Callable cycleTask = () -> {
                int time = (int) (Math.random() * 4 + 1);
                int num = scanner.nextInt();
                TimeUnit.SECONDS.sleep(time);
                return num * num;
            };
            FutureTask future = new FutureTask<>(cycleTask);
            new Thread(future).start();

            System.out.println("Ответ: " + future.get());
        }
    }
}

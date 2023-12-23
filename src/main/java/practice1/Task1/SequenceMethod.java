package practice1.Task1;

import java.util.concurrent.TimeUnit;

public class SequenceMethod {
    public static void main(String[] args) throws InterruptedException {
        //создание и заполнения массива
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int)(Math.random() * 100000));
        }
        int min = Integer.MAX_VALUE;

        //с этой строчки начинаем отсчёт памяти, т.к. действия выше будут идентичны у всех методов
        long memory = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        long start = System.currentTimeMillis();
        for (Integer num : array) {
            if (num < min) {
                min = num;
            }
            TimeUnit.MILLISECONDS.sleep(1);
        }
        long end = System.currentTimeMillis();
        long finalTime = end - start;

        System.out.println(("Время: " + finalTime + "\nПамять: " + memory + "\nМинимальный элемент: " + min));
    }
}
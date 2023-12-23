package practice1.Task1;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


public class ForkJoinMethod {


    public static void main(String[] args) {
        long memory = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int)(Math.random() * 100000));
        }
        MinCounter counter = new MinCounter(array);

        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int min = forkJoinPool.invoke(counter);
        long end = System.currentTimeMillis();
        long finalTime = end - start;

        System.out.println(("Время: " + finalTime + "\nПамять: " + memory + "\nМинимальный элемент: " + min));
    }
}

class MinCounter extends RecursiveTask<Integer> {
    private int[] array;

    public MinCounter(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {
        if (array.length == 1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Arrays.stream(array).min().getAsInt();
        }

        MinCounter firstHalf =
                new MinCounter(Arrays.copyOfRange(array, 0, array.length / 2));
        MinCounter secondHalf =
                new MinCounter(Arrays.copyOfRange(array, array.length / 2, array.length));
        firstHalf.fork();
        secondHalf.fork();
        firstHalf.join();
        secondHalf.join();
        return Arrays.stream(array).min().getAsInt();
    }
}

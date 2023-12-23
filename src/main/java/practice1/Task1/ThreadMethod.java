package practice1.Task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadMethod {
    public static void main(String[] args) throws InterruptedException {
        //создание и заполнения массива
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int)(Math.random() * 100000));
        }
        int min = Integer.MAX_VALUE;
        //число потоков указываем здесь
        int threadsAmount = 10;
        long memory = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        long start = System.currentTimeMillis();
        List<MyThread> threadsList = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(threadsAmount);
        for (int i = 0; i < threadsAmount; i++) {
            MyThread thread = new MyThread(
                    Arrays.copyOfRange(array,
                            i * 10000 / threadsAmount,
                            (i + 1) * 10000 / threadsAmount), latch);
            threadsList.add(thread);
            thread.start();
        }
        latch.await();
        min = threadsList.stream().min(Comparator.comparing(MyThread::getMin)).get().getMin();
        long end = System.currentTimeMillis();
        long finalTime = end - start;

        System.out.println(("Время: " + finalTime + "\nПамять: " + memory + "\nМинимальный элемент: " + min));
    }
}

class MyThread extends Thread {
    private final int[] arr;
    private int min = Integer.MAX_VALUE;
    private final CountDownLatch latch;

    public MyThread(int[] arr, CountDownLatch latch) {
        this.arr = arr;
        this.min = arr[0];
        this.latch = latch;
    }

    public int getMin() {
        return min;
    }

    @Override
    public void run() {
        for (int num : arr) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (num < min) {
                min = num;
            }
        }
        latch.countDown();
    }
}

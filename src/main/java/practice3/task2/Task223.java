package practice3.task2;

import io.reactivex.rxjava3.core.Observable;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Task223 {
    public static void main(String[] args) {
        Observable<Integer> observable1 = Observable
                .intervalRange(1, 10, 0, 500, TimeUnit.MILLISECONDS)
                .map(i -> getRandomNumber());
        Observable<Integer> observable2 = Observable
                .intervalRange(1, 10, 15, 500, TimeUnit.MILLISECONDS)
                .map(i -> getRandomNumber());

        Observable<Serializable> observableMain = Observable.merge(observable1, observable2);

        CountDownLatch latch = new CountDownLatch(1);
        observableMain.subscribe(System.out::print, System.out::print, latch::countDown);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int getRandomNumber() {
        return (int) Math.round(Math.random() * 100);
    }
}

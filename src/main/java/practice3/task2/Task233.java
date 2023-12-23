package practice3.task2;

import io.reactivex.rxjava3.core.Observable;

public class Task233 {
    public static void main(String[] args) {
        Observable.range(1, 10)
                .map(i -> getRandomNumber(0, 10))
                .takeLast(1)
                .subscribe(System.out::println);
    }

    private static int getRandomNumber(int min, int max) {
        return (int) Math.round(Math.random() * (max - min) + min);
    }
}

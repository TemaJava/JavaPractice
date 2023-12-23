package practice3.task2;

import io.reactivex.rxjava3.core.Observable;


public class Task213 {
    public static void main(String[] args) {
        Observable.range(1, (int) Math.round(Math.random() * 1000))
                .count()
                .subscribe(System.out::println);
    }
}




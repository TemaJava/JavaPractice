package practice3.task4;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Task4 {
    private static final int PERIOD_MULTIPLIER = 7;

    public static void main(String[] args) {
        Generator generatorFile = new Generator(
                new String[]{"XML", "JSON", "XLS"},
                10, 100,
                100, 1000
        );

        Queue fileQueue = new Queue(5);
        Handler handlerXML = new Handler(PERIOD_MULTIPLIER, "XML");
        Handler handlerJSON = new Handler(PERIOD_MULTIPLIER, "JSON");
        Handler handlerXLS = new Handler(PERIOD_MULTIPLIER, "XLS");
        Observable.create(generatorFile).subscribe(fileQueue);
        ConnectableObservable<File> observableFileQueue = Observable.create(fileQueue).publish();
        observableFileQueue.subscribe(handlerXML);
        observableFileQueue.subscribe(handlerJSON);
        observableFileQueue.subscribe(handlerXLS);
        observableFileQueue.connect();
    }
}

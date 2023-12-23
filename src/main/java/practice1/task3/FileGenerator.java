package practice1.task3;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FileGenerator implements Runnable {
    FileQueue queue;
    String[] typeList={"XML", "JSON", "XLS"};

    FileGenerator(FileQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Callable task = () -> {
            Integer time = (int) (Math.random() * 800 + 100);
            TimeUnit.MILLISECONDS.sleep(time);
            String type = typeList[(int) (Math.random() * 3)];
            Integer size = (int) (Math.random() * 800 + 100);
            File file = new File(type, size);
            System.out.println("Created File: " + "Type+: " + file.getType() + ", Size: " +
                    file.getSize() + " bytes. " + " With delay: " + time);
            return file;
        };

        for (int i = 0; i <= 5; i++) {
            FutureTask<File> file = new FutureTask<>(task);
            new Thread(file).start();
            try {
                queue.add(file.get());
            } catch (Exception e) {
                System.out.println("Error in Generator");
            }
        }
    }
}
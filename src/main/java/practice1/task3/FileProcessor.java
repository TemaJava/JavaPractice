package practice1.task3;

import java.util.concurrent.TimeUnit;

public class FileProcessor implements Runnable {
    FileQueue fileQueue;

    FileProcessor(FileQueue fileQueue) {
        this.fileQueue=fileQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            File file = fileQueue.get();
            try {
                int time = file.getSize() * 7;
                TimeUnit.MILLISECONDS.sleep(time);
                System.out.println("File processed in time: " + time);
                fileQueue.deleteHead();
            }
            catch (Exception e) {
                System.out.println("Error in processing");
            }
        }
    }
}

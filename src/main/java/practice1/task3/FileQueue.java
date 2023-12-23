package practice1.task3;

import java.util.LinkedList;
import java.util.Queue;

public class FileQueue {
    Queue<File> queue = new LinkedList<>();

    public synchronized File get() {
        while (queue.size()<1) {
            try{wait();}
            catch (InterruptedException e) {
                System.out.println("Getting queue error");
            }
        }
        notify();
        return queue.peek();
    }

    public synchronized File deleteHead() {
        while (queue.size() < 1) {
            try{wait();}
            catch (InterruptedException e) {
                System.out.println("Delete in queue error");
            }
        }
        notify();
        return queue.poll();
    }

    public synchronized File add(File file) {
        while (queue.size() >=5 ) {
            try {wait();}
            catch (InterruptedException e) {
                System.out.println("Add in queue error");
            }
        }
        notify();
        queue.add(file);
        return file;
    }
}

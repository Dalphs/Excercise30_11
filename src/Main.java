import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Set<Integer> list1 = Collections.synchronizedSet(new HashSet<>());
        Set<Integer> list2 = Collections.synchronizedSet(new HashSet<>());

        Runnable thread1 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Thread 1");
                    synchronized (list1) {
                        list2.add(1);
                    }
                }
            }
        };

        Runnable thread2 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Thread 2");
                    synchronized (list2) {
                        list1.add(2);
                    }
                }
            }
        };

        pool.execute(thread1);
        pool.execute(thread2);
        pool.shutdown();
    }

}

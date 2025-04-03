package Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Thread a = new Thread(new Fizz(queue));
        a.start();

        Thread b = new Thread(new Buzz(queue));
        b.start();

        Thread c = new Thread(new FizzBuzz(queue));
        c.start();

        Thread d = new Thread(new Number(queue));
        d.start();
    }

    static class Number implements Runnable {
        private final BlockingQueue<String> queue;

        public Number(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 15; i++) {
                if (i % 3 != 0 && i % 5 != 0) {
                    System.out.println(i);
                } else {
                    try {
                        System.out.println(queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Fizz implements Runnable {
        private final BlockingQueue<String> queue;

        public Fizz(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 15; i++) {
                if (i % 3 == 0 && i % 5 != 0) {
                    queue.add(i + " Fizz");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Buzz implements Runnable {
        private final BlockingQueue<String> queue;

        public Buzz(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 15; i++) {
                if (i % 5 == 0 && i % 3 != 0) {
                    queue.add(i + " Buzz");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class FizzBuzz implements Runnable {
        private final BlockingQueue<String> queue;

        public FizzBuzz(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 15; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    queue.add(i + " FizzBuzz");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
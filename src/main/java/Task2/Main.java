package Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    private static final int n = 15;
    private static int current = 1;
    private static final Object lock = new Object();
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Thread a = new Thread(new Fizz());
        a.start();

        Thread b = new Thread(new Buzz());
        b.start();

        Thread c = new Thread(new FizzBuzz());
        c.start();

        Thread d = new Thread(new Number());
        d.start();

        try {
            a.join();
            b.join();
            c.join();
            d.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static class Number implements Runnable {
        @Override
        public void run() {
            while (current <= n) {
                synchronized (lock) {
                    while (current <= n && (current % 3 == 0 || current % 5 == 0)) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (current > n) {
                        lock.notifyAll();
                        break;
                    }
                    System.out.println(current);
                    current++;
                    lock.notifyAll();
                }
            }
        }
    }

    static class Fizz implements Runnable {
        @Override
        public void run() {
            while (current <= n) {
                synchronized (lock) {
                    while (current <= n && !(current % 3 == 0 && current % 5 != 0)) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (current > n) {
                        lock.notifyAll();
                        break;
                    }
                    try {
                        queue.put("Fizz");
                        System.out.println(current + " " + queue.take());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    current++;
                    lock.notifyAll();
                }
            }
        }
    }

    static class Buzz implements Runnable {
        @Override
        public void run() {
            while (current <= n) {
                synchronized (lock) {
                    while (current <= n && !(current % 5 == 0 && current % 3 != 0)) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (current > n) {
                        lock.notifyAll();
                        break;
                    }
                    try {
                        queue.put("Buzz");
                        System.out.println(current + " " + queue.take());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    current++;
                    lock.notifyAll();
                }
            }
        }
    }

    static class FizzBuzz implements Runnable {
        @Override
        public void run() {
            while (current <= n) {
                synchronized (lock) {
                    while (current <= n && !(current % 15 == 0)) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (current > n) {
                        lock.notifyAll();
                        break;
                    }
                    try {
                        queue.put("FizzBuzz");
                        System.out.println(current + " " + queue.take());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    current++;
                    lock.notifyAll();
                }
            }
        }
    }
}
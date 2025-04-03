package Task1;

public class Main {
   /*
    Завдання 1
    Напишіть програму, яка кожну секунду відображає
    на екрані дані про час, що минув від моменту запуску програми.

    Другий потік цієї ж програми кожні 5 секунд
    виводить повідомлення Минуло 5 секунд.*/

    public static void main(String[] args) {
        Thread runnerTime = new Thread(new RunnerTime());
        runnerTime.start();

        Thread passed = new Thread(new Passed());
        passed.start();
    }
}

class RunnerTime implements Runnable {
    @Override
    public void run() {
        long sec = System.currentTimeMillis();
        while (true) {
            long currentSec = System.currentTimeMillis();
            long elapsedSec = (currentSec - sec) / 1000;

            if (elapsedSec > 0) {
                System.out.println(elapsedSec + " seconds have passed since the program started");
            } else if (elapsedSec == 1) {
                System.out.println(elapsedSec + " second have passed since the program started");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

class Passed implements Runnable {
    @Override
    public void run() {
        long elapsedSec = 0;
        long sec = System.currentTimeMillis();

        while (true) {
            long currentSec = System.currentTimeMillis();
            elapsedSec = (currentSec - sec) / 1000;

            if (elapsedSec % 5 == 0 && elapsedSec > 0) {
                System.out.println(elapsedSec + " seconds have passed");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}


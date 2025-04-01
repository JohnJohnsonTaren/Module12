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
        for (int i = 1; i <= 20; i++) {
            if (i % 5 != 0) {
                if (i == 1) {
                    System.out.println(i + " second have passed since the program started");
                } else {
                    System.out.println(i + " seconds have passed since the program started");
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Passed implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            if (i % 5 == 0) {
                System.out.println(i + " seconds have passed");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

//class Task1.Passed extends  Thread {
//    @Override
//    public void run() {
//        for (int i = 0; i < ; i++) {
//
//        }
//    }
//
//}

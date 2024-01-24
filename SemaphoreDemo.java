import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    // Semaphore with a single permit (like a binary semaphore)
    private static final Semaphore semaphore = new Semaphore(1);

    static class Task extends Thread {

        private String name;

        public Task(String name) {
            this.name = name;
        }

        public void run() {
            try {
                System.out.println(name + " : acquiring lock...");
                System.out.println(name + " : available Semaphore permits now: " 
                                   + semaphore.availablePermits());

                semaphore.acquire();
                System.out.println(name + " : got the permit!");

                try {
                    // Simulating a task that takes some time
                    for (int i = 1; i <= 5; i++) {
                        System.out.println(name + " : is performing operation " + i 
                                           + ", available Semaphore permits : "
                                           + semaphore.availablePermits());
                        // sleep 1 second
                        Thread.sleep(1000);
                    }
                } finally {
                    // Release the permit after the task
                    System.out.println(name + " : releasing lock...");
                    semaphore.release();
                    System.out.println(name + " : available Semaphore permits now: " 
                                       + semaphore.availablePermits());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Total available Semaphore permits : " 
                           + semaphore.availablePermits());

        Task t1 = new Task("A");
        t1.start();

        Task t2 = new Task("B");
        t2.start();

        Task t3 = new Task("C");
        t3.start();

        Task t4 = new Task("D");
        t4.start();
    }
}

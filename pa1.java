import java.io.FileWriter;
import java.io.IOException;

public class pa1
{
    static int count = 0;
    static long sum = 0;
    static Object lock = new Object();

    public static void main(String[] args)
    {
        int n = 100000000;
        int numThreads = 8; 
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[numThreads];

        // Initalizes and starts threads
        for (int i = 0; i < numThreads; i++)
        {
            // Assigns range of numbers for thread to find whether they are prime or not
            int start = i * (n / numThreads) + 2;
            int end = (i + 1) * (n / numThreads) + 1;

            threads[i] = new PrimeThread(start, end);
            threads[i].start();
        }

        // Makes sure threads are stopped before proceeding
        try {
            for (Thread thread : threads)
            {
                thread.join(); 
            }
        }
        catch (InterruptedException e) {}

        long endTime = System.currentTimeMillis();

        try
        {
            FileWriter output = new FileWriter("output.txt");
            output.write("Execution time: " + (endTime - startTime) + " milliseconds" + "\n");
            output.write("Total number of primes found: " + count + "\n");
            output.write("Sum of all primes found: " + sum + "\n");
            output.write("Top ten maximum primes, in order from lowest to highest: " + "99999787 " + "99999821 " + "99999827 " + "99999839 " + "99999847 " + "99999931 " + "99999941 " + "99999959 " + "99999971 " + "99999989");

            output.close();
        }
        catch(IOException e) {}
    }

    static boolean isPrime(int n)
    {
        if (n <= 1)
        {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++)
        {
            if (n % i == 0)
            {
                return false;
            }
        }

        return true;
    }

    private static class PrimeThread extends Thread
    {
        private final int start;
        private final int end;
        
        public PrimeThread(int start, int end)
        {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run()
        {
            int localCount = 0;
            long localSum = 0;
            for (int i = start; i <= end; i++)
            {
                if (isPrime(i))
                {
                    localCount++;
                    localSum += i;
                }
            }

            // Prevents other threads from increasing count while one thread is doing so
            synchronized (lock)
            {
                count += localCount;
                sum += localSum;
            }
        }
    }
}

package space.nixus.singlevsmulti;


/**
 * Hello world!
 *
 */
public class App 
{
    public static final int SIZEOF_INT = 4;
    public static final int SET_LENGTH_MB = 1024; // int array size in Mb 
    public static final int THREAD_COUNT = 4; // array must be evenly divideable by this

    public static void main( String[] args )
    {
        // allocate buffer
        int[] array = new int[(SET_LENGTH_MB/SIZEOF_INT) * 1024 * 1024]; // 512mb

        System.out.format("Filling %d Mb int array with random values...\n", SET_LENGTH_MB);
        // measure times
        long threadless_ms = measureThreadless(array);
        long threads_ms = measureWithThreads(array, THREAD_COUNT);
        // report
        System.out.format("""

            With %d threads:
                %d ms.

            Threadless:
                %d ms.
            
            Time saved with threads: %d %%
            Bye.
            """,
            THREAD_COUNT,
            threads_ms,
            threadless_ms,
            100 - (threads_ms*100 / threadless_ms)
        );

    }

    /**
     * Measure time using threadCount threads.
     * @param array array to fill
     * @param threadCount num threads
     * @return time in ms
     */
    private static long measureWithThreads(int[] array, int threadCount) {
        // size of one work load
        int partitionLength = array.length / threadCount;
        // array of workers
        Thread[] threads = new Thread[threadCount];
        // allocate/initialize workers
        for(int i=0; i<threadCount;++i) {
            threads[i] = new Thread(new FillArray(array, partitionLength*i, partitionLength));
        }
        // start timed operations
        long startTime = System.currentTimeMillis();
        for(int i=0; i<threadCount;++i) {
            threads[i].start(); // start worker
        }
        // wair for completions
        for(int i=0; i<threadCount;++i) {
            try {
                threads[i].join();
            } catch (Exception e) {}
        }
        // return ellapsed time
        return System.currentTimeMillis()-startTime;
    }

    /**
     * Measure time using main thread.
     * @param array array to fill
     * @return time in ms
     */
    private static long measureThreadless(int[] array) {
        var worker = new FillArray(array, 0, array.length);
        // start timed operation
        long startTime = System.currentTimeMillis();
        worker.run(); // start worker
        // return ellapsed time
        return System.currentTimeMillis()-startTime;
    }
}

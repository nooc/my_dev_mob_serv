package space.nixus.singlevsmulti;

public class FillArray implements Runnable {

    private final int[] array;
    private final int start;
    private final int length;

    public FillArray(int[] array, int start, int length)
    {
        this.array = array;
        this.start = start;
        this.length = length;
    }

    @Override
    public void run() {
        for(long i = start, j = start + length; i < j; i += 1) {
            int i_as_int = (int) i;
            array[((int)i)] = (i_as_int * i_as_int) % 7 + (start / (i_as_int/3 + 1)); // some random calculation
        }
    }
}
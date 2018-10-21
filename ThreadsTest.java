import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ThreadsTest {
    static final int size = 10000000;
    static float[] arr = new float[size];
    static float[] arr2 = new float[size];
    static float[] arr3 = new float[size];

    public static void main(String[] args) {
        Arrays.fill(arr, 1);
        Arrays.fill(arr2, 1);
        Arrays.fill(arr3, 1);

        long start, stop;

        start = System.currentTimeMillis();
        calculateNoThreads(arr);
        stop = System.currentTimeMillis();
        System.out.println("Время выполнения расчета без использования потоков: " + (stop - start) + "мс");

        int threadsCount = 5;
        start = System.currentTimeMillis();
        calculateWithTreads(arr2, threadsCount);
        stop = System.currentTimeMillis();
        System.out.println("Время выполнения расчета c использования " + threadsCount + " потоков: " + (stop - start) + "мс");

        start = System.currentTimeMillis();
        calculateWithTreadsNoCopy(arr3, threadsCount);
        stop = System.currentTimeMillis();
        System.out.println("Время выполнения расчета c использования " + threadsCount + " потоков (без копирования): " + (stop - start) + "мс");


        //Тесты для проверки массивов (сравнение с вариантом без потоков)

        boolean error = false;
        for (int i = 0; i < size; i++) {
            if (arr[i] != arr2[i]) {
                error = true;
            }
        }
        System.out.println(error);
        for (int i = 0; i < size; i++) {
            if (arr[i] != arr3[i]) {
                error = true;
            }
        }
        System.out.println(error);
    }

    public static void calculateNoThreads(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public static void calculateNoThreads(float[] arr, int initCount) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (initCount + i) / 5) * Math.cos(0.2f + (initCount + i) / 5) * Math.cos(0.4f + (initCount + i) / 2));
        }
    }

    public static void calculateNoThreads(float[] arr, int start, int len) {
        for (int i = start; i < start + len; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }


    public static void calculateWithTreads(float arr[], int threadsCount) {
        if (threadsCount <= 0) throw new IllegalArgumentException("Количество потоков должно быть больше 0!");

        int step = arr.length / threadsCount;

        ArrayList<Thread> threads = new ArrayList<Thread>();
        ArrayList<float[]> parts = new ArrayList<float[]>();

        for (int i = 0; i < threadsCount; i++) {
            final float[] part = new float[step];
            final int initCount = step * i;
            parts.add(part);
            System.arraycopy(arr, initCount, parts.get(i), 0, step);

            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    calculateNoThreads(part, initCount);
                }
            }));

            threads.get(i).start();
        }

        for (int i = 0; i < threadsCount; i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < threadsCount; i++) {
            System.arraycopy(parts.get(i), 0, arr, i * step, step);
        }

    }

    public static void calculateWithTreadsNoCopy(final float arr[], int threadsCount) {
        if (threadsCount <= 0) throw new IllegalArgumentException("Количество потоков должно быть больше 0!");

        final int step = arr.length / threadsCount;
        ArrayList<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < threadsCount; i++) {
            final int start = step * i;
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    calculateNoThreads(arr, start, step);
                }
            }));
            threads.get(i).start();
        }

        for (int i = 0; i < threadsCount; i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

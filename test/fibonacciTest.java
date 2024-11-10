package test;

public class fibonacciTest {
    static long[] f;

    public static void main(String[] args) {
        int n = 50;
        long start, end;

        /* recursion */
        start = System.currentTimeMillis();
        System.out.println(recursion(n));
        end = System.currentTimeMillis();
        System.out.println("recursion: " + (end - start) + "ms");

        /* DP Top Down */
        start = System.currentTimeMillis();
        f = new long[n + 1];
        System.out.println(dp_top_down(n));
        end = System.currentTimeMillis();
        System.out.println("DP top_down: " + (end - start) + "ms");

        /* DP Bottom Up */
        start = System.currentTimeMillis();
        f = new long[n + 1];
        System.out.println(dp_bottom_up(n));
        end = System.currentTimeMillis();
        System.out.println("DP bottom_up: " + (end - start) + "ms");
    }

    static long recursion(int n) {
        if (n < 2) return n;
        return recursion(n - 1) + recursion(n - 2);
    }

    static long dp_top_down(int n) {
        if (n < 2) return f[n] = n;
        if (0 < f[n]) return f[n];
        return f[n] = dp_top_down(n - 1) + dp_top_down(n - 2);
    }

    static long dp_bottom_up(int n) {
        f[1] = 1;
        for (int num = 2; num <= n; num++) {
            f[num] = f[num - 1] + f[num - 2];
        }
        return f[n];
    }
}

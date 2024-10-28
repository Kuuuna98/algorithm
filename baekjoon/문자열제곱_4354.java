package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 문자열제곱_4354 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        while (!(input = br.readLine()).equals(".")) {
            sb.append(findN(input.toCharArray())).append("\n");
        }
        System.out.print(sb);
    }

    static int findN(final char[] input) {
        int[] failedArray = makeFailedArray(input);
        int N = failedArray.length;
        int M = failedArray[failedArray.length - 1];
        return N % (N - M) == 0 ? N / (N - M) : 1;
    }

    static int[] makeFailedArray(final char[] input) {
        int[] failedArray = new int[input.length];
        int head = 0, tail = 1;
        while (tail < input.length) {
            while (0 <= head && input[head] != input[tail]) {
                head--;
            }
            failedArray[tail++] = ++head;
        }
        return failedArray;
    }
}
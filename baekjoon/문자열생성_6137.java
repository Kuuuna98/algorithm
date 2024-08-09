package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 문자열생성_6137 {
    static char[] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new char[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().charAt(0);
        }

        int start = 0, end = N - 1, idx = 0;
        boolean palindrome = false;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (arr[start] < arr[end]) {
                sb.append(arr[start++]);
            } else if (arr[start] > arr[end]) {
                sb.append(arr[end--]);
            } else if (palindrome) {
                sb.append(arr[start++]);
            } else {
                int result = check(start, end);
                if (result == 1) {
                    sb.append(arr[end--]);
                } else {
                    sb.append(arr[start++]);
                    if (result == 0) palindrome = true;
                }
            }
            if (++idx == 80) {
                idx = 0;
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }

    static int check(int start, int end) {
        while (start < end) {
            if (arr[start] < arr[end]) {
                return -1;
            } else if (arr[start] > arr[end]) {
                return 1;
            }
            start++;
            end--;
        }
        return 0;
    }
}
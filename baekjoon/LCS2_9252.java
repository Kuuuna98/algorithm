package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LCS2_9252 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().trim().toCharArray();
        char[] B = br.readLine().trim().toCharArray();
        int[][] lcs_arr = new int[A.length + 1][B.length + 1];

        for (int a = 1; a <= A.length; a++) {
            for (int b = 1; b <= B.length; b++) {
                if (A[a - 1] == B[b - 1]) {
                    lcs_arr[a][b] = lcs_arr[a - 1][b - 1] + 1;
                } else if (lcs_arr[a][b - 1] < lcs_arr[a - 1][b]) {
                    lcs_arr[a][b] = lcs_arr[a - 1][b];
                } else {
                    lcs_arr[a][b] = lcs_arr[a][b - 1];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int a = A.length;
        int b = B.length;
        while (0 < lcs_arr[a][b]) {
            if (lcs_arr[a][b] == lcs_arr[a][b - 1]) {
                b--;
            } else if (lcs_arr[a][b] == lcs_arr[a - 1][b]) {
                a--;
            } else {
                sb.append(A[--a]);
                b--;
            }
        }
        System.out.println(lcs_arr[A.length][B.length]);
        System.out.print(sb.reverse());
    }
}

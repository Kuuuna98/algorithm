package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LCS2_9252 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().trim().toCharArray();
        char[] B = br.readLine().trim().toCharArray();
        int[][] lcs_list = new int[A.length + 1][B.length + 1];

        for (int a = 1; a <= A.length; a++) {
            for (int b = 1; b <= B.length; b++) {
                if (A[a - 1] == B[b - 1]) {
                    lcs_list[a][b] = lcs_list[a - 1][b - 1] + 1;
                } else if (lcs_list[a][b - 1] < lcs_list[a - 1][b]) {
                    lcs_list[a][b] = lcs_list[a - 1][b];
                } else {
                    lcs_list[a][b] = lcs_list[a][b - 1];
                }
            }
        }

        System.out.println(lcs_list[A.length][B.length]);
        if (0 < lcs_list[A.length][B.length]) {
            int length = lcs_list[A.length][B.length];
            StringBuilder sb = new StringBuilder();
            for (int a = A.length; 0 < a; a--) {
                for (int b = B.length; 0 < b; b--) {
                    if (lcs_list[a][b] == length) {
                        while (0 < length) {
                            if (lcs_list[a][b] == lcs_list[a][b - 1]) {
                                b--;
                            } else if (lcs_list[a][b] == lcs_list[a - 1][b]) {
                                a--;
                            } else {
                                sb.append(A[--a]);
                                length--;
                            }
                        }
                        break;
                    }
                }
            }
            System.out.print(sb.reverse());
        }
    }
}

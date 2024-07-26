package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A와B_12904 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine().trim();
        char[] T = br.readLine().trim().toCharArray();
        System.out.println(checkST(S, T, 0, T.length - 1, false) ? 1 : 0);
    }

    static boolean checkST(final String S, final char[] T, int left, int right, boolean reverse) {
        if (S.length() == right - left + 1) {
            StringBuilder stringT = new StringBuilder(String.valueOf(T).substring(left, right + 1));
            if (reverse) stringT.reverse();
            return S.equals(String.valueOf(stringT));
        }

        if (reverse) {
            return checkST(S, T, left + 1, right, T[left] == 'A' ? reverse : !reverse);
        } else {
            return checkST(S, T, left, right - 1, T[right] == 'A' ? reverse : !reverse);
        }
    }
}

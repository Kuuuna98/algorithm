package baekjoon;

import java.io.*;
import java.util.*;

public class 주사위쌓기_2116 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int[] sum = new int[6];
        int[] be = new int[6];
        int[] temp = new int[6];
        int idx = 0, re_idx = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 6; i++) {
            be[i] = temp[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 6; i++) {
            for (idx = 0; idx < 6; idx++) {
                if (be[i] == temp[idx]) {
                    switch (idx) {
                        case 0:
                            re_idx = 5;
                            break;
                        case 1:
                            re_idx = 3;
                            break;
                        case 2:
                            re_idx = 4;
                            break;
                        case 3:
                            re_idx = 1;
                            break;
                        case 4:
                            re_idx = 2;
                            break;
                        case 5:
                            re_idx = 0;
                            break;
                    }
                    break;
                }
            }
            be[i] = temp[re_idx];
            sum[i] += 6;
            if (temp[idx] == 6 || temp[re_idx] == 6) {
                sum[i]--;
                if (temp[idx] == 5 || temp[re_idx] == 5)
                    sum[i]--;
            }
        }
        for (int num = 1; num < N; num++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < 6; i++) {
                temp[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < 6; i++) {
                for (idx = 0; idx < 6; idx++) {
                    if (be[i] == temp[idx]) {
                        switch (idx) {
                            case 0:
                                re_idx = 5;
                                break;
                            case 1:
                                re_idx = 3;
                                break;
                            case 2:
                                re_idx = 4;
                                break;
                            case 3:
                                re_idx = 1;
                                break;
                            case 4:
                                re_idx = 2;
                                break;
                            case 5:
                                re_idx = 0;
                                break;
                        }
                        break;
                    }
                }
                be[i] = temp[re_idx];
                sum[i] += 6;
                if (temp[idx] == 6 || temp[re_idx] == 6) {
                    sum[i]--;
                    if (temp[idx] == 5 || temp[re_idx] == 5)
                        sum[i]--;
                }
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int value : sum)
            answer = value < answer ? answer : value;
        System.out.println(answer);
        br.close();
    }
}
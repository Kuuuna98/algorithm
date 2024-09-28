package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 컨베이어벨트위의로봇_20055 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int N2 = 2 * N;
        int[] conveyor = new int[N2];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N2; i++) {
            conveyor[i] = Integer.parseInt(st.nextToken());
        }

        int pointer = 0;
        int cnt = 0;
        int round = 0;
        boolean[] robots = new boolean[2 * N];

        while (cnt < K) {
            round++;

            if (--pointer == -1) {
                pointer = N2 - 1;
            }
            int out_idx = (pointer + N - 1) % N2;

            if (robots[out_idx]) {
                robots[out_idx] = false;
            }

            int r = out_idx == 0 ? N2 - 1 : out_idx - 1;
            int next_r = out_idx;
            while (r != pointer) {
                if (robots[r]) {
                    if (!robots[next_r] && conveyor[next_r] > 0) {
                        robots[r] = false;
                        if (--conveyor[next_r] == 0) {
                            cnt++;
                        }
                        if (next_r == out_idx) {
                            continue;
                        }
                        robots[next_r] = true;
                    }
                }
                next_r = r;
                r = r == 0 ? N2 - 1 : r - 1;
            }


            if (conveyor[pointer] > 0) {
                robots[pointer] = true;
                if (--conveyor[pointer] == 0) {
                    cnt++;
                }
            }
        }

        System.out.println(round);
    }

}
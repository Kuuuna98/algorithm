package baekjoon;

import java.io.*;
import java.util.*;

public class 주사위굴리기_14499 {
    static char dice[], board[][];
    static int up,east,north;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        dice = new char[6];
        up = 0;
        east = 2;
        north = 1;

        Arrays.fill(dice,'0');
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = st.nextToken().charAt(0);
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int c = 0; c < K; c++) {
            int tmp;
            switch (st.nextToken().charAt(0)) {
                case '1': // 동
                    if (y + 1 < M) {
                        tmp = east;
                        east = up;
                        up = 5 - tmp;

                        check(x, ++y);
                    }
                    break;
                case '2': // 서
                    if (0 <= y - 1) {
                        tmp = up;
                        up = east;
                        east = 5 - tmp;

                        check(x, --y);
                    }
                    break;
                case '3': // 북
                    if (0 <= x - 1) {
                        tmp = north;
                        north = up;
                        up = 5 - tmp;

                        check(--x, y);
                    }
                    break;
                default:// 남
                    if (x + 1 < N) {
                        tmp = up;
                        up = north;
                        north = 5 - tmp;

                        check(++x, y);
                    }
                    break;
            }
        }
        System.out.print(sb.toString());
        br.close();
    }

    static void check(int x, int y) {
        if ('0' == board[x][y]) {
            board[x][y]=dice[5-up];
        } else {
            dice[5-up]=board[x][y];
            board[x][y]='0';
        }
        sb.append(dice[up]).append("\n");
    }

}
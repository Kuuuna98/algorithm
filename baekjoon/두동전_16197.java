package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 두동전_16197 {
    static int N, M;

    public static void main(String args[]) throws Exception {
        System.out.println(bfs());
    }

    static int bfs() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        char[][] board = new char[N][M];

        int tmpi = -1, tmpj = -1;
        Queue<int[]> queue = new LinkedList<>(); //0:time, 12 34
        for (int n = 0; n < N; n++) {
            board[n] = br.readLine().toCharArray();
            for (int m = 0; m < M; m++) {
                if (board[n][m] == 'o') {
                    if (tmpi == -1) {
                        tmpi = n;
                        tmpj = m;
                    } else {
                        queue.offer(new int[]{0, tmpi, tmpj, n, m});
                    }
                }
            }
        }

        int[] di = {-1, 0, 1, 0};
        int[] dj = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            if (node[0] == 10) break;

            for (int z = 0; z < 4; z++) {
                int coin1_ni = node[1] + di[z];
                int coin1_nj = node[2] + dj[z];
                int coin2_ni = node[3] + di[z];
                int coin2_nj = node[4] + dj[z];

                if (check(coin1_ni, coin1_nj)) {
                    if (board[coin1_ni][coin1_nj] == '#') {
                        coin1_ni = node[1];
                        coin1_nj = node[2];
                    }

                    if (check(coin2_ni, coin2_nj)) {
                        if (board[coin2_ni][coin2_nj] == '#') {
                            coin2_ni = node[3];
                            coin2_nj = node[4];
                        }
                        queue.offer(new int[]{node[0] + 1, coin1_ni, coin1_nj, coin2_ni, coin2_nj});
                    } else {
                        return node[0] + 1;
                    }
                } else {
                    if (check(coin2_ni, coin2_nj)) {
                        return node[0] + 1;
                    }
                }
            }

        }

        return -1;
    }

    static boolean check(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= M) return false;
        return true;
    }

}
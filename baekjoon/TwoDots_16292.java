package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TwoDots_16292 {
    final static int[] di = {0, 1, 0, -1};
    final static int[] dj = {1, 0, -1, 0};
    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String args[]) throws Exception {
        init();
        System.out.println(playTwoDots());
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        N = Integer.parseInt(input.split(" ")[0]);
        M = Integer.parseInt(input.split(" ")[1]);
        map = new char[N][];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }

    static String playTwoDots() {
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                if (map[i][j] == map[i + 1][j] && map[i][j] == map[i][j + 1]) {
                    visited[i][j] = true;
                    if (checkCycle(map[i][j], i, j + 1, 2)) return "Yes";
                    visited[i][j] = false;

                }
            }
        }
        return "No";
    }

    static boolean checkCycle(char target, int i, int j, int d) {

        for (int z = 0; z < 4; z++) {
            if (z == d) continue;
            int ni = i + di[z];
            int nj = j + dj[z];
            if (check(ni, nj) && target == map[ni][nj]) {
                if (visited[ni][nj]) {
                    return true;
                } else {
                    visited[ni][nj] = true;
                    if (checkCycle(target, ni, nj, z < 2 ? z + 2 : z - 2)) return true;
                    visited[ni][nj] = false;
                }
            }
        }

        return false;
    }

    static boolean check(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= M) return false;
        return true;
    }

}
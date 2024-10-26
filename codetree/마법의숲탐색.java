package codetree;

import java.io.*;
import java.util.*;

public class 마법의숲탐색 {
    static final int[] di = new int[] { 0, -1, 0, 1 };
    static final int[] dj = new int[] { -1, 0, 1, 0 };

    static int R, C, K;
    static int[][] infos;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        init();
        int answer = 0;
        for (int k = 1; k <= K; k++) {
            answer += play(k) + 1;
        }
        System.out.println(answer);
    }

    static void init() throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        infos = new int[K + 1][2];
        for (int k = 1; k <= K; k++) {
            st = new StringTokenizer(br.readLine(), " ");
            infos[k][0] = Integer.parseInt(st.nextToken()) - 1;
            infos[k][1] = Integer.parseInt(st.nextToken());
        }
    }

    static int play(int k) {
        int monster_i = -2, monster_j = infos[k][0], monster_d = infos[k][1];

        while (monster_i < R - 2) {
            // 남쪽 이동
            if (!moveSouth(monster_i, monster_j)) {
                // 서쪽 이동
                if (!moveWest(monster_i, monster_j)) {
                    // 동쪽 이동
                    if (!moveEast(monster_i, monster_j)) {
                        break;
                    } else {
                        monster_i++;
                        monster_j++;
                        if (monster_d++ == 3)
                            monster_d = 0;
                    }
                } else {
                    monster_i++;
                    monster_j--;
                    if (monster_d-- == 0)
                        monster_d = 3;
                }
            } else {
                monster_i++;
            }
        }

        if (monster_i < 0 || monster_i - 1 < 0) {
            for(int i=0; i<R; i++) {
                for(int j=0; j<C; j++) {
                    map[i][j] = 0;
                }
            }
            return -1;
        }

        // 골렘 저장
        map[monster_i - 1][monster_j] = monster_d == 0 ? -k : k;
        map[monster_i][monster_j + 1] = monster_d == 1 ? -k : k;
        map[monster_i + 1][monster_j] = monster_d == 2 ? -k : k;
        map[monster_i][monster_j - 1] = monster_d == 3 ? -k : k;
        map[monster_i][monster_j] = k;

        // 정령 남쪽 이동
        return moveMonster(monster_i, monster_j);
    }

    static int moveMonster(int monster_i, int monster_j) {
        int max_i = monster_i;

        Queue<int[]> queue = new LinkedList<int[]>();
        boolean[][] visited = new boolean[R][C];
        queue.offer(new int[] { monster_i, monster_j });
        visited[monster_i][monster_j] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            if (max_i < node[0])
                max_i = node[0];

            for (int z = 0; z < 4; z++) {
                int ni = node[0] + di[z];
                int nj = node[1] + dj[z];
                if (check(ni, nj) && !visited[ni][nj] && (map[node[0]][node[1]] == map[ni][nj]
                        || map[node[0]][node[1]] == -map[ni][nj] || (map[node[0]][node[1]] < 0 && map[ni][nj] != 0))) {
                    queue.offer(new int[] { ni, nj });
                    visited[ni][nj] = true;
                }

            }

        }

        return max_i;
    }

    static boolean check(int i, int j) {
        return 0 <= i && i < R && 0 <= j && j < C;
    }

    static boolean moveSouth(int i, int j) {
        if (0 <= i + 1 && (map[i + 1][j - 1] != 0 || map[i + 1][j + 1] != 0))
            return false;
        if (map[i + 2][j] != 0)
            return false;
        return true;
    }

    static boolean moveWest(int i, int j) {
        if (j - 2 < 0)
            return false;
        if (0 <= i - 1 && map[i - 1][j - 1] != 0)
            return false;
        if (0 <= i && map[i][j - 2] != 0)
            return false;
        if (0 <= i + 1 && (map[i + 1][j - 1] != 0 || map[i + 1][j - 2] != 0))
            return false;
        if (map[i + 2][j - 1] != 0)
            return false;
        return true;
    }

    static boolean moveEast(int i, int j) {
        if (C <= j + 2)
            return false;
        if (0 <= i - 1 && map[i - 1][j + 1] != 0)
            return false;
        if (0 <= i && map[i][j + 2] != 0)
            return false;
        if (0 <= i + 1 && (map[i + 1][j + 1] != 0 || map[i + 1][j + 2] != 0))
            return false;
        if (map[i + 2][j + 1] != 0)
            return false;
        return true;
    }
}
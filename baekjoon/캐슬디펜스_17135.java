package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 캐슬디펜스_17135 {
    static final int[] di = {0, -1, 0};
    static final int[] dj = {-1, 0, 1};
    static int N, M, D;
    static int[][] enemies;
    static int len_enemies;
    static int MAX;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        enemies = new int[N * M][2];
        len_enemies = 0;
        MAX = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                if (st.nextToken().charAt(0) == '1') {
                    enemies[len_enemies][0] = i;
                    enemies[len_enemies++][1] = j;
                }
            }
        }
        makeArcher(new int[3], 0, 0);
        System.out.println(MAX);
    }

    static void makeArcher(int[] archers, int idx, int st) {
        if (idx == 3) {
            castleDefence(archers);
            return;
        }
        for (int i = st; i < M; i++) {
            if (MAX == len_enemies) return;
            archers[idx] = i;
            makeArcher(archers, idx + 1, i + 1);
        }
    }

    static void castleDefence(int[] archers) {
        int cnt = 0;
        int n = N;
        int[][] map = new int[N][M];
        for (int i = 0; i < len_enemies; i++) {
            map[enemies[i][0]][enemies[i][1]] = -1;
        }
        while (--n >= 0) {
            for (int i = 0; i < 3; i++) {
                if (findEnemy(map, n, archers[i])) cnt++;
            }
        }
        if (MAX < cnt) {
            MAX = cnt;
        }
    }

    static boolean findEnemy(int[][] map, int n, int archer_j) {
        if (map[n][archer_j] == -1) {
            map[n][archer_j] = n + 1;
            return true;
        } else if (map[n][archer_j] == n + 1) {
            return false;
        } else {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{n, archer_j});
            boolean[][] visit = new boolean[n + 1][M];
            visit[n][archer_j] = true;
            while (!queue.isEmpty()) {
                int[] node = queue.poll();
                for (int z = 0; z < 3; z++) {
                    int ni = node[0] + di[z];
                    int nj = node[1] + dj[z];
                    if (ni >= 0 && ni <= n && nj >= 0 && nj < M && n + 1 - ni + Math.abs(archer_j - nj) <= D && !visit[ni][nj]) {
                        if (map[ni][nj] == -1) {
                            map[ni][nj] = n + 1;
                            return true;
                        } else if (map[ni][nj] == n + 1) {
                            return false;
                        } else {
                            visit[ni][nj] = true;
                            queue.offer(new int[]{ni, nj});
                        }
                    }
                }
            }
        }
        return false;
    }
}
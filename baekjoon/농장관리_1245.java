package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 농장관리_1245 {
    final static int[] di = new int[]{-1, -1, -1, 0, 0, 0, 1, 1, 1};
    final static int[] dj = new int[]{-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int N, M;
    static int[][] map;
    static int[][] visited;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(findMountainTop());
    }

    public static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new int[N][M];
    }

    public static int findMountainTop() {
        int countMountainTop = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0) {
                    if (isMountainTop(i, j, i * N + j)) countMountainTop++;
                }
            }
        }
        return countMountainTop;
    }

    public static boolean isMountainTop(int i, int j, int visitedValue) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = visitedValue;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int z = 0; z <= 8; z++) {
                int ni = node[0] + di[z];
                int nj = node[1] + dj[z];
                if (checkRange(ni, nj)) {
                    if (map[i][j] < map[ni][nj]) return false;
                    if (visited[ni][nj] < visitedValue) {
                        visited[ni][nj] = visitedValue;
                        if (map[i][j] == map[ni][nj]) {
                            queue.add(new int[]{ni, nj});
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < M;
    }

}
package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 군탈체포조_23354 {
    static final int[] di = new int[]{0, -1, 0, 1};
    static final int[] dj = new int[]{-1, 0, 1, 0};
    static final int INF = 987654321;

    static int N, SIZE;
    static List<int[]> targets;
    static int[][] map, arr;
    static int answer;

    public static void main(String[] args) throws Exception {
        init();
        if (1 < SIZE) {
            answer = INF;
            play();
        }
        System.out.println(answer);
    }

    public static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        targets = new ArrayList<>();
        map = new int[N][N];

        int home_i = 0, home_j = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    targets.add(new int[]{i, j});
                } else if (map[i][j] == -1) {
                    home_i = i;
                    home_j = j;
                    map[i][j] = 0;
                }
            }
        }
        targets.add(0, new int[]{home_i, home_j});
        SIZE = targets.size();

        answer = 0;
        arr = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                arr[i][j] = INF;
            }
        }
    }

    public static void play() {
        bfs();
        boolean[] visited = new boolean[SIZE];
        visited[0] = true;
        dfs(0, visited, 0, 1);
    }

    public static void dfs(int index, boolean[] visited, int tolls, int V) {
        if (answer <= tolls) return;
        if (V == visited.length) {
            tolls += arr[index][0];
            if (tolls < answer) answer = tolls;
            return;
        }

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, visited, tolls + arr[index][i], V + 1);
                visited[i] = false;
            }
        }
    }

    public static void bfs() {
        int size = targets.size();
        next:
        for (int i = 0; i < size; i++) {
            boolean[][] visited = new boolean[N][N];
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
            pq.add(new int[]{targets.get(i)[0], targets.get(i)[1], 0});
            visited[targets.get(i)[0]][targets.get(i)[1]] = true;
            int cnt = 0;

            while (!pq.isEmpty()) {
                int[] node = pq.poll();
                for (int z = 0; z < 4; z++) {
                    int ni = node[0] + di[z];
                    int nj = node[1] + dj[z];
                    if (checkRange(ni, nj) && !visited[ni][nj]) {
                        for (int j = i + 1; j < size; j++) {
                            if (ni == targets.get(j)[0] && nj == targets.get(j)[1]) {
                                if (node[2] < arr[i][j]) {
                                    arr[i][j] = arr[j][i] = node[2];
                                }
                                if (++cnt == size - i - 1) {
                                    continue next;
                                }
                            }
                        }
                        visited[ni][nj] = true;
                        pq.add(new int[]{ni, nj, node[2] + map[ni][nj]});
                    }
                }
            }
        }
    }

    public static boolean checkRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }
}

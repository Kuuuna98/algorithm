package codetree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 회전하는빙하 {
    static final int[] di = new int[]{-1, 0, 1, 0};
    static final int[] dj = new int[]{0, 1, 0, -1};
    static int N, Q, SIZE;
    static int[][] map;
    static int[] order_level;

    public static void main(String[] args) throws Exception {
        init();
        for (int q = 0; q < Q; q++) {
            if (0 < order_level[q]) rotateIce(order_level[q]);
            meltIce();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getTotalIceCnt()).append("\n");
        sb.append(getLargestGroupSize()).append("\n");
        System.out.print(sb);
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        SIZE = (int) Math.pow(2, N);
        map = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order_level = new int[Q];
        st = new StringTokenizer(br.readLine().trim(), " ");
        for (int q = 0; q < Q; q++) {
            order_level[q] = Integer.parseInt(st.nextToken());
        }
    }

    static void rotateIce(final int level) {
        int length = (int) Math.pow(2, level);
        for (int i = 0; i < SIZE; i += length) {
            for (int j = 0; j < SIZE; j += length) {
                splitMap(i, j, length);
            }
        }
    }

    static void splitMap(int st_i, int st_j, int length) {
        int half_length = length / 2;
        for (int i = 0; i < half_length; i++) {
            for (int j = 0; j < half_length; j++) {
                int tmp = map[st_i + i][st_j + j];
                map[st_i + i][st_j + j] = map[st_i + half_length + i][st_j + j];
                map[st_i + half_length + i][st_j + j] = map[st_i + half_length + i][st_j + half_length + j];
                map[st_i + half_length + i][st_j + half_length + j] = map[st_i + i][st_j + half_length + j];
                map[st_i + i][st_j + half_length + j] = tmp;
            }
        }
    }

    static void meltIce() {
        int[][] tmp = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (0 < map[i][j]) {
                    int ice_cnt = 0;
                    for (int z = 0; z < 4; z++) {
                        int ni = i + di[z];
                        int nj = j + dj[z];
                        if (checkRange(ni, nj) && 0 < map[ni][nj]) {
                            ice_cnt++;
                        }
                    }
                    if (ice_cnt < 3) tmp[i][j] = map[i][j] - 1;
                    else tmp[i][j] = map[i][j];
                }
            }
        }
        map = tmp;
    }

    static int getTotalIceCnt() {
        int total_cnt = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                total_cnt += map[i][j];
            }
        }
        return total_cnt;
    }

    static int getLargestGroupSize() {
        int largest_group_size = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (0 < map[i][j] && !visited[i][j]) {
                    int size = 1;
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int[] node = queue.poll();
                        for (int z = 0; z < 4; z++) {
                            int ni = node[0] + di[z];
                            int nj = node[1] + dj[z];
                            if (checkRange(ni, nj) && 0 < map[ni][nj] && !visited[ni][nj]) {
                                size++;
                                queue.add(new int[]{ni, nj});
                                visited[ni][nj] = true;
                            }
                        }
                    }
                    if (largest_group_size < size) largest_group_size = size;
                }
            }
        }
        return largest_group_size;
    }

    static boolean checkRange(int i, int j) {
        return 0 <= i && i < SIZE && 0 <= j && j < SIZE;
    }
}
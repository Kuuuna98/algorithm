package codetree;

import java.io.*;
import java.util.*;

public class 고대문명유적탐사 {
    static final int[] di = new int[]{-1, 0, 1, 0};
    static final int[] dj = new int[]{0, -1, 0, 1};
    static int[][] map;
    static int[] wall_numbers;
    static int K, M, wall_index;

    public static void main(String[] args) throws Exception {
        init();
        StringBuilder sb = new StringBuilder();
        int total = 0;
        while (0 < K--) {
            // 탐사진행
            // 3*3 격자 선택
            if (goAdventure() == 0)
                break;
            // 유물획득
            total = 0;
            int count = getDiamond();
            while (0 < count) {
                total += count;
                fillMap();
                count = getDiamond();
            }

            sb.append(total).append(" ");
        }
        System.out.println(sb);

    }

    static void init() throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[5][5];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        wall_numbers = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int m = 0; m < M; m++) {
            wall_numbers[m] = Integer.parseInt(st.nextToken());
        }
        wall_index = 0;
    }

    static int goAdventure() {
        int target_i = 1, target_j = 1, target_d = 0;
        int max_diamond_count = 0;

        // 중심 축 (1), (2)가 같은 경우 열이 작고, 행이 작은 구간 우선
        for (int j = 1; j <= 3; j++) {
            for (int i = 1; i <= 3; i++) {
                for (int d = 0; d < 3; d++) {
                    rotateMap(i, j);
                    int diamond_count = countDiamond();
                    if ((max_diamond_count < diamond_count) || (max_diamond_count == diamond_count && d < target_d)) {
                        target_i = i;
                        target_j = j;
                        target_d = d;
                        max_diamond_count = diamond_count;
                    }
                }
                rotateMap(i, j);
            }
        }

        for (int d = 0; d <= target_d; d++) {
            rotateMap(target_i, target_j);
        }

        return max_diamond_count;
    }

    static void rotateMap(int target_i, int target_j) {
        int[][] temp = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = map[i + target_i - 1][j + target_j - 1];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map[i + target_i - 1][j + target_j - 1] = temp[3 - 1 - j][i];
            }
        }
    }

    static int countDiamond() {
        int total = 0;
        Queue<int[]> queue = new LinkedList<int[]>();
        boolean[][] visited = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j]) {
                    int count = 1;
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int[] node = queue.poll();
                        for (int z = 0; z < 4; z++) {
                            int ni = node[0] + di[z];
                            int nj = node[1] + dj[z];
                            if (check(ni, nj) && !visited[ni][nj] && map[i][j] == map[ni][nj]) {
                                queue.offer(new int[]{ni, nj});
                                visited[ni][nj] = true;
                                count++;
                            }
                        }
                    }
                    if (3 <= count)
                        total += count;
                }
            }
        }
        return total;
    }

    static int getDiamond() {
        int total = 0;
        Queue<int[]> queue = new LinkedList<int[]>();
        Queue<int[]> queue_save = new LinkedList<int[]>();
        boolean[][] visited = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j]) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int[] node = queue.poll();
                        queue_save.offer(node);
                        for (int z = 0; z < 4; z++) {
                            int ni = node[0] + di[z];
                            int nj = node[1] + dj[z];
                            if (check(ni, nj) && !visited[ni][nj] && map[i][j] == map[ni][nj]) {
                                queue.offer(new int[]{ni, nj});
                                visited[ni][nj] = true;
                            }
                        }
                    }
                    if (3 <= queue_save.size()) {
                        total += queue_save.size();
                        while (!queue_save.isEmpty()) {
                            int[] node = queue_save.poll();
                            map[node[0]][node[1]] = 0;
                        }
                    } else {
                        queue_save.clear();
                    }
                }
            }
        }
        return total;
    }

    static void fillMap() {
        for (int j = 0; j < 5; j++) {
            for (int i = 4; 0 <= i; i--) {
                if (map[i][j] == 0)
                    map[i][j] = wall_numbers[wall_index++];
            }
        }
    }

    static boolean check(int i, int j) {
        return 0 <= i && i < 5 && 0 <= j && j < 5;
    }

}
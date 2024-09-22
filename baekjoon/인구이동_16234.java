package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 인구이동_16234 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean change = true;
        int day = -1;
        final int[] di = {-1, 0, 1, 0};
        final int[] dj = {0, 1, 0, -1};
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> list = new ArrayList<>();
        while (change) {
            day++;
            change = false;
            boolean[][] visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j]) {
                        list.clear();
                        queue.offer(new int[]{i, j});
                        visit[i][j] = true;
                        int sum = map[i][j];
                        while (!queue.isEmpty()) {
                            int[] node = queue.poll();
                            list.add(node);
                            for (int z = 0; z < 4; z++) {
                                int ni = node[0] + di[z];
                                int nj = node[1] + dj[z];
                                if (ni >= 0 && ni < N && nj >= 0 && nj < N && !visit[ni][nj]) {
                                    int num = Math.abs(map[ni][nj] - map[node[0]][node[1]]);
                                    if (num >= L && num <= R) {
                                        queue.offer(new int[]{ni, nj});
                                        visit[ni][nj] = true;
                                        sum += map[ni][nj];
                                    }
                                }
                            }
                        }

                        int avg = sum / list.size();
                        if (list.size() > 1) change = true;
                        for (int[] node : list) {
                            map[node[0]][node[1]] = avg;
                        }
                    }
                }
            }
        }
        System.out.println(day);
        br.close();
    }
}
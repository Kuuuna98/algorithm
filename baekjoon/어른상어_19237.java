package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 어른상어_19237 {
    static int N, M, K;
    static int[][] sharks, direction;
    static Map<Integer, Integer>[][] smells;
    static boolean[][] map;

    public static void main(String args[]) throws Exception {
        read();
        System.out.println(play());
    }

    static void read() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        sharks = new int[M + 1][];
        direction = new int[M + 1][4 * 4];
        smells = new HashMap[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                smells[i][j] = new HashMap<>();
                if (tmp != 0) {
                    sharks[tmp] = new int[]{i, j, 0};
                    smells[i][j].put(tmp, 0);
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int s = 1; s <= M; s++) {
            sharks[s][2] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int s = 1; s <= M; s++) {
            int idx = 0;
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int z = 0; z < 4; z++) {
                    direction[s][idx++] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }
    }

    static int play() {
        final int[] di = {-1, 1, 0, 0};
        final int[] dj = {0, 0, -1, 1};

        int time = 0;
        int cnt = M;
        Queue<Integer> queue = new LinkedList<>();
        map = new boolean[N][N];

        while (time++ < 1000) {
            for (int s = 1; s <= M; s++) {
                if (sharks[s] == null) continue;
                int i = sharks[s][0];
                int j = sharks[s][1];
                int d = sharks[s][2];

                int tmp_d_me = -1;
                int tmp_d_not_me = -1;

                for (int z = 0; z < 4; z++) {
                    int dir = direction[s][d * 4 + z];
                    int ni = i + di[dir];
                    int nj = j + dj[dir];

                    if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
                        for (Integer key : smells[ni][nj].keySet()) {
                            if (time - smells[ni][nj].get(key) > K) {
                                queue.offer(key);
                            }
                        }
                        while (!queue.isEmpty()) {
                            smells[ni][nj].remove(queue.poll());
                        }

                        if (smells[ni][nj].size() == 0) {
                            cnt += move(ni, nj, dir, s);
                            break;
                        }

                        if (smells[ni][nj].containsKey(s)) {
                            if (tmp_d_me == -1) tmp_d_me = dir;
                        } else if (tmp_d_not_me == -1) {
                            tmp_d_not_me = dir;
                        }
                    }
                }

                if (sharks[s] != null && sharks[s][0] == i && sharks[s][1] == j) {
                    int dir = tmp_d_me == -1 ? tmp_d_not_me : tmp_d_me;
                    int ni = i + di[dir];
                    int nj = j + dj[dir];
                    cnt += move(ni, nj, dir, s);
                }
            }

            for (int s = 1; s <= M; s++) {
                if (sharks[s] != null) {
                    smells[sharks[s][0]][sharks[s][1]].put(s, time);
                    map[sharks[s][0]][sharks[s][1]] = false;
                }
            }

            if (cnt == 1) return time;
        }
        return -1;
    }

    static int move(int i, int j, int dir, int s) {
        if (map[i][j]) {
            sharks[s] = null;
            return -1;
        } else {
            sharks[s][0] = i;
            sharks[s][1] = j;
            sharks[s][2] = dir;
            map[i][j] = true;
            return 0;
        }
    }
}
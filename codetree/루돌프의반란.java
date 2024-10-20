package codetree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 루돌프의반란 {
    static final int[] di = new int[]{-1, -1, -1, 0, 0, 1, 1, 0, 1};
    static final int[] dj = new int[]{-1, 0, 1, 1, 0, 0, -1, -1, 1};
    static final int INF = 987654321;
    static int N, M, P, C, D;
    static int R_i, R_j;
    static int[][] map;
    static int[] santa_points;
    static int[][] santa_infos;

    public static void main(String[] args) throws Exception {
        init();
        for (int m = 1; m <= M; m++) {
            checkR(m);
            moveS(m);
            if (!plusScore()) break;
        }
        StringBuilder sb = new StringBuilder();
        for (int p = 1; p <= P; p++) {
            sb.append(santa_points[p]).append(" ");
        }
        System.out.println(sb);
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim(), " ");
        R_i = Integer.parseInt(st.nextToken()) - 1;
        R_j = Integer.parseInt(st.nextToken()) - 1;

        map = new int[N][N];
        santa_points = new int[P + 1];
        santa_infos = new int[P + 1][3];

        map[R_i][R_j] = -1;
        for (int p = 0; p < P; p++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            int number = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;
            santa_infos[number][0] = i;
            santa_infos[number][1] = j;
            santa_infos[number][2] = -2;
            map[i][j] = number;
        }
    }

    static boolean plusScore() {
        int cnt = 0;
        for (int s = 1; s <= P; s++) {
            if (checkRange(santa_infos[s][0], santa_infos[s][1])) {
                cnt++;
                santa_points[s]++;
            }
        }
        return 0 < cnt;
    }

    static void checkR(int round) {
        int target_s = 0, target_d = INF;
        for (int s = 1; s <= P; s++) {
            if (checkRange(santa_infos[s][0], santa_infos[s][1])) {
                int dist = (R_i - santa_infos[s][0]) * (R_i - santa_infos[s][0]) + (R_j - santa_infos[s][1]) * (R_j - santa_infos[s][1]);
                if (dist < target_d || (dist == target_d && (santa_infos[target_s][0] < santa_infos[s][0] || (santa_infos[target_s][0] == santa_infos[s][0] && santa_infos[target_s][1] < santa_infos[s][1])))) {
                    target_s = s;
                    target_d = dist;
                }
            }
        }

        int target_z = 0, diff = target_d;
        for (int z = 0; z < 9; z++) {
            int ni = R_i + di[z];
            int nj = R_j + dj[z];
            int nd = (ni - santa_infos[target_s][0]) * (ni - santa_infos[target_s][0]) + (nj - santa_infos[target_s][1]) * (nj - santa_infos[target_s][1]);
            if (checkRange(ni, nj) && nd < diff) {
                target_z = z;
                diff = nd;
            }
        }

        moveR(target_z, round);
    }

    static void moveR(int z, int round) {
        map[R_i][R_j] = 0;

        R_i += di[z];
        R_j += dj[z];

        if (0 < map[R_i][R_j]) {
            int S_number = map[R_i][R_j];
            int ni = R_i + C * di[z];
            int nj = R_j + C * dj[z];
            santa_points[S_number] += C;
            if (checkRange(ni, nj)) {
                if (0 < map[ni][nj]) {
                    move(z, ni, nj);
                }
                map[ni][nj] = S_number;
            }
            santa_infos[S_number][0] = ni;
            santa_infos[S_number][1] = nj;
            santa_infos[S_number][2] = round;
        }

        map[R_i][R_j] = -1;
    }

    static void moveS(int round) {
        for (int s = 1; s <= P; s++) {
            if (checkRange(santa_infos[s][0], santa_infos[s][1]) && 1 < round - santa_infos[s][2]) {
                int target_z = 0;
                int dist = (R_i - santa_infos[s][0]) * (R_i - santa_infos[s][0]) + (R_j - santa_infos[s][1]) * (R_j - santa_infos[s][1]);
                for (int z = 1; z < 9; z += 2) {
                    int ni = santa_infos[s][0] + di[z];
                    int nj = santa_infos[s][1] + dj[z];
                    if (checkRange(ni, nj) && map[ni][nj] <= 0) {
                        int nd = (R_i - ni) * (R_i - ni) + (R_j - nj) * (R_j - nj);
                        if (nd < dist) {
                            dist = nd;
                            target_z = z;
                        }
                    }
                }

                if (target_z != 0) {
                    map[santa_infos[s][0]][santa_infos[s][1]] = 0;
                    santa_infos[s][0] += di[target_z];
                    santa_infos[s][1] += dj[target_z];
                    if (map[santa_infos[s][0]][santa_infos[s][1]] == -1) {
                        santa_points[s] += D;

                        if (target_z < 4) target_z += 4;
                        else target_z -= 4;

                        santa_infos[s][0] += D * di[target_z];
                        santa_infos[s][1] += D * dj[target_z];
                        santa_infos[s][2] = round;
                        if (checkRange(santa_infos[s][0], santa_infos[s][1])) {
                            if (0 < map[santa_infos[s][0]][santa_infos[s][1]]) {
                                move(target_z, santa_infos[s][0], santa_infos[s][1]);
                            }
                            map[santa_infos[s][0]][santa_infos[s][1]] = s;
                        }
                    } else {
                        map[santa_infos[s][0]][santa_infos[s][1]] = s;
                    }
                }
            }
        }
    }

    static void move(int z, int i, int j) {
        int S_number = map[i][j];
        int ni = i + di[z];
        int nj = j + dj[z];

        if (checkRange(ni, nj)) {
            if (0 < map[ni][nj]) {
                move(z, ni, nj);
            }
            map[ni][nj] = S_number;
        }
        santa_infos[S_number][0] = ni;
        santa_infos[S_number][1] = nj;
    }

    static boolean checkRange(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }
}
package codetree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 꼬리잡기놀이 {
    final static int[] di = {0, -1, 0, 1};
    final static int[] dj = {1, 0, -1, 0};
    static int N;
    static int M;
    static int[][] teamMap;
    static int[][] map;
    static int[][] team;

    public static void main(String[] args) throws Exception {
        int K = init();
        int score = 0;
        for (int k = 0; k < K; k++) {
//			먼저 각 팀은 머리사람을 따라서 한 칸 이동합니다.
            move();
//			각 라운드마다 공이 정해진 선을 따라 던져집니다. n개의 행, n개의 열이 주어진다고 했을 때 공이 던져지는 선은 다음과 같습니다.
//			공이 던져지는 경우에 해당 선에 사람이 있으면 최초에 만나게 되는 사람만이 공을 얻게 되어 점수를 얻게 됩니다.
            score += tossBall(k);
        }
        System.out.println(score);
    }

    static int init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        teamMap = new int[N][N]; // 팀은 1번 부터
        map = new int[N][N];
        team = new int[M + 1][5]; // 머리 꼬리 위치 팀원

        int[][] tmp = new int[N][N];

        int idx = 1;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                tmp[i][j] = input.charAt(j + j) - '0';
                if (tmp[i][j] == 1) {
                    team[idx][0] = i;
                    team[idx][1] = j;
                    team[idx++][4] = 1;
                    map[i][j] = 1;
                } else if (tmp[i][j] != 0 && tmp[i][j] != 4) {
                    map[i][j] = tmp[i][j];
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int m = 1; m <= M; m++) {
            teamMap[team[m][0]][team[m][1]] = m;
            queue.offer(new int[]{team[m][0], team[m][1]});

            while (!queue.isEmpty()) {
                int[] node = queue.poll();

                for (int z = 0; z < 4; z++) {
                    int ni = node[0] + di[z];
                    int nj = node[1] + dj[z];
                    if (check(ni, nj) && tmp[ni][nj] > 0 && teamMap[ni][nj] == 0) {
                        teamMap[ni][nj] = m;
                        queue.offer(new int[]{ni, nj});
                        if (tmp[ni][nj] != 4)
                            team[m][4]++;
                        if (tmp[ni][nj] == 3) {
                            team[m][2] = ni;
                            team[m][3] = nj;
                        }

                    }
                }
            }
        }
        return K;
    }

    static void move() {
        for (int m = 1; m <= M; m++) {

            int head_i = team[m][0];
            int head_j = team[m][1];
            int tail_i = team[m][2];
            int tail_j = team[m][3];


            map[tail_i][tail_j] = 0;
            int zj = 0;
            for (; zj < 4; zj++) {
                int ni = team[m][2] + di[zj];
                int nj = team[m][3] + dj[zj];
                if (check(ni, nj) && teamMap[ni][nj] == m && map[ni][nj] == 2) {
                    map[ni][nj] = 3;
                    team[m][2] = ni;
                    team[m][3] = nj;
                    break;
                }
            }
            if (zj == 4) {
                map[head_i][head_j] = 3;
                team[m][2] = head_i;
                team[m][3] = head_j;
            } else {
                map[head_i][head_j] = 2;
            }
            for (int z = 0; z < 4; z++) {
                int ni = team[m][0] + di[z];
                int nj = team[m][1] + dj[z];
                if (check(ni, nj) && teamMap[ni][nj] == m && map[ni][nj] == 0 && (zj != 4 || tail_i != ni || tail_j != nj)) {
                    map[ni][nj] = 1;
                    team[m][0] = ni;
                    team[m][1] = nj;
                    break;
                }
            }
        }
    }

    static int tossBall(int k) {
        int dir = k / N;
        if (dir > 3)
            dir %= 4;

        int i = 0;
        int j = 0;
        if (dir == 0) {
            i = k % N;
            j = 0;
        } else if (dir == 1) {
            i = N - 1;
            j = k % N;
        } else if (dir == 2) {
            i = N - 1 - k % N;
            j = N - 1;
        } else {
            i = 0;
            j = N - 1 - k % N;
        }
        int cnt = 0;
        for (int n = 0; n < N; n++) {
            if (map[i][j] > 0) {
                if (map[i][j] == 1) {
                    cnt = 1;
                } else if (map[i][j] == 3) {
                    cnt = team[teamMap[i][j]][4];
                } else {
                    cnt = checkOrder(new boolean[N][N], team[teamMap[i][j]][0], team[teamMap[i][j]][1], i, j, 1);
                }
                int head_i = team[teamMap[i][j]][0];
                int head_j = team[teamMap[i][j]][1];
                team[teamMap[i][j]][0] = team[teamMap[i][j]][2];
                team[teamMap[i][j]][1] = team[teamMap[i][j]][3];
                team[teamMap[i][j]][2] = head_i;
                team[teamMap[i][j]][3] = head_j;
                map[team[teamMap[i][j]][0]][team[teamMap[i][j]][1]] = 1;
                map[head_i][head_j] = 3;
                break;
            }

            i += di[dir];
            j += dj[dir];
        }
        return cnt * cnt;
    }

    static int checkOrder(boolean[][] visited, int i, int j, int target_i, int target_j, int idx) {
        for (int z = 0; z < 4; z++) {
            int ni = i + di[z];
            int nj = j + dj[z];
            if (check(ni, nj) && map[ni][nj] == 2 && !visited[ni][nj]) {
                if (ni == target_i && nj == target_j) {
                    return idx + 1;
                } else {
                    visited[ni][nj] = true;
                    return checkOrder(visited, ni, nj, target_i, target_j, idx + 1);
                }
            }
        }
        return 0;
    }

    static boolean check(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N)
            return false;
        return true;
    }
}

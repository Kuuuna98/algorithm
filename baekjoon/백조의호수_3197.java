package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백조의호수_3197 {
    final static int INF = 987654321;
    final static int[] di = new int[]{0, -1, 0, 1};
    final static int[] dj = new int[]{-1, 0, 1, 0};
    static int R, C;
    static int[][] map, team;
    static int[] team_info;
    static int L1_i, L1_j, L2_i, L2_j;

    public static void main(String[] args) throws Exception {
        init();
        Queue<int[]> queue = checkMeltingTime();
        int round = 0;
        while (find(team[L1_i][L1_j]) != find(team[L2_i][L2_j]) && !queue.isEmpty()) {
            int[] node = queue.poll();
            if (round == map[node[0]][node[1]]) {
                round++;
            }
            checkTeam(node[0], node[1], round);
        }
        System.out.println(round);
    }

    public static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim();
        R = Integer.parseInt(input.substring(0, input.indexOf(' ')));
        C = Integer.parseInt(input.substring(input.indexOf(' ') + 1));

        team = new int[R][C];
        map = new int[R][C];
        L1_i = -1;
        for (int i = 0; i < R; i++) {
            input = br.readLine().trim();
            for (int j = 0; j < C; j++) {
                if (input.charAt(j) == '.') {
                    map[i][j] = 0;
                } else if (input.charAt(j) == 'X') {
                    map[i][j] = INF;
                } else {
                    if (L1_i == -1) {
                        L1_i = i;
                        L1_j = j;
                    } else {
                        L2_i = i;
                        L2_j = j;
                    }
                    map[i][j] = 0;
                }
            }
        }
    }

    public static Queue<int[]> checkMeltingTime() {
        Queue<int[]> queue_map = new LinkedList<>();
        Queue<int[]> queue_team = new LinkedList<>();
        int team_size = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0) {
                    queue_map.add(new int[]{i, j});
                    if (team[i][j] == 0) {
                        team[i][j] = ++team_size;
                        queue_team.add(new int[]{i, j});
                        while (!queue_team.isEmpty()) {
                            int[] node = queue_team.poll();
                            for (int z = 0; z < 4; z++) {
                                int ni = node[0] + di[z];
                                int nj = node[1] + dj[z];

                                if (checkRange(ni, nj) && map[ni][nj] == 0 && team[ni][nj] == 0) {
                                    queue_team.add(new int[]{ni, nj});
                                    team[ni][nj] = team_size;
                                }
                            }
                        }
                    }
                }
            }
        }

        team_info = new int[team_size + 1];
        for (int i = 0; i <= team_size; i++) {
            team_info[i] = i;
        }

        while (!queue_map.isEmpty()) {
            int[] node = queue_map.poll();
            queue_team.add(node);

            for (int z = 0; z < 4; z++) {
                int ni = node[0] + di[z];
                int nj = node[1] + dj[z];

                if (checkRange(ni, nj) && map[node[0]][node[1]] + 1 < map[ni][nj]) {
                    map[ni][nj] = map[node[0]][node[1]] + 1;
                    queue_map.add(new int[]{ni, nj});
                }
            }
        }
        return queue_team;
    }

    public static void checkTeam(int i, int j, int round) {
        for (int z = 0; z < 4; z++) {
            int ni = i + di[z];
            int nj = j + dj[z];

            if (checkRange(ni, nj) && map[ni][nj] <= round && team_info[team[ni][nj]] != team_info[team[i][j]]) {
                if (team_info[team[ni][nj]] == 0) {
                    team[ni][nj] = team[i][j];
                    checkTeam(ni, nj, round);
                } else {
                    union(team[i][j], team[ni][nj]);
                }
            }
        }
    }

    public static int find(int item) {
        if (item != team_info[item]) return team_info[item] = find(team_info[item]);
        return item;
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA < rootB) team_info[rootB] = rootA;
        else team_info[rootA] = rootB;
    }

    public static boolean checkRange(int i, int j) {
        return 0 <= i && i < R && 0 <= j && j < C;
    }

}
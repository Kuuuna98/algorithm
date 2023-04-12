## [💥](https://www.acmicpc.net/problem/11559) [11559] Puyo Puyo

> **난이도: 골드 4<br>
> 소요 시간: 55분<br>
> 메모리: 12180KB<br>
> 시간: 80ms**

## 리뷰

- 프로그래머스의 프렌즈4블록과 비슷한 문제

## 전체 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main_11559_PuyoPuyo {
    static final int N = 12, M = 6;
    static final int[] di = {-1, 0, 1, 0};
    static final int[] dj = {0, 1, 0, -1};

    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[N][];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int puyo = 0;
        boolean pop = true;
        reset:
        while (pop) {
            pop = false;
            for (int j = 0; j < M; j++) {
                for (int i = N - 1; i >= 0; i--) {
                    if (map[i][j] == '.') {
                        break;
                    }
                    if (visited[i][j]) {
                        continue;
                    }
                    if (bfs(i, j)) {
                        pop = true;
                    }
                }
            }
            if (pop) {
                popBlock();
                puyo++;
            }
        }

        System.out.println(puyo);
    }

    static boolean bfs(int i, int j) {
        char target = map[i][j];
        List<int[]> list = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();

        visited[i][j] = true;
        list.add(new int[]{i, j});
        queue.offer(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int z = 0; z < 4; z++) {
                int ni = node[0] + di[z];
                int nj = node[1] + dj[z];
                if (check(ni, nj) && !visited[ni][nj] && map[ni][nj] == target) {
                    visited[ni][nj] = true;
                    list.add(new int[]{ni, nj});
                    queue.offer(new int[]{ni, nj});
                }
            }
        }

        if (list.size() < 4) {
            for (int[] node : list) {
                visited[node[0]][node[1]] = false;
            }
            return false;
        }
        return true;
    }

    static void popBlock() {

        for (int j = 0; j < M; j++) {
            int idx = N - 1;
            for (int i = N - 1; i >= 0; i--) {
                if (map[i][j] == '.') {
                    break;
                }
                if (!visited[i][j]) {
                    if (i < idx) {
                        map[idx][j] = map[i][j];
                        map[i][j] = '.';
                    }
                    idx--;
                } else {
                    map[i][j] = '.';
                    visited[i][j] = false;
                }
            }
        }

    }

    static boolean check(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= M) return false;
        return true;
    }

}
```

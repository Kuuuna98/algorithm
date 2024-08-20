package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 뱀과사다리게임_16928 {
    static Map<Integer,Integer> ladderAndSnake;

    public static void main(String args[]) throws Exception {

        init();
        System.out.print(bfs());
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ladderAndSnake = new HashMap<>();

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            ladderAndSnake.put(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            ladderAndSnake.put(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
    }

    static int bfs() {
        boolean[] visited = new boolean[101];
        visited[1] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 1});

        while (true) {
            int[] node = queue.poll();
            for (int dice = 1; dice <= 6; dice++) {
                int num = ladderAndSnake.getOrDefault(node[1] + dice,node[1] + dice);
                if (num > 100) break;
                if (num == 100) return node[0] + 1;

                if (visited[num]) continue;
                visited[num] = true;
                queue.offer(new int[]{node[0] + 1, num});
            }
        }
    }
}
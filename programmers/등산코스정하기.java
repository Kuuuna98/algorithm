package programmers;

import java.util.*;

public class 등산코스정하기 {
    int answer_summit = 0;
    int answer_intensity = 10000001;
    List<int[]>[] graph;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList[n + 1];
        boolean[] gate_check = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] path : paths) {
            graph[path[0]].add(new int[]{path[1], path[2]});
            graph[path[1]].add(new int[]{path[0], path[2]});
        }

        for (int gate : gates) {
            gate_check[gate] = true;
        }

        Arrays.sort(summits);
        for (int gate : gates) {
            checkIntensity(n, gate, summits, Arrays.copyOf(gate_check, gate_check.length));
        }

        return new int[]{answer_summit, answer_intensity};
    }

    void checkIntensity(final int n, final int gate, final int[] summits, boolean[] visited) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        pq.add(new int[]{gate, 0});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            visited[node[0]] = true;
            for (int summit : summits) {
                if (node[0] == summit) {
                    if (node[1] < answer_intensity || (node[1] == answer_intensity && summit < answer_summit)) {
                        answer_intensity = node[1];
                        answer_summit = summit;
                    }
                    return;
                }
            }

            for (int[] graphItem : graph[node[0]]) {
                if (!visited[graphItem[0]]) {
                    pq.add(new int[]{graphItem[0], Math.max(graphItem[1], node[1])});
                }
            }
        }
    }
}
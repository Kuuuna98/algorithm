## [🦠](https://www.acmicpc.net/problem/26732) [bj26732] Agar.io

> **난이도: 골드 5<br>
> 소요 시간: 55분<br>
> 메모리: 68884KB<br>
> 시간: 836ms**

## 리뷰

- 스페인어 문제,, @\_@ 시간초과로 고생했다..

## 전체 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_26732 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Long> pq_min = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Long> pq_max = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long cur = 2, max = 1;
        int time = 0;
        while (N-- > 0) {
            long cell = Long.parseLong(st.nextToken());
            if (cell < cur) {
                pq_min.add(cell);
            } else {
                if (max < cell) max = cell;
                pq_max.add(cell);
            }
        }

        while (!pq_max.isEmpty() && !pq_min.isEmpty()) {
            if (max == cur) break;
            time++;
            cur += pq_min.poll();
            while (!pq_max.isEmpty() && pq_max.peek() < cur) {
                pq_min.add(pq_max.poll());
            }
        }

        System.out.println(pq_max.isEmpty() || max == cur ? time : "NIE");
    }
}
```

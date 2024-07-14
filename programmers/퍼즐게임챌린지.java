package programmers;

public class 퍼즐게임챌린지 {
    int N;

    public int solution(int[] diffs, int[] times, long limit) {
        N = diffs.length;
        int left = 1, right = 300000;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (checkLevel(mid, diffs, times) <= limit) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    long checkLevel(int mid, int[] diffs, int[] times) {
        long time = 0;
        for (int i = 0; i < N; i++) {
            time += times[i];
            if (mid < diffs[i]) {
                time += (diffs[i] - mid) * (times[i] + times[i - 1]);
            }
        }
        return time;
    }
}
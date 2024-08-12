package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class 이중우선순위큐_7662 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap();

            while (k-- > 0) {
                String input = br.readLine();
                if (input.charAt(0) == 'I') {
                    int num = Integer.parseInt(input.substring(2));
                    if (map.containsKey(num)) {
                        map.replace(num, map.get(num) + 1);
                    } else {
                        map.put(num, 1);
                    }
                } else if (!map.isEmpty()) {
                    int key = input.charAt(2) == '1' ? map.lastKey() : map.firstKey();
                    int cnt = map.get(key);
                    if (cnt == 1) {
                        map.remove(key);
                    } else {
                        map.replace(key, cnt - 1);
                    }
                }
            }

            if (map.isEmpty()) {
                sb.append("EMPTY").append("\n");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            }
        }
        System.out.print(sb);
    }
}
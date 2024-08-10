package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리2_17404 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] cost = new int[N][3];
        int[][] color = new int[N][3];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            color[i] = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
        }

        int answer = 987654321;
        //R
        cost[0][0] = color[0][0];
        cost[0][1] = cost[0][2] = 987654321;
        for(int i=1; i<N; i++){
            cost[i][0] = color[i][0] + Math.min(cost[i-1][1],cost[i-1][2]);
            cost[i][1] = color[i][1] + Math.min(cost[i-1][0],cost[i-1][2]);
            cost[i][2] = color[i][2] + Math.min(cost[i-1][1],cost[i-1][0]);
        }
        answer = Math.min(answer,Math.min(cost[N-1][1],cost[N-1][2]));

        //G
        cost[0][1] = color[0][1];
        cost[0][0] = cost[0][2] = 987654321;
        for(int i=1; i<N; i++){
            cost[i][0] = color[i][0] + Math.min(cost[i-1][1],cost[i-1][2]);
            cost[i][1] = color[i][1] + Math.min(cost[i-1][0],cost[i-1][2]);
            cost[i][2] = color[i][2] + Math.min(cost[i-1][1],cost[i-1][0]);
        }
        answer = Math.min(answer,Math.min(cost[N-1][0],cost[N-1][2]));

        //B
        cost[0][2] = color[0][2];
        cost[0][0] = cost[0][1] = 987654321;
        for(int i=1; i<N; i++){
            cost[i][0] = color[i][0] + Math.min(cost[i-1][1],cost[i-1][2]);
            cost[i][1] = color[i][1] + Math.min(cost[i-1][0],cost[i-1][2]);
            cost[i][2] = color[i][2] + Math.min(cost[i-1][1],cost[i-1][0]);
        }
        answer = Math.min(answer,Math.min(cost[N-1][0],cost[N-1][1]));
        System.out.println(answer);
    }
}
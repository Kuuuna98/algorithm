package baekjoon;

import java.io.*;
import java.util.*;

public class 배열돌리기3_16935 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()),
                R = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        int[][] temp;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        while (st.hasMoreTokens()) {
            switch (st.nextToken()) {
                case "4":
                    temp = new int[M][N];
                    M=N;
                    N=temp.length;
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < M; j++) {
                            temp[i][j] = arr[j][i];
                        }
                    }
                    arr = temp;
                case "1":
                    for (int i = 0; i < N / 2; i++) {
                        int[] tmp = Arrays.copyOf(arr[N / 2 - 1 - i], M);
                        arr[N / 2 - 1 - i] = Arrays.copyOf(arr[N / 2 + i], M);
                        arr[N / 2 + i] = tmp;
                    }
                    break;
                case "3":
                    temp = new int[M][N];
                    M=N;
                    N=temp.length;
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < M; j++) {
                            temp[i][j] = arr[j][i];
                        }
                    }
                    arr = temp;
                case "2":
                    for (int j = 0; j < M / 2; j++) {
                        for (int i = 0; i < N; i++) {
                            int tmp = arr[i][M / 2 - 1 - j];
                            arr[i][M / 2 - 1 - j] = arr[i][M / 2 + j];
                            arr[i][M / 2 + j] = tmp;
                        }
                    }
                    break;
                case "5":
                    for (int i = 0; i < N / 2; i++) {
                        for (int j = 0; j < M / 2; j++) {
                            int tmp = arr[N / 2 + i][j];
                            arr[N / 2 + i][j] = arr[N / 2 + i][M / 2 + j];
                            arr[N / 2 + i][M / 2 + j] = arr[i][M / 2 + j];
                            arr[i][M / 2 + j] = arr[i][j];
                            arr[i][j] = tmp;
                        }
                    }
                    break;
                case "6":
                    for (int i = 0; i < N / 2; i++) {
                        for (int j = 0; j < M / 2; j++) {
                            int tmp = arr[i][M / 2 + j];
                            arr[i][M / 2 + j] = arr[N / 2 + i][M / 2 + j];
                            arr[N / 2 + i][M / 2 + j] = arr[N / 2 + i][j];
                            arr[N / 2 + i][j] = arr[i][j];
                            arr[i][j] = tmp;
                        }
                    }
                    break;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                sb.append(arr[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}
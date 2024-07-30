package baekjoon;

import java.util.*;
import java.io.*;

public class 로봇청소기_14503 {

    public static int[] di = { 0, -1, 0, 1 };
    public static int[] dj = { -1, 0, 1, 0 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), M = sc.nextInt();
        int[][] arr = new int[N][M];
        int r = sc.nextInt(), c = sc.nextInt(), d = sc.nextInt();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        sc.close();

        int dr = 0, dc = 0, clean = 0;

        num1: while (true) {
            //1
            arr[r][c] = 2;
            clean++;
            num2: while (true) {
                dr = r + di[d];
                dc = c + dj[d];
                if (arr[dr][dc] == 0) {
                    //2-a
                    r = dr;
                    c = dc;
                    d = (d == 0) ? 3 : d - 1;
                    break num2;
                } else {
                    if (arr[r + di[0]][c + dj[0]] != 0 && arr[r + di[1]][c + dj[1]] != 0
                            && arr[r + di[2]][c + dj[2]] != 0 && arr[r + di[3]][c + dj[3]] != 0) {
                        //2-c
                        int dd = (d == 0) ? 3 : d - 1;
                        if (arr[r + di[dd]][c + dj[dd]] == 2) {
                            r = r + di[dd];
                            c = c + dj[dd];
                        } else {
                            //2-d
                            break num1;
                        }
                    } else {
                        //2-b
                        d = (d == 0) ? 3 : d - 1;
                    }
                }
            }
        }
        System.out.print(clean);
    }
}
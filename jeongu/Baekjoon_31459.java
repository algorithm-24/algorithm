package jeongu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_31459 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    int T = Integer.parseInt(st.nextToken());
    for (int t = 0; t < T; t++) {
      st = new StringTokenizer(br.readLine());
      int X = Integer.parseInt(st.nextToken());
      int Y = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      int[][] arr = new int[X][Y];

      int count = 0;
      for (int i = 0; i < X; i++) {
        for (int j = 0; j < Y; j++) {
          if (arr[i][j] == 0) {
            count++;
            if (i + x < X && j + y < Y) {
              arr[i + x][j + y] = 1;
            }
          }
        }
      }
      sb.append(count).append("\n");
    }
    System.out.println(sb);
  }

}

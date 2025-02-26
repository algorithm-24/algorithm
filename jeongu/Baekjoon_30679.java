package jeongu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_30679 {

  static int[][] arr, visited;
  static int N, M, D; // D : 방향 0:우, 1:하, 2:좌, 3:상

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    arr = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int count = 0;
    for (int i = 0; i < N; i++) {
      visited = new int[N][M];
      for (int j = 0; j < N; j++) {
        Arrays.fill(visited[j], -1);
      }
      D = 0;
      if (func(i)) {
        count++;
        sb.append(i + 1).append(" ");
      }
    }
    System.out.println(count);
    System.out.println(sb);
  }

  static boolean func(int row) {
    int col = 0;
    while (true) {
      if (row < 0 || row >= N || col < 0 || col >= M) return false;
      if (visited[row][col] == D) return true;
      visited[row][col] = D;
      switch (D) {
        case 0: // 우
          col += arr[row][col];
          break;
        case 1: // 하
          row += arr[row][col];
          break;
        case 2: // 좌
          col -= arr[row][col];
          break;
        case 3: // 상
          row -= arr[row][col];
          break;
      }
      D = (D + 1) % 4;
    }
  }

}

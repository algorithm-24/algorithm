package jeongu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_17143 {

  static int R, C;
  static Shark[][] arr, temp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken()); // 행 수
    C = Integer.parseInt(st.nextToken()); // 열 수
    int M = Integer.parseInt(st.nextToken()); // 상어의 수

    arr = new Shark[R + 1][C + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()); // 상어 위치 (행)
      int c = Integer.parseInt(st.nextToken()); // 상어 위치 (열)
      int s = Integer.parseInt(st.nextToken()); // 속력
      int d = Integer.parseInt(st.nextToken()); // 방향
      int z = Integer.parseInt(st.nextToken()); // 크기

      arr[r][c] = new Shark(r, c, s, d, z);
    }

    int sum = 0;

    // 1. 오른쪽으로 이동한다.
    for (int k = 1; k <= C; k++) {
      // 2. 상어를 잡는다.
      for (int i = 1; i <= R; i++) {
        if (arr[i][k] != null) {
          sum += arr[i][k].z;
          arr[i][k] = null;
          break;
        }
      }

      // 3. 상어가 이동한다.
      temp = new Shark[R + 1][C + 1];
      for (int i = 1; i <= R; i++) {
        for (int j = 1; j <= C; j++) {
          if (arr[i][j] != null) {
            move(arr[i][j]);
          }
        }
      }
      arr = temp;
    }

    System.out.println(sum);
  }

  public static void move(Shark shark) {
    int length = shark.s;  // 이동해야 하는 거리

    while (length > 0) {
      int distance = 0; // 한번에 이동한 거리
      boolean isTurned = false;

      if (shark.d == 1) {  // 위
        if (length > shark.r - 1) {  // 방향 전환
          distance = shark.r - 1;
          shark.r = 1;
          isTurned = true;
        } else {
          shark.r -= length;
        }
      } else if (shark.d == 2) { // 아래
        if (length > R - shark.r) {  // 방향 전환
          distance = R - shark.r;
          shark.r = R;
          isTurned = true;
        } else {
          shark.r += length;
        }
      } else if (shark.d == 3) { // 오른쪽
        if (length > C - shark.c) {  // 방향 전환
          distance = C - shark.c;
          shark.c = C;
          isTurned = true;
        } else {
          shark.c += length;
        }
      } else if (shark.d == 4) { // 왼쪽
        if (length > shark.c - 1) {  // 방향 전환
          distance = shark.c - 1;
          shark.c = 1;
          isTurned = true;
        } else {
          shark.c -= length;
        }
      }
      if (isTurned) { // 방향 변경
        shark.d = shark.d % 2 == 0 ? shark.d - 1 : shark.d + 1;
      }
      if (!isTurned) {
        distance = length;
      }
      length -= distance;
    }

    eat(shark);
  }

  public static void eat(Shark shark) {
    if (temp[shark.r][shark.c] != null) {
      Shark other = temp[shark.r][shark.c];
      temp[shark.r][shark.c] = other.z > shark.z ? other : shark;
    } else {
      temp[shark.r][shark.c] = shark;
    }
  }

  public static class Shark{
    int r;
    int c;
    int s;
    int d;
    int z;

    public Shark(int r, int c, int s, int d, int z) {
      this.r = r;
      this.c = c;
      this.s = s;
      this.d = d;
      this.z = z;
    }
  }

}

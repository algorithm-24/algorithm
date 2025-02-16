package search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_5213 {

  static int[][][] tiles;
  static boolean[][] visited;
  static int[] dx = {-1, 0, 1, 1, 0, -1}; // 앞 3, 뒤 3
  static int[] dyForOdd = {-1, -1, -1, 0, 1, 0};
  static int[] dyForEven = {0, -1, 0, 1, 1, 1};
  static Pos answer;

  static int N;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    tiles = new int[N + 1][N][2]; // 홀수 줄에 N 개의 타일, 짝수 줄에 N - 1 개의 타일
    visited = new boolean[N + 1][N];
    answer = new Pos(0, 0, null);

    for (int i = 0; i < N * N; i++) {
      int row = i / N + 1;
      int col = i % N;
      if (row % 2 == 0 && col == N - 1) { // 짝수 줄 마지막 타일은 0, 0 으로 빈칸 표현
        tiles[row][col][0] = 0;
        tiles[row][col][1] = 0;
      } else {
        st = new StringTokenizer(br.readLine());
        tiles[row][col][0] = Integer.parseInt(st.nextToken());
        tiles[row][col][1] = Integer.parseInt(st.nextToken());
      }
    }

    bfs(new Pos(1, 0, null));
    System.out.println(answer.getCount());
    System.out.println(answer.getPath());
  }

  static void bfs(Pos start) {
    Queue<Pos> queue = new LinkedList<>();
    queue.add(start);
    visited[start.row][start.col] = true;

    while (!queue.isEmpty()) {
      Pos now = queue.poll();

      if (now.row * N + now.col > answer.row * N + answer.col) {
        answer = now;
        if (now.row == N && now.col == N - 1) {
          break;
        }
      }

      boolean isOdd = now.row % 2 == 1;

      for (int i = 0; i < 6; i++) {
        int next_row = now.row + dx[i];
        int next_col = now.col;
        if (isOdd) {
          next_col += dyForOdd[i];
        } else {
          next_col += dyForEven[i];
        };

        if (next_row >= 0 && next_col >= 0 && next_row <= N && next_col < N) {
          if (!visited[next_row][next_col]) {
            int nowLR = i < 3 ? 0 : 1;
            int nextLR = i < 3 ? 1 : 0;
            if (tiles[now.row][now.col][nowLR] == tiles[next_row][next_col][nextLR]) {
              visited[next_row][next_col] = true;
              queue.offer(new Pos(next_row, next_col, now));
            }
          }
        }
      }
    }
  }

  static class Pos {
    int row;
    int col;
    Pos pre;

    public Pos(int row, int col, Pos pre) {
      this.row = row;
      this.col = col;
      this.pre = pre;
    }

    public String getPath() {
      if (pre != null) {
        return pre.getPath() + ((this.row - 1) * N + this.col + 1 - (this.row - 1) / 2) + " ";
      } else {
        return ((this.row - 1) * N + this.col + 1) + " ";
      }
    }

    public int getCount() {
      if (pre == null) {
        return 1;
      } else {
        return pre.getCount() + 1;
      }
    }
  }

}

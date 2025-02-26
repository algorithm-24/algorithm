package jeongu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_4991 {

  static char[][] room;
  static int w, h, dirtyNum, dirtyCount, move, min;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;

    while (true) {
      st = new StringTokenizer(br.readLine());
      w = Integer.parseInt(st.nextToken());
      h = Integer.parseInt(st.nextToken());
      dirtyNum = 0;
      dirtyCount = 0;
      move = 0;
      min = Integer.MAX_VALUE;
      int x = 0, y = 0;

      if (w == 0 && h == 0) break;
      room = new char[h][w];
      for (int i = 0; i < h; i++) {
        String s = br.readLine();
        for (int j = 0; j < w; j++) {
          room[i][j] = s.charAt(j);
          if (room[i][j] == 'o') {
            x = i;
            y = j;
          } else if (room[i][j] == '*') {
            dirtyNum++;
          }
        }
      }

      if (bfs(x, y, 0)) {
        sb.append(min).append("\n");
      } else {
        sb.append(-1).append("\n");
      }
    }

    System.out.println(sb);
  }

  static boolean bfs(int x, int y, int move) {
    if (dirtyCount == dirtyNum) {
      min = Math.min(min, move);
      return true;
    }
    if (move >= min) {  // 불필요한 연산을 줄이기 위함
      return false;
    }

    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{x, y});
    boolean[][] isVisited = new boolean[h][w];
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};
    isVisited[x][y] = true;
    int length = 0;
    boolean clear = false;

    while (!queue.isEmpty()) {
      int size = queue.size();
      length++;
      for (int s = size; s > 0; s--) {
        int[] now = queue.poll();
        int now_x = now[0];
        int now_y = now[1];

        for (int i = 0; i < 4; i++) {
          int next_x = now_x + dx[i];
          int next_y = now_y + dy[i];
          if (next_x >= 0 && next_x < h && next_y >= 0 && next_y < w) {
            if (!isVisited[next_x][next_y]) {
              if (room[next_x][next_y] == '*') {
                dirtyCount++;
                room[next_x][next_y] = '.';
                isVisited[next_x][next_y] = true;
                clear |= bfs(next_x, next_y, move + length);  // 모든 더러운 칸을 지운 경우가 하나라도 있을 경우 true 가 리턴되기 위함
                dirtyCount--;
                room[next_x][next_y] = '*';
              } else if (room[next_x][next_y] != 'x') {
                queue.add(new int[]{next_x, next_y});
                isVisited[next_x][next_y] = true;
              }
            }
          }
        }
      }
    }
    return clear;
  }

}

package study_24_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 그래프, bfs
 * 답을 참고했다.
 * down() 메서드를 처음에 실수해서 풀고 맞왜틀을 계속 외쳤었음.
 * bfs 로직도 그림이 그려지지 않았다. 계속 ch 배열을 만들어주는게 핵심이였음.
 */
public class 움직이는미로탈출_16954 {
    static int[][] arr, ch;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 0, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[8][8];
        ch = new int[8][8];
        for (int i = 0; i < 8; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                if (s.charAt(j) == '#') {
                    arr[i][j] = 1;
                }
            }
        }
        int[] start = {7, 0};
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int len = q.size();
            ch = new int[8][8];
            for (int j = 0; j < len; j++) {
                int[] p = q.poll();
                if (arr[p[0]][p[1]] == 1) {
                    continue;
                }
                if (p[0] == 0 && p[1] == 7) {
                    System.out.println(1);
                    return;
                }

                for (int i = 0; i < 9; i++) {
                    int nx = p[0] + dx[i];
                    int ny = p[1] + dy[i];
                    if (!isValid(nx, ny)) {
                        continue;
                    }
                    q.offer(new int[]{nx, ny});
                    ch[nx][ny] = 1;
                }
            }
            down();
        }

        System.out.println(0);
    }

    private static void down() {
        for (int i = 0; i < 8; i++) {
            if (arr[7][i] == 1) {
                arr[7][i] = 0;
            }
        }
        for (int i = 7; i >= 1; i--) {
            for (int j = 0; j < 8; j++) {
                if (arr[i - 1][j] == 1) {
                    arr[i][j] = 1;
                    arr[i - 1][j] = 0;
                }
            }
        }
    }

    private static boolean isValid(int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8 || arr[nx][ny] == 1 || ch[nx][ny] == 1) {
            return false;
        }
        return true;
    }
}

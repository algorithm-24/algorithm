import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dx = { 1, 0, -1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[][] board = new int[n][m];
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        for (int y = 0; y < n; y++) {
            int dir = 0;
            int[][][] visited = new int[n][m][4];
            int curY = y;
            int curX = 0;
            while (true) {
                if (!isInBoard(curY, curX)) {
                    break;
                }
                if (visited[curY][curX][dir] == 1) {
                    ans.add(y + 1);
                    break;
                }
                int prev = board[curY][curX];
                visited[curY][curX][dir] = 1;
                curY += dy[dir] * prev;
                curX += dx[dir] * prev;
                dir = (dir + 1) % 4;
            }
        }

        System.out.println(ans.size());
        ans.forEach(it -> System.out.print(it + " "));
    }

    public static boolean isInBoard(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}


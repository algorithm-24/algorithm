import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int[] dx = { 0, -1, 0, 1 };
    public static int[] dy = { 1, 0, -1, 0 };

    public static class Nav {
        int a, b;
        List<Integer> path;

        public Nav(int a, int b, List<Integer> path) {
            this.a = a;
            this.b = b;
            this.path = path;
        }

        public Nav to(int a, int b, int[][] tileNum) {
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(tileNum[a][b]);
            return new Nav(a, b, newPath);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] board = new int[n][2 * n];
        int[][] tileNum = new int[n][2 * n];
        int[][] visited = new int[n][2 * n];

        int tmp = 1;
        for (int row = 0; row < n; row++) {
            for (int col = row % 2; col < 2 * n - row % 2; col += 2) {
                board[row][col] = scanner.nextInt();
                board[row][col + 1] = scanner.nextInt();
                tileNum[row][col] = tmp;
                tileNum[row][col + 1] = tmp;
                tmp++;
            }
        }

        List<Integer> path = new ArrayList<>();
        path.add(1);
        Queue<Nav> queue = new LinkedList<>();
        Nav ans = new Nav(0, 0, path);
        queue.add(ans);
        int maxNum = 1;

        while (!queue.isEmpty()) {
            Nav nav = queue.poll();
            if (visited[nav.a][nav.b] == 1) {
                continue;
            }
            if (maxNum < tileNum[nav.a][nav.b]) {
                maxNum = tileNum[nav.a][nav.b];
                ans = nav;
            }

            int row = nav.a;
            int col1 = nav.b;
            int col2 = ((nav.a + nav.b) % 2 == 0 ? 1 : -1) + nav.b;
            List<Integer> cols = List.of(col1, col2);
            visited[row][col1] = 1;
            visited[row][col2] = 1;

            for (int col : cols) {
                for (int i = 0; i < 4; i++) {
                    int tmpRow = row + dx[i];
                    int tmpCol = col + dy[i];
                    if (tmpRow >= 0 && tmpRow < n && tmpCol >= 0 && tmpCol < 2 * n
                        && tileNum[tmpRow][tmpCol] > 0
                        && visited[tmpRow][tmpCol] == 0
                        && board[row][col] == board[tmpRow][tmpCol]
                    ) {
                        queue.add(nav.to(tmpRow, tmpCol, tileNum));
                    }
                }
            }
        }

        System.out.println(ans.path.size());
        for (var i : ans.path) {
            System.out.print(i + " ");
        }
    }
}


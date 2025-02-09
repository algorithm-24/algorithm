import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    private static int[][] board = new int[20][20];
    private static int n;
    private static int size = 2;
    private static int remain = 2;
    private static Pair pos = new Pair(0, 0);
    private static Pair[] dir = {
        new Pair(1, 0),
        new Pair(-1, 0),
        new Pair(0, 1),
        new Pair(0, -1)
    };

    private static int ans = 0;

    public static class Pair {
        public final int left, right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    public static class Triple {
        public final int left, right, dist;

        public Triple(int left, int right, int dist) {
            this.left = left;
            this.right = right;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = scanner.nextInt();
                if (board[i][j] == 9) {
                    pos = new Pair(i, j);
                    board[i][j] = 0;
                }
            }
        }

        while (pos != null) {
            pos = findNextPos();
        }

        System.out.println(ans);
    }

    private static Pair findNextPos() {
        PriorityQueue<Triple> pq = new PriorityQueue<>(Comparator.comparingInt(lhs -> lhs.dist));
        int[][] visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        visited[pos.left][pos.right] = 0;
        pq.add(new Triple(pos.left, pos.right, 0));

        while (!pq.isEmpty()) {
            Triple cur = pq.poll();
            if (visited[cur.left][cur.right] < cur.dist) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                Triple newPos = new Triple(cur.left + dir[i].left, cur.right + dir[i].right, cur.dist + 1);
                if (newPos.left >= 0 && newPos.left < n && newPos.right >= 0 && newPos.right < n && visited[newPos.left][newPos.right] > newPos.dist && board[newPos.left][newPos.right] <= size) {
                    pq.add(newPos);
                    visited[newPos.left][newPos.right] = visited[cur.left][cur.right] + 1;
                }
            }
        }

        int dist = Integer.MAX_VALUE;
        Pair nextPos = null;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                int curDist = visited[x][y];
                if (board[x][y] < size && board[x][y] > 0 && dist > curDist) {
                    dist = curDist;
                    nextPos = new Pair(x, y);
                }
            }
        }

        if (nextPos != null) {
            ans += dist;
            remain--;
            if (remain == 0) {
                size++;
                remain = size;
            }
            board[nextPos.left][nextPos.right] = 0;
        }

        return nextPos;
    }
}

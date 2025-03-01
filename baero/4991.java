import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] board = new char[20][20];
    static int[][] visited = new int[20][20];
    static int[][] memoi = new int[1 << 10][11];
    static Pair[] dirts = new Pair[11];
    static int[][] adjList = new int[11][11];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Pair {
        final int y, x;
        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Triple {
        final int x, y, z;
        public Triple(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int maxX = Integer.parseInt(st.nextToken());
            int maxY = Integer.parseInt(st.nextToken());
            if (maxX == 0 && maxY == 0) {
                break;
            }
            bw.write(solution(maxY, maxX) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int solution(int maxY, int maxX) throws IOException {
        int dirtsSize = 1;
        for (int i = 0; i < maxY; i++) {
            String s = br.readLine();
            for (int j = 0; j < maxX; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'o') dirts[0] = new Pair(i, j);
                if (board[i][j] == '*') dirts[dirtsSize++] = new Pair(i, j);
            }
        }

        if (dirtsSize == 1) return 0;

        for (int i = 0; i < dirtsSize; i++) {
            for (int j = 0; j < dirtsSize; j++) {
                adjList[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < dirtsSize - 1; i++) {
            for (int j = i + 1; j < dirtsSize; j++) {
                int tmp = bfs(maxY, maxX, dirts[i], dirts[j]);
                if (tmp == Integer.MAX_VALUE) return -1;
                adjList[i][j] = tmp;
                adjList[j][i] = tmp;
            }
        }

        for (int i = 0; i < (1 << (dirtsSize - 1)); i++) {
            for (int j = 0; j < dirtsSize; j++) {
                memoi[i][j] = -1;
            }
        }

        return tsp(0, 0, dirtsSize - 1);
    }

    static int tsp(int visited, int current, int n) {
        if (visited == (1 << n) - 1) return 0;
        if (memoi[visited][current] != -1) return memoi[visited][current];
        int result = Integer.MAX_VALUE;
        for (int next = 1; next <= n; next++) {
            if ((visited & (1 << (next-1))) == 0) {
                int nextCost = tsp(visited | (1 << (next-1)), next, n);
                if (nextCost != Integer.MAX_VALUE) {
                    int totalCost = adjList[current][next] + nextCost;
                    result = Math.min(result, totalCost);
                }
            }
        }

        memoi[visited][current] = result;
        return result;
    }

    static int bfs(int maxY, int maxX, Pair start, Pair end) {
        if (start.y == end.y && start.x == end.x) {
            return 0;
        }

        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                visited[i][j] = -1;
            }
        }

        Queue<Triple> queue = new LinkedList<>();
        queue.add(new Triple(start.x, start.y, 0));
        visited[start.y][start.x] = 0;

        while (!queue.isEmpty()) {
            Triple cur = queue.poll();

            if (cur.y == end.y && cur.x == end.x) return cur.z;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < maxX && ny >= 0 && ny < maxY
                    && board[ny][nx] != 'x'
                    && visited[ny][nx] == -1
                ) {
                    visited[ny][nx] = cur.z + 1;
                    queue.add(new Triple(nx, ny, cur.z + 1));
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}


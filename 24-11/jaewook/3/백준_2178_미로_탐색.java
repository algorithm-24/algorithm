import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 단순 bfs
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];

        for(int i = 0; i < n; i++) {
            String row = br.readLine();

            for(int j = 0; j < m; j++) {
                board[i][j] = row.charAt(j) - '0';
            }
        }

        int[][] step = new int[n][m];

        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{0, 0, 1});

        final int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x1 = curr[0], y1 = curr[1], cost1 = curr[2];
            int cost2 = cost1 + 1;

            for(int[] move : moves) {
                int x2 = x1 + move[0], y2 = y1 + move[1];

                if(x2 < 0 || x2 >= n || y2 < 0 || y2 >= m || board[x2][y2] == 0 || step[x2][y2] > 0) {
                    continue;
                }

                step[x2][y2] = cost2;

                queue.add(new int[]{x2, y2, cost2});
            }
        }

        br.close();

        System.out.print(step[n-1][m-1]);
    }
}

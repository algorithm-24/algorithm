import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @source: https://www.acmicpc.net/problem/1577 (도로의 개수)
 * @classfication: dp
 * @문제 푼 날짜 (자력으로 풂?): 24.11.17 (O)
 **/
public class Main {
    final static int[][] moves = {{1, 0}, {0, 1}};
    static ArrayList<int[]>[][] board;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        board = new ArrayList[n+1][m+1];
        dp = new long[n+1][m+1];

        for(int i=0;i<n+1;i++) {
            for(int j=0;j<m+1;j++) {
                board[i][j] = new ArrayList<>();
            }
        }

        for(int i=0;i<k;i++) {
            st = new StringTokenizer(br.readLine());

            int s1 = Integer.parseInt(st.nextToken()), e1 = Integer.parseInt(st.nextToken()), s2 = Integer.parseInt(st.nextToken()), e2 = Integer.parseInt(st.nextToken());

            board[s1][e1].add(new int[]{s2, e2});
            board[s2][e2].add(new int[]{s1, e1});
        }

        br.close();

        System.out.print(bt(0, 0));
    }

    private static long bt(int x, int y) {
        if(dp[x][y] > 0) {
            return dp[x][y];
        }

        if(x == board.length-1 && y == board[0].length - 1) {
            return 1;
        }

        long result = 0;
        ArrayList<int[]> notNext = board[x][y];

        for(int[] move : moves) {
            int x2 = x + move[0], y2 = y + move[1];
            boolean flag = true;

            if(x2 < board.length && y2 < board[0].length) {
                for(int[] nn : notNext) {
                    if(x2 == nn[0] && y2 == nn[1]) {
                        flag = false;
                        break;
                    }
                }
            } else {
                flag = false;
            }

            if(flag) {
                result += bt(x2, y2);
            }
        }

        return dp[x][y] = result;
    }
}

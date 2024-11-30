import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @source: https://www.acmicpc.net/problem/1022 (소용돌이 예쁘게 출력하기)
 * @classification: implementation
 * @문제 푼 날짜 (자력으로 풂?): 24.11.30 (X)
 **/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();

        int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());

        final int[][] moves = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        int moveIndex = 0;

        int[][] board = new int[x2-x1+1][y2-y1+1];

        int x = 0, y = 0, num = 1;
        int boardItemCnt = 0;
        int cnt = 0, max = 1;

        while(boardItemCnt < board.length * board[0].length) {
            if(x >= x1 && x <= x2 && y >= y1 && y <= y2) {
                board[x-x1][y-y1] = num;
                ++boardItemCnt;
            }

            ++num;
            ++cnt;
            int[] move = moves[moveIndex];
            x += move[0];
            y += move[1];

            if(cnt == max) {
                cnt = 0;

                if(moveIndex == 1 || moveIndex == 3) {
                    ++max;
                }

                moveIndex = (moveIndex+1) % 4;
            }
        }

        int maxLen = (num-1+"").length();

        StringBuilder output = new StringBuilder();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                String strN = board[i][j] + "";
                int minus = maxLen - (strN.length());
                if(j > 0) minus++;

                for(int k = 0; k < minus; k++) {
                    output.append(" ");
                }

                output.append(board[i][j]);
            }
            output.append("\n");
        }

        System.out.print(output);
    }
}

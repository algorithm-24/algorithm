import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @source: https://www.acmicpc.net/problem/20005 (보스몬스터 전리품)
 * @classification: bfs
 * @문제 푼 날짜 (자력으로 풂?): 24.11.27 (O, 매우 비효율적인 방법)
 **/
// todo: 비효율적인 코드 업솔빙 꼭해볼것
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken()), playerCnt = Integer.parseInt(st.nextToken());

        HashSet<Character> playerNames = new HashSet<>();

        int[][] players = new int[26][2]; // 보스까지 거리, 데미지
        char[][] board = new char[m][n];

        int[][] playersLoc = new int[26][2];

        for(int i = 0; i < m; i++) {
            String data = br.readLine();

            for(int j = 0; j < n; j++) {
                char c = data.charAt(j);

                if(c != '.' && c != 'X' && c != 'B') {
                    playerNames.add(c);
                    playersLoc[(int) c - 'a'] = new int[]{i, j};
                } // else if(c == 'B') {}

                board[i][j] = c;
            }
        }

        final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for(int i = 0; i < playerCnt; i++) {
            st = new StringTokenizer(br.readLine());
            char playerName = st.nextToken().charAt(0);
            int playerIndex = playerName - 'a';
            int damage = Integer.parseInt(st.nextToken());
            int[] playerLoc = playersLoc[playerIndex];

            Queue<int[]> queue = new LinkedList<>();

            int toBossDist = 0;
            queue.offer(new int[]{playerLoc[0], playerLoc[1], 0});

            L1: while( !queue.isEmpty() ) {
                int[] poll = queue.poll();
                int x1 = poll[0], y1 = poll[1], dist1 = poll[2];
                int dist2 = dist1 + 1;

                for(int[] move : moves) {
                    int x2 = x1 + move[0], y2 = y1 + move[1];

                    if(x2 < 0 || x2 >= board.length || y2 < 0 || y2 >= board[0].length) {
                        continue;
                    }

                    if(board[x2][y2] == 'B') {
                        toBossDist = dist2;
                        break L1;
                    }

                    if(board[x2][y2] == 'X' || board[x2][y2] == playerName) {
                        continue;
                    }

                    board[x2][y2] = playerName;
                    queue.offer(new int[]{x2, y2, dist2});
                }
            }

            players[playerIndex] = new int[]{toBossDist, damage};
        }

        int bossHealth = Integer.parseInt(br.readLine());

        br.close();

        int attackerCnt = 0;

        while(bossHealth > 0) {
            for(char playerName : playerNames) {
                --players[(int) playerName - 'a'][0];
                int[] player = players[(int) playerName - 'a'];
                int dist = player[0], damage = player[1];

                if(dist == 0) {
                    ++attackerCnt;
                }

                if(dist <= 0) {
                    bossHealth -= damage;
                }
            }
        }

        System.out.print(attackerCnt);
    }
}

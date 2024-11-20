import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] board = new char[8][8];
    static int[][] cache = new int[8][8];
    static List<int[]> barrier = new ArrayList<>();
    static Queue<int[]> player = new PriorityQueue<>((p1, p2) -> {
        if (p1[2] == p2[2]) {
            if(p1[0] == p2[0]) {
                return p2[1] - p1[1];
            }

            return p2[0] - p1[0];
        }

        return p1[2] - p2[2];
    });
    static final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {0, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    static int sec = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 8; i++) {
            String row = br.readLine();

            for (int j = 0; j < 8; j++) {
                char c = row.charAt(j);
                board[i][j] = c;

                if(c == '#') {
                    barrier.add(new int[]{i, j});
                }
            }
        }

        barrier.sort((b1, b2) -> b2[0] - b1[0]);

        br.close();

        if(board[7][0] == '#') {
            System.out.print(0);
            return;
        }

        player.offer(new int[]{7, 0, 0});

        while( !player.isEmpty() ) {
            int[] poll = player.poll();
            int x1 = poll[0], y1 = poll[1], step1 = poll[2];
            int step2 = step1 + 1;

            if(sec < step1) {
                for(int i=0;i<barrier.size();i++) {
                    int[] b_poll = barrier.get(i);

                    board[b_poll[0]][b_poll[1]] = '.';

                    if(b_poll[0]+1 > 7) continue;

                    barrier.set(i, new int[]{b_poll[0]+1, b_poll[1]});
                    board[b_poll[0]+1][b_poll[1]] = '#';
                }

                ++sec;
            }

            // 렌더링
//            System.out.println(x1 + " " + y1 + " " + step1);
//            for(int i = 0; i < 8; i++) {
//                System.out.println(Arrays.toString(board[i]));
//            }

            if(x1 == 0 && y1 == 7) {
                System.out.print(1);
                return;
            }

            for(int[] move : moves) {
                int x2 = x1 + move[0], y2 = y1 + move[1];

                if(x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7 || board[x2][y2] == '#' || cache[x2][y2] == step2) {
                    continue;
                }

                if(x2 > 0 && board[x2-1][y2] == '#') {
                    continue;
                }

                cache[x2][y2] = step2;
                player.offer(new int[]{x2, y2, step2});
            }
        }

        System.out.print(0);
    }
}

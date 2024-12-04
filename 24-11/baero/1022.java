import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r1 = scanner.nextInt();
        int c1 = scanner.nextInt();
        int r2 = scanner.nextInt();
        int c2 = scanner.nextInt();

        int minR = Math.min(r1, r2);
        int minC = Math.min(c1, c2);
        int maxR = Math.max(r1, r2);
        int maxC = Math.max(c1, c2);

        int[][] board = new int[maxR - minR + 1][maxC - minC + 1];
        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        int cnt = (maxC - minC + 1) * (maxR - minR + 1);
        int dir = 1;
        int x = 0;
        int y = 0;
        int len = 1;
        int cur = 1;
        int largest = -1;

        while (cnt > 0) {
            for (int i = 0; i < 2; i++) {
                for(int j = 0; j < len; j++) {
                    if (r1 <= y && y <= r2 && c1 <= x && x <= c2) {
                        board[y - minR][x - minC] = cur;
                        largest = Math.max(largest, cur);
                        cnt--;
                    }
                    cur++;
                    y += dy[dir];
                    x += dx[dir];
                }
                dir = (++dir) % 4;
            }
            len++;
        }

        String format = "%" + String.valueOf(largest).length() + "d ";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= maxR - minR; i++) {
            for (int j = 0; j <= maxC - minC; j++) {
                stringBuilder.append(String.format(format, board[i][j]));
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder);
    }
}
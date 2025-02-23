import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            solution(scanner);
        }
    }

    public static void solution(Scanner scanner) {
        int boardX = scanner.nextInt();
        int boardY = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int ans = 0;

        int[][] board = new int[boardX][boardY];
        for (int i = 0; i < boardX; i++) {
            for (int j = 0; j < boardY; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    ans++;
                    if (i + x < boardX && j + y < boardY) {
                        board[i + x][j + y] = 1;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}

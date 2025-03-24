import java.util.Scanner;

public class Main {
    private static final int UNVISITED = 1000000000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[][] memoi = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                memoi[i][j] = i == j ? 0 : UNVISITED;
            }
        }

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            memoi[a][b] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    memoi[i][j] = Math.min(memoi[i][j], memoi[i][k] + memoi[k][j]);
                }
            }
        }


        for (int i = 1; i <= n; i++) {
            int ans = 0;
            for (int j = 1; j <= n; j++) {
                if (i != j && (memoi[i][j] == UNVISITED && memoi[j][i] == UNVISITED)) {
                    ans++;
                }
            }
            System.out.println(ans);
        }

    }
}

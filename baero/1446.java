import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static class Triple {
        int first;
        int second;
        int third;

        public Triple(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int d = scanner.nextInt();

        List<Triple> shortCuts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            shortCuts.add(new Triple(x, y, z));
        }

        int[] dp = new int[d + 1];

        dp[0] = 0;

        for (int i = 1; i <= d; i++) {
            dp[i] = dp[i - 1] + 1;
            for (var shortCut: shortCuts) {
                if (shortCut.second == i) {
                    dp[i] = Math.min(dp[shortCut.first] + shortCut.third, dp[i]);
                }
            }
        }

        System.out.println(dp[d]);
    }

}

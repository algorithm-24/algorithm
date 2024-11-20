import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static List<Integer> civil;
    static List<List<Integer>> adjList;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        dp = new int[10001][2];
        visited = new boolean[10001];

        int n = scanner.nextInt();
        civil = IntStream.range(0, n).boxed()
                .map(i -> scanner.nextInt())
                .collect(Collectors.toList());
        adjList = IntStream.rangeClosed(0, n).boxed()
                .map(i -> new ArrayList<Integer>())
                .collect(Collectors.toList());

        IntStream.range(1, n).forEach(i -> {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        });

        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int cur) {
        if (visited[cur]) return;
        visited[cur] = true;
        dp[cur][0] = 0;
        dp[cur][1] = civil.get(cur - 1);

        adjList.get(cur).stream()
                .filter(next -> !visited[next])
                .forEach(next -> {
                    dfs(next);
                    dp[cur][0] += Math.max(dp[next][0], dp[next][1]);
                    dp[cur][1] += dp[next][0];
                });
    }
}

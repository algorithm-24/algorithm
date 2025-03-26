package jaewook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *@source: https://www.acmicpc.net/problem/10159 (저울)
 *@classification: dfs
 * @문제 푼 날짜 (자력으로 풂?): 25.03.26 (O1)
 **/
public class Q10159 {
    static boolean[] visited;
    static ArrayList<int[]>[] graph;
    static int cut = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st;

        graph = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            graph[a].add(new int[]{b, 1});
            graph[b].add(new int[]{a, -1});
        }

        br.close();

        for(int i = 0; i < n; i++) {
            visited = new boolean[n];
            visited[i] = true;
            cut = n - 1;

            solve(i, 1);
            solve(i, -1);

            result.append(cut).append("\n");
        }

        System.out.print(result);
    }

    private static void solve(int index, int type) {

        for(int[] edge : graph[index]) {
            int next_index = edge[0];
            int next_type = edge[1];

            if( !visited[next_index] && type == next_type) {
                visited[next_index] = true;
                cut--;

                solve(next_index, next_type);
            }
        }
    }
}

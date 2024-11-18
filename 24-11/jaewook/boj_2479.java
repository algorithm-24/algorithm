import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @source: https://www.acmicpc.net/problem/2479 (경로 찾기)
 * @classfication: dijkstra
 * @문제 푼 날짜 (자력으로 풂?): 24.11.18 (O)
 **/
// 단순 bfs 문제입니다
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        String[] nodes = new String[n+1];
        int[] costs = new int[n+1];
        final int INF = 1001;
        Arrays.fill(costs, INF);

        for(int i=1;i<=n;i++) {
            nodes[i] = br.readLine();
        }

        st = new StringTokenizer(br.readLine());
        int str = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        costs[str] = 0;

        br.close();

        Queue<int[]> queue = new LinkedList<>();
        Queue<String> logs = new LinkedList<>();
        String answer = "";

        queue.offer(new int[]{str, 0});
        logs.offer(String.valueOf(str));

        while( !queue.isEmpty() ) {
            int[] poll = queue.poll();
            String log1 = logs.poll();
            int sIdx = poll[0];
            int cost1 = poll[1];

            String n1 = nodes[poll[0]];

            for(int i=1;i<=n;i++) {
                if(i == sIdx) continue;

                String n2 = nodes[i];
                if(costs[i] <= cost1+1) continue;

                int diff = 0;

                for(int j=0;j<k;j++) {
                    if(n1.charAt(j) != n2.charAt(j)) {
                        ++diff;
                    }

                    if(diff == 2) {
                        break;
                    }
                }

                if(diff == 1) {
                    costs[i] = cost1+1;
                    String log2 = log1 + " " + i;

                    if(i != end) {
                        queue.offer(new int[]{i, costs[i]});
                        logs.offer(log2);
                    } else {
                        answer = log2;
                    }
                }
            }
        }

        System.out.print(answer.equals("") ? -1 : answer);
    }
}

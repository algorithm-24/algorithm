import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<int[]>[] graph;
    static int[] d;
    static int e;
    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()) - 1;
        e = Integer.parseInt(st.nextToken()) - 1;

        graph = new ArrayList[n];
        d = new int[n];

        Arrays.fill(d, INF);
        d[s] = 0;

        for(int i=0;i<n;i++)
            graph[i] = new ArrayList<>();

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1, b = Integer.parseInt(st.nextToken()) - 1;
            int dist = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, dist});
            graph[b].add(new int[]{a, dist});
        }

        br.close();

        dijkstra(s);
        System.out.print(d[e] == INF ? 0 : d[e]);
    }

    private static void dijkstra(int init) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p2[1] - p1[1]);

        for(int[] arr : graph[init]) {
            d[arr[0]] = arr[1];
            pq.add(new int[]{arr[0], arr[1]});
        }

        while( !pq.isEmpty() ) {
            int[] poll = pq.poll();

            int node1 = poll[0];
            int dist1 = poll[1];

            for(int[] arr : graph[node1]) {
                int node2 = arr[0];
                int dist2 = arr[1];

                int v = Math.min(dist1, dist2);

                if(d[node2] == INF || d[node2] < v) {
                    d[node2] = v;
                    pq.add(new int[]{node2, d[node2]});
                }
            }
        }
    }
}

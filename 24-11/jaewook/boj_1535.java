import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 안녕
// 단순하게 백트래킹
// todo: dp로 변경
public class Main {
    static int answer = 0;
    static boolean[] visited;
    static int[][] data;
    static int[] dp = new int[100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        data = new int[n][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            data[i][0] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            data[i][1] = Integer.parseInt(st.nextToken());
        }

        br.close();

        bt(0, 0, 0);

        System.out.print(answer);
    }

    private static void bt(int index, int v, int w) {
        for(int i=index;i< data.length;i++) {
            if(visited[i]) continue;
            int w2 = w + data[i][0];
            int v2 = v + data[i][1];

            if(w2 < 100) {
                dp[w2] = v2;
                answer = Math.max(answer, v2);

                visited[i] = true;
                bt(i+1, v2, w2);
                visited[i] = false;
            }
        }
    }
}
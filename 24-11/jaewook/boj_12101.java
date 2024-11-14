import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @source: https://www.acmicpc.net/problem/12101 (1, 2, 3 더하기 2)
 * @classfication: 단순 백트래킹
 **/
public class Main {
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        br.close();

        bt("", 0);

        System.out.print(-1);
    }

    private static void bt(String memo, int sum) {
        if(sum == n) {
            --k;

            if(k == 0) {
                System.out.print(memo);
                System.exit(0);
            }

            return;
        }

        for(int i=1;i<=3;i++) {
            int sum2 = sum + i;

            if(sum2 > n) break;

            bt(memo.isEmpty() ? i+"" : memo+"+"+i, sum2);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @source: https://www.acmicpc.net/problem/17610 (양팔저울)
 * @classfication: backtracking
 * @문제 푼 날짜 (자력으로 풂?): 24.11.16 (X)
 **/
public class Main {
    static int[] nums;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int numTotal = 0;
        nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
            numTotal += num;
        }

        visited = new boolean[numTotal + 1];

        br.close();

        bt(0, 0);

        int answer = 0;

        for(int i=1;i<=numTotal;i++) {
            if( !visited[i] ) {
                ++answer;
            }
        }

        System.out.print(answer);
    }

    private static void bt(int depth, int sum) {
        if(depth == nums.length) {
            if(sum > 0)
                visited[sum] = true;

            return;
        }

        bt(depth+1, sum);
        bt(depth+1, sum + nums[depth]);
        bt(depth+1, sum - nums[depth]);
    }
}

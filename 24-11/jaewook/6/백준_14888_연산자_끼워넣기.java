import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @source: https://www.acmicpc.net/problem/14888 (연산자 끼워넣기)
 * @classfication: backtracking
 * @문제 푼 날짜 (자력으로 풂?): 24.12.17 (O)
 **/
// 단순 백트래킹
public class Main {
    static int max = -1_000_000_001;
    static int min = 1_000_000_001;
    static int[] operations = new int[4]; // +, -, *, /
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[n];

        for(int i=0;i<n;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<4;i++) {
            operations[i] = Integer.parseInt(st.nextToken());
        }

        br.close();

        bt(1, nums[0]);

        System.out.println(max);
        System.out.println(min);
    }

    private static void bt(int index, int sum) {
        if(index == nums.length) {
            max = Math.max(sum, max);
            min = Math.min(sum, min);

            return;
        }

        for(int i=0;i<operations.length;i++) {
            int operation = operations[i];

            if(operation == 0) continue;

            --operations[i];

            int sum2 = sum, num = nums[index];

            if(i == 0) {
                sum2 += num;
            } else if(i == 1) {
                sum2 -= num;
            } else if(i == 2) {
                sum2 *= num;
            } else if(i == 3) {
                sum2 /= num;
            }

            bt(index+1, sum2);

            ++operations[i];
        }
    }
}

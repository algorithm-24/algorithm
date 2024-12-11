import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @source: https://www.acmicpc.net/problem/1111 (IQ Test)
 * @classfication: brute-force
 * @문제 푼 날짜 (자력으로 풂?): 24.12.03 (X)
 **/
// 수학이 아닌 브루트포스로 풀어버림
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        final String multiAnswer = "A";
        final String noAnswer = "B";

        if(n == 1) {
            System.out.print(multiAnswer);
            return;
        }

        int[] seq = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean allEq = true;

        for(int i=0;i<n;i++) {
            int num = Integer.parseInt(st.nextToken());
            seq[i] = num;

            if(i > 0 && seq[i-1] != seq[i]) {
                allEq = false;
            }
        }

        if(allEq) {
            System.out.print(seq[0]);
            return;
        }

        br.close();

        ArrayList<int[]> memo = new ArrayList<>();

        int zeroNum = seq[0];
        int oneNum = seq[1];

        for(int b=-1000000;b<=1000000;b++) {
            for(int a=-10000;a<=10000;a++) {
                int y = zeroNum * a + b;

                if(y == oneNum) {
                    memo.add(new int[]{a, b});
                }
            }
        }

        for(int i=2;i<n;i++) {
            int prev = seq[i-1];
            int curr = seq[i];

            int correctCnt = 0;

            for(int j=0;j<memo.size();j++) {
                int[] arr = memo.get(j);
                int a = arr[0], b = arr[1];

                int y = prev * a + b;

                if(y == curr) {
                    ++correctCnt;
                } else {
                    memo.remove(j);
                    --j;
                }
            }

            if(correctCnt == 0) {
                System.out.print(noAnswer);
                return;
            }
        }

        if(memo.size() == 1) {
            int[] arr = memo.get(0);
            int a = arr[0];
            int b = arr[1];

            int last = a * seq[n-1] + b;
            System.out.print(last);
        } else {
            System.out.print(multiAnswer);
        }
    }
}

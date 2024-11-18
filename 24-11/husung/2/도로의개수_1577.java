package study_24_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * dp
 * 뭔가 이상하게 풀었는데 맞긴 했다.
 * 입력값이 a b와 c d가 오름차순으로 안들어오는 경우가 있어서 임의로 조건문을 통해 정렬해서 값을 넣었다.
 */
public class 도로의개수_1577 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int k = Integer.parseInt(br.readLine());
        long[][] dp = new long[n + 1][m + 1];
        List<int[]>[][] list = new List[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                list[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (d > b || c > a) {
                list[c][d].add(new int[]{a, b});
            } else {
                list[a][b].add(new int[]{c, d});
            }
        }

        dp[0][0] = 1;
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                long x = 0;
                long y = 0;
                if (i - 1 < 0) {
                    x = 0;
                } else {
                    x = dp[i - 1][j];
                }
                if (j - 1 < 0) {
                    y = 0;
                } else {
                    y = dp[i][j - 1];
                }
                for (int[] tmp : list[i][j]) {
                    if (i - 1 >= 0 && i - 1 == tmp[0] && j == tmp[1]) {
                        x = 0;
                    } else if (j - 1 >= 0 && i == tmp[0] && j - 1 == tmp[1]) {
                        y = 0;
                    }
                }
                dp[i][j] = x + y;
                if (i == 0 && j == 0) dp[i][j] = 1;
            }
        }
        System.out.println(dp[n][m]);
    }
}

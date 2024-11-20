package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1535 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 사람 수
        int N = Integer.parseInt(br.readLine());

        int[] health = new int[N + 1];
        int[] joy = new int[N + 1];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            health[i] = Integer.parseInt(st1.nextToken());
        }

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            joy[i] = Integer.parseInt(st2.nextToken());
        }

        // 체력 제한(100 이상이면 세준이는 죽음)
        int maxHealth = 100;

        // 다이나믹 프로그래밍 배열 선언
        // dp[i][h]: i번째 사람까지 인사했을 때, h 체력으로 얻을 수 있는 최대 기쁨
        int[][] dp = new int[N + 1][maxHealth];

        // DP 테이블 채우기
        for (int i = 1; i <= N; i++) {
            for (int h = 0; h < maxHealth; h++) {
                if (h >= health[i]) {
                    dp[i][h] = Math.max(dp[i - 1][h], dp[i - 1][h - health[i]] + joy[i]);
                } else {
                    dp[i][h] = dp[i - 1][h];
                }
            }
        }

        int answer = 0;
        for (int h = 0; h < maxHealth; h++) {
            answer = Math.max(answer, dp[N][h]);
        }

        System.out.println(answer);

    }
}
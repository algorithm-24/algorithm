package week4;

import java.io.*;
import java.util.*;

public class Boj_1022 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기`w
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        // 최대 자릿수 계산
        int maxDigits = 0;
        for (int i = r1; i <= r2; ++i) {
            for (int j = c1; j <= c2; ++j) {
                maxDigits = Math.max(maxDigits, countDigits(calculateValue(i, j)));
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = r1; i <= r2; ++i) {
            for (int j = c1; j <= c2; ++j) {
                sb.append(String.format("%" + maxDigits + "d ", calculateValue(i, j)));
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    // (i, j) 좌표의 값을 계산하는 함수
    public static int calculateValue(int i, int j) {
        int n = Math.max(Math.abs(i), Math.abs(j)); // 소용돌이의 층 계산
        int val = (2 * n + 1);
        val *= val; // 해당 층의 시작 값 계산

        int diff = 2 * n;
        if (i == n) return val - (n - j); // 위쪽 변
        val -= diff;
        if (j == -n) return val - (n - i); // 왼쪽 변
        val -= diff;
        if (i == -n) return val - (j + n); // 아래쪽 변
        val -= diff;
        return val - (i + n); // 오른쪽 변
    }

    // 숫자의 자릿수를 계산하는 함수
    public static int countDigits(int val) {
        return val == 0 ? 1 : (int) Math.log10(val) + 1; // 자릿수 계산
    }
}

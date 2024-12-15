package week4;

import java.io.*;
import java.util.*;

public class Boj_18114 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] weights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weights);

        for (int weight : weights) {
            if (weight == C) {
                System.out.println(1);
                return;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = weights[i] + weights[j];

                if (sum == C) {
                    System.out.println(1);
                    return;
                }

                if (sum < C) {
                    int remaining = C - sum;

                    // 세 번째 물건을 찾기 위해 이진 탐색 수행하기
                    if (Arrays.binarySearch(weights, j + 1, N, remaining) >= 0) {
                        System.out.println(1);
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }
}

package study_24_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * brute force
 * 문제를 제대로 읽고 문제에서 주어진대로 풀면 됨
 * 주어진 추 들로 모든 경우의 수를 조합하는 문제였음. 재귀가 약하다.
 */
public class 양팔저울_17610 {
    static int n;
    static int[] arr, ch;
    private static void D(int idx, int weight) {
        if (idx > n) return;
        if (weight >= 0 && ch[weight] == 0) {
            ch[weight] = 1;
        }
        if (idx < n) {
            D(idx + 1, weight);
            D(idx + 1, weight + arr[idx]);
            D(idx + 1, weight - arr[idx]);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        ch = new int[(int)(sum + 1)];

        D(0, 0);

//        for (Integer integer : ch) {
//            System.out.print(integer + " ");
//        }
//        System.out.println();
        int res = 0;
        for (int i = 1; i <= sum; i++) {
            if (ch[i] == 0) res++;
        }
        System.out.println(res);
    }

}

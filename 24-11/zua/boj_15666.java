import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15666 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int[] sequence = new int[m];
        StringBuilder sb = new StringBuilder();

        // 초기 깊이 : 0, 탐색 시작 위치: 0
        backtrack(arr, sequence, 0, 0, m, sb);

        System.out.println(sb);
    }

    private static void backtrack(int[] arr, int[] sequence, int depth, int start, int m, StringBuilder sb) {
        if (depth == m) {
            for (int num : sequence) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
            return;
        }

        // 현재 위치 이후의 숫자들에 대해 탐색
        int temp = 0;
        for (int i = start; i < arr.length; i++) {
            if (temp != arr[i]) {
                sequence[depth] = arr[i];
                temp = arr[i];
                backtrack(arr, sequence, depth + 1, i + 1, m, sb);
            }
        }
    }
}
/*
14508 KB / 112 ms
 */
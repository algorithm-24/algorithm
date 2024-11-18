import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @source: https://www.acmicpc.net/problem/13164 (행복 유치원)
 * @classfication: 단순 구현
 * @문제 푼 날짜 (자력으로 풂?): 24.11.17 (O)
 **/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int[] tallArr = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p2[1] - p1[1]);
        boolean[] kArr = new boolean[n];

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++) {
            int tall = Integer.parseInt(st.nextToken());
            tallArr[i] = tall;

            if(i > 0)
                pq.offer(new int[]{i-1, tallArr[i] - tallArr[i-1]});
        }

        br.close();

        for(int i=0;i<k-1;i++) {
            int[] arr = pq.poll();
            kArr[arr[0]] = true;
        }

        long answer = 0;
        int str = 0;

        for(int i=0;i<n;i++) {
            if(kArr[i]) {
                answer += tallArr[i] - tallArr[str];
                str = i+1;
            }
        }

        answer += tallArr[tallArr.length-1] - tallArr[str];
        System.out.print(answer);
    }
}

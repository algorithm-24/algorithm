package jaewook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @source: https://www.acmicpc.net/problem/2295 (세 수의 합)
 * @classification: hash
 * @문제 푼 날짜 (혼자 힘으로 풂?): 25.02.26 (X)
 **/
public class Q2295 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];

        for(int i=0;i<n;i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);
        HashSet<Integer> sum = new HashSet<>();

        for(int i=0;i<n;i++) {
            for(int j=i;j<n;j++) {
                sum.add(nums[i] + nums[j]);
            }
        }

        for(int i=n-1;i>=0;i--) {
            for(int j=i-1;j>=0;j--) {
                if(sum.contains(nums[i] - nums[j])) {
                    System.out.println(nums[i]);
                    return;
                }
            }
        }

        br.close();
    }
}

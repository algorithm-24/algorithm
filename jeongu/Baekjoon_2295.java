package jeongu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Baekjoon_2295 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int[] arr = new int[N];
    ArrayList<Integer> two = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      arr[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(arr);

    for (int i = 0; i < N; i++) {
      for (int j = i; j < N; j++) {
        two.add(arr[i] + arr[j]);
      }
    }

    Collections.sort(two);

    for (int i = N - 1; i >= 0; i--) {
      int target = arr[i];  // 목표 (세 수의 합)

      for (int j = 0; j < N; j++) {
        int find = target - arr[j]; // two 배열에서 찾으려는 값 (세 수의 합 - 숫자 하나)

        int start = 0;
        int end = two.size();
        while (start <= end) {
          int mid = (start + end) / 2;

          if (two.get(mid) == find) {
            System.out.println(target);
            return;
          } else if (two.get(mid) < find) {
            start = mid + 1;
          } else {
            end = mid - 1;
          }
        }

      }
    }
  }

}

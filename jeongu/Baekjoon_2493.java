package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baekjoon_2493 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    Stack<int[]> tops = new Stack<>();
    int[] answer = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int value = Integer.parseInt(st.nextToken());
      while (!tops.isEmpty() && tops.peek()[1] <= value){
        tops.pop();
      }
      if (tops.isEmpty()) {
        answer[i] = 0;
      } else {
        answer[i] = tops.peek()[0];
      }
      tops.push(new int[]{i + 1, value});   // 위치, 값
    }

    for (int i = 0; i < N; i++) {
      System.out.print(answer[i] + " ");
    }
  }

}

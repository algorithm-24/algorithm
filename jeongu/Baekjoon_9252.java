package jeongu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_9252 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s1 = br.readLine();
    String s2 = br.readLine();

    int l1 = s1.length();
    int l2 = s2.length();

    int[][] D = new int[l1 + 1][l2 + 1];

    for (int i = 1; i <= l1; i++) {
      for (int j = 1; j <= l2; j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          D[i][j] = D[i - 1][j - 1] + 1;
        } else {
          D[i][j] = Math.max(D[i - 1][j], D[i][j - 1]);
        }
      }
    }
    System.out.println(D[l1][l2]);
    if (D[l1][l2] > 0) {
      StringBuilder answer = new StringBuilder();
      int i = l1;
      int j = l2;
      while (i > 0 && j > 0) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          answer.insert(0, s1.charAt(i - 1));
          i--;
          j--;
        } else {
          if (D[i - 1][j] > D[i][j - 1]) {
            i--;
          } else {
            j--;
          }
        }
      }
      System.out.println(answer);
    }
  }

}

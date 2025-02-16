package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_12865 {

  static int N, K;
  static int[][] values;
  static int[][] products;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken()); // 물품의 수
    K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

    products = new int[N + 1][2]; // 무게, 가치
    values = new int[N + 1][100_001]; // N 번째 물품까지 무게에 따른 최대 가치

    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      int W = Integer.parseInt(st.nextToken());   // 무게
      int V = Integer.parseInt(st.nextToken());   // 가치

      products[i][0] = W;
      products[i][1] = V;
    }

    for (int i = 1; i <= N; i++) {   // i 번째 물품까지
      for (int j = 1; j <= K; j++) {   // 최대 무게 j 에 대해서
        if (j < products[i][0]) { // 물품의 무게가 최대 무게 j 보다 크다면
          values[i][j] = values[i - 1][j];  // i - 1 번까지의 최대가치와 i 번까지의 최대가치가 같음 (애초에 들어갈 수 없으니까)
        } else {  // 물건을 담았을 때와 담지 않았을 때 를 비교
          values[i][j] = Math.max(values[i - 1][j], values[i - 1][j - products[i][0]] + products[i][1]);
        }
      }
    }

    System.out.println(values[N][K]);
  }

}

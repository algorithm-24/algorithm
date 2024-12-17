import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @source: https://www.acmicpc.net/problem/16395 (파스칼의 삼각형)
 * @classfication: dp
 * @문제 푼 날짜 (자력으로 풂?): 24.12.14 (O)
 **/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        br.close();

        int[][] triangle = new int[30][30];
        triangle[0][0] = 1;

        for(int i=1;i<30;i++) {
            for(int j=0;j<=i;j++) {
                if(j-1 >= 0 && triangle[i-1][j-1] > 0) {
                    triangle[i][j] += triangle[i-1][j-1];
                }

                if(j < i && triangle[i-1][j] > 0) {
                    triangle[i][j] += triangle[i-1][j];
                }
            }
        }

        System.out.print(triangle[n-1][k-1]);
    }
}

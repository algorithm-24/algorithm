import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @source: https://www.acmicpc.net/problem/1522 (문자열 교환)
 * @classification: sliding-window
 * @문제 푼 날짜 (자력으로 풂?): 24.11.30 (X)
 **/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = br.readLine().toCharArray();
        br.close();

        int aCnt = 0;

        for(char c : chars) {
            if(c == 'a') {
                aCnt++;
            }
        }

        int minBCnt = 1001;

        for(int i=0;i<chars.length;i++) {
            int tempBCnt = 0;

            for(int j=0;j<aCnt;j++) {
                int index = (i+j) % chars.length;

                if(chars[index] == 'b') {
                    ++tempBCnt;
                }
            }

            minBCnt = Math.min(minBCnt, tempBCnt);
        }

        System.out.print(minBCnt);
    }
}

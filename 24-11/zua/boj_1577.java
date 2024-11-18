import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1577 {

    static int N, M, K; // 도시 크기 N, M  및 공사 구간 개수 K
    static long[][] dp; // (0, 0)에서 (x, y)까지의 경로 수 저장
    static List<int[]> road; // 공사 구간 정보를 저장하는 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new long[M + 1][N + 1];
        road = new ArrayList<>();

        dp[0][0] = 1;

        // 공사 구간 입력 처리
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            road.add(new int[]{a, b, c, d});
        }

        for (int x = 0; x < M; x++) { // 행(세로) 순회
            for (int y = 0; y < N; y++) {
                if (y > 0) {
                    boolean isBlocked = false;
                    for (int[] r : road) {
                        if (check(y - 1, x, y, x, r)) {
                            isBlocked = true;
                            break;
                        }
                    }
                    // 위쪽 경로가 가능하면 DP 값 갱신
                    if (!isBlocked) dp[x][y] += dp[x][y - 1];
                }

                if (x > 0) {
                    boolean isBlocked = false;
                    for (int[] r : road) {
                        if (check(y, x - 1, y, x, r)) {
                            isBlocked = true;
                            break;
                        }
                    }
                    // 왼쪽 경로가 가능하면 DP 값 갱신
                    if (!isBlocked) dp[x][y] += dp[x - 1][y];
                }
            }
        }
        System.out.println(dp[M][N]);
    }

    // 공사 구간 확인 함수
    private static boolean check(int y1, int x1, int y2, int x2, int[] r) {
        return (y1 == r[0] && x1 == r[1] && y2 == r[2] && x2 == r[3] ||
                y1 == r[2] && x1 == r[3] && y2 == r[0] && x2 == r[1]);
    }
}

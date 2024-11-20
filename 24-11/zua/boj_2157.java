import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2157 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 도시 수
        int M = Integer.parseInt(st.nextToken()); // 최대 방문 도시 수
        int K = Integer.parseInt(st.nextToken()); // 항공편 수

        int[][] scoreBoard = new int[N + 1][N + 1]; // scoreBoard[from][to]: 최대 기내식 점수
        for (int i = 0; i < K; i++) {
            // 항공편 정보 입력 분리
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            scoreBoard[from][to] = Math.max(scoreBoard[from][to], score);
        }

        // DP 배열 초기화: dp[방문 도시 수][현재 도시]
        int[][] dp = new int[M + 1][N + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE); // 불가능한 경로 초기화
        }
        dp[1][1] = 0; // 시작점 설정

        // 동적 프로그래밍 수행
        for (int visited = 0; visited < M; visited++) {
            for (int current = 0; current <= N; current++) {
                if (dp[visited][current] == Integer.MIN_VALUE) continue;
                for (int next =  current + 1; next <=N ; next++) {
                    if (scoreBoard[current][next] == 0) continue;
                    int newScore = dp[visited][current] + scoreBoard[current][next];
                    dp[visited + 1][next] = Math.max(dp[visited + 1][next], newScore);
                }
            }
        }

        // 최대 기내식 점수 계산
        int maxScore = 0; // 최대 점수 초기화
        for (int visited = 2; visited <= M; visited++) {
            maxScore = Math.max(maxScore, dp[visited][N]);
        }

        // 결과 출력: 최대 기내식 점수 출력
        System.out.println(maxScore);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @source: https://www.acmicpc.net/problem/14889 (스타트와 링크)
 * @classfication: bruteforce
 * @문제 푼 날짜 (자력으로 풂?): 24.12.18 (O)
 * @reference: https://www.acmicpc.net/source/58349778 (더 효율적인 방법)
 **/
public class Main {
    static boolean[] team;
    static int gap = 2000;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        team = new boolean[n];
        arr = new int[n][n];
        StringTokenizer st;

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0;j<n;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();

        dfs(0, 0);

        System.out.print(gap);
    }

    private static void dfs(int depth, int index) {
        if(depth == team.length / 2) {
            int aTeam = 0, bTeam = 0;

            for(int i=0;i<arr.length;i++) {
                for(int j=0;j<arr[0].length;j++) {
                    if(team[i] && team[j]) {
                        aTeam += arr[i][j];
                    } else if(!team[i] && !team[j]) {
                        bTeam += arr[i][j];
                    }
                }
            }

            gap = Math.min(gap, Math.abs(aTeam - bTeam));

            return;
        }

        for(int i=index;i<team.length;i++) {
            if( team[i] ) continue;

            team[i] = true;
            dfs(depth+1, i+1);
            team[i] = false;
        }
    }
}

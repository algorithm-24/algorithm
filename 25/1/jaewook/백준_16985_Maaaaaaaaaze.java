package Baekjoon.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @source: https://www.acmicpc.net/problem/16985 (Maaaaaaaaaze)
 * @classfication: bfs
 * @문제 푼 날짜 (자력으로 풂?): 25.01.12 (X, chatGPT 한테 디버깅 맡김)
 **/
// todo: 판을 회전 시킬때 비트마스킹 공부할 것
public class Main {
    static boolean[][][] fixMap = new boolean[5][5][5];
    static boolean[][][] visited;
    final static int INF = 125;
    static int minDist = INF;
    final static int[][][] strEnd = {
            {{0, 0, 0}, {4, 4, 4}},
            {{0, 0, 4}, {4, 4, 0}},
            {{0, 4, 0}, {4, 0, 4}},
            {{0, 4, 4}, {4, 0, 0}}
    };
    static boolean[] rVisited = new boolean[5];
    static boolean[][][] map2 = new boolean[5][5][5];
    final static int[][] moves = {{-1, 0, 0}, {0, 1, 0}, {1, 0, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1},};
    static boolean[][] rotation = new boolean[5][5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                st = new StringTokenizer(br.readLine());

                for(int k=0;k<5;k++) {
                    fixMap[i][j][k] = st.nextToken().equals("1");
                }
            }
        }

        br.close();

        solve(0);

        System.out.print(minDist == INF ? -1 : minDist);
    }

    private static void solve(int depth) {
        if(depth == 5) {
            for(int[][] se : strEnd) {
                int[] s = se[0];
                int[] e = se[1];

                if(map2[s[0]][s[1]][s[2]] && map2[e[0]][e[1]][e[2]]) {
                    visited = new boolean[5][5][5];
                    bfs(s, e);
                }
            }

            return;
        }

        for(int i=0;i<5;i++) {
            if(rVisited[i]) continue;

            rVisited[i] = true;
            copy(map2[depth], fixMap[i]);

            for(int j=0;j<4;j++) { // 회전

                //boolean[][] rotation = new boolean[5][5];
                for(int k=0;k<5;k++) {
                    for(int l=0;l<5;l++) {
                        boolean val = map2[depth][k][l];
                        rotation[l][4-k] = val;
                    }
                }

                copy(map2[depth], rotation);
                solve(depth+1);
            }

            rVisited[i] = false;
        }
    }

    private static void copy(boolean[][] arr, boolean[][] copy) {
        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                boolean val = copy[i][j];
                arr[i][j] = val;
            }
        }
    }

    private static void bfs(int[] str, int[] end) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{str[0], str[1], str[2], 0});
        visited[str[0]][str[1]][str[2]] = true;

        while( !queue.isEmpty() ) {
            int[] poll = queue.poll();
            int x1 = poll[0], y1 = poll[1], z1 = poll[2], count1 = poll[3];

            for(int[] move : moves) {
                int x2 = x1 + move[0];
                int y2 = y1 + move[1];
                int z2 = z1 + move[2];
                int count2 = count1 + 1;

                if(x2 < 0 || x2 >= 5 || y2 < 0 || y2 >= 5 || z2 < 0 || z2 >= 5) continue;
                if( !map2[x2][y2][z2] ) continue;
                if(visited[x2][y2][z2]) continue;

                visited[x2][y2][z2] = true;

                if(x2 == end[0] && y2 == end[1] && z2 == end[2]) {
                    if(count2 == 12) {
                        System.out.print(12);
                        System.exit(0);
                    }

                    minDist = Math.min(minDist, count2);
                    return;
                }

                queue.offer(new int[]{x2, y2, z2, count2});
            }
        }
    }
}

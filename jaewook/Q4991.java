package jaewook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @source: https://www.acmicpc.net/problem/4991 (로봇 청소기)
 * @classification: brute-force, bfs
 * @문제 푼 날짜 (자력으로 풂?): 25.03.02 (X)
 **/
public class Q4991 {

    static int total_dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder result = new StringBuilder();
        int[][] bfs_map;
        ArrayDeque<int[]> que;
        final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while(true) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) {
                break;
            }

            char[][] board = new char[h][w];
            ArrayList<int[]> dirty = new ArrayList<>();
            int[] robot = new int[2];

            for(int i=0;i<h;i++) {
                String row = br.readLine();

                for(int j=0;j<w;j++) {
                    char obj = row.charAt(j);
                    board[i][j] = obj;

                    if(obj == '*') {
                        dirty.add(new int[]{i, j});
                    } else if(obj == 'o') {
                        robot[0] = i;
                        robot[1] = j;
                    }
                }
            }

            int[][] dist = new int[dirty.size()+1][dirty.size()+1];

            // 로봇에서 오염지점
            bfs_map = new int[h][w];
            que = new ArrayDeque<>();
            que.addLast(new int[]{robot[0], robot[1]});
            int dirty_check = 0;

            L1: while( !que.isEmpty()) {
                int[] poll = que.pollFirst();

                for(int[] move : moves) {
                    int x = poll[0] + move[0];
                    int y = poll[1] + move[1];

                    if(x < 0 || x >= h || y < 0 || y >= w) continue;
                    if(board[x][y] == 'o' || board[x][y] == 'x') continue;
                    if(bfs_map[x][y] > 0) continue;

                    bfs_map[x][y] = bfs_map[poll[0]][poll[1]] + 1;
                    que.addLast(new int[]{x, y});

                    if(board[x][y] == '*') {
                        for(int i=0;i<dirty.size();i++) {
                            int[] d = dirty.get(i);
                            if(d[0] == x && d[1] == y) {
                                dist[0][i+1] = bfs_map[x][y];
                                break;
                            }
                        }

                        dirty_check++;
                    }

                    if(dirty_check == dirty.size()) {
                        break L1;
                    }
                }
            }

            if(dirty_check != dirty.size()) {
                result.append(-1).append('\n');
                continue;
            }

            for(int i=0;i<dirty.size()-1;i++) {
                int[] str = dirty.get(i);
                que = new ArrayDeque<>();
                que.addLast(new int[]{str[0], str[1]});
                int dirty_cnt = dirty.size() - (i + 1);
                bfs_map = new int[h][w];

                L2:
                while (!que.isEmpty()) {
                    int[] poll = que.pollFirst();

                    for (int[] move : moves) {
                        int x = poll[0] + move[0];
                        int y = poll[1] + move[1];

                        if (x < 0 || x >= h || y < 0 || y >= w) continue;
                        if (x == str[0] && y == str[1]) continue;
                        if (board[x][y] == 'x')
                            continue;
                        if (bfs_map[x][y] > 0) continue;

                        bfs_map[x][y] = bfs_map[poll[0]][poll[1]] + 1;
                        que.addLast(new int[]{x, y});

                        if (board[x][y] == '*') { // todo: error
                            for (int j = i+1; j < dirty.size(); j++) {
                                int[] d = dirty.get(j);
                                if (d[0] == x && d[1] == y) {
                                    dirty_cnt--;

                                    dist[i+1][j + 1] = bfs_map[x][y];
                                    dist[j + 1][i+1] = bfs_map[x][y];

                                    break;
                                }
                            }
                        }

                        if (dirty_cnt == 0) {
                            break L2;
                        }
                    }
                }
            }

            total_dist = 450;

            boolean[] visited = new boolean[dirty.size()+1];
            for(int i=1;i<dist.length;i++) {
                bt(1, i, dist, visited, dist[0][i]);
            }

            result.append(total_dist).append('\n');
        }

        br.close();

        System.out.print(result);
    }

    private static void bt(int depth, int node, int[][] dist, boolean[] visited, int val) {
        if(depth == dist.length) {
            total_dist = Math.min(val, total_dist);
            return;
        }

        for(int i=1;i<dist.length;i++) {
            if(visited[i]) continue;
            visited[i] = true;
            bt(depth+1, i, dist, visited, val+dist[node][i]);
            visited[i] = false;
        }
    }
}

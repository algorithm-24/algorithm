package week3;

import java.util.*;
import java.io.*;

public class boj_20005 {
    static int m, n, p;
    static char[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        board = new char[m][n];
        List<Player> players = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();

        // 보드 입력 및 플레이어 위치 초기화
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j);
                if (Character.isLowerCase(board[i][j])) {
                    queue.offer(new Node(board[i][j], i, j));
                }
            }
        }

        // 플레이어 정보 입력
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            char id = st.nextToken().charAt(0);
            int dps = Integer.parseInt(st.nextToken());
            players.add(new Player(id, dps));
        }

        // 보스 체력 입력
        int bossHp = Integer.parseInt(br.readLine());

        // 결과 출력
        System.out.println(bfs(queue, players, bossHp));
    }

    static int bfs(Queue<Node> queue, List<Player> players, int bossHp) {
        boolean[][][] visited = new boolean[m][n][26];
        Set<Character> attackers = new HashSet<>();
        Map<Character, Player> playerMap = new HashMap<>();

        // 플레이어 맵 초기화
        for (Player player : players) {
            playerMap.put(player.id, player);
        }

        while (bossHp > 0 && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                char playerId = current.id;

                if (attackers.contains(playerId)) continue;

                for (int dir = 0; dir < 4; dir++) {
                    int nx = current.x + dx[dir];
                    int ny = current.y + dy[dir];

                    if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                    if (visited[nx][ny][playerId - 'a'] || board[nx][ny] == 'X') continue;

                    visited[nx][ny][playerId - 'a'] = true;

                    if (board[nx][ny] == 'B') {
                        attackers.add(playerId);
                    } else {
                        queue.offer(new Node(playerId, nx, ny));
                    }
                }
            }

            // 공격 로직 최적화
            for (char attacker : attackers) {
                bossHp -= playerMap.get(attacker).dps;
                if (bossHp <= 0) break;
            }
        }

        return attackers.size();
    }

    static class Node {
        char id;
        int x, y;
        Node(char id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    static class Player {
        char id;
        int dps;
        Player(char id, int dps) {
            this.id = id;
            this.dps = dps;
        }
    }
}
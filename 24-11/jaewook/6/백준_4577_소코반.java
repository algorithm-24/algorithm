import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @source: https://www.acmicpc.net/problem/4577 (소코반)
 * @classfication: implementation
 * @문제 푼 날짜 (자력으로 풂?): 24.12.15 (O)
 **/
public class Main {
    static char[][] board;
    static int[] player;
    static StringBuilder result = new StringBuilder();
    static int gameCnt = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int h = Integer.parseInt(st.nextToken());
            final int w = Integer.parseInt(st.nextToken());

            if(h == 0 && w == 0) {
                break;
            }

            player = new int[2];
            ArrayList<int[]> goalList = new ArrayList<>();

            board = new char[h][w];

            for(int i=0;i<h;i++) {
                String row = br.readLine();

                for(int j=0;j<w;j++) {
                    char obj = row.charAt(j);
                    board[i][j] = obj;

                    if(obj == 'w' || obj == 'W') {
                        player[0] = i;
                        player[1] = j;
                    }
                    if(obj == '+' || obj == 'B' || obj == 'W') {
                        goalList.add(new int[]{i, j});
                    }
                }
            }

            String cmd = br.readLine();
            boolean complete = false;

            for(char c : cmd.toCharArray()) {
                int moveX = 0, moveY = 0;

                if(c == 'U') {
                    --moveX;
                } else if(c == 'D') {
                    ++moveX;
                } else if(c == 'L') {
                    --moveY;
                } else if(c == 'R') {
                    ++moveY;
                }

                int playerNextX = player[0] + moveX;
                int playerNextY = player[1] + moveY;

                if(playerNextX < 0 || playerNextX >= board.length || playerNextY < 0 || playerNextY >= board[0].length)
                    continue;

                if(board[playerNextX][playerNextY] == '#')
                    continue;

                int nextBoxX = playerNextX + moveX;
                int nextBoxY = playerNextY + moveY;

                if(board[playerNextX][playerNextY] == 'b') {
                    if(board[nextBoxX][nextBoxY] == '.') { // 밀릴 위치가 비어 있다면
                        board[nextBoxX][nextBoxY] = 'b';
                        board[playerNextX][playerNextY] = 'w';

                        movePlayer(playerNextX, playerNextY);
                    } else if(board[nextBoxX][nextBoxY] == '+') { // 밀릴 위치가 목표 지점이라면
                        board[nextBoxX][nextBoxY] = 'B';
                        board[playerNextX][playerNextY] = 'w';

                        movePlayer(playerNextX, playerNextY);
                    }
                } else if(board[playerNextX][playerNextY] == 'B') {
                    if(board[nextBoxX][nextBoxY] == '.') { // 밀릴 위치가 비어 있다면
                        board[nextBoxX][nextBoxY] = 'b';
                        board[playerNextX][playerNextY] = 'W';

                        movePlayer(playerNextX, playerNextY);
                    } else if(board[nextBoxX][nextBoxY] == '+') { // 밀릴 위치가 목표 지점이라면
                        board[nextBoxX][nextBoxY] = 'B';
                        board[playerNextX][playerNextY] = 'W';

                        movePlayer(playerNextX, playerNextY);
                    }
                } else if(board[playerNextX][playerNextY] == '.') {
                    board[playerNextX][playerNextY] = 'w';

                    movePlayer(playerNextX, playerNextY);
                } else {
                    throw new RuntimeException("player가 다음에 갈 자리가 이상합니다. " + board[playerNextX][playerNextY]);
                }

                // 검증
                for(int i=0;i<goalList.size();i++) {
                    int[] goal = goalList.get(i);

                    if(board[goal[0]][goal[1]] != 'B') {
                        break;
                    }

                    if(i == goalList.size() - 1) {
                        complete = true;
                    }
                }

                if(complete) {
                    break;
                }
            }

            writeState(complete);
        }

        br.close();
        System.out.print(result);
    }

    private static void movePlayer(int playerNextX, int playerNextY) {
        if(board[player[0]][player[1]] == 'w') {
            board[player[0]][player[1]] = '.';
        } else if(board[player[0]][player[1]] == 'W') {
            board[player[0]][player[1]] = '+';
        } else {
            throw new RuntimeException("player 있던 자리가 이상합니다. " + board[player[0]][player[1]]);
        }

        player[0] = playerNextX;
        player[1] = playerNextY;
    }

    private static void writeState(boolean complete) {
        result.append("Game ").append(gameCnt++).append(": ").append(complete ? "complete" : "incomplete").append('\n');

        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                result.append(board[i][j]);
            }
            result.append('\n');
        }
    }
}
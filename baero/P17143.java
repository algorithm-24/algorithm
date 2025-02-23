import java.util.Scanner;

public class Main {

    private static final int[] DIR_R = { 0, -1, 1, 0, 0 };
    private static final int[] DIR_C = { 0, 0, 0, 1, -1 };

    public static class Shark {
        final int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        int m = scanner.nextInt();
        Shark[][][] board = new Shark[c + 1][r + 1][c + 1];

        while (m-- > 0) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int s = scanner.nextInt();
            int d = scanner.nextInt();
            int z = scanner.nextInt();

            if (d == 1 || d == 2) {
                s %= 2 * (r - 1);
            } else {
                s %= 2 * (c - 1);
            }

            board[1][x][y] = new Shark(x, y, s, d, z);
        }

        int phase = 0;
        int ans = 0;

        while (phase < c) {
            phase++;

            for (int i = 1; i <= r; i++) {
                if (board[phase][i][phase] != null) {
                    ans += board[phase][i][phase].z;
                    board[phase][i][phase] = null;
                    break;
                }
            }

            if (phase == c) {
                break;
            }

            for (int i = 1; i <= r; i++) {
                for (int j = 1; j <= c; j++) {
                    if (board[phase][i][j] != null) {
                        Shark newShark = move(r, c, board[phase][i][j]);
                        if (board[phase + 1][newShark.r][newShark.c] == null
                                || board[phase + 1][newShark.r][newShark.c].z < newShark.z) {
                            board[phase + 1][newShark.r][newShark.c] = newShark;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    public static Shark move(int maxR, int maxC, Shark shark) {
        int dir = shark.d;
        int s = shark.s;
        int r = shark.r;
        int c = shark.c;

        int nextR = r;
        int nextC = c;

        while (s-- > 0) {

            nextR += DIR_R[dir];
            nextC += DIR_C[dir];
            if (nextR < 1 || nextR > maxR || nextC < 1 || nextC > maxC) {
                if (dir == 1) dir = 2;
                else if (dir == 2) dir = 1;
                else if (dir == 3) dir = 4;
                else if (dir == 4) dir = 3;

                nextR += DIR_R[dir] * 2;
                nextC += DIR_C[dir] * 2;
            }
        }

        return new Shark(nextR, nextC, shark.s, dir, shark.z);
    }
}

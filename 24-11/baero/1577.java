import java.util.*;

public class Main {
    static int n;
    static int m;
    static int k;
    static List<Integer> roads = new ArrayList<>();
    static long[] memoi;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        memoi = new long[111000];
        visited = new boolean[111000];

        n = scanner.nextInt(); // 100
        m = scanner.nextInt(); // 100
        k = scanner.nextInt();
        for (int i = 0; i < k; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            int d = scanner.nextInt();
            int tmp = getRoadNumber(a, b, c, d);
            roads.add(tmp);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        memoi[0] = 1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (visited[cur]) continue;
            visited[cur] = true;
            int x = getX(cur);
            int y = getY(cur);

            if (x < n) {
                int road = getRoadNumber(x, y, x + 1, y);
                if (!roads.contains(road)) {
                    int next = getIdx(x + 1, y);
                    memoi[next] += memoi[cur];
                    queue.add(next);
                }
            }
            if (y < m) {
                int road = getRoadNumber(x, y, x, y + 1);
                if (!roads.contains(road)) {
                    int next = getIdx(x, y + 1);
                    memoi[next] += memoi[cur];
                    queue.add(next);
                }
            }
        }

        System.out.println(memoi[getIdx(n, m)]);
    }

    static int getRoadNumber(int ax, int ay, int bx, int by) {
        if (ax == bx) {
            int y = Math.min(ay, by);
            return ax * 10000 + y * 10;
        }
        return Math.min(ax, bx) * 10000 + ay * 10 + 1;
    }

    static int getIdx(int x, int y) {
        return x * 1000 + y;
    }

    static int getX(int idx) {
        return idx / 1000;
    }

    static int getY(int idx) {
        return idx % 1000;
    }

}

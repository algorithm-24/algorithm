import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static List<List<Pair>> adjList;
    private static final Scanner SCANNER = new Scanner(System.in);

    private static class Pair implements Comparable<Pair> {
        int left, right;

        public static Pair of(int left, int right) {
            Pair pair = new Pair();
            pair.left = left;
            pair.right = right;
            return pair;
        }

        @Override
        public int compareTo(Pair o) {
            return o.right - this.right;
        }
    }

    private static int solution(int n, int s, int e) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        List<Integer> memoi = new ArrayList<>(Collections.nCopies(n + 1, 0));
        memoi.set(s, Integer.MAX_VALUE);
        pq.offer(Pair.of(s, Integer.MAX_VALUE));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int idx = cur.left;
            int weight = cur.right;

            if (memoi.get(idx) > weight) {
                continue;
            }

            adjList.get(idx).forEach(it -> {
                int nextWeight = Math.min(it.right, weight);
                if (nextWeight > memoi.get(it.left)) {
                    memoi.set(it.left, nextWeight);
                    pq.offer(Pair.of(it.left, nextWeight));
                }
            });
        }

        return memoi.get(e);
    }

    private static void getBridge() {
        int h1 = SCANNER.nextInt();
        int h2 = SCANNER.nextInt();
        int k = SCANNER.nextInt();

        adjList.get(h1).add(Pair.of(h2, k));
        adjList.get(h2).add(Pair.of(h1, k));
    }

    public static void main(String[] args) {
        int n = SCANNER.nextInt();
        int m = SCANNER.nextInt();
        int s = SCANNER.nextInt();
        int e = SCANNER.nextInt();

        adjList = IntStream.range(0, n + 1)
                .<List<Pair>>mapToObj(it -> new ArrayList<>())
                .collect(Collectors.toList());
        IntStream.range(0, m).forEach(it -> getBridge());

        System.out.println(solution(n, s, e));
    }
}

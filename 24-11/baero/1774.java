import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static class Pair {
        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
        int a, b;
    }

    static class Triple implements Comparable<Triple> {
        Triple(double a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        double a;
        int b, c;

        @Override
        public int compareTo(Triple o) {
            return Double.compare(this.a, o.a);
        }
    }

    static int findParent(List<Integer> parents, int target) {
        int tmp = target;
        while (tmp != parents.get(tmp)) {
            tmp = parents.get(tmp);
        }
        return tmp;
    }

    static boolean union(List<Integer> parents, int a, int b) {
        int aParent = findParent(parents, a);
        int bParent = findParent(parents, b);
        if (aParent == bParent) {
            return false;
        }
        if (aParent < bParent) parents.set(bParent, aParent);
        else parents.set(aParent, bParent);
        return true;
    }

    static double calculateDistance(List<Pair> nodes, int a, int b) {
        Pair aNode = nodes.get(a - 1);
        Pair bNode = nodes.get(b - 1);
        long dx = aNode.a - bNode.a;
        long dy = aNode.b - bNode.b;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Integer> parents = IntStream.range(0, n + 1)
                .boxed()
                .collect(Collectors.toList());

        List<Pair> nodes = IntStream.range(0, n)
                .boxed()
                .map(i -> {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    return new Pair(x, y);
                })
                .collect(Collectors.toList());

        int numOfUnions = n - (int) IntStream.range(0, m)
                .boxed()
                .map(i -> {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    return union(parents, x, y);
                })
                .filter(i -> i)
                .count();

        List<Triple> edges = IntStream
                .rangeClosed(1, n)
                .boxed()
                .flatMap(i -> IntStream
                        .rangeClosed(i + 1, n)
                        .boxed()
                        .filter(j -> findParent(parents, i) != findParent(parents, j))
                        .map(j -> new Triple(calculateDistance(nodes, i, j), i, j))
                )
                .sorted()
                .collect(Collectors.toList());

        double sumOfDistances = 0.0;
        for (Triple edge : edges) {
            if (numOfUnions <= 1) break;
            int a = edge.b;
            int b = edge.c;
            if (union(parents, a, b)) {
                sumOfDistances += edge.a;
                numOfUnions--;
            }
        }

        System.out.printf("%.2f", sumOfDistances);
    }
}

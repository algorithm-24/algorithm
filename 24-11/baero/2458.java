import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<List<Boolean>> memoi = IntStream.rangeClosed(0, n)
                .mapToObj(i -> new ArrayList<>(Collections.nCopies(n + 1, Boolean.FALSE)))
                .collect(Collectors.toList());

        IntStream.range(0, m).forEach(it -> {
            int left = scanner.nextInt();
            int right = scanner.nextInt();
            memoi.get(left).set(right, true);
        });

        IntStream.range(1, n + 1).forEach(mid -> {
            IntStream.range(1, n + 1).forEach(left -> {
                IntStream.range(1, n + 1).forEach(right -> {
                    if (memoi.get(left).get(mid) && memoi.get(mid).get(right)) {
                        memoi.get(left).set(right, true);
                    }
                });
            });
        });

        long ans = IntStream.range(1, n + 1)
                .mapToObj(i ->
                    IntStream.range(1, n + 1)
                            .mapToObj(it -> memoi.get(i).get(it) || memoi.get(it).get(i))
                            .filter(it -> it)
                            .count())
                .filter(it -> it == n - 1)
                .count();

        System.out.println(ans);
    }
}

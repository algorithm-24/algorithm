import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int base = scanner.nextInt();
        List<Integer> list = IntStream.range(1, n).boxed().map(i -> scanner.nextInt()).collect(Collectors.toList());

        for (int i = list.size() - 1; i > 0; i--) {
            list.set(i, list.get(i) - list.get(i - 1));
        }
        list.set(0, list.get(0) - base);
        list.sort(Integer::compareTo);

        while (k-- > 1) {
            list.remove(list.size() - 1);
        }

        long ans = list.stream().mapToLong(Integer::longValue).sum();
        System.out.println(ans);
    }
}

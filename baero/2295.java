import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] arr = new long[n];
        Set<Long> sumSet = new HashSet<>();

        for (int i = 0; i < n; i++) arr[i] = scanner.nextInt();
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) sumSet.add(arr[i] + arr[j]);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (sumSet.contains(arr[i] - arr[j])) {
                    System.out.print(arr[i]);
                    return;
                }
            }
        }
    }
}


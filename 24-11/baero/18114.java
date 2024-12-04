import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int c = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();

            if (w[i] == c) {
                System.out.println(1);
                return;
            }
        }
        Arrays.sort(w);

        int lhs = 0;
        int rhs = n - 1;

        while (lhs < rhs) {
            int tmp = w[lhs] + w[rhs];

            for (int i = lhs + 1; i < rhs; i++) {
                if (tmp + w[i] == c) {
                    System.out.println(1);
                    return;
                }
            }

            if (tmp == c) {
                System.out.println(1);
                return;
            }
            if (tmp > c) rhs--;
            if (tmp < c) lhs++;
        }

        System.out.println(0);
    }
}
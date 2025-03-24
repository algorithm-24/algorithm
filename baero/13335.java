import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int l = scanner.nextInt();
        int w = scanner.nextInt();
        int[] trucks = new int[n];
        int[] time = new int[n];
        for (int i = 0; i < n; i++) {
            trucks[i] = scanner.nextInt();
            time[i] = Integer.MAX_VALUE;
        }

        int currentTime = 0;
        int lhs = 0;
        int rhs = 0;
        int sum = 0;
        while (rhs < n || lhs < n) {
            currentTime++;

            if (currentTime - time[lhs] == l) {
                sum -= trucks[lhs];
                lhs++;
            }

            if (rhs < n && sum + trucks[rhs] <= w) {
                sum += trucks[rhs];
                time[rhs] = currentTime;
                rhs++;
            }
        }

        System.out.println(currentTime);
    }
}

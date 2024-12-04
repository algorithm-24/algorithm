import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        if (n == 1 || (n == 2 && arr[0] != arr[1])) {
            System.out.println('A');
            return;
        }

        if (n == 2 && arr[0] == arr[1]) {
            System.out.println(arr[0]);
            return;
        }

        int a, b;

        if (arr[0] == arr[1]) {
            a = 1;
            b = 0;
        } else {
            a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
            b = arr[1] - a * arr[0];
        }

        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i - 1] * a + b;
            if (tmp != arr[i]) {
                System.out.println("B");
                return;
            }
        }

        System.out.println(arr[n - 1] * a + b);
    }
}
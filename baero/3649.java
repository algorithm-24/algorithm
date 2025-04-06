import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;

        while ((s = br.readLine()) != null) {
            int x = Integer.parseInt(s) * 10_000_000;
            int n = Integer.parseInt(br.readLine()) ;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(br.readLine());

            solution(x, n, arr);
        }
    }

    static void solution(int x, int n, int[] arr) {
        Arrays.sort(arr);

        for (int i = 0; i < n - 1; i++) {
            if (x > arr[i]) {
                int j = binarySearch(arr, x - arr[i], i + 1, n - 1);
                if (j == -1) continue;
                System.out.println("yes " + arr[i] + " " + arr[j]);
                return;
            }
        }

        System.out.println("danger");
    }

    static int binarySearch(int[] arr, int target, int lhs, int rhs) {
        while (lhs <= rhs) {
            int mid = (lhs + rhs) >>> 1;
            if (arr[mid] == target) {
                return mid;
            } else if(arr[mid] < target) {
                lhs = mid + 1;
            } else {
                rhs = mid - 1;
            }
        }
        return -1;
    }
}


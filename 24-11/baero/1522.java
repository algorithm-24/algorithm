import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String q = input + input;

        int size = 0;
        int cur = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'a') size++;
        }

        for (int i = 0; i < size; i++) {
            if (input.charAt(i) == 'b') cur++;
        }

        int ans = cur;

        for (int i = 0; i < q.length() - size; i++) {
            if (q.charAt(i + size) == 'b') cur++;
            if (q.charAt(i) == 'b') cur--;
            ans = Math.min(ans, cur);
        }

        System.out.println(ans);
    }
}
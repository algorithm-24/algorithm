import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        String b = scanner.nextLine();

        int[][] memoi = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) {
                    memoi[i][j] = memoi[i-1][j-1] + 1;
                } else {
                    memoi[i][j] = Math.max(memoi[i-1][j], memoi[i][j-1]);
                }
            }
        }

        int length = memoi[a.length()][b.length()];
        System.out.println(length);
        if (length > 0) {
            Stack<Character> stack = new Stack<>();
            int i = a.length();
            int j = b.length();
            while (i > 0 && j > 0) {
                if (memoi[i][j] == memoi[i - 1][j]) i--;
                else if (memoi[i][j] == memoi[i][j-1]) j--;
                else {
                    i--;
                    j--;
                    stack.push(a.charAt(i));
                }
            }

            while(!stack.isEmpty()) {
                System.out.print(stack.pop());
            }
        }

    }
}

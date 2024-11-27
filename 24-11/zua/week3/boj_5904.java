package week3;

import java.io.*;

public class boj_5904 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(findMoo(N));
    }

    // Moo 수열에서 N번째 글자를 찾는 함수
    static char findMoo(int n) {
        int k = 0;
        int length = 3;

        // N이 포함된 수열의 길이를 구함
        while (n > length) {
            k++;
            length = 2 * length + (k + 3);
        }

        return findNthChar(n, k, length); // N번째 글자를 구하는 재귀 호출
    }

    // Moo 수열의 N번째 글자를 재귀적으로 찾는 함수
    static char findNthChar(int n, int k, int length) {
        int prevLength = (length - (k+3)) / 2;

        if (n <= prevLength) {
            return findNthChar(n, k - 1, prevLength);
        } else if (n > prevLength + (k + 3)) {
            return findNthChar(n - prevLength - (k+3), k-1, prevLength);
        } else {
            return (n-prevLength == 1) ? 'm' : 'o';
        }
    }
}
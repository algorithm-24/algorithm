package week4;

import java.io.*;

public class Boj_1522 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String input = br.readLine();
        int length = input.length();
        int aCount = 0;

        // 전체 문자열에서 'a'의 개수를 계산
        for (char c : input.toCharArray()) {
               if (c == 'a') aCount++;
        }

        // 슬라이딩 윈도우 초기화
        int minChanges = Integer.MAX_VALUE;
        int currentBCount = 0;

        // 첫 번째 윈도우에서 'b'의 개수 세기
        for (int i = 0; i < aCount; i++) {
            if (input. charAt(i) == 'b') currentBCount++;
        }

        // 슬라이딩 윈도우 이동
        for (int i = 0; i < length; i++) {
            minChanges = Math.min(minChanges, currentBCount);

            // 윈도우의 끝부분 처리
            if (input.charAt(i) == 'b') currentBCount--;

            // 윈도우를 원형으로 처리
            int nextIndex = (i + aCount) % length;
            if (input.charAt(nextIndex) == 'b') currentBCount++;
        }

        System.out.println(minChanges);
    }
}
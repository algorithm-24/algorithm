package jaewook;

/**
 * @source: https://school.programmers.co.kr/learn/courses/30/lessons/150368 (2023 KAKAO BLIND RECRUITMENT > 이모티콘 할인행사)
 * @classification: brute force
 * @문제 푼 날짜 (자력으로 풂?): 25.04.07 (O, 푸는 시간 너무 오래 걸림)
 **/
public class Q150368 {
    static int[] moneys;
    static int[] answer = new int[2];

    private static void bt(int index, int[][] users, int[] emoticons) {
        if(index == emoticons.length) {
            int a = 0;
            int b = 0;

            for(int i=0;i<users.length;i++) {
                if(users[i][1] <= moneys[i]) {
                    ++a;
                } else {
                    b += moneys[i];
                }
            }

            if(a > answer[0]) {
                answer[0] = a;
                answer[1] = b;
            } else if(a == answer[0]) {
                if(b > answer[1]) {
                    answer[1] = b;
                }
            }

            return;
        }

        for(int sale=10;sale<=40;sale+=10) {
            int emo = emoticons[index] / 100 * (100-sale);

            for(int i=0;i<users.length;i++) {
                int min_sale = users[i][0];

                if(min_sale > sale) continue;

                moneys[i] += emo;
            }

            bt(index+1, users, emoticons);

            for(int i=0;i<users.length;i++) {
                int min_sale = users[i][0];

                if(min_sale > sale) continue;

                moneys[i] -= emo;
            }
        }
    }

    public static int[] solution(int[][] users, int[] emoticons) {
        moneys = new int[users.length];
        bt(0, users, emoticons);

        return answer;
    }

    public static void main(String[] args) {
        solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000});
    }
}
